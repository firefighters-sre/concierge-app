package com.redhat.quarkus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

@Entity
public class RegistroAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Pessoa pessoa;
    private LocalTime horaEntrada;
    private LocalTime horaSaída;
    private String tipoPessoa;
    private String destino;

    public RegistroAcesso() {
        // Default constructor for JPA
    }

    public RegistroAcesso(Pessoa pessoa, LocalTime horaEntrada, LocalTime horaSaída, 
                          String tipoPessoa, String destino) {
        this.pessoa = pessoa;
        this.horaEntrada = horaEntrada;
        this.horaSaída = horaSaída;
        this.tipoPessoa = tipoPessoa;
        this.destino = destino;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSaída() {
        return horaSaída;
    }

    public void setHoraSaída(LocalTime horaSaída) {
        this.horaSaída = horaSaída;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "RegistroAcesso{" +
               "id=" + id +
               ", pessoa=" + pessoa +
               ", horaEntrada=" + horaEntrada +
               ", horaSaída=" + horaSaída +
               ", tipoPessoa='" + tipoPessoa + '\'' +
               ", destino='" + destino + '\'' +
               '}';
    }
}
