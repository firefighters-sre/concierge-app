package com.redhat.quarkus.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection="AccessLog")
public class AccessLog extends PanacheMongoEntity {
    private Long registroId;
    private Long pessoaId;
    private String horaEntrada;
    private String horaSaída;
    private String destino;

    public Long getRegistroId() {
        return registroId;
    }

    public void setRegistroId(Long registroId) {
        this.registroId = registroId;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSaída() {
        return horaSaída;
    }

    public void setHoraSaída(String horaSaída) {
        this.horaSaída = horaSaída;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "AccessLog{" +
                "registroId=" + registroId +
                ", pessoaId=" + pessoaId +
                ", horaEntrada='" + horaEntrada + '\'' +
                ", horaSaída='" + horaSaída + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
}