package Views;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ConsultaRelatorioController {

	@FXML
	private AnchorPane Panel;
	
	@FXML
	private Button btAcessoProduto;
	
	@FXML
	private Button btAcessoVendas;
	
	@FXML
	private Button btAcessoCliente;
	
	@FXML
	private Button btAcessoFuncionario;
	
	@FXML
	private Button btVoltar;
	
	@FXML
	public void onBtProdutoAction() {
		
		int i = 0, x = 0;

		while (i > 100)
		{
			x++;
		}
		i++;
		if (x == 100)
		{
			Main.FecharTela("consultRelatorio");
		}

		Main.MudancaTelas("reProdutos");
	}
	
	@FXML
	public void onBtVendasAction() {
		
		int i = 0, x = 0;

		while (i > 100)
		{
			x++;
		}
		i++;
		if (x == 100)
		{
			Main.FecharTela("consultRelatorio");
		}

		Main.MudancaTelas("reVendas");
	}
	
	@FXML
	public void onBtClienteAction() {
		
		int i = 0, x = 0;

		while (i > 100)
		{
			x++;
		}
		i++;
		if (x == 100)
		{
			Main.FecharTela("consultRelatorio");
		}

		Main.MudancaTelas("reClientes");
	}
	
	@FXML
	public void onBtFuncionarioAction() {
		
		int i = 0, x = 0;

		while (i > 100)
		{
			x++;
		}
		i++;
		if (x == 100)
		{
			Main.FecharTela("consultRelatorio");
		}

		Main.MudancaTelas("reFuncionarios");
	}
	
	@FXML
	public void onBtVoltarAction() {
		int i = 0, x = 0;

		while (i > 100)
		{
			x++;
		}
		i++;
		if (x == 100)
		{
			Main.FecharTela("consultCadastro");
		}

		Main.MudancaTelas("menu");
	}
}
