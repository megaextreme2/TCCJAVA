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
    @Column(length = 200)
    private String descricao;
    @Column(nullable = false)
    private boolean codStatus;

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

    public String getTipo_servico() {
        return tipo;
    }

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

    public void setTipo_servico(String tipo_servico) {
        this.tipo = tipo_servico;
    }

    public boolean isCodStatus() {
        return false;
    }

    public void setCodStatus(boolean codStatus) {
        this.codStatus = codStatus;
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
