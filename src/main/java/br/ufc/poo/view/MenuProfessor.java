package br.ufc.poo.view;

import javax.swing.JOptionPane;
import br.ufc.poo.model.Professor;
import br.ufc.poo.model.Projeto;
import br.ufc.poo.repository.ProjetoRepository;
import br.ufc.poo.enums.StatusProjeto;

public class MenuProfessor {

    private Professor professor;
    private ProjetoRepository projetoRepository;

    public MenuProfessor(Professor professor, ProjetoRepository projetoRepository) {
        this.professor = professor;
        this.projetoRepository = projetoRepository;
    }

    private void criarProjeto() {
        String titulo = JOptionPane.showInputDialog("Título:");
        String area = JOptionPane.showInputDialog("Área de estudo:");
        String dataInicio = JOptionPane.showInputDialog("Data de início");
        String prazo = JOptionPane.showInputDialog("Prazo");
        int vagas = Integer.parseInt(JOptionPane.showInputDialog("Vagas:"));

        Projeto projeto = new Projeto(titulo, area, professor, dataInicio, prazo, vagas, StatusProjeto.ABERTO);

        projetoRepository.adicionarProjeto(projeto);
        JOptionPane.showMessageDialog(null, "Projeto criado com sucesso");
    }

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
                    criarProjeto();
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
