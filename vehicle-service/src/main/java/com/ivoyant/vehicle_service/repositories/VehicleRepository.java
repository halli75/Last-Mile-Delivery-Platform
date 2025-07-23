package com.ivoyant.vehicle_service.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoyant.vehicle_service.models.VehicleModel;

public interface VehicleRepository extends JpaRepository<VehicleModel, Integer> {

    List<VehicleModel> findByDriverId(int driverId);

    @Modifying
    @Transactional
    @Query("UPDATE VehicleModel v SET v.nextServiceDate = :date WHERE v.id = :id")
    void setNextServiceDateById(@Param("id") int id, @Param("date") LocalDate date);
}
