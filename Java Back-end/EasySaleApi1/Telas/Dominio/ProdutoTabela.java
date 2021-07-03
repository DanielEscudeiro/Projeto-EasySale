package Dominio;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProdutoTabela {

	private final SimpleIntegerProperty idProduto;
	private final SimpleStringProperty descricaoProduto;
	private final SimpleIntegerProperty quantidadeProduto;
	private final SimpleDoubleProperty valorProduto;

	public ProdutoTabela(Integer id, String descricao, Integer quantidade, double valor) {

		super();

		this.idProduto = new SimpleIntegerProperty(id);
		this.descricaoProduto = new SimpleStringProperty(descricao);
		this.quantidadeProduto = new SimpleIntegerProperty(quantidade);
		this.valorProduto = new SimpleDoubleProperty(valor);
	}

	public Integer getId() {
		return idProduto.get();
	}

	public String getDescricao() {
		return descricaoProduto.get();
	}

	public Integer getQuantidade() {
		return quantidadeProduto.get();
	}

	public double getValor() {
		return valorProduto.get();
	}
}
