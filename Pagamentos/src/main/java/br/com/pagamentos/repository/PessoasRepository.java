package br.com.pagamentos.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.pagamentos.model.Pessoa;

public class PessoasRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public void salvarOuAtualizar(Pessoa pessoa) {
		this.em.merge(pessoa);
	}
	
	public void excluir(Pessoa pessoa) {
		this.em.remove(pessoa);
	}
	 
	public List<Pessoa> buscarTodas() {
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa", Pessoa.class);
		
		return query.getResultList();
	}
	
	public Pessoa porId(Long id) {
		return this.em.find(Pessoa.class, id);
	}
}
