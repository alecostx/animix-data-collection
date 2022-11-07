package com.mycompany.animix;

/**
 *
 * @author Alexandre Costa
 */
public class Maquina {

    private Integer idMaquina;
    private Integer fkStudio;
    private Double temperatura;
    private Double temperaturaIdeal;
    private Double discoIdeal;
    private String disco;
    private Double memoriaIdeal;
    private String memoria;
    private Double processadorIdeal;
    private String processador;
    private String sistema;
    private Boolean monitoraDisco;
    private Boolean monitoraMemoria;
    private Boolean monitoraProcessador;
    private Boolean monitoraTemperatura;

    public Double getTemperaturaIdeal() {
        return temperaturaIdeal;
    }

    public void setTemperaturaIdeal(Double temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Integer getFkStudio() {
        return fkStudio;
    }

    public void setFkStudio(Integer fkStudio) {
        this.fkStudio = fkStudio;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getDiscoIdeal() {
        return discoIdeal;
    }

    public void setDiscoIdeal(Double discoIdeal) {
        this.discoIdeal = discoIdeal;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public Double getMemoriaIdeal() {
        return memoriaIdeal;
    }

    public void setMemoriaIdeal(Double memoriaIdeal) {
        this.memoriaIdeal = memoriaIdeal;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public Double getProcessadorIdeal() {
        return processadorIdeal;
    }

    public void setProcessadorIdeal(Double processadorIdeal) {
        this.processadorIdeal = processadorIdeal;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public Boolean getMonitoraDisco() {
        return monitoraDisco;
    }

    public void setMonitoraDisco(Boolean monitoraDisco) {
        this.monitoraDisco = monitoraDisco;
    }

    public Boolean getMonitoraMemoria() {
        return monitoraMemoria;
    }

    public void setMonitoraMemoria(Boolean monitoraMemoria) {
        this.monitoraMemoria = monitoraMemoria;
    }

    public Boolean getMonitoraProcessador() {
        return monitoraProcessador;
    }

    public void setMonitoraProcessador(Boolean monitoraProcessador) {
        this.monitoraProcessador = monitoraProcessador;
    }

    public Boolean getMonitoraTemperatura() {
        return monitoraTemperatura;
    }

    public void setMonitoraTemperatura(Boolean monitoraTemperatura) {
        this.monitoraTemperatura = monitoraTemperatura;
    }

}
