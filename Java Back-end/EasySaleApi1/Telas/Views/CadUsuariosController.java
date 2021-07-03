package Views;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import Dominio.UrlApi;
import Dominio.Usuario;
import Util.GerarHash;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadUsuariosController {

	@FXML
	private TextField tfUsuario;

	@FXML
	private PasswordField tfSenha;

	@FXML
	private Button btSalvar;
	public static String Nome;

	public static String getNome() {
		return Nome;
	}

	public static void setNome(String nome) {
		Nome = nome;
	}

	@FXML
	public void onBtSalvarAction()
	{
		if(tfUsuario.getText().isEmpty() || tfSenha.getText().isEmpty()) {

			Alerts.showAlert("EasySale", null, "Todos os campos precisam ser preenchidos!", AlertType.ERROR);
		}
		else {
			
			Conectando conect = new Conectando();
			
			if(conect.doPostCliente()) {
				
				Alerts.responseAlertOK("EasySale", null, "O usuário foi cadastrado com sucesso!", AlertType.CONFIRMATION, "CadastroUsuario", "OK");
				
				Main.MudancaTelas("CadastroFuncionario");
			}
			else {
				Alerts.showAlert("EasySale", null, "Não foi cadastrar o usuário, tente novamente.", AlertType.ERROR);
			}
			
			
		}
	}

	public class Conectando
	{

		public Boolean doPostCliente()
		{
			String result;

			Usuario  usuario = null;		
			ObjectMapper mapper = new ObjectMapper();
			HttpPost post = new HttpPost(UrlApi.getConexao()+ "/api/login");
			post.addHeader("content-Type", "application/json");

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"nome\":\"" + Nome + "\", \n");
			json.append("\"usuario\":\"" + tfUsuario.getText() + "\", \n");
			json.append("\"senha\":\"" + GerarHash.getHashMd5(tfSenha.getText()) +"\" \n");
			json.append("}");

			try
			{
				post.setEntity(new StringEntity(json.toString()));
				HttpClient httpClient = HttpClients.createDefault();
				HttpResponse response;
				response = httpClient.execute(post);
				
				if( response.getStatusLine().getStatusCode() == 200)
				{
					return true;	
				}	
				
				
				//result = EntityUtils.toString(response.getEntity());
				//JSONObject obj = new JSONObject(result);
				//usuario = mapper.readValue(obj.toString(), Usuario.class);


			}
			catch(ClientProtocolException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				//e.printStackTrace();
			}


			return false;		
		}
	}

}
