package br.ufc.poo.view;

import javax.swing.JOptionPane;

import br.ufc.poo.repository.UsuarioRepository;
import br.ufc.poo.model.Aluno;
import br.ufc.poo.model.Professor;
import br.ufc.poo.model.Coordenador;
import br.ufc.poo.model.Usuario;

public class MenuPrincipal {

    private UsuarioRepository usuarioRepository;

    public MenuPrincipal(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private void login() {
        String email = JOptionPane.showInputDialog("Email:");
        String senha = JOptionPane.showInputDialog("Senha:");

        Usuario usuario = usuarioRepository.buscarPorEmail(email);

        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado");
            return;
        }

        if (!usuario.getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(null, "Senha incorreta");
            return;
        }
        JOptionPane.showMessageDialog(null, "Login realizado com sucesso");
    }

    private void cadastrarAluno() {
        String nome = JOptionPane.showInputDialog("Nome:");
        String email = JOptionPane.showInputDialog("Email:");
        String senha = JOptionPane.showInputDialog("Senha:");
        int matricula = Integer.parseInt(JOptionPane.showInputDialog("Matricula"));

        Aluno aluno = new Aluno(nome, email, senha, matricula);
        usuarioRepository.adicionarUsuario(aluno);

        JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
    }

    private void cadastrarProfessor() {
        String nome = JOptionPane.showInputDialog("Nome:");
        String email = JOptionPane.showInputDialog("Email:");
        String senha = JOptionPane.showInputDialog("Senha:");
        String areaAtuacao = JOptionPane.showInputDialog("Área de atuação:");

        Professor professor = new Professor(nome, email, senha, areaAtuacao);
        usuarioRepository.adicionarUsuario(professor);

        JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso");
    }

    private void cadastrarCoordenador() {
        String nome = JOptionPane.showInputDialog("Nome:");
        String email = JOptionPane.showInputDialog("Email:");
        String senha = JOptionPane.showInputDialog("Senha:");
        String curso = JOptionPane.showInputDialog("Curso:");

        Coordenador coordenador = new Coordenador(nome, email, senha, curso);
        usuarioRepository.adicionarUsuario(coordenador);

        JOptionPane.showMessageDialog(null, "Coordenador cadastrado com sucesso");
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
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                    }
            }
        } while (opcao != 0);
    }
}
