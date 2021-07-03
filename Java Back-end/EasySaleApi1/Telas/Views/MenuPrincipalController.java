package Views;

import java.net.URL;
import java.util.ResourceBundle;

import Dominio.Alterando;
import Dominio.UsuarioNome;
import javafx.fxml.Initializable;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MenuPrincipalController {
	
	@FXML
	private AnchorPane Panel;
	
	@FXML
	public  Label lbUsuario;
	
	@FXML
	private Button btSair;
	
	
	@FXML
	public void onBtCadastroAction() {
		
		Main.MudancaTelas("consultCadastro");
	}
	
	@FXML
	public void onBtVendaAction() {
		Alterando.setAlterando(false);
		Main.MudancaTelas("Venda");
	
	}
	
	@FXML
	public void onBtRelatorioAction() {

		Main.MudancaTelas("consultRelatorio");
	}
	
	@FXML
	public void onBtSairAction(){

		Alerts.responseAlert("EasySale", null, "Você tem certeza que deseja sair do EasySale? ", AlertType.WARNING, "menu", "Sim", "Não");
	}
	
	@FXML
	public void onMouseMovedAction() {
		lbUsuario.setText("Olá, " + UsuarioNome.getNome() + ", seja bem-vindo!");	
		}
}
