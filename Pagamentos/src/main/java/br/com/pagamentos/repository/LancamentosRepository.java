package br.com.pagamentos.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.pagamentos.model.Lancamento;

public class LancamentosRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public void salvarOuAtualizar(Lancamento lancamento) {
		this.em.merge(lancamento);
	}
	
	public void excluir(Lancamento lancamento) {
		this.em.remove(lancamento);
	}
	
	public List<Lancamento> buscarTodos() {
		return this.em.createQuery("from Lancamento", Lancamento.class)
				.getResultList();
	}

	public Lancamento porId(Long id) {
		return this.em.find(Lancamento.class, id);
	}
	
	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = em.createQuery("select distinct descricao from Lancamento "
				+ "where upper(descricao) like upper(:descricao)", String.class);
				query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

}
