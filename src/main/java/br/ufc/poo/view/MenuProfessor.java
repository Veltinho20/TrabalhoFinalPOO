package br.ufc.poo.view;

import javax.swing.JOptionPane;

public class MenuProfessor {

    public void exibirMenu() {

        int opcao;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU DO PROFESSOR" +
                    "\n\n1 - Criar novo projeto" +
                    "\n2 - Editar projeto existente" +
                    "\n3 - Visualizar inscritos" +
                    "\n4 - Enviar notificações" +
                    "\n0 - Sair"));

            switch (opcao) {

                case 1:
                    JOptionPane.showMessageDialog(null, "Funcionalidade não implementada");
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Funcionalidade não implementada");
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Funcionalidade não implementada");
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Funcionalidade não implementada");
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo do menu do professor...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        } while (opcao != 0);
    }
}
