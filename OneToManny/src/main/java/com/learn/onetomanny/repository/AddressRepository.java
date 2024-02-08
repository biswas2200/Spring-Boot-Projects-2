package com.learn.onetomanny.repository;

import com.learn.onetomanny.entity.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Addresses, Long> {
}
