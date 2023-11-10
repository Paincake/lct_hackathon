package com.example.lct_hackathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lct_hackathon.dto.CompletedTaskReportProjection;
import com.example.lct_hackathon.entity.CompletedTask;

@Repository
public interface CompletedTaskRepository extends JpaRepository<CompletedTask, Long>{

    @Query(nativeQuery = true, value = """
        SELECT 
            employee_id AS employeeId, 
            EXTRACT(epoch FROM AVG(start_timestamp - on_way_timestamp))/3600 AS avgRoadTime, 
            EXTRACT(epoch FROM AVG(completion_timestamp - start_timestamp))/3600 AS avgCompletionTime, 
            COUNT(employee_id) AS completedTasks 
        FROM completed_task 
        GROUP BY employee_id;
            """)
    List<CompletedTaskReportProjection> getProjections();
}
