package com.example.lct_hackathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import com.example.lct_hackathon.entity.BusinessPoint;

@Repository
public interface BusinessPointRepository extends JpaRepository<BusinessPoint, Long> {
    @Query(nativeQuery = true, value = """
            SELECT bp.address FROM business_point bp WHERE bp.longitude = :longitude AND bp.latitude = :latitude
            """)
    String findAddressByLongitudeAndLatitude(@Param("longitude") Double longitude, @Param("latitude") Double latitude);

    @Query(nativeQuery = true, value = """
            UPDATE business_point SET cards_and_materials_delivered=1::bool WHERE latitude = :latitude AND longitude = :longitude
            """)
    void performDeliveryTask(@Param("longitude") double longitude, @Param("latitude") double latitude);
}
