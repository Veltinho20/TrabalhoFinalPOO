package br.ufc.poo.view;

import br.ufc.poo.view.MenuPrincipal;
import br.ufc.poo.repository.UsuarioRepository;
import br.ufc.poo.repository.ProjetoRepository;

public class Main {

    public static void main(String[] args) {

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        ProjetoRepository projetoRepository = new ProjetoRepository();
        MenuPrincipal menuPrincipal = new MenuPrincipal(usuarioRepository, projetoRepository);

        menuPrincipal.exibirMenu();
    }
}
