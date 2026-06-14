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
import br.ufc.poo.exception.ProjetoInexistenteException;
import br.ufc.poo.exception.UsuarioNaoEncontradoException;

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

    public void removerProjeto() throws SistemaSemProjetosCadastradosException, ProjetoInexistenteException {
        if (projetoRepository.listarProjetos().isEmpty()) {
            throw new SistemaSemProjetosCadastradosException("Não há projetos cadastrados no sistema.");
        }

        String titulo = JOptionPane.showInputDialog("Digite o título do projeto:");

        Projeto projetoRemover = null;

        for (Projeto p : projetoRepository.listarProjetos()) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                projetoRemover = p;
                break;
            }
        }

        if (projetoRemover == null) {
            throw new ProjetoInexistenteException("Projeto não encontrado");
        }

        projetoRepository.removerProjeto(projetoRemover);
        JOptionPane.showMessageDialog(null, "Projeto removido com sucesso.");
    }

    public void gerenciarProjetos() {
        int opcao;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("GERENCIAR PROJETOS" +
                    "\n\n1 - Listar projetos" +
                    "\n2 - Remover Projeto" +
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
                        removerProjeto();
                    } catch (SistemaSemProjetosCadastradosException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (ProjetoInexistenteException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo do menu GERENCIAR PROJETOS...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (opcao != 0);
    }

    public void removerUsuario() throws SistemaSemUsuariosCadastradosException, UsuarioNaoEncontradoException {
        if (usuarioRepository.listarUsuarios().isEmpty()) {
            throw new SistemaSemUsuariosCadastradosException("Não há usuários cadastrados no sistema");
        }

        String email = JOptionPane.showInputDialog("Digite o email do usuário que deseja remover");

        Usuario usuarioRemover = null;

        for (Usuario u : usuarioRepository.listarUsuarios()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                usuarioRemover = u;
                break;
            }
        }

        if (usuarioRemover == null) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }

        usuarioRepository.removerUsuario(usuarioRemover);
        JOptionPane.showMessageDialog(null, "Usuario removido com sucesso!");
    }

    public void gerenciarUsuarios() {
        int opcao;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("GERENCIAR USUÁRIOS" +
                    "\n\n1 - Listar usuários" +
                    "\n2 - Remover usuário" +
                    "\n0 - Sair"));

            switch (opcao) {
                case 1:
                    try {
                        exibirUsuarios();
                    } catch (SistemaSemUsuariosCadastradosException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        removerUsuario();
                    } catch (SistemaSemUsuariosCadastradosException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (UsuarioNaoEncontradoException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo do menu GERENCIAR USUÁRIOS...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        } while (opcao != 0);
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
                    gerenciarProjetos();
                    break;

                case 2:
                    gerenciarUsuarios();
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
