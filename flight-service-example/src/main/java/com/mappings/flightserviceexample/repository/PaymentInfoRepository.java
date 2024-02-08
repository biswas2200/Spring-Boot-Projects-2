package com.mappings.flightserviceexample.repository;

import com.mappings.flightserviceexample.entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, String> {
}
