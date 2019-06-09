package br.com.pagamentos.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pagamentos.model.Lancamento;
import br.com.pagamentos.model.Pessoa;
import br.com.pagamentos.model.TipoLancamento;
import br.com.pagamentos.repository.LancamentosRepository;
import br.com.pagamentos.repository.PessoasRepository;
import br.com.pagamentos.service.CadastroLancamentoService;
import br.com.pagamentos.service.NegocioException;

@Named
@ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentoService cadastroLancamentoService;
	
	@Inject
	private LancamentosRepository lancamentosRepository;
	
	@Inject
	private PessoasRepository pessoasRepository;
	
	private List<Pessoa> pessoas;
	
	private Lancamento lancamento;
	
	public CadastroLancamentoBean() {
		this.limpar();
	}
	
	public void prepararCadastro() {
		this.pessoas = this.pessoasRepository.buscarTodas();
		
		if (lancamento == null) {
			this.limpar();
		}
	}
	
	public void salvar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try {
			this.cadastroLancamentoService.salvar(lancamento);
			
			this.limpar();
			facesContext.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			facesContext.addMessage(null, mensagem);
		}
	}
	
	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentosRepository.descricoesQueContem(descricao);
	}
	
	private void limpar() {
		this.lancamento = new Lancamento();
	}
	
	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

}
