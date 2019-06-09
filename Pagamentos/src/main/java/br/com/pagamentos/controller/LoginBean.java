package br.com.pagamentos.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBean usuario;

	private String nomeUsuario;
	private String senha;
	
	public String login() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		if ("admin".equals(this.nomeUsuario) && "admin".equals(this.senha)) {
			this.usuario.setNome(this.nomeUsuario);
			this.usuario.setDataLogin(new Date());
			
			return "/lancamentos/ConsultaLancamentos?faces-redirect=true";
		} else {
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, mensagem);
		}
		
		return null;
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}
	
	public String getMensagemDeSaudacao() {
		Calendar calendar = Calendar.getInstance();
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		
		String retorno = null;
		
		if (hora > 6 && hora < 12) {
			retorno = "Bom dia";
		} else if (hora > 12 && hora < 18) {
			retorno = "Boa tarde";
		} else {
			retorno = "Boa noite";
		}
		return retorno;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
