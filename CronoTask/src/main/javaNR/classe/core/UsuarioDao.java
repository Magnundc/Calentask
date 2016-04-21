package classe.core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioDao {
	private EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("usuario-persistence-unit");
	private EntityManager em = emf.createEntityManager();

	public void adicionar(Usuario usuario) {
		try {
			em.getTransaction().begin();

			em.persist(usuario);

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (em != null) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}

	public void remover(Usuario usuario) {
		em.getTransaction().begin();
		em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
		em.getTransaction().commit();
	}

	public void editar(Usuario usuario, Usuario usuarioAnterior) {
		remover(usuarioAnterior);
		adicionar(usuario);
	}

	
	public List<Usuario> listar() {
		List<Usuario> result = new ArrayList<Usuario>();
		try {
			String jpql = "from Usuario";
			result = JpaUtil.getEntityManager()
					.createQuery(jpql, Usuario.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JpaUtil.closeEntityManager();
		}
		return result;
	}
}
