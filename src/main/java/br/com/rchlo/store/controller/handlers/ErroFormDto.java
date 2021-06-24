package br.com.rchlo.store.controller.handlers;

public class ErroFormDto {

    private String field;
    private String erro;

    public ErroFormDto(String field, String erro) {
        this.field = field;
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public String getField() {
        return field;
    }
}
