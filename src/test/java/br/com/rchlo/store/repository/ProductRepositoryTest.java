package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import br.com.rchlo.store.dto.ProductDto;
import br.com.rchlo.store.repository.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private EntityManager entityManager;
    private ProductRepository productRepository;

    @BeforeEach
    public void beforeEach() {
        this.entityManager = JPAUtil.getEntityManager();
        this.productRepository = new ProductRepository(this.entityManager);
        this.entityManager.getTransaction().begin();
        populaProdutos();
    }

    @AfterEach
    public void afterEach() {
        this.entityManager.getTransaction().rollback();
    }

    @Test
    void shouldListAllProductsOrderedByName() {

        List<Product> products = productRepository.findAllByOrderByName();

        assertEquals(2, products.size());

        Product firstProduct = products.get(0);
        assertEquals(7L, firstProduct.getCode());
        assertEquals("Jaqueta Puffer Juvenil Com Capuz Super Mario Branco", firstProduct.getName());

        Product secondProduct = products.get(1);
        assertEquals(1L, secondProduct.getCode());
        assertEquals("Regata Infantil Mario Bros Branco", secondProduct.getName());
    }

    @Test
    void  listProductsQuantityByColorWhenTheListIsEmpty(){
        this.entityManager.getTransaction().rollback();
        List<ProductByColorDto> productsByColor = productRepository.productsByColor();
        assertThat(productsByColor).isEmpty();
    }

    @Test
    void  shouldListProductsQuantityByColorWhenTheListHaveOnlyOneColor(){
        List<ProductByColorDto> products = productRepository.productsByColor();
        assertEquals(1, products.size());
        assertEquals(2, products.get(0).getAmount());
        assertEquals("Branca", products.get(0).getColor());
    }

    @Test
    void  shouldListProductsQuantityByColorWhenTheListHaveTwoColorsOrMore(){
        entityManager.persist(new Product(233L,
                "Jaqueta Puffer Juvenil Com Capuz Super Mario Branco",
                "A Jaqueta Puffer Juvenil Com Capuz Super Mario Branco é confeccionada em material sintético.",
                "jaqueta-puffer-juvenil-com-capuz-super-mario-branco-13834193_sku",
                "Nintendo",
                new BigDecimal("201.90"),
                null,
                Color.BLUE,
                148));

        List<ProductByColorDto> products = productRepository.productsByColor();

        assertEquals(2, products.size());
        assertEquals(2, products.get(0).getAmount());
        assertEquals(1, products.get(1).getAmount());
        assertEquals("Branca", products.get(0).getColor());
        assertEquals("Azul", products.get(1).getColor());
    }



    private void populaProdutos(){
        entityManager.persist(new Product(7L,
                "Jaqueta Puffer Juvenil Com Capuz Super Mario Branco",
                "A Jaqueta Puffer Juvenil Com Capuz Super Mario Branco é confeccionada em material sintético.",
                "jaqueta-puffer-juvenil-com-capuz-super-mario-branco-13834193_sku",
                "Nintendo",
                new BigDecimal("199.90"),
                null,
                Color.WHITE,
                147));
        entityManager.persist(new Product(1L,
                "Regata Infantil Mario Bros Branco",
                "A Regata Infantil Mario Bros Branco é confeccionada em fibra natural. Aposte!",
                "regata-infantil-mario-bros-branco-14040174_sku",
                "Nintendo",
                new BigDecimal("29.90"),
                null,
                Color.WHITE,
                98));
    }

}