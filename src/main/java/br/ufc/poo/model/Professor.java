package br.ufc.poo.model;

public class Professor extends Usuario {

    private String areaAtuacao;

    public Professor(String nome, String email, String senha, String areaAtuacao) {
        super(nome, email, senha);
        this.areaAtuacao = areaAtuacao;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    @Override
    public String exibirInfo() {
        return "Professor: " + getNome() +
                "\nÁrea de atuação: " + areaAtuacao;
    }
}
