package br.com.pagamentos.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.pagamentos.model.Pessoa;
import br.com.pagamentos.repository.PessoasRepository;
import br.com.pagamentos.util.Transactional;

public class CadastroPessoaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoasRepository pessoasRepository;
	
	@Transactional
	public void salvar(Pessoa pessoa) throws NegocioException {
		if (pessoa.getNome() == null || pessoa.getNome().trim().equals("")) {
			throw new NegocioException("Por favor, preencha o campo nome.");
		}
		
		this.pessoasRepository.salvarOuAtualizar(pessoa);
	}
	
	@Transactional
	public void excluir(Pessoa pessoa) throws NegocioException {
		pessoa = this.pessoasRepository.porId(pessoa.getId());
		
		if (pessoa.getId() != null) {
			this.pessoasRepository.excluir(pessoa);
		}
	}
}
