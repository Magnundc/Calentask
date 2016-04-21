package Servlet.Pagnias;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classe.core.Usuario;
import classe.core.UsuarioBo;

/**
 * Servlet implementation class NovoUsuario
 */

@WebServlet(value = "/novoUsuario")
public class NovoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String acaoParam = req.getParameter("acao");
		String acao = acaoParam == null ? "" : acaoParam;

		Usuario usuario = getUsuario(req);

		UsuarioBo bo = new UsuarioBo();

		if (acao.equals("Enviar")) {
			if (!usuario.getNomeUsuario().equals("")) {
				bo.adicionar(usuario);
			} else {
				req.getRequestDispatcher("sistema/500.jsp").forward(req,
						resp);
			}
		}

		if (acao.equals("Limpar")) {
			req.setAttribute("nome", "");
			req.setAttribute("nivelCurso", "");
			req.setAttribute("endereco", "");
			req.setAttribute("telefone", "");
			req.setAttribute("email", "");
			req.setAttribute("nomeResponsavel", "");
			req.setAttribute("telefoneResponsavel", "");
			req.setAttribute("cnpj", "");
		}

		// Referência para a sessão.
		HttpSession sessao = req.getSession();
		req.getRequestDispatcher("sistema/novaInstituicao.jsp").forward(req,
				resp);

	}

	private Usuario getUsuario(HttpServletRequest req) {
		String nomeUsuarioParam = req.getParameter("nome");
		String senhaParam = req.getParameter("senha");
		String emailParam = req.getParameter("email");
		
		String nome = nomeUsuarioParam == null ? "" : nomeUsuarioParam;
		String senha = senhaParam == null ? "" : senhaParam;
		String email = emailParam == null ? "" : emailParam;
		
		return new Usuario(nome, senha, email);

	}

}