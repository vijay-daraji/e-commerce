package com.vijay.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.ecommerceapp.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>{


}
