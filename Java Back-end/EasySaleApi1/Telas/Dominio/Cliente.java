package Dominio;



public class Cliente {
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	
	public String getdT_Nascimento() {
		return dT_Nascimento;
	}
	public void setdT_Nascimento(String DT_Nascimento) {
		this.dT_Nascimento = DT_Nascimento;
	}
	
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	public String getEndereco() {
		return Endereco;
	}
	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	private Integer id;
	private String nome;
	private String email;
	public String dT_Nascimento;
	private String Bairro;
	private String Endereco;
	
}
