package org.example.naturalidsample.product;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class ProductWithNaturalIdRepositoryTest {

    private static final String PRODUCT_KEY1 = "KEY111";
    private static final String PRODUCT_KEY2 = "KEY222";
    private static final String PRODUCT_KEY3 = "KEY333";

    @Autowired
    private ProductWithNaturalIdRepository productWithNaturalIdRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("TRUNCATE TABLE product_with_natural_id");
        productWithNaturalIdRepository.save(new ProductWithNaturalId(PRODUCT_KEY1, "새상품1", 9999));
        productWithNaturalIdRepository.save(new ProductWithNaturalId(PRODUCT_KEY2, "새상품2", 9999));
        productWithNaturalIdRepository.save(new ProductWithNaturalId(PRODUCT_KEY3, "새상품3", 9999));
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
        productWithNaturalIdRepository.findByKey(PRODUCT_KEY1).get();
        productWithNaturalIdRepository.findByKey(PRODUCT_KEY1).get();
        productWithNaturalIdRepository.findByKey(PRODUCT_KEY1).get();
    }

    @Test
    void findByNaturalId() throws Exception {
        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY1).get();
        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY1).get();
        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY1).get();
    }

    @Test
    void findById() throws Exception {
        final ProductWithNaturalId productWithNaturalId = productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY1).get();
        productWithNaturalIdRepository.findById(productWithNaturalId.getId()).get();
    }

    @Test
    void findById2() throws Exception {
        final ProductWithNaturalId productWithNaturalId = productWithNaturalIdRepository.findById(1L).get();
        productWithNaturalIdRepository.findByNaturalId(productWithNaturalId.getKey()).get();
    }

    @Test
    void findAll() throws Exception {
        productWithNaturalIdRepository.findAll();

        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY1).get();
        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY1).get();
        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY1).get();

        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY2).get();
        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY2).get();
        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY2).get();

        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY3).get();
        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY3).get();
        productWithNaturalIdRepository.findByNaturalId(PRODUCT_KEY3).get();
    }

}