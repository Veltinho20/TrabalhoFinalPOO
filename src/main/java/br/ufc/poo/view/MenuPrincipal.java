package br.ufc.poo.view;

import javax.swing.JOptionPane;

public class MenuPrincipal {

    public void exibirMenu() {

        int opcao;

        do {

            opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU PRINCIPAL" +
                    "\n1 - Login" +
                    "\n2 - Cadastro" +
                    "\n0 - Sair"));

            switch (opcao) {

                case 1:
                    JOptionPane.showMessageDialog(null,"Funcionalidade não implementada");
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Funcionalidade não implementada");
                    break;

                case 0:
                    break;

                default:
                    if (opcao != 0) {
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                    }
            }
        } while (opcao != 0);
    }
}
