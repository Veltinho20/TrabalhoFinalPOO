package br.ufc.poo.view;

import javax.swing.JOptionPane;

import br.ufc.poo.model.*;
import br.ufc.poo.repository.UsuarioRepository;
import br.ufc.poo.repository.ProjetoRepository;
import br.ufc.poo.repository.ParticipacaoRepository;
import br.ufc.poo.enums.StatusParticipacao;
import br.ufc.poo.enums.StatusProjeto;
import br.ufc.poo.exception.SistemaSemUsuariosCadastradosException;
import br.ufc.poo.exception.SistemaSemProjetosCadastradosException;

public class MenuCoordenador {

    private UsuarioRepository usuarioRepository;
    private ProjetoRepository projetoRepository;
    private ParticipacaoRepository participacaoRepository;

    public MenuCoordenador(UsuarioRepository usuarioRepository, ProjetoRepository projetoRepository, ParticipacaoRepository participacaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.projetoRepository = projetoRepository;
        this.participacaoRepository = participacaoRepository;
    }

    public void exibirUsuarios() throws SistemaSemUsuariosCadastradosException {
        String mensagem = "";

        for (Usuario u : usuarioRepository.listarUsuarios()) {
            mensagem += u.exibirInfo() + "\n\n";
        }

        if (mensagem.isEmpty()) {
            throw new SistemaSemUsuariosCadastradosException("Não há usuários cadastrados no sistema.");
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void exibirProjetos() throws SistemaSemProjetosCadastradosException {
        String mensagem = "";

        for (Projeto p : projetoRepository.listarProjetos()) {
            mensagem += p.exibirInfo() + "\n\n";
        }

        if (mensagem.isEmpty()) {
            throw new SistemaSemProjetosCadastradosException("Não há projetos cadastrados no sistema");
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void gerarRelatorio() {
        String relatorio = "RELATÓRIO DO SISTEMA" +
                "\n\nUsuários cadastrados: " + usuarioRepository.listarUsuarios().size() +
                "\n\nProjetos cadastrados: " + projetoRepository.listarProjetos().size() +
                "\n\nParticipações cadastradas: " + participacaoRepository.listarParticipacoes().size();

        JOptionPane.showMessageDialog(null, relatorio);
    }

    public void estatisticas() {
        int totalAlunos = 0;
        int totalProfessores = 0;
        int totalCoordenadores = 0;
        int totalProjetosAbertos = 0;
        int totalParticipacoesAtivas = 0;

        for (Usuario u : usuarioRepository.listarUsuarios()) {
            if (u instanceof Aluno) {
                totalAlunos++;
            } else if (u instanceof Professor) {
                totalProfessores++;
            } else if (u instanceof Coordenador) {
                totalCoordenadores++;
            }
        }
        for (Projeto p : projetoRepository.listarProjetos()) {
            if (p.getStatus() == StatusProjeto.ABERTO) {
                totalProjetosAbertos++;
            }
        }
        for (Participacao p : participacaoRepository.listarParticipacoes()) {
            if (p.getStatus() == StatusParticipacao.ATIVA) {
                totalParticipacoesAtivas++;
            }
        }
        String estatisticas = "ESTATÍSTICAS GERAIS" +
                "\n\nAlunos: " + totalAlunos +
                "\nProfessores: " + totalProfessores +
                "\nCoordenadores: " + totalCoordenadores +
                "\nProjetos abertos: " + totalProjetosAbertos +
                "\nParticipações ativas: " + totalParticipacoesAtivas;

        JOptionPane.showMessageDialog(null, estatisticas);
    }
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
                    try {
                        exibirProjetos();
                    } catch (SistemaSemProjetosCadastradosException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        exibirUsuarios();
                    } catch (SistemaSemUsuariosCadastradosException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                case 3:
                    gerarRelatorio();
                    break;

                case 4:
                    estatisticas();
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
