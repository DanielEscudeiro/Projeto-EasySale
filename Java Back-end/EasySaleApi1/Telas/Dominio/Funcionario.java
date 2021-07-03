package Dominio;

public class Funcionario {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getdT_Nascimento() {
		return dT_Nascimento;
	}
	public void setdT_Nascimento(String DT_Nascimento) {
		this.dT_Nascimento = DT_Nascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	private int id;
	private String nome;
	private String email;
	private String telefone;
	private String dT_Nascimento;
	private String endereco;
	private String bairro;
	
}
