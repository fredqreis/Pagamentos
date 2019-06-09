package br.com.pagamentos.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pagamentos.model.Pessoa;
import br.com.pagamentos.repository.PessoasRepository;
import br.com.pagamentos.service.CadastroPessoaService;
import br.com.pagamentos.service.NegocioException;

@Named
@ViewScoped
public class CadastroPessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPessoaService cadastroPessoaService;
	
	@Inject
	private PessoasRepository pessoasRepository;
	
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	
	public CadastroPessoaBean() {
		this.limpar();
	}
	
	public void prepararCadastro() {
		this.pessoas = this.pessoasRepository.buscarTodas();
		
		if (pessoa == null) {
			this.limpar();
		}
	}
	
	public void salvar() throws NegocioException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try {
			this.cadastroPessoaService.salvar(pessoa);
		
			this.limpar();
			facesContext.addMessage(null, new FacesMessage("Pessoa salva com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			facesContext.addMessage(null, mensagem);
		}
	}
	
	private void limpar() {
		this.pessoa = new Pessoa();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getTodasPessoas() {
		return pessoas;
	}

}
