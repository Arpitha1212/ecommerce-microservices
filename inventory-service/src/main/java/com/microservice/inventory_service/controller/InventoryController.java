package com.microservice.inventory_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.inventory_service.dto.InventoryResponse;
import com.microservice.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;


    @GetMapping
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public ResponseEntity<?> isInStock(@RequestParam List<String> skuCode) {
        List<InventoryResponse> inventoryList = inventoryService.isInStock(skuCode);
        return ResponseEntity.ok(inventoryList);
    }

}
