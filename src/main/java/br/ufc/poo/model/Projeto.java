package br.ufc.poo.model;

import br.ufc.poo.enums.StatusProjeto;

public class Projeto {

    private String titulo;
    private String areaEstudo;
    private Professor orientador;
    private String dataInicio;
    private String prazo;
    private int vagas;
    private StatusProjeto status;

    public Projeto(String titulo, String areaEstudo, Professor orientador, String dataInicio, String prazo, int vagas, StatusProjeto status) {
        this.titulo = titulo;
        this.areaEstudo = areaEstudo;
        this.orientador = orientador;
        this.dataInicio = dataInicio;
        this.prazo = prazo;
        this.vagas = vagas;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Professor getOrientador() {
        return orientador;
    }

    public void setOrientador(Professor orientador) {
        this.orientador = orientador;
    }

    public String getAreaEstudo() {
        return areaEstudo;
    }

    public void setAreaEstudo(String areaEstudo) {
        this.areaEstudo = areaEstudo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public String exibirInfo() {
        return "===== DADOS DO PROJETO =====" +
                "\n\nTítulo: " + titulo +
                "\nÁrea de estudo: " + areaEstudo +
                "\nOrientador: " + orientador.getNome() +
                "\nData de início: " + dataInicio +
                "\nPrazo: " + prazo +
                "\nVagas: " + vagas +
                "\nStatus: " + status;
    }
}
