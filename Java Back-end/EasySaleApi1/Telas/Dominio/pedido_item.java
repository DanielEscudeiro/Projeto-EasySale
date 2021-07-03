package Dominio;

public class pedido_item {

	public int getId_Produto() {
		return Id_Produto;
	}
	public void setId_Produto(int Id_Produto) {
		this.Id_Produto = Id_Produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getValor_Unitario() {
		return valor_Unitario;
	}
	public void setValor_Unitario(String valor_Unitario) {
		this.valor_Unitario = valor_Unitario;
	}
	private int Id;
	private int Id_Produto;
	private int quantidade;
	private String valor_Unitario;		



	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	private Produto produto;

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
}








