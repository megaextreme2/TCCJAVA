package com.fiebtcc.barbersclub.barbersclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.text.DecimalFormat;

@Entity
@Table(name="Avaliacao")
public class Avaliacao {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementar
    private Long id;
    @Column(nullable = true, columnDefinition = "DECIMAL(5)")
    private Double media;
    @Column(nullable = true, columnDefinition = "INTEGER")
    private Long quantiA;
    @Column(nullable = false)
    private boolean codStatus;
    @Transient  //Atributos que não estão em uma coluna
    @JsonIgnore
    private String mensagemErro = "";

    @Transient
    @JsonIgnore
    private boolean isValid = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Long getQuantiA() {
        return quantiA;
    }

    public void setQuantiA(Long quantiA) {
        this.quantiA = quantiA;
    }

    public boolean isCodStatus() {
        return codStatus;
    }

    public void setCodStatus(boolean codStatus) {
        this.codStatus = codStatus;
    }
    public String getMensagemErro() {
        return mensagemErro;
    }
    public boolean validarAvaliacao(){

        return isValid;
    }
}
