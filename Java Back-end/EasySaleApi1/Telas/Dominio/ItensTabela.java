package Dominio;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItensTabela {
	
	private final SimpleIntegerProperty idProduto;
	private final SimpleStringProperty descricaoProduto;
	private final SimpleStringProperty valorUnitarioProduto;
	private final SimpleIntegerProperty quantidadeProduto;
	private final SimpleStringProperty valorTotalProduto;
	
	public ItensTabela(Integer id, String descricao, String valorUnitario, Integer quantidade, String valorTotal) {
		
		super();
		
		this.idProduto = new SimpleIntegerProperty(id);
		this.descricaoProduto = new SimpleStringProperty(descricao);
		this.valorUnitarioProduto = new SimpleStringProperty(valorUnitario);
		this.quantidadeProduto = new SimpleIntegerProperty(quantidade);
		this.valorTotalProduto = new SimpleStringProperty(valorTotal);
	}
	
	public Integer getId_Produto() {
		return idProduto.get();
	}


	public String getDescricao() {
		return descricaoProduto.get();
	}


	public String getValor_Unitario() {
		return valorUnitarioProduto.get();
	}


	public Integer getQuantidade() {
		return quantidadeProduto.get();
	}


	public String getValor_Total() {
		return valorTotalProduto.get();
	}
}
