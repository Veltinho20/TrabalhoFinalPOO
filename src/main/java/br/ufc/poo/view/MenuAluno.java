package br.ufc.poo.view;

import javax.swing.JOptionPane;

public class MenuAluno {

    public void exibirMenu() {

        int opcao;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU DO ALUNO" +
                    "\n\n1 - Visualizar projetos disponíveis" +
                    "\n2 - Inscrever-se em projeto" +
                    "\n3 - Cancelar inscrição" +
                    "\n4 - Ver histórico em projetos" +
                    "\n5 - Notificações" +
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

                case 5:
                    JOptionPane.showMessageDialog(null, "Funcionalidade não implementada");
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo do menu do aluno...");
                    break;

                default:
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                        break;
            }
        } while (opcao != 0);
    }
}
