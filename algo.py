import numpy as np
import random
import matplotlib.pyplot as plt
from geopy.distance import geodesic
import pandas as pd


def to_coordinates(str):
    latitude_str, longitude_str = str.split(", ")
    latitude = float(latitude_str.split()[0])
    longitude = float(longitude_str.split()[0])
    point = (latitude,longitude)
    return point

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

class GeneticAlgorithm:
    def __init__(self, points, population_size, generations):
        self.points = points
        self.population_size = population_size
        self.generations = generations
        self.population = []
        self.best_fitness = float('inf')
        self.best_solution = []

    def initialize_population(self):
        for _ in range(self.population_size):
            solution = self.randomize_solution()
            self.population.append(solution)

    def randomize_solution(self):
        solution = [0]
        rand_solution = list(range(1,len(self.points)))
        random.shuffle(rand_solution)
        for sol in rand_solution:
            solution.append(sol)
        return solution

    def calculate_fitness(self, solution):
        fitness = 0
        for i in range(len(solution) - 1):
            current_point = self.points[solution[i]]
            next_point = self.points[solution[i+1]]
            fitness += self.calculate_distance(current_point, next_point)
        return fitness

    def calculate_distance(self, point1, point2):
        return np.sqrt((point2.x - point1.x)**2 + (point2.y - point1.y)**2)

    def crossover(self, parent1, parent2):
        child = [-1] * len(parent1)
        child[0] = parent1[0]
        start_index = random.randint(1, len(parent1)-2)
        end_index = random.randint(start_index+1, len(parent1)-1)
        for i in range(start_index, end_index+1):
            child[i] = parent1[i]
        j = 0
        for i in range(len(parent2)):
            if child.count(parent2[i]) == 0:
                while child[j] != -1:
                    j += 1
                child[j] = parent2[i]
        return child

    def mutate(self, solution):
        index1 = random.randint(1, len(solution)-1)
        index2 = random.randint(1, len(solution)-1)
        solution[index1], solution[index2] = solution[index2], solution[index1]
        return solution

    def evolve(self):
        self.initialize_population()
        for _ in range(self.generations):
            fitness_scores = []
            for solution in self.population:
                fitness = self.calculate_fitness(solution)
                fitness_scores.append((solution, fitness))
            fitness_scores.sort(key=lambda x: x[1])
            if fitness_scores[0][1] < self.best_fitness:
                self.best_fitness = fitness_scores[0][1]
                self.best_solution = fitness_scores[0][0]
            next_generation = [self.best_solution]
            while len(next_generation) < self.population_size:
                parent1, parent2 = random.choices(fitness_scores[:int(self.population_size/2)], k=2)
                child = self.crossover(parent1[0], parent2[0])
                if random.random() < 0.05:
                    child = self.mutate(child)
                next_generation.append(child)
            self.population = next_generation


    def plot_best_solution(self):
        x = [self.points[i].x for i in self.best_solution]
        y = [self.points[i].y for i in self.best_solution]
        coordinates = list(zip(x, y))
        plt.plot(x, y, 'o-')
        plt.show()
        return coordinates


tasks = pd.read_excel('./offices.xlsx',sheet_name='Sheet1')
column_name = 'location'
column = tasks[column_name]
# Создание списка с координатами офисов
offices = [(45.044960, 38.977047), (45.012835, 39.071711), (45.053773, 38.941986)]

# Создание нового столбца для суммы расстояний
tasks["office_destination"] = 0

# Перебор каждой строки таблицы
for index, row in tasks.iterrows():
    point = (row["location"])
    latitude, longitude = to_coordinates(point)
    # Рассчет суммы расстояний
    sum_distance = 0
    for office in offices:
        distance = geodesic((latitude,longitude), office).kilometers
        sum_distance += distance
    tasks.at[index, "office_destination"] = sum_distance


tasks.to_excel("./offices.xlsx", index=False)
#priority 3 - сложное,2-среднее,1-легкое
# Сортировка по приоритету и расстоянию
tasks.sort_values(by=["priority","office_destination"], ascending=[False,False], inplace=True)
tasks_list=[]
for index, row in tasks.iterrows():
    # значения столбцов задачи
    task_id = row.loc["id"]
    priority = row.loc["priority"]
    distance = row.loc["office_destination"]
    done= row.loc["done"]
    location = to_coordinates(row.loc["location"])
    tasks_list.append({"id": task_id, "priority": priority, "office_destination": distance,"done":done,"location":location})
workers=pd.read_excel("workers.xlsx")
workers_list=[]
# 3-синьор, 2-мидл, 1-джун
workers.sort_values(by=["grade","hours"],ascending=[False,False])
for index, row in workers.iterrows():
    # значения столбцов задачи
    worker_id = str(row.loc["id"])
    worker_location = to_coordinates(row.loc["location"])
    grade = row.loc["grade"]
    hourse = row.loc["hours"]
    workers_list.append({"id":worker_id,"location":worker_location,"grade":grade,"hours":hourse})
# Инициализируем словарь для хранения назначенных задач на каждого работника
assigned_tasks = {worker_id["id"]: [] for worker_id in workers_list}
coordinates = {worker_id["id"]: [] for worker_id in workers_list} #координаты для каждого работника
roots = {worker_id["id"]: [] for worker_id in workers_list} #лучший путь для каждого сотрудника
# Перебор задач и распределяем их на работников
for task in tasks_list:
    # Сортировка группы работников по ближайшему расстоянию до задачи
    workers_list.sort(key=lambda x: geodesic(x["location"], task["location"]))
    workers_list.sort(reverse=True, key=lambda x:x["hours"],) # по оставшемуся времени
    if task["priority"] == 3:
        hourse = 4
    elif task["priority"] == 2:
        hourse= 2
    elif task["priority"] == 1:
        hourse= 1.5
    for worker in workers_list:
        if (task["priority"] <= worker["grade"]) and (worker["hours"] -hourse>=0):
                assigned_tasks[worker["id"]].append(task["id"])
                if not coordinates[worker["id"]]:
                    coordinates[worker["id"]].append(worker["location"])
                coordinates[worker["id"]].append(task["location"])
                worker["hours"] -= hourse+geodesic(worker["location"], task["location"]).kilometers/30 #30 это примерная скорость автомобиля
                break;

print(assigned_tasks)
print(tasks_list)
print(coordinates)

for key,value in coordinates.items():
    points=[]
    coor_list=value
    if len(coor_list)>2:
        for coor in coor_list:
            x,y=coor
            points.append(Point(x,y))
        genetic_algorithm = GeneticAlgorithm(points, population_size=100, generations=500)
        genetic_algorithm.evolve()
        roots[key].append(genetic_algorithm.plot_best_solution())
    else:
        roots[key].append(value)
print(roots)