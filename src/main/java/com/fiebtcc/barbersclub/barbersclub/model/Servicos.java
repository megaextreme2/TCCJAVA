package com.fiebtcc.barbersclub.barbersclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="Servicos")
public class Servicos {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementar
    private Long id;
    @Column(nullable = false, length = 50)
    private String tipo;
    @Column(nullable = false, length = 20)
    private String nome;
    @Column
    private byte[] foto;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    private int preco;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {this.nome = nome;}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public boolean isCod_status() {
        return cod_status;
    }

    public void setCod_status(boolean cod_status) {
        this.cod_status = cod_status;
    }
    public String getMensagemErro() {
        return mensagemErro;
    }
    public boolean validarServicos(){
        if(nome.length() > 20){
        mensagemErro += "Nome do serviço maior do que deveria";
        isValid = false;}
        if (nome.isEmpty()){
            mensagemErro += "Nome do serviço insuficiente";
            isValid = false;}
        return isValid;
    }

}
