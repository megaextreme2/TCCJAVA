package com.fiebtcc.barbersclub.barbersclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="Admin")
public class Admin {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementar
    private Long id;
    @Column(nullable = true, length = 100)
    private String nome;
    @Column(nullable = false, length = 14)
    private String cnpj;

    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 20)
    private String senha;
    @Column(nullable = true, length = 2000)
    private String relatorio;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
    public boolean validarAdmin(){

        return isValid;
    }
}
