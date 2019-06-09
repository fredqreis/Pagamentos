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
public class ConsultaPessoasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoasRepository pessoasRepository;
	
	@Inject
	private CadastroPessoaService cadastroPessoaService;
	
	private List<Pessoa> pessoas;
	private Pessoa pessoaSelecionada;
	
	public void consultar() {
		this.pessoas = pessoasRepository.buscarTodas();
	}
	
	public void excluir() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try {
			this.cadastroPessoaService.excluir(pessoaSelecionada);
			this.consultar();
				
			facesContext.addMessage(null, new FacesMessage(
					"Pessoa " + pessoaSelecionada.getNome() + " excluída com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, mensagem);
		}
	} 
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}
	
	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
}
