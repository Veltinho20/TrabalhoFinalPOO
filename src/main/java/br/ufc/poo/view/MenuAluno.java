package br.ufc.poo.view;

import javax.swing.JOptionPane;

import br.ufc.poo.enums.StatusParticipacao;
import br.ufc.poo.model.Participacao;
import br.ufc.poo.model.Projeto;
import br.ufc.poo.repository.ProjetoRepository;
import br.ufc.poo.model.Aluno;
import br.ufc.poo.repository.ParticipacaoRepository;

public class MenuAluno {

    private ProjetoRepository projetoRepository;
    private Aluno aluno;
    private ParticipacaoRepository participacaoRepository;

    public MenuAluno(Aluno aluno, ProjetoRepository projetoRepository, ParticipacaoRepository participacaoRepository) {
        this.projetoRepository = projetoRepository;
        this.aluno = aluno;
        this.participacaoRepository = participacaoRepository;
    }

    public void visualizarProjetos() {
        if (projetoRepository.listarProjetos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há projetos disponíveis");
            return;
        }

        String mensagem = "";

        for (Projeto p : projetoRepository.listarProjetos()) {
            mensagem += p.exibirInfo() + "\n\n";
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void inscreverEmProjeto() {
        if (projetoRepository.listarProjetos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há projetos disponíveis");
            return;
        }
        String titulo = JOptionPane.showInputDialog("Digite o título do projeto");

        Projeto projetoEncontrado = null;

        for (Projeto p : projetoRepository.listarProjetos()) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                projetoEncontrado = p;
                break;
            }
        }
        if (projetoEncontrado == null) {
            JOptionPane.showMessageDialog(null, "Projeto não encontrado");
            return;
        }
        Participacao participacao = new Participacao(aluno, projetoEncontrado, StatusParticipacao.ATIVA);
        participacaoRepository.adicionarParticipacao(participacao);
        JOptionPane.showMessageDialog(null, "Inscrição bem sucedida");
    }

    public void historicoProjetos() {
        String mensagem = "";

        for (Participacao p : participacaoRepository.listarParticipacoes()) {
            if (p.getAluno().equals(aluno)) {
                mensagem += p.exibirInfo() + "\n\n";

            }
        }
        if (mensagem.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você não possui participações em projetos");
            return;
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void cancelarParticipacao() {
        String titulo = JOptionPane.showInputDialog("Digite o título do projeto");

        for (Participacao p : participacaoRepository.listarParticipacoes()) {
            if (p.getAluno().equals(aluno) && p.getProjeto().getTitulo().equalsIgnoreCase(titulo)) {
                if (p.getStatus() == StatusParticipacao.CANCELADA) {
                    JOptionPane.showMessageDialog(null, "Essa inscrição já está cancelada");
                } else {
                    p.setStatus(StatusParticipacao.CANCELADA);
                    JOptionPane.showMessageDialog(null, "Inscrição cancelada");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Você não está inscrito nesse projeto");
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
                    inscreverEmProjeto();
                    break;

                case 3:
                    cancelarParticipacao();
                    break;

                case 4:
                    historicoProjetos();
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
