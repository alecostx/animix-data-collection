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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Alexandre Costa
 */
public class Maquina {

    private Integer idMaquina;
    private Integer fkStudio;
    private String disco;
    private Double discoIdeal;
    private String memoria;
    private Double memoriaIdeal;
    private String processador;
    private Double processamentoIdeal;
    private Integer quantidadeDiscos;
    private String sistema;
    private Boolean monitoraDisco;
    private Boolean monitoraMemoria;
    private Boolean monitoraProcessador;
    private Boolean monitoraTemperatura;
    private Double temperaturaIdeal;
    private Integer situacao;

    // Objetos conexão
    Connection connection = new Connection();
    JdbcTemplate database = connection.getConnection();

    public Maquina getMaquina(Integer idMaquina) {
        List<Maquina> maquinas = database.query("select * from maquinas where idMaquina = ?", new BeanPropertyRowMapper<>(Maquina.class), idMaquina);
        Maquina maquina = maquinas.get(maquinas.size() - 1);
        return maquina;
    }

    public void setarInfos(Integer idMaquina) {
        Sistema sistema = new Sistema();
        Memoria memoria = new Memoria();
        Processador processador = new Processador();
        DiscoGrupo grupoDeDiscos = new DiscoGrupo();
        Conversor conversor = new Conversor();

        String sistemaOp = sistema.getSistemaOperacional();
        String processadorNome = processador.getNome();
        String memoriaTotal = conversor.formatarBytes(memoria.getTotal());
        Integer qtdDisco = grupoDeDiscos.getQuantidadeDeDiscos();
        
        String discoTotal = conversor.formatarBytes(grupoDeDiscos.getTamanhoTotal());

        database.update("UPDATE maquinas SET "
                + "disco = ?"
                + ",memoria = ?"
                + ",processador = ?"
                + ",quantidadeDiscos = ?"
                + ",sistema = ?"
                + " WHERE idMaquina = ?", discoTotal, memoriaTotal, processadorNome, qtdDisco, sistemaOp, idMaquina);
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

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public Double getDiscoIdeal() {
        return discoIdeal;
    }

    public void setDiscoIdeal(Double discoIdeal) {
        this.discoIdeal = discoIdeal;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public Double getMemoriaIdeal() {
        return memoriaIdeal;
    }

    public void setMemoriaIdeal(Double memoriaIdeal) {
        this.memoriaIdeal = memoriaIdeal;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public Double getProcessamentoIdeal() {
        return processamentoIdeal;
    }

    public void setProcessamentoIdeal(Double processamentoIdeal) {
        this.processamentoIdeal = processamentoIdeal;
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

    public Double getTemperaturaIdeal() {
        return temperaturaIdeal;
    }

    public void setTemperaturaIdeal(Double temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }

    public Integer getQuantidadeDiscos() {
        return quantidadeDiscos;
    }

    public void setQuantidadeDiscos(Integer quantidadeDiscos) {
        this.quantidadeDiscos = quantidadeDiscos;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }
    
}
