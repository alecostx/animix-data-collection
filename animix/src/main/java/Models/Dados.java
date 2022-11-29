package Models;

import java.util.ArrayList;
import java.util.List;

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
    private Double leitura;
    private Double escrita;
    private Integer qtdProcessos;
    private Integer qtdServicos;
    private String dataColeta;
    private String momento;
    private Boolean isCritico;
    private List<String> comment;

    public Dados() {
        this.comment = new ArrayList<>();
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public Integer getIdDado() {
        return idDado;
    }

    public void setIdDado(Integer idDado) {
        this.idDado = idDado;
    }

    public Boolean getIsCritico() {
        return isCritico;
    }

    public void setIsCritico(Boolean isCritico) {
        this.isCritico = isCritico;
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

    public Double getLeitura() {
        return leitura;
    }

    public void setLeitura(Double leitura) {
        this.leitura = leitura;
    }

    public Double getEscrita() {
        return escrita;
    }

    public void setEscrita(Double escrita) {
        this.escrita = escrita;
    }
    

    public String toString() {
        return ":warning: Alerta de dado crítico :warning: "
                + "\n Maquina ID = " + fkMaquina
                + "\n Uso da CPU = " + usoCpu + " %"
                + "\n Uso da memoria = " + usoMemoria + " %"
                + "\n Leitura do disco = " + leitura
                + "\n Escrita do disco = " + escrita
                + "\n Quantiade de processos = " + qtdProcessos
                + "\n Quantidade de serviços = " + qtdServicos
                + "\n Data da coleta = " + dataColeta
                + "\n Hora da coleta = " + momento
                + "\n Informações = " + comment.toString();

    }

}
