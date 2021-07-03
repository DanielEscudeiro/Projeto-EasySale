package Views;

import Dominio.Alterando;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ConsultaCadastroController {
	
	@FXML
	private AnchorPane Panel;
	
	@FXML
	private Button btAcessoProduto;
	
	@FXML
	private Button btAcessoCliente;
	
	@FXML
	private Button btAcessoFuncionario;
	
	@FXML
	private Button btVoltar;
	
	@FXML
	public void onBtProdutoAction() {
		Alterando.setAlterando(false);
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

		Main.MudancaTelas("CadastroProduto");
	}
	
	@FXML
	public void onBtClienteAction() {
		Alterando.setAlterando(false);
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

		Main.MudancaTelas("CadastroCliente");
	}
	
	@FXML
	public void onBtFuncionarioAction() {
		
		int i = 0, x = 0;
		Alterando.setAlterando(false);

		while (i > 100)
		{
			x++;
		}
		i++;
		if (x == 100)
		{
			Main.FecharTela("consultCadastro");
		}

		Main.MudancaTelas("CadastroFuncionario");
	}
	
	@FXML
	public void onBtVoltarAction() {
		int i = 0, x = 0;
		Alterando.setAlterando(false);

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
