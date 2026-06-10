package br.ufc.poo.view;

import br.ufc.poo.view.MenuPrincipal;
import br.ufc.poo.repository.UsuarioRepository;
import br.ufc.poo.repository.ProjetoRepository;
import br.ufc.poo.repository.ParticipacaoRepository;

public class Main {

    public static void main(String[] args) {

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        ProjetoRepository projetoRepository = new ProjetoRepository();
        ParticipacaoRepository participacaoRepository = new ParticipacaoRepository();
        MenuPrincipal menuPrincipal = new MenuPrincipal(usuarioRepository, projetoRepository, participacaoRepository);

        menuPrincipal.exibirMenu();
    }
}
