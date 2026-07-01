package com.microservice.order_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.order_service.dto.InventoryResponse;
import com.microservice.order_service.dto.OrderLineItemsDTO;
import com.microservice.order_service.dto.OrderRequest;
import com.microservice.order_service.entity.Order;
import com.microservice.order_service.entity.OrderLineItems;
import com.microservice.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public void createOrder(OrderRequest orderRequest) {
      Order order = new Order();
      order.setOrderNumber(UUID.randomUUID().toString());
      List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsList().stream().map(this::mapToDto).toList();
      order.setOrderLineItemsList(orderLineItems);
      List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
      InventoryResponse[] inventoryResponses =  webClient.get().uri("http://localhost:8082/api/inventory", 
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())      
                    .retrieve()  // for sending the request and getting the response
                    .bodyToMono(InventoryResponse[].class) //for getting the response as boolean       
                    .block();

        if(!inventoryResponses[0].getIsInStock()) {
            throw new IllegalArgumentException("Product is not in stock");
        }
        orderRepository.save(order);
    }

    public OrderLineItems mapToDto(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItem = new OrderLineItems();
        orderLineItem.setPrice(orderLineItemsDTO.getPrice());
        orderLineItem.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItem.setSkuCode(orderLineItemsDTO.getSkuCode());
        return orderLineItem;
    }

}
