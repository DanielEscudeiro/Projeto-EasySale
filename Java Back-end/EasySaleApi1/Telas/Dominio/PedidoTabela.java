package Dominio;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PedidoTabela {

	private final SimpleIntegerProperty Nr_Pedido;
	private final SimpleStringProperty dt_pedido;
	private final SimpleIntegerProperty Id_Cliente ;
	private final SimpleStringProperty nome_cliente;



	public PedidoTabela(int Nr_pedido, String dt_pedido, int Id_Cliente, String nome_cliente) {

		super();

		this.Nr_Pedido = new SimpleIntegerProperty(Nr_pedido);
		this.dt_pedido = new SimpleStringProperty(dt_pedido);
		this.Id_Cliente = new SimpleIntegerProperty(Id_Cliente);
		this.nome_cliente = new SimpleStringProperty(nome_cliente);
	}


	public int getNr_Pedido() {
		return Nr_Pedido.get();
	}


	public String getDt_Pedido() {
		return dt_pedido.get();
	}


	public int getId_Cliente() {
		return Id_Cliente.get();
	}


	public String getNome_Cliente() {
		return nome_cliente.get();
	}

}
