package classe.core;

import java.util.List;

public class UsuarioBo {
	private UsuarioDao dao = new UsuarioDao();

	public boolean validar(Usuario usuario) {
		for (Usuario u : dao.listar()) {
			if (u.getNomeUsuario().equals(usuario.getNomeUsuario())
					&& u.getSenha().equals(usuario.getSenha())) {
				return true;
			}
		}
		return false;
	}
	public void adicionar(Usuario usuario) {
			dao.adicionar(usuario);
		}
		
		
		public List<Usuario> listar() {
			return dao.listar();
		}

	}


