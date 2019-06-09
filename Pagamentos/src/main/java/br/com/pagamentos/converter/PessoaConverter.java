package br.com.pagamentos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.pagamentos.model.Pessoa;
import br.com.pagamentos.repository.PessoasRepository;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {

	@Inject
	private PessoasRepository pessoasRepository;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.pessoasRepository.porId(new Long(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pessoa pessoa = ((Pessoa) value);
			return pessoa.getId() == null ? null : pessoa.getId().toString();
		}
		
		return null;
	}

}
