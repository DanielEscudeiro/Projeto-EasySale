package Dominio;

import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.StringConverter;

public class FuncionarioTabela {
	
	private final SimpleIntegerProperty idFuncionario;
	private final SimpleStringProperty nomeFuncionario;
	private final SimpleStringProperty emailFuncionario;
	private final SimpleStringProperty telefoneFuncionario;
	private final SimpleStringProperty dtNascimentoFuncionario;
	private final SimpleStringProperty enderecoFuncionario;
	private final SimpleStringProperty bairroFuncionario;
	
	
	public FuncionarioTabela(Integer id, String nome, String endereco, String bairro, String email, String telefone, String DT_Nascimento) {
		
		//super();
		
		this.idFuncionario = new SimpleIntegerProperty(id);
		this.nomeFuncionario = new SimpleStringProperty(nome);
		this.enderecoFuncionario = new SimpleStringProperty(endereco);
		this.bairroFuncionario = new SimpleStringProperty(bairro);
		this.emailFuncionario = new SimpleStringProperty(email);
		this.telefoneFuncionario = new SimpleStringProperty(telefone);
		this.dtNascimentoFuncionario = new SimpleStringProperty(DT_Nascimento);
		
		
	}
	
	public Integer getId() {
		return idFuncionario.get();
	}
	
	public String getNome() {
		return nomeFuncionario.get();
	}
	
	public String getEmail() {
		return emailFuncionario.get();
	}
	
	public String getTelefone(){
		return telefoneFuncionario.get();
	}

	public String getDT_Nascimento() {
		return dtNascimentoFuncionario.get();
	}
	
	public String getEndereco() {
		return enderecoFuncionario.get();
	}
	
	public String getBairro() {
		return bairroFuncionario.get();
	}
}
