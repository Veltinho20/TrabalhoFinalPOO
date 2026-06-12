package br.ufc.poo.view;

import javax.swing.JOptionPane;

import br.ufc.poo.enums.StatusParticipacao;
import br.ufc.poo.model.Participacao;
import br.ufc.poo.model.Professor;
import br.ufc.poo.model.Projeto;
import br.ufc.poo.repository.ProjetoRepository;
import br.ufc.poo.enums.StatusProjeto;
import br.ufc.poo.repository.ParticipacaoRepository;

public class MenuProfessor {

    private Professor professor;
    private ProjetoRepository projetoRepository;
    private ParticipacaoRepository participacaoRepository;

    public MenuProfessor(Professor professor, ProjetoRepository projetoRepository, ParticipacaoRepository participacaoRepository) {
        this.professor = professor;
        this.projetoRepository = projetoRepository;
        this.participacaoRepository = participacaoRepository;
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

    public void visualizarInscritos() {
        String titulo = JOptionPane.showInputDialog("Digite o título do projeto");
        String mensagem = "";

        for (Participacao p : participacaoRepository.listarParticipacoes()) {
            if (p.getProjeto().getTitulo().equalsIgnoreCase(titulo) && p.getStatus() != StatusParticipacao.CANCELADA) {
                mensagem += p.getAluno().getNome() + "\n";
            }
        }
        if (mensagem.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há alunos inscritos nesse projeto");
            return;
        }
        JOptionPane.showMessageDialog(null, "Alunos inscritos:\n\n" + mensagem);
    }

    public void editarProjeto() {
        String titulo = JOptionPane.showInputDialog("Digite o título do projeto");
        Projeto projetoEncontrado = null;

        for (Projeto p : projetoRepository.listarProjetos()) {
            if (p.getTitulo().equalsIgnoreCase(titulo) && p.getOrientador().equals(professor)) {
                projetoEncontrado = p;
                break;
            }
        }
        if (projetoEncontrado == null) {
            JOptionPane.showMessageDialog(null, "Projeto não encontrado ou não pertence a você");
            return;
        }
        String novaArea = JOptionPane.showInputDialog("Nova área do projeto:", projetoEncontrado.getAreaEstudo());
        String novoPrazo = JOptionPane.showInputDialog("Novo prazo:", projetoEncontrado.getPrazo());
        int novasVagas = Integer.parseInt(JOptionPane.showInputDialog("Nova quantidade de vagas:", projetoEncontrado.getVagas()));

        projetoEncontrado.setAreaEstudo(novaArea);
        projetoEncontrado.setPrazo(novoPrazo);
        projetoEncontrado.setVagas(novasVagas);

        JOptionPane.showMessageDialog(null, "Projeto editado com sucesso");
    }

    public void notificacao() {
        String titulo = JOptionPane.showInputDialog("Digite o título do projeto");
        String mensagem = JOptionPane.showInputDialog("Digite a mensagem da notificação");
        String alunosNotificados = "";

        for (Participacao p : participacaoRepository.listarParticipacoes()) {
            if (p.getProjeto().getTitulo().equalsIgnoreCase(titulo) && p.getStatus() == StatusParticipacao.ATIVA) {
                alunosNotificados += p.getAluno().getNome() + "\n";
            }
        }
        if (alunosNotificados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há alunos ativos nesse projeto");
            return;
        }
        JOptionPane.showMessageDialog(null, "Notificação enviada: \n\n" +
                mensagem + "\n\nAlunos notificados: \n\n" +
                alunosNotificados);
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
                    editarProjeto();
                    break;

                case 3:
                    visualizarInscritos();
                    break;

                case 4:
                    notificacao();
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
