package br.ufc.poo.view;

import br.ufc.poo.view.MenuPrincipal;
import br.ufc.poo.repository.UsuarioRepository;

public class Main {

    public static void main(String[] args) {

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        MenuPrincipal menuPrincipal = new MenuPrincipal(usuarioRepository);

        menuPrincipal.exibirMenu();
    }
}
