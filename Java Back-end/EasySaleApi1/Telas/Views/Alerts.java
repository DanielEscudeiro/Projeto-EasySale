 package Views;

import application.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {
	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}
	
	public static void responseAlert(String title, String header, String content, AlertType type, String tela, String botao1, String botao2) {
		ButtonType b1 = new ButtonType(botao1);
		ButtonType b2 = new ButtonType(botao2);
		
		Alert alert = new Alert(type, "", b1, b2);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
		
		if(alert.getResult() == b1) {
			Main.FecharTela(tela);
		}
		else {
			alert.close();
		}
	}
	
	public static void responseAlertOK(String title, String header, String content, AlertType type, String tela, String botao1) {
		ButtonType b1 = new ButtonType(botao1);
		
		Alert alert = new Alert(type, "", b1);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
		
		if(alert.getResult() == b1) {
			Main.FecharTela(tela);
		}
	}
}

