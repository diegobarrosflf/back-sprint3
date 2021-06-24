package br.com.rchlo.store.domain;

public enum Size {

    SMALL("Pequeno"),
    MEDIUM("Médio"),
    LARGE("Grande"),
    EXTRA_LARGE("Extra_Grande");

    private String description;

    Size(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
