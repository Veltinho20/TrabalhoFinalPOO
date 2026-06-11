package br.ufc.poo.view;

import javax.swing.JOptionPane;
import br.ufc.poo.repository.UsuarioRepository;
import br.ufc.poo.model.Usuario;
import br.ufc.poo.repository.ProjetoRepository;
import br.ufc.poo.model.Projeto;

public class MenuCoordenador {

    private UsuarioRepository usuarioRepository;
    private ProjetoRepository projetoRepository;

    public MenuCoordenador(UsuarioRepository usuarioRepository, ProjetoRepository projetoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.projetoRepository = projetoRepository;
    }

    public void exibirUsuarios() {
        String mensagem = "";

        for (Usuario u : usuarioRepository.listarUsuarios()) {
            mensagem += u.exibirInfo() + "\n\n";
        }

        if (mensagem.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há usuários cadastrados");
            return;
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void exibirProjetos() {
        String mensagem = "";

        for (Projeto p : projetoRepository.listarProjetos()) {
            mensagem += p.exibirInfo() + "\n\n";
        }

        if (mensagem.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há projetos cadastrados");
            return;
        }
        JOptionPane.showMessageDialog(null, mensagem);
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
                    exibirProjetos();
                    break;

                case 2:
                    exibirUsuarios();
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Funcionalidade não implementada");
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Funcionalidade não implementada");
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
