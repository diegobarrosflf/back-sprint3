package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import br.com.rchlo.store.repository.util.JPAUtil;
import br.com.rchlo.store.repository.util.builder.ProductBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Product product1 = new ProductBuilder()
                .withCode(17L)
                .withName("Jaqueta Teste Puffer Juvenil Com Capuz Super Mario Branco")
                .withDescrption("A Jaqueta Puffer Juvenil Com Capuz Super Mario Branco é confeccionada em material sintético.")
                .withSlug("jaqueta-puffer-juvenil-com-capuz-super-mario-branco-13834193_sku")
                .withBrand("Nintendo")
                .withPrice("190.90")
                .withDiscount("190.90")
                .withColor(Color.BLUE)
                .withWeightInGrams(144)
                .create();
        entityManager.persist(product1);

        List<ProductByColorDto> products = productRepository.productsByColor();

        assertEquals(2, products.size());
        assertEquals(2, products.get(0).getAmount());
        assertEquals(1, products.get(1).getAmount());
        assertEquals("Branca", products.get(0).getColor());
        assertEquals("Azul", products.get(1).getColor());
    }



    private void populaProdutos(){
        Product product1 = new ProductBuilder()
                .withCode(7L)
                .withName("Jaqueta Puffer Juvenil Com Capuz Super Mario Branco")
                .withDescrption("A Jaqueta Puffer Juvenil Com Capuz Super Mario Branco é confeccionada em material sintético.")
                .withSlug("jaqueta-puffer-juvenil-com-capuz-super-mario-branco-13834193_sku")
                .withBrand("Nintendo")
                .withPrice("199.90")
                .withDiscount("199.90")
                .withColor(Color.WHITE)
                .withWeightInGrams(147)
                .create();

        Product product2 = new ProductBuilder()
                .withCode(1L)
                .withName("Regata Infantil Mario Bros Branco")
                .withDescrption("A Regata Infantil Mario Bros Branco é confeccionada em fibra natural. Aposte!")
                .withSlug("regata-infantil-mario-bros-branco-14040174_sku")
                .withBrand("Nintendo")
                .withPrice("29.90")
                .withDiscount("29.90")
                .withColor(Color.WHITE)
                .withWeightInGrams(98)
                .create();

        entityManager.persist(product1);
        entityManager.persist(product2);

    }

}