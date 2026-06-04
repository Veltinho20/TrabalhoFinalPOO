package br.ufc.poo.model;

public class Coordenador extends Usuario {

    private String curso;

    public Coordenador(String nome, String email, String senha, String curso) {
        super(nome, email, senha);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String exibirInfo() {
        return "Coordenador: " + getNome() +
                "\nCurso: " + curso;
    }
}
