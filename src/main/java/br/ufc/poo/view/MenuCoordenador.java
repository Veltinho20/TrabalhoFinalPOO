package br.ufc.poo.view;

import javax.swing.JOptionPane;

public class MenuCoordenador {

    public void exibirMenu() {

        int opcao;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU DO COORDENADOR" +
                    "\n\n1 - Gerenciar projetos" +
                    "\n2 - Gerenciar usuários" +
                    "\n3 - Gerar relatórios" +
                    "\n4 - Estatísticas gerais" +
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
                    JOptionPane.showMessageDialog(null, "Saindo do menu do coordenador");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        } while (opcao != 0);
    }
}
