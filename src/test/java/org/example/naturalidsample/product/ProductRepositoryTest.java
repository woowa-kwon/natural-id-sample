package org.example.naturalidsample.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@SpringBootTest
class ProductRepositoryTest {

    private static final String PRODUCT_KEY = "KEY!!!";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("TRUNCATE TABLE product");
        productRepository.save(new Product(PRODUCT_KEY, "새상품", 9999));
        entityManager.clear();
        System.out.println();
        System.out.println();
        System.out.println("===================================");
        System.out.println("초기화 완료!!");
        System.out.println("===================================");
        System.out.println();
        System.out.println();
    }

    @Test
    void findByKey() throws Exception {
        productRepository.findByKey(PRODUCT_KEY).get();
        productRepository.findByKey(PRODUCT_KEY).get();
        productRepository.findByKey(PRODUCT_KEY).get();
    }

}