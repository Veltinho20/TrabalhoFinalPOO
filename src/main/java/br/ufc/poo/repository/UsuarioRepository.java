package br.ufc.poo.repository;

import java.util.ArrayList;
import br.ufc.poo.model.Usuario;

public class UsuarioRepository {

    private ArrayList<Usuario> usuarios;

    public UsuarioRepository() {
        this.usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario buscarPorEmail(String email) {

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
}
