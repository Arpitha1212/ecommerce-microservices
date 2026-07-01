// package com.microservice.productservice;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.AutoConfiguration;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.DynamicPropertyRegistry;
// import org.springframework.test.context.DynamicPropertySource;
// import org.springframework.test.web.servlet.MockMvc;
// import org.testcontainers.containers.MongoDBContainer;
// import org.testcontainers.junit.jupiter.Container;
// import org.testcontainers.junit.jupiter.Testcontainers;

// import com.microservice.productservice.entity.Product;
// import com.microservice.productservice.repository.ProductRepository;


// @SpringBootTest
// @Testcontainers
// @AutoConfiguration
// class ProductserviceApplicationTests {
    
// 	@Autowired
//     private MockMvc mockMvc;

// 	@Autowired
//     private ProductRepository productRepository;

// 	@Container
// 	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.5");

// 	@DynamicPropertySource
//     static void configureMongoProperties(DynamicPropertyRegistry registry) {
//         registry.add(
//             "spring.data.mongodb.uri",
//             mongoDBContainer::getReplicaSetUrl
//         );
//     }

// 	  @Test
//       void shouldSaveProduct() {
//         Product product = Product.builder()
//                 .name("Laptop")
//                 .description("Gaming Laptop")
//                 .price(65000)
//                 .build();

//         Product saved = productRepository.save(product);
//         assertNotNull(saved.getId());
//         assertEquals(1, productRepository.count());
//     }

// }
