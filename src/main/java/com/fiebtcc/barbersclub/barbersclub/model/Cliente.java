package com.fiebtcc.barbersclub.barbersclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Cliente")
public class Cliente {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementar
    private Long id;
    @Column(nullable = false, length = 120)
    private String nome;
    @Column(nullable = false, length = 15)
    private String cpf;
    @Column (nullable = false, length = 50)
    private String email;
    @Column
    private byte[] foto;
    @Column(nullable = false, length = 20)
    private String senha;
    @Column
    private Date data_nasciment;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Date getData_nasciment() {
        return data_nasciment;
    }

    public void setData_nasciment(Date data_nasciment) {
        this.data_nasciment = data_nasciment;
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

    public void setId(Long id) {
        this.id = id;
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
    public boolean validarCliente(){

        return isValid;
    }
}
