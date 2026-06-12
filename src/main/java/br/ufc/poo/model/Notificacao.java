package br.ufc.poo.model;

public class Notificacao {

    private Usuario destinatario;
    private String mensagem;
    private boolean lida;

    public Notificacao(Usuario destinatario, String mensagem) {
        this.destinatario = destinatario;
        this.mensagem = mensagem;
        this.lida = false;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public String exibirInfo() {
        return "Destinatário: " + destinatario.getNome() +
                "\nMensagem: " + mensagem +
                "\nStatus: " + (lida ? "Lida" : "Não lida");
    }
}
