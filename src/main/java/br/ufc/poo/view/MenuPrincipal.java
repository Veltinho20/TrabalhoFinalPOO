package br.ufc.poo.view;

import javax.swing.JOptionPane;

import br.ufc.poo.repository.UsuarioRepository;
import br.ufc.poo.model.Aluno;
import br.ufc.poo.model.Professor;
import br.ufc.poo.model.Coordenador;
import br.ufc.poo.model.Usuario;
import br.ufc.poo.repository.ProjetoRepository;
import br.ufc.poo.repository.ParticipacaoRepository;
import br.ufc.poo.repository.NotificacaoRepository;

public class MenuPrincipal {

    private UsuarioRepository usuarioRepository;
    private ProjetoRepository projetoRepository;
    private ParticipacaoRepository participacaoRepository;
    private NotificacaoRepository notificacaoRepository;


    public MenuPrincipal(UsuarioRepository usuarioRepository, ProjetoRepository projetoRepository, ParticipacaoRepository participacaoRepository, NotificacaoRepository notificacaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.projetoRepository = projetoRepository;
        this.participacaoRepository = participacaoRepository;
        this.notificacaoRepository = notificacaoRepository;
    }

    private void login() {
        String email = JOptionPane.showInputDialog("Email:");
        String senha = JOptionPane.showInputDialog("Senha:");

        Usuario usuario = usuarioRepository.buscarPorEmail(email);

        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            return;
        }

        if (!usuario.getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(null, "Senha incorreta.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Login realizado com sucesso.");

        if (usuario instanceof Aluno) {
            new MenuAluno((Aluno) usuario, projetoRepository, participacaoRepository, notificacaoRepository).exibirMenu();
        } else if (usuario instanceof Professor) {
            new MenuProfessor((Professor) usuario, projetoRepository, participacaoRepository, notificacaoRepository).exibirMenu();
        } else if (usuario instanceof Coordenador) {
            new MenuCoordenador(usuarioRepository, projetoRepository, participacaoRepository).exibirMenu();
        }
    }

    private void cadastrarAluno() {
        String nome = JOptionPane.showInputDialog("Nome:");
        String email = JOptionPane.showInputDialog("Email:");
        String senha = JOptionPane.showInputDialog("Senha:");
        int matricula = Integer.parseInt(JOptionPane.showInputDialog("Matrícula:"));

        Aluno aluno = new Aluno(nome, email, senha, matricula);
        usuarioRepository.adicionarUsuario(aluno);

        JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso.");
    }

    private void cadastrarProfessor() {
        String nome = JOptionPane.showInputDialog("Nome:");
        String email = JOptionPane.showInputDialog("Email:");
        String senha = JOptionPane.showInputDialog("Senha:");
        String areaAtuacao = JOptionPane.showInputDialog("Área de atuação:");

        Professor professor = new Professor(nome, email, senha, areaAtuacao);
        usuarioRepository.adicionarUsuario(professor);

        JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso.");
    }

    private void cadastrarCoordenador() {
        String nome = JOptionPane.showInputDialog("Nome:");
        String email = JOptionPane.showInputDialog("Email:");
        String senha = JOptionPane.showInputDialog("Senha:");
        String curso = JOptionPane.showInputDialog("Curso:");

        Coordenador coordenador = new Coordenador(nome, email, senha, curso);
        usuarioRepository.adicionarUsuario(coordenador);

        JOptionPane.showMessageDialog(null, "Coordenador cadastrado com sucesso.");
    }

    public void exibirMenu() {

        int opcao;

        do {

            opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU PRINCIPAL" +
                    "\n1 - Login" +
                    "\n2 - Cadastrar aluno" +
                    "\n3 - Cadastrar Professor" +
                    "\n4 - Cadastrar Coordenador" +
                    "\n0 - Sair"));

            switch (opcao) {

                case 1:
                    login();
                    break;

                case 2:
                    cadastrarAluno();
                   break;

                case 3:
                    cadastrarProfessor();
                    break;

                case 4:
                    cadastrarCoordenador();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Encerrando o sistema...");
                    break;

                default:
                    if (opcao != 0) {
                        JOptionPane.showMessageDialog(null, "Opção inválida.");
                    }
            }
        } while (opcao != 0);
    }
}
