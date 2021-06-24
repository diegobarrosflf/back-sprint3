package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.ProductImage;

public class ProductImageDto {

    private final Long id;
    private final String imageUrl;

    public ProductImageDto(ProductImage productImage) {
        this.imageUrl = productImage.getImageUrl();
        this.id = productImage.getId();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getId() {
        return id;
    }

}
