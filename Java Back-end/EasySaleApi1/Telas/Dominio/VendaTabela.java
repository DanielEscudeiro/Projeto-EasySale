package Dominio;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class VendaTabela {
	
	private final SimpleStringProperty idVenda;
	private final SimpleStringProperty descricaoVenda;
	private final SimpleStringProperty valorUnitarioVenda;
	private final SimpleStringProperty quantidadeVenda;
	private final SimpleStringProperty valorTotalVenda;

	
	public VendaTabela(String id, String descricao, String valorUnitario, String quantidade, String valorTotal) {
		
		super();
		
		this.idVenda = new SimpleStringProperty(id);
		this.descricaoVenda = new SimpleStringProperty(descricao);
		this.valorUnitarioVenda = new SimpleStringProperty(valorUnitario);
		this.quantidadeVenda = new SimpleStringProperty(quantidade);
		this.valorTotalVenda = new SimpleStringProperty(valorTotal);
	}


	public String getId() {
		return idVenda.get();
	}


	public String getDescricao() {
		return descricaoVenda.get();
	}


	public String getValor() {
		return valorUnitarioVenda.get();
	}


	public String getQuantidade() {
		return quantidadeVenda.get();
	}


	public String getValorTotal() {
		return valorTotalVenda.get();
	}
}
