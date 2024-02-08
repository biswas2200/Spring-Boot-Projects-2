package com.mappings.flightserviceexample.repository;

import com.mappings.flightserviceexample.entity.PassengerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerInfoRepository extends JpaRepository<PassengerInfo, Long > {
}
