package br.com.rchlo.store.domain;

public enum PaymentStatus {

    CREATED("Criado"),
    CONFIRMED("Confirmado"),
    CANCELED("Cancelado");

    private String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
