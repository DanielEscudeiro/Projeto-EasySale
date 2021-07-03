package Views;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import Dominio.Alterando;
import Dominio.Cliente;
import Dominio.UrlApi;
import Util.TextFieldFormatter;
import Util.removeAcento;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CadClientesController {

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfDataNascimento;

	@FXML
	private TextField tfEndereco;

	@FXML
	private TextField tfBairro;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btVoltar;

	public static int id;
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		CadClientesController.id = id;
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		CadClientesController.nome = nome;
	}

	public static String getEndereco() {
		return endereco;
	}

	public static void setEndereco(String endereco) {
		CadClientesController.endereco = endereco;
	}

	public static String getBairro() {
		return bairro;
	}

	public static void setBairro(String bairro) {
		CadClientesController.bairro = bairro;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		CadClientesController.email = email;
	}

	public static String getDtNascimento() {
		return dtNascimento;
	}

	public static void setDtNascimento(String dtNascimento) {
		CadClientesController.dtNascimento = dtNascimento;
	}

	public static String nome;
	public static String endereco;
	public static String bairro;
	public static String email;
	public static String dtNascimento;
	public int x = 0;

	@FXML
	public void tfDataKeyRelased() {

		TextFieldFormatter dt = new TextFieldFormatter();
		dt.setMask("##/##/####");
		dt.setCaracteresValidos("0123456789");
		dt.setTf(tfDataNascimento);
		dt.formatter();

	}

	@FXML
	public void onMouseEnteredAction() {

		if(x == 0)
		{
			if(Alterando.isAlterando() == true)
			{
				tfNome.setText(nome);
				tfEndereco.setText(endereco);
				tfBairro.setText(bairro);
				tfEmail.setText(email);
				tfDataNascimento.setText(dtNascimento);
			}
		}
	}

	@FXML
	public void onBtSalvarAction() {

		if(tfNome.getText().isEmpty() || tfEmail.getText().isEmpty() || tfDataNascimento.getText().isEmpty() || tfEndereco.getText().isEmpty() || tfBairro.getText().isEmpty()) {

			Alerts.showAlert("EasySale", null, "Todos os campos precisam ser preenchidos!", AlertType.ERROR);
		}
		else {
			if (Alterando.isAlterando() == false)
			{
				Conectando conect = new Conectando();

				if(conect.doPostCliente()) {

					tfNome.setText(null);;
					tfEndereco.setText(null);
					tfBairro.setText(null);
					tfEmail.setText(null);
					tfDataNascimento.setText(null);

					Alerts.showAlert("EasySale", null, "O cliente foi cadastrado com sucesso!", AlertType.CONFIRMATION);
				}
				else {
					Alerts.showAlert("EasySale", null, "Não foi possível cadastrar o cliente, tente novamente.", AlertType.ERROR);
				}

			}
			else
			{
				Conectando conect = new Conectando();

				if (conect.doPutCliente())
				{
					tfNome.setText(null);;
					tfEndereco.setText(null);
					tfBairro.setText(null);
					tfEmail.setText(null);
					tfDataNascimento.setText(null);

					Alerts.responseAlertOK("EasySale", null, "O cliente foi atualizado com sucesso!", AlertType.CONFIRMATION, "CadastroCliente", "Ok");
					Main.FecharTela("reClientes");
					Main.MudancaTelas("reClientes");

					ReClientesController.setEntrou(1);

				}
				else {
					Alerts.showAlert("EasySale", null, "Não foi possível atualizar o cliente, tente novamente.", AlertType.ERROR);
				}
			}


		}
	}

	public class Conectando
	{

		public Boolean doPostCliente()
		{
			String result;

			Cliente cliente = null;		
			ObjectMapper mapper = new ObjectMapper();
			HttpPost post = new HttpPost(UrlApi.getConexao() + "/api/cliente");
			post.addHeader("content-Type", "application/json");

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"nome\":\"" + removeAcento.removerAcento(tfNome.getText().toUpperCase().trim()) + "\", \n");
			json.append("\"email\":\"" + tfEmail.getText().toUpperCase().trim() + "\", \n");
			json.append("\"dT_Nascimento\":\"" + tfDataNascimento.getText() + "\", \n");
			json.append("\"endereco\":\"" + removeAcento.removerAcento(tfEndereco.getText().toUpperCase().trim()) + "\", \n");
			json.append("\"bairro\":\"" + removeAcento.removerAcento(tfBairro.getText().toUpperCase().trim()) + "\" \n");
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
				//cliente = mapper.readValue(obj.toString(), Cliente.class);


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
		public boolean doPutCliente()
		{

			Cliente cliente = null;		
			ObjectMapper mapper = new ObjectMapper();
			HttpPut put = new HttpPut(UrlApi.getConexao() + "/api/cliente");
			put.addHeader("content-Type", "application/json");

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"id\":"+ id + ", \n");
			json.append("\"nome\":\"" + removeAcento.removerAcento(tfNome.getText().toUpperCase().trim()) + "\", \n");
			json.append("\"email\":\"" + tfEmail.getText().toUpperCase().trim() + "\", \n");
			json.append("\"dT_Nascimento\":\"" + tfDataNascimento.getText() + "\", \n");
			json.append("\"endereco\":\"" + removeAcento.removerAcento(tfEndereco.getText().toUpperCase().trim()) + "\", \n");
			json.append("\"bairro\":\"" + removeAcento.removerAcento(tfBairro.getText().toUpperCase().trim()) + "\" \n");
			json.append("}");

			try
			{
				put.setEntity(new StringEntity(json.toString()));
				HttpClient httpClient = HttpClients.createDefault();
				HttpResponse response;
				response = httpClient.execute(put);

				if( response.getStatusLine().getStatusCode() == 200)
				{
					return true;	
				}	

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

	public void onBtVoltarAction() {

		tfNome.setText(null);;
		tfEndereco.setText(null);
		tfBairro.setText(null);
		tfEmail.setText(null);
		tfDataNascimento.setText(null);

		if(Alterando.isAlterando() == true)
		{
			Main.MudancaTelas("reClientes");
		}
		else
		{
			
			Main.MudancaTelas("consultCadastro");
		}
	}
}
