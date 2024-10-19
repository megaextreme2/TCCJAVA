package com.fiebtcc.barbersclub.barbersclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="BarberShop")
public class BarberShop {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementar
    private Long id;
    @Column(nullable = false, columnDefinition = "INTEGER")
    private int qtd_barbeiro;
    @Column(nullable = false, length = 9)
    private String cep;
    @Column(nullable = true, length = 35)
    private String nome;
    @Column(nullable = false, length = 75)
    private String logradouro;
    @Column
    private byte[] foto;
    @Column(nullable = false, length = 40)
    private String bairro;
    @Column(nullable = false, length = 40)
    private String cidade;
    @Column(nullable = false, length = 2)
    private String uf;
    @Column(length = 200)
    private String descricao;
    @Column(nullable = false)
    private boolean cod_status;
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

    public int getQtd_barbeiro() {
        return qtd_barbeiro;
    }

    public void setQtd_barbeiro(int qtd_barbeiro) {
        this.qtd_barbeiro = qtd_barbeiro;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isCod_status() {
        return cod_status;
    }

    public void setCod_status(boolean codStatus) {
        this.cod_status = codStatus;
    }
    public String getMensagemErro() {
        return mensagemErro;
    }
    public boolean validarBarberShop(){

        return isValid;
    }
}
