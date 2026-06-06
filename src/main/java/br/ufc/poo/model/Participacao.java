package br.ufc.poo.model;

import br.ufc.poo.enums.StatusParticipacao;

public class Participacao {

    private Aluno aluno;
    private Projeto projeto;
    private StatusParticipacao status;

    public Participacao(Aluno aluno, Projeto projeto, StatusParticipacao status) {
        this.aluno = aluno;
        this.projeto = projeto;
        this.status = status;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public StatusParticipacao getStatus() {
        return status;
    }

    public void setStatus(StatusParticipacao status) {
        this.status = status;
    }

    public String exibirInfo() {
        return "Aluno: " + aluno.getNome() +
                "\nProjeto: " + projeto.getTitulo() +
                "\nStatus: " + status;
    }
}
