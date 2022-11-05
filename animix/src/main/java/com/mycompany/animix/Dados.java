/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animix;

/**
 *
 * @author Alexandre Costa
 */
public class Dados {
    private Integer idDado;
    private Integer fkMaquina;
    private Double temperatura;
    private Double porcentCpu;
    private Double porcentMemoria;
    private Double porcentDisco;
    private Integer qtdProcessos;

    public Integer getIdDado() {
        return idDado;
    }

    public void setIdDado(Integer idDado) {
        this.idDado = idDado;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getPorcentCpu() {
        return porcentCpu;
    }

    public void setPorcentCpu(Double porcentCpu) {
        this.porcentCpu = porcentCpu;
    }

    public Double getPorcentMemoria() {
        return porcentMemoria;
    }

    public void setPorcentMemoria(Double porcentMemoria) {
        this.porcentMemoria = porcentMemoria;
    }

    public Double getPorcentDisco() {
        return porcentDisco;
    }

    public void setPorcentDisco(Double porcentDisco) {
        this.porcentDisco = porcentDisco;
    }

    public Integer getQtdProcessos() {
        return qtdProcessos;
    }

    public void setQtdProcessos(Integer qtdProcessos) {
        this.qtdProcessos = qtdProcessos;
    }
    
}
