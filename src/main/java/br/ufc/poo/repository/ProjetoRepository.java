package br.ufc.poo.repository;

import java.util.ArrayList;
import br.ufc.poo.model.Projeto;

public class ProjetoRepository {

    private ArrayList<Projeto> projetos;

    public ProjetoRepository() {
        this.projetos = new ArrayList<>();
    }

    public void adicionarProjeto(Projeto projeto) {
        projetos.add(projeto);
    }

    public ArrayList<Projeto> listarProjetos() {
        return projetos;
    }
}
