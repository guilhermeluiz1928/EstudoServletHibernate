package br.com.gui.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.gui.model.Cliente;

public class CrudCliente {
	
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("treinamento"); 

	public String inserirCliente(Cliente cliente) {

		String retorno = null;
		
		EntityManager entity = factory.createEntityManager();
		
		entity.getTransaction().begin();
		entity.persist(cliente);
		entity.getTransaction().commit();
		entity.close();
		
		if(cliente.getId()==null) {
			retorno="nao";
		}else {
			retorno="sim";
		}
		
		return retorno;

	}
	
	public String alterarCliente(Cliente cliente) {

		String retorno = null;

		EntityManager entity = factory.createEntityManager();
		
		entity.getTransaction().begin();
		entity.merge(cliente);
		entity.getTransaction().commit();
		entity.close();
		
		if(cliente.getId()==null) {
			retorno="nao";
		}else {
			retorno="sim";
		}
		
		return retorno;

	}
	
	public String excluirCliente(Long id) {

		String retorno = null;
		
		EntityManager entity = factory.createEntityManager();
		
		Cliente clienteExc = entity.find(Cliente.class, id);
		
		entity.getTransaction().begin();
		entity.remove(clienteExc);
		entity.getTransaction().commit();
		entity.close();
		
		if(clienteExc.getId()==null) {
			retorno="sim";
		} else {
			retorno="nao";
		}
		
		return retorno;

	}
	
	public List<Cliente> listarClientes() {

		EntityManager entity = factory.createEntityManager();
		
		//com NativeQuery eh um sql convencional
		//Query query = entity.createNativeQuery("SELECT * FROM TGFPAR WHERE CODPARC>=:codparc", Cliente.class);
		//query.setParameter("codparc", 1L);
		
		Query query = entity.createQuery("from Cliente c");
		
		List<Cliente> lista = query.getResultList();
		
		entity.close();
		
		return lista;

	}

}
