package br.com.pagamentos.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pagamentos.model.Lancamento;
import br.com.pagamentos.repository.LancamentosRepository;
import br.com.pagamentos.service.CadastroLancamentoService;
import br.com.pagamentos.service.NegocioException;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LancamentosRepository lancamentosRepository;
	
	@Inject
	private CadastroLancamentoService cadastroLancamentoService;
	
	private List<Lancamento> lancamentos;
	private Lancamento lancamentoSelecionado;
	
	public void consultar() {
		this.lancamentos = this.lancamentosRepository.buscarTodos();
	}
	
	public void excluir() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try {
			this.cadastroLancamentoService.excluir(lancamentoSelecionado);
			this.consultar();
			
			facesContext.addMessage(null, new FacesMessage(
					"Lançamento " + lancamentoSelecionado.getDescricao() + " excluído com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, mensagem);
		}
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}
	
	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

}
