package br.ufc.poo.repository;

import java.util.ArrayList;
import br.ufc.poo.model.Notificacao;

public class NotificacaoRepository {
    private ArrayList<Notificacao> notificacoes;

    public NotificacaoRepository() {
        this.notificacoes = new ArrayList<>();
    }

    public void adicionarNotificacao(Notificacao notificacao) {
        notificacoes.add(notificacao);
    }

    public ArrayList<Notificacao> listarNotificacoes() {
        return notificacoes;
    }
}
