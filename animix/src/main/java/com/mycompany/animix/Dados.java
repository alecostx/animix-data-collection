package com.mycompany.animix;

/**
 *
 * @author Alexandre Costa
 */
public class Dados {
    private Integer idDado;
    private Integer fkMaquina;
    private Double usoCpu;
    private Double usoMemoria;
    private Double temperatura;
    private Double porcentDisco;
    private Integer qtdProcessos;
    private Integer qtdServicos;
    private String dataColeta;
    private String momento; 

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

    public Double getUsoCpu() {
        return usoCpu;
    }

    public void setUsoCpu(Double usoCpu) {
        this.usoCpu = usoCpu;
    }

    public Double getUsoMemoria() {
        return usoMemoria;
    }

    public void setUsoMemoria(Double usoMemoria) {
        this.usoMemoria = usoMemoria;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
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

    public Integer getQtdServicos() {
        return qtdServicos;
    }

    public void setQtdServicos(Integer qtdServicos) {
        this.qtdServicos = qtdServicos;
    }

    public String getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(String dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    @Override
    public String toString() {
        return          "---Dado---"  
                + "\n idDado = " + idDado 
                + "\n fkMaquina = " + fkMaquina 
                + "\n usoCpu = " + usoCpu 
                + "\n usoMemoria = " + usoMemoria 
                + "\n temperatura = " + temperatura 
                + "\n porcentDisco = " + porcentDisco 
                + "\n qtdProcessos = " + qtdProcessos
                + "\n qtdServicos = " + qtdServicos
                + "\n dataColeta = " + dataColeta
                + "\n momento = " + momento ;
    }
}
