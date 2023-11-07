package com.example.lct_hackathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lct_hackathon.entity.CityPoint;
import com.example.lct_hackathon.entity.CityPointProjection;


import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

@Repository
public interface CityPointRepository extends JpaRepository<CityPoint, Long>{
    @Query(nativeQuery = true, value = """
        SELECT address, longitude, latitude, st_distancesphere(position, ST_SetSRID(ST_MakePoint(:lon,:lat),4326),2) as distance
        FROM city_point
        ORDER BY distance; 
            """)
    List<CityPointProjection> findAllCityPointsOrderedByDistance(@Param("lon") double lon, @Param("lat") double lat);
}
