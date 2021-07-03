package Dominio;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ConsultaClienteTabela {
	
	private final SimpleIntegerProperty idCliente;
	private final SimpleStringProperty nomeCliente;
	
	public ConsultaClienteTabela(Integer id, String nome) {
		
		super();
		
		this.idCliente = new SimpleIntegerProperty(id);
		this.nomeCliente = new SimpleStringProperty(nome);
	}
	
	public Integer getId() {
		return idCliente.get();
	}
	
	public String getNome() {
		return nomeCliente.get();
	}
}
