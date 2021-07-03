package Views;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpEntity;
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
import Dominio.Funcionario;
import Dominio.UrlApi;
import Util.ConverterStringDataPicker;
import Util.TextFieldFormatter;
import Util.removeAcento;
import Views.CadClientesController.Conectando;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CadFuncionarioController {

	int retorno;

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
	private TextField tfTelefone;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btVoltar;

	public static int id;
	public static String nome;
	public static String email;
	public static String dataNascimento;
	public static String endereco;
	public static String bairro;
	public static String telefone;
	
	
	
	public static int getId() {
		return id;
	}


	public static void setId(int id) {
		CadFuncionarioController.id = id;
	}


	public static String getNome() {
		return nome;
	}


	public static void setNome(String nome) {
		CadFuncionarioController.nome = nome;
	}


	public static String getEmail() {
		return email;
	}


	public static void setEmail(String email) {
		CadFuncionarioController.email = email;
	}


	public static String getDataNascimento() {
		return dataNascimento;
	}


	public static void setDataNascimento(String dataNascimento) {
		CadFuncionarioController.dataNascimento = dataNascimento;
	}


	public static String getEndereco() {
		return endereco;
	}


	public static void setEndereco(String endereco) {
		CadFuncionarioController.endereco = endereco;
	}


	public static String getBairro() {
		return bairro;
	}


	public static void setBairro(String bairro) {
		CadFuncionarioController.bairro = bairro;
	}


	public static String getTelefone() {
		return telefone;
	}


	public static void setTelefone(String telefone) {
		CadFuncionarioController.telefone = telefone;
	}

	public int x = 0;
	
	@FXML
	public void tfTelefoneKeyRelased() {
		
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##)#####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfTelefone);
		tff.formatter();
		
	}
	
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
				tfTelefone.setText(telefone);
				tfDataNascimento.setText(dataNascimento);
			}
		}
	}


	@FXML
	public void onBtSalvarAction() {
		
		if(tfNome.getText().isEmpty() || tfEmail.getText().isEmpty() || tfDataNascimento.getText().isEmpty() || tfEndereco.getText().isEmpty() || tfBairro.getText().isEmpty() || tfTelefone.getText().isEmpty()) {

			Alerts.showAlert("EasySale", null, "Todos os campos precisam ser preenchidos!", AlertType.ERROR);
		}
		else {
			if (Alterando.isAlterando() == false)
			{

				Conectando conect = new Conectando();

				if (conect.doPostFuncionario())
				{
					CadUsuariosController.setNome(tfNome.getText());
					
					tfNome.setText(null);;
					tfEndereco.setText(null);
					tfBairro.setText(null);
					tfEmail.setText(null);
					tfTelefone.setText(null);
					tfDataNascimento.setText(null);

					Alerts.responseAlertOK("EasySale", null, "O funcionário foi cadastrado com sucesso, faça seu cadastro de usuário.", AlertType.CONFIRMATION, "CadastroFuncionario", "OK");
				
					Main.MudancaTelas("CadastroUsuario");
					
				}
				else {
					Alerts.showAlert("EasySale", null, "Não foi possível cadastrar o funcionário, tente novamente", AlertType.ERROR);
				}
			}
			else
			{
				
				Conectando conect = new Conectando();
				
				if (conect.doPutFuncionario())
				{

					tfNome.setText(null);;
					tfEndereco.setText(null);
					tfBairro.setText(null);
					tfEmail.setText(null);
					tfTelefone.setText(null);
					tfDataNascimento.setText(null);

					Alerts.responseAlertOK("EasySale", null, "O funcionário foi atualizado com sucesso!", AlertType.CONFIRMATION, "CadastroFuncionario", "OK");
					Main.FecharTela("reFuncionarios");
					Main.MudancaTelas("reFuncionarios");

					ReFuncionarioController.setEntrou(1);
				}
				else {
					
					Alerts.showAlert("EasySale", null, "Não foi possível atualizar o funcionário, tente novamente", AlertType.ERROR);
				}
			}
		}
	}

	public class Conectando
	{

		public Boolean doPostFuncionario()
		{
			String result;

			Funcionario funcionario = null;		
			ObjectMapper mapper = new ObjectMapper();
			HttpPost post = new HttpPost(UrlApi.getConexao() + "/api/funcionario");
			post.addHeader("content-Type", "application/json");

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"nome\":\"" + removeAcento.removerAcento(tfNome.getText().toUpperCase().trim()) + "\", \n");
			json.append("\"email\":\"" + tfEmail.getText().toUpperCase().trim() + "\", \n");
			json.append("\"telefone\":\"" + tfTelefone.getText().trim() + "\", \n");
			json.append("\"dt_Nascimento\":\"" + tfDataNascimento.getText() + "\", \n");
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
		
		public boolean doPutFuncionario()
		{
	
			ObjectMapper mapper = new ObjectMapper();
			HttpPut put = new HttpPut(UrlApi.getConexao() + "/api/funcionario");
			put.addHeader("content-Type", "application/json");

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"id\":"+ id + ", \n");
			json.append("\"nome\":\"" + removeAcento.removerAcento(tfNome.getText().toUpperCase().trim()) + "\", \n");
			json.append("\"email\":\"" + tfEmail.getText().toUpperCase().trim() + "\", \n");
			json.append("\"telefone\":\"" + tfTelefone.getText().trim() + "\", \n");
			json.append("\"dt_Nascimento\":\"" + tfDataNascimento.getText() + "\", \n");
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
	
	@FXML
	public void onBtVoltarAction() {
	
		
		tfNome.setText(null);;
		tfEndereco.setText(null);
		tfBairro.setText(null);
		tfEmail.setText(null);
		tfTelefone.setText(null);
		tfDataNascimento.setText(null);
		
		
		if(Alterando.isAlterando() == true)
		{
			Main.MudancaTelas("reFuncionarios");
		}
		else
		{
			Main.MudancaTelas("consultCadastro");
		}
		
	}
}
