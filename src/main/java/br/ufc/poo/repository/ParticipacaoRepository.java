package br.ufc.poo.repository;

import java.util.ArrayList;
import br.ufc.poo.model.Participacao;

public class ParticipacaoRepository {

    private ArrayList<Participacao> participacoes;

    public ParticipacaoRepository() {
        this.participacoes = new ArrayList<>();
    }

    public void adicionarParticipacao(Participacao participacao) {
        participacoes.add(participacao);
    }

    public ArrayList<Participacao> listarParticipacoes() {
        return participacoes;
    }
}
