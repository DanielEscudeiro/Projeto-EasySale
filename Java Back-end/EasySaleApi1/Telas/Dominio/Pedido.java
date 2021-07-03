package Dominio;

import java.util.ArrayList;

public class Pedido {
	public int Nr_Pedido;
	public int getNr_Pedido() {
		return Nr_Pedido;
	}
	public void setNr_Pedido(int Nr_Pedido) {
		this.Nr_Pedido = Nr_Pedido;
	}
	public String getdT_Pedido() {
		return dT_Pedido;
	}
	public void setdT_Pedido(String dT_Pedido) {
		this.dT_Pedido = dT_Pedido;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ArrayList<pedido_item> getItens() {
		return itens;
	}
	public void setItens(ArrayList<pedido_item> itens) {
		this.itens = itens;
	}
	public String dT_Pedido;	
	public String tipo;
	public Cliente cliente; 
	public ArrayList<pedido_item> itens; 

	


}
