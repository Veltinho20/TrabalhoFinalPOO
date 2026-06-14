package br.ufc.poo.model;

public class Aluno extends Usuario{

    private int matricula;

    public Aluno(String nome, String email, String senha, int matricula) {
        super(nome, email, senha);
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public String exibirInfo() {
        return "Aluno: " + getNome() +
                "\nMatrícula: " + matricula +
                "\nEmail: " + getEmail();
    }
}
