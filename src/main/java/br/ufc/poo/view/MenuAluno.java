package br.ufc.poo.view;

import javax.swing.JOptionPane;

import br.ufc.poo.model.Projeto;
import br.ufc.poo.repository.ProjetoRepository;

public class MenuAluno {

    private ProjetoRepository projetoRepository;

    public MenuAluno(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public void visualizarProjetos() {
        String mensagem = "";
        for (Projeto p : projetoRepository.listarProjetos()) {
            mensagem += p.exibirInfo() + "\n\n";
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }
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
                    visualizarProjetos();
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
