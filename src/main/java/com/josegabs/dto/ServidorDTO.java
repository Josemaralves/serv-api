package com.josegabs.dto;

import java.util.ArrayList;
import java.util.List;

public class ServidorDTO {

    private String cpf;
    private String nome;
    private List<CargoDTO> cargo;

    public ServidorDTO() {
        cargo = new ArrayList<>();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CargoDTO> getCargo() {
        return cargo;
    }

    public void setCargo(List<CargoDTO> cargo) {
        this.cargo = cargo;
    }
}
