package br.com.pagamentos.test;

import org.junit.Test;

import br.com.pagamentos.model.Pessoa;

public class CriaPessoas {
	
	@Test
	public void salvar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Teste 3");
	}

}
