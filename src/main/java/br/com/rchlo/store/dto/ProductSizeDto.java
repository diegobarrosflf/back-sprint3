package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Size;

public class ProductSizeDto {

    private final String size;

    public ProductSizeDto(Size size) {
        this.size = size.getDescription();
    }

    public String getSize(){
        return size;
    };
}
