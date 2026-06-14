package br.ufc.poo.view;

import javax.swing.JOptionPane;

import br.ufc.poo.enums.StatusParticipacao;
import br.ufc.poo.exception.*;
import br.ufc.poo.model.Notificacao;
import br.ufc.poo.model.Participacao;
import br.ufc.poo.model.Projeto;
import br.ufc.poo.repository.ProjetoRepository;
import br.ufc.poo.model.Aluno;
import br.ufc.poo.repository.ParticipacaoRepository;
import br.ufc.poo.repository.NotificacaoRepository;

public class MenuAluno {

    private ProjetoRepository projetoRepository;
    private Aluno aluno;
    private ParticipacaoRepository participacaoRepository;
    private NotificacaoRepository notificacaoRepository;

    public MenuAluno(Aluno aluno, ProjetoRepository projetoRepository, ParticipacaoRepository participacaoRepository, NotificacaoRepository notificacaoRepository) {
        this.projetoRepository = projetoRepository;
        this.aluno = aluno;
        this.participacaoRepository = participacaoRepository;
        this.notificacaoRepository = notificacaoRepository;
    }

    public void visualizarProjetos() throws SistemaSemProjetosCadastradosException {
        if (projetoRepository.listarProjetos().isEmpty()) {
            throw new SistemaSemProjetosCadastradosException("Não há projetos cadastrados.");
        }

        String mensagem = "";

        for (Projeto p : projetoRepository.listarProjetos()) {
            mensagem += p.exibirInfo() + "\n\n";
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void inscreverEmProjeto() throws SistemaSemProjetosCadastradosException, ProjetoInexistenteException, ProjetoSemVagasException {
        if (projetoRepository.listarProjetos().isEmpty()) {
            throw new SistemaSemProjetosCadastradosException("Não há projetos cadastrados.");
        }

        String titulo = JOptionPane.showInputDialog("Digite o título do projeto:");

        Projeto projetoEncontrado = null;

        for (Projeto p : projetoRepository.listarProjetos()) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                projetoEncontrado = p;
                break;
            }
        }
        if (projetoEncontrado == null) {
            throw new ProjetoInexistenteException("Projeto não encontrado");
        }

        if (projetoEncontrado.getVagas() <= 0) {
            throw new ProjetoSemVagasException("Projeto sem vagas disponíveis");
        }
        Participacao participacao = new Participacao(aluno, projetoEncontrado, StatusParticipacao.ATIVA);
        participacaoRepository.adicionarParticipacao(participacao);
        projetoEncontrado.setVagas(projetoEncontrado.getVagas() - 1);
        JOptionPane.showMessageDialog(null, "Inscrição bem sucedida");
    }

    public void historicoProjetos() throws AlunoSemParticipacoesException {
        String mensagem = "";

        for (Participacao p : participacaoRepository.listarParticipacoes()) {
            if (p.getAluno().equals(aluno)) {
                mensagem += p.exibirInfo() + "\n\n";

            }
        }
        if (mensagem.isEmpty()) {
            throw new AlunoSemParticipacoesException("Você ainda não possui participações em projetos");
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void cancelarParticipacao() throws ParticipacaoJaCanceladaException {
        String titulo = JOptionPane.showInputDialog("Digite o título do projeto");

        for (Participacao p : participacaoRepository.listarParticipacoes()) {
            if (p.getAluno().equals(aluno) && p.getProjeto().getTitulo().equalsIgnoreCase(titulo)) {
                if (p.getStatus() == StatusParticipacao.CANCELADA) {
                    throw new ParticipacaoJaCanceladaException("Essa inscrição já está cancelada.");
                } else {
                    p.setStatus(StatusParticipacao.CANCELADA);
                    p.getProjeto().setVagas(p.getProjeto().getVagas() + 1);
                    JOptionPane.showMessageDialog(null, "Inscrição cancelada");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Você não está inscrito nesse projeto");
    }

    public void notificacoes() throws AlunoSemNotificacoesException {
        String mensagem = "";

        for (Notificacao n : notificacaoRepository.listarNotificacoes()) {
            if (n.getDestinatario().equals(aluno)) {
                mensagem += n.exibirInfo() + "\n\n";
                n.setLida(true);
            }
        }
        if (mensagem.isEmpty()) {
            throw new AlunoSemNotificacoesException("Você não possui notificações");
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
                    try {
                        visualizarProjetos();
                    } catch (SistemaSemProjetosCadastradosException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        inscreverEmProjeto();
                    } catch (SistemaSemProjetosCadastradosException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (ProjetoInexistenteException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (ProjetoSemVagasException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        cancelarParticipacao();
                    } catch (ParticipacaoJaCanceladaException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        historicoProjetos();
                    } catch (AlunoSemParticipacoesException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        notificacoes();
                    } catch (AlunoSemNotificacoesException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
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
