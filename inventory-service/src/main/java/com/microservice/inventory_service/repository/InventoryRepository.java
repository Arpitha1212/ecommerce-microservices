package com.microservice.inventory_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.inventory_service.entity.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Transactional(readOnly = true)
    public List<Inventory> findBySkuCodeIn(List<String> skuCode);

}

