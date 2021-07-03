package Dominio;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClienteTabela {
	
	private final SimpleIntegerProperty idCliente;
	private final SimpleStringProperty nomeCliente;
	private final SimpleStringProperty enderecoCliente;
	private final SimpleStringProperty bairroCliente;
	private final SimpleStringProperty emailCliente;
	private final SimpleStringProperty dtNascimentoCliente;
	
	public ClienteTabela(Integer id, String nome, String endereco, String bairro, String email, String DT_Nascimento) {
		
		super();
		
		this.idCliente = new SimpleIntegerProperty(id);
		this.nomeCliente = new SimpleStringProperty(nome);
		this.enderecoCliente = new SimpleStringProperty(endereco);
		this.bairroCliente = new SimpleStringProperty(bairro);
		this.emailCliente = new SimpleStringProperty(email);
		this.dtNascimentoCliente = new SimpleStringProperty(DT_Nascimento);
	}
	
	public Integer getId() {
		return idCliente.get();
	}
	
	public String getNome() {
		return nomeCliente.get();
	}
	
	public String getEndereco() {
		return enderecoCliente.get();
	}
	
	public String getBairro() {
		return bairroCliente.get();
	}
	
	public String getEmail() {
		return emailCliente.get();
	}
	
	public String getDT_Nascimento() {
		return dtNascimentoCliente.get();
	}
}
