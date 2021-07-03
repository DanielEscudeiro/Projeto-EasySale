package application;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	
	private static Stage stage;
	private static Scene LoginCena;
	private static Scene CadastroUsuarioCena;
	private static Scene CadastroClienteCena;
	private static Scene CadastroFuncionarioCena;
	private static Scene CadastroProdutoCena;
	private static Scene VendaCena;
	private static Scene ReClientesCena;
	private static Scene ReProdutosCena;
	private static Scene ReFuncionarioCena;
	private static Scene ReVendaCena;
	private static Scene ConsultaCadastrosCena;
	private static Scene ConsultaRelatoriosCena;
	private static Scene MenuPrincipalCena;
	private static Scene ConsultaClientesCena;
	private static Scene ReItensVendaCena;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			stage = primaryStage;
			
			Parent Login = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
			LoginCena = new Scene(Login);
		
			Parent CadastroUsuario = FXMLLoader.load(getClass().getResource("/Views/CadastroUsuarios.fxml"));
			CadastroUsuarioCena = new Scene(CadastroUsuario);
			
			Parent CadastroCliente = FXMLLoader.load(getClass().getResource("/Views/CadastroClientes.fxml"));
			CadastroClienteCena = new Scene(CadastroCliente);
			
			Parent CadastroFuncionario = FXMLLoader.load(getClass().getResource("/Views/CadastroFuncionario.fxml"));
			CadastroFuncionarioCena = new Scene(CadastroFuncionario);
			
			Parent CadastroProduto = FXMLLoader.load(getClass().getResource("/Views/CadastroProdutos.fxml"));
			CadastroProdutoCena = new Scene(CadastroProduto);
		
			Parent Venda = FXMLLoader.load(getClass().getResource("/Views/Venda.fxml"));
			VendaCena = new Scene(Venda);
			
			Parent reClientes = FXMLLoader.load(getClass().getResource("/Views/RelatorioClientes.fxml"));
			ReClientesCena = new Scene(reClientes);
			
			Parent reProdutos = FXMLLoader.load(getClass().getResource("/Views/RelatorioProdutos.fxml"));
			ReProdutosCena = new Scene(reProdutos);
			
			Parent reFuncionario = FXMLLoader.load(getClass().getResource("/Views/RelatorioFuncionarios.fxml"));
			ReFuncionarioCena = new Scene(reFuncionario);
			
			Parent reVenda = FXMLLoader.load(getClass().getResource("/Views/RelatorioVendas.fxml"));
			ReVendaCena = new Scene(reVenda);
			
			Parent consultCadastro = FXMLLoader.load(getClass().getResource("/Views/ConsultaCadastro.fxml"));
			ConsultaCadastrosCena = new Scene(consultCadastro);
			
			Parent consultRelatorio = FXMLLoader.load(getClass().getResource("/Views/ConsultaRelatorio.fxml"));
			ConsultaRelatoriosCena = new Scene(consultRelatorio);
			
			Parent menu = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
			MenuPrincipalCena = new Scene(menu);
			
			Parent consultClientes =  FXMLLoader.load(getClass().getResource("/Views/ConsultaCliente.fxml"));
			ConsultaClientesCena = new Scene(consultClientes);
			
			Parent reItensVenda =  FXMLLoader.load(getClass().getResource("/Views/RelatorioItensVenda.fxml"));
			ReItensVendaCena = new Scene(reItensVenda);
			
			
			primaryStage.setScene(LoginCena);//MenuPrincipalCena);
			stage.initStyle(StageStyle.UNDECORATED); //Tirar os botoes de palco.
			
			stage.centerOnScreen();
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void MudancaTelas(String descricao) {
		
		if(descricao.equals("Login")) 
			stage.setScene(LoginCena);
			
		else if(descricao.equals("CadastroUsuario")) { 
			stage.setScene(CadastroUsuarioCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("CadastroCliente")) {
			stage.setScene(CadastroClienteCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("CadastroFuncionario")) {
			stage.setScene(CadastroFuncionarioCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("CadastroProduto")) {
			stage.setScene(CadastroProdutoCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("Venda")) {
			stage.setScene(VendaCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("reClientes")) {
			stage.setScene(ReClientesCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("reProdutos")) {
			stage.setScene(ReProdutosCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("reFuncionarios")) {
			stage.setScene(ReFuncionarioCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("reVendas")) {
			stage.setScene(ReVendaCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("consultCadastro")) {
			stage.setScene(ConsultaCadastrosCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("consultRelatorio")) {
			stage.setScene(ConsultaRelatoriosCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("menu")) {
			stage.setScene(MenuPrincipalCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("consultClientes")) {
			stage.setScene(ConsultaClientesCena);
			stage.centerOnScreen();
			stage.show();
		}
		
		else if(descricao.equals("reItensVenda")) {
			stage.setScene(ReItensVendaCena);
			stage.centerOnScreen();
			stage.show();
		}
		
	}


	public static void FecharTela(String descricao) {
	
		if(descricao.equals("Login")) {
			stage.setScene(LoginCena);
			stage.close();
		}
		
		else if(descricao.equals("CadastroUsuario")) {
			stage.setScene(CadastroUsuarioCena);
			stage.close();
		}
		
		else if(descricao.equals("CadastroCliente")) {
			stage.setScene(CadastroClienteCena);
			stage.close();
		}
		
		else if(descricao.equals("CadastroFuncionario")) {
			stage.setScene(CadastroFuncionarioCena);
			stage.close();
		}
		
		else if(descricao.equals("CadastroProduto")) {
			stage.setScene(CadastroProdutoCena);
			stage.close();
		}
		
		else if(descricao.equals("Venda")) {
			stage.setScene(VendaCena);
			stage.close();
		}
		
		else if(descricao.equals("reClientes")) {
			stage.setScene(ReClientesCena);
			stage.close();
		}
		
		else if(descricao.equals("reProdutos")) {
			stage.setScene(ReProdutosCena);
			stage.close();
		}
		
		else if(descricao.equals("reFuncionarios")) {
			stage.setScene(ReFuncionarioCena);
			stage.close();
		}
		
		else if(descricao.equals("reVendas")) {
			stage.setScene(ReVendaCena);
			stage.close();
		}
		
		else if(descricao.equals("consultCadastro")) {
			stage.setScene(ConsultaCadastrosCena);
			stage.close();
		}
		
		else if(descricao.equals("consultRelatorio")) {
			stage.setScene(ConsultaRelatoriosCena);
			stage.close();
		}
		
		else if(descricao.equals("menu")) {
			stage.setScene(MenuPrincipalCena);
			stage.close();
		}
		
		else if(descricao.equals("consultClientes")) {
			stage.setScene(ConsultaClientesCena);
			stage.close();
		}
		
		else if(descricao.equals("reItensVenda")) {
			stage.setScene(ReItensVendaCena);
			stage.centerOnScreen();
			stage.close();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}