package br.com.rchlo.store.repository.util.builder;

import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;

import java.math.BigDecimal;

public class ProductBuilder {

    private Long code;
    private String name;
    private String description;
    private String slug;
    private String brand;
    private BigDecimal price;
    private BigDecimal discount;
    private Color color;

    private Integer weightInGrams;

    public ProductBuilder withCode(Long code) {
        this.code = code;
        return this;
    }

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withDescrption(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public ProductBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductBuilder withPrice(String price) {
        this.price = new BigDecimal(price);
        return this;
    }

    public ProductBuilder withDiscount(String discount) {
        this.discount = new BigDecimal(discount);
        return this;
    }

    public ProductBuilder withColor(Color color) {
        this.color = color;
        return this;
    }

    public ProductBuilder withWeightInGrams(int weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }

    public Product create() {
        return new Product(code, name, description, slug, brand, price, discount, color, weightInGrams);
    }
}
