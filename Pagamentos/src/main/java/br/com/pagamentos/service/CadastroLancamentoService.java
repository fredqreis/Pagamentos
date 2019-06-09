package br.com.pagamentos.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.pagamentos.model.Lancamento;
import br.com.pagamentos.repository.LancamentosRepository;
import br.com.pagamentos.util.Transactional;

public class CadastroLancamentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LancamentosRepository lancamentosRepository;
	
	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new NegocioException("Por favor, preencha a descrição.");
		}
		
		if (lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException("Data de pagamento não pode ser uma data futura.");
		}
		
		this.lancamentosRepository.salvarOuAtualizar(lancamento);
	}
	
	@Transactional
	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentosRepository.porId(lancamento.getId());
		
		if (lancamento.getDataPagamento() != null) {
			throw new NegocioException("Não é possível excluir um lançamento pago!");
		}
		
		this.lancamentosRepository.excluir(lancamento);
	}

}
