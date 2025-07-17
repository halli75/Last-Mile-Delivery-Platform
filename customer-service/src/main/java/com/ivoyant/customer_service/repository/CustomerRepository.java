package com.ivoyant.customer_service.repository;

import com.ivoyant.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
