package Dominio;

public class Itens_Venda {
	 public int Nr_Pedido;
     public int Id_Produto;
     public String Descricao; 
     public int Quantidade;
     public String Valor_Total;
     
	public int getNr_Pedido() {
		return Nr_Pedido;
	}
	public void setNr_Pedido(int nr_Pedido) {
		Nr_Pedido = nr_Pedido;
	}
	public int getId_Produto() {
		return Id_Produto;
	}
	public void setId_Produto(int id_Produto) {
		Id_Produto = id_Produto;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public int getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	public String getValor_Total() {
		return Valor_Total;
	}
	public void setValor_Total(String Valor_Total) {
		this.Valor_Total = Valor_Total;
	} 
     
     

}
