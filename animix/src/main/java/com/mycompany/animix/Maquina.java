package com.mycompany.animix;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import com.github.britooo.looca.api.util.Conversor;
import java.util.List;

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

    public void setarInfos() {
        Sistema sistema = new Sistema();
        Memoria memoria = new Memoria();
        Processador processador = new Processador();
        Temperatura temperatura = new Temperatura();
        DiscoGrupo grupoDeDiscos = new DiscoGrupo();
        ServicoGrupo grupoDeServicos = new ServicoGrupo();
        ProcessoGrupo grupoDeProcessos = new ProcessoGrupo();
        Conversor conversor = new Conversor();
        List<Disco> discos = grupoDeDiscos.getDiscos();

        String sistemaOp = sistema.getSistemaOperacional();
        String fabricante = sistema.getFabricante();
        String processadorNome = processador.getNome();
        String memoriaTotal = conversor.formatarBytes(memoria.getTotal());
        Integer qtdDisco = grupoDeDiscos.getQuantidadeDeDiscos();
        String discoTotal = conversor.formatarBytes(grupoDeDiscos.getTamanhoTotal());
        System.out.println(discoTotal);

    }

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
