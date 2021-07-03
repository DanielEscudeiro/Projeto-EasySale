package Views;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import Dominio.UrlApi;
import Dominio.Usuario;
import Dominio.UsuarioNome;
import Util.GerarHash;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField tfUsuario;

	@FXML
	private PasswordField tfSenha;

	@FXML
	private Button btEntrar;
	
	@FXML
	private Button btSair;
	
	@FXML
	public void onBtEntrarAction()
	{
		if(tfUsuario.getText().isEmpty() || tfSenha.getText().isEmpty()) {

			Alerts.showAlert("EasySale", null, "Todos os campos precisam ser preenchidos!", AlertType.ERROR);
		}
		else {
			
			Conectando conect = new Conectando();

			try {

				Usuario user  =  conect.doGetLogin(tfUsuario.getText(), GerarHash.getHashMd5(tfSenha.getText()));

				UsuarioNome.setNome(user.getNome());
			
				//Alerts.showAlert("EasySale", null, "Bem Vindo " + user.getNome(), AlertType.INFORMATION);

				int i = 0, x = 0;

				while (i > 100)
				{
					x++;
				}
				i++;
				if (x == 100)
				{
					Main.FecharTela("Login");
				}


				Main.MudancaTelas("menu");
				
		


			}
			catch(Exception ex){
				
				Alerts.responseAlertOK("EasySale", "O usúario ou senha informados estão incorretos.", "Tente novamente ou entre em contato com o administrador do sistema.", AlertType.ERROR, "", "OK");
			}		
		}
	}



	public class Conectando
	{
		public Usuario doGetLogin(String user, String senha)

		{
			String result = null;
			Usuario usuario = null;

			HttpClient httpClient = HttpClientBuilder.create().build();

			ObjectMapper mapper = new ObjectMapper();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao()+ "/api/login/user:"+user+"/password:"+senha);

			//System.out.println(httpGet.getRequestLine());

			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				if(response != null)
				{
					HttpEntity resEntity = response.getEntity();
					result = EntityUtils.toString(resEntity);
					JSONObject obj = new JSONObject(result);
					usuario = mapper.readValue(obj.toString(), Usuario.class);

				}
				else
				{
					//System.out.println("Erro com API Nada Encontrado");
				}
			}
			catch (Exception ex)
			{
				//System.out.println(ex.getMessage() + "   TESTE");
			}

			return usuario;

		}
	}
	
	@FXML
	public void onBtSairAction(){

		Alerts.responseAlert("EasySale", null, "Você tem certeza que deseja sair do EasySale? ", AlertType.WARNING, "Login", "Sim", "Não");
	}
}
