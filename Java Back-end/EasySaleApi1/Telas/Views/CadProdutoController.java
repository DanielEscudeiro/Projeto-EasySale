package Views;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

import Dominio.Alterando;
import Dominio.Produto;
import Dominio.UrlApi;
import Util.removeAcento;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadProdutoController {

	@FXML
	private TextField tfDescricao;

	@FXML
	private TextField tfValor;

	@FXML
	private TextField tfQuantidade;

	@FXML
	private Button btNovo;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btVoltar;

	public static int  id;
	public static String descricao;
	public static int quantidade;
	public static double valor;



	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		CadProdutoController.id = id;
	}

	public static String getDescricao() {
		return descricao;
	}

	public static void setDescricao(String descricao) {
		CadProdutoController.descricao = descricao;
	}

	public static int getQuantidade() {
		return quantidade;
	}

	public static void setQuantidade(int quantidade) {
		CadProdutoController.quantidade = quantidade;
	}

	public static double getValor() {
		return valor;
	}

	public static void setValor(double valor) {
		CadProdutoController.valor = valor;
	}

	public int x = 0;

	@FXML
	public void onMouseEnteredAction() {
		if(x == 0)
		{
			if(Alterando.isAlterando() == true)
			{
				tfDescricao.setText(descricao);
				tfQuantidade.setText(String.valueOf(quantidade));
				tfValor.setText(String.valueOf(valor));
				
			}
		}
	}

	@FXML
	public void onBtSalvarAction() {

		if(tfDescricao.getText().isEmpty() || tfValor.getText().isEmpty() || tfQuantidade.getText().isEmpty()) {

			Alerts.showAlert("EasySale", null, "Todos os campos precisam ser preenchidos!", AlertType.ERROR);
		}
		else {

			if (Alterando.isAlterando() == false)
			{

				Conectando conect = new Conectando();

				if (conect.doPostProduto())
				{
					tfDescricao.setText(null);;
					tfQuantidade.setText(null);
					tfValor.setText(null);

					Alerts.showAlert("EasySale", null, "O Produto foi cadastrado com sucesso!", AlertType.CONFIRMATION);
				}
				else
				{
					Alerts.showAlert("EasySale", null, "Não foi possível cadastrar o produto, tente novamente.", AlertType.ERROR);
				}
			}
			else {

				Conectando conect = new Conectando();

				if (conect.doPutProduto())
				{

					tfDescricao.setText(null);;
					tfQuantidade.setText(null);
					tfValor.setText(null);


					Alerts.responseAlertOK("EasySale", null, "O produto foi atualizado com sucesso!", AlertType.CONFIRMATION, "CadastroProduto", "OK");
					Main.FecharTela("reProdutos");
					Main.MudancaTelas("reProdutos");

					ReProdutoController.setEntrou(1);

				}
				else {
					Alerts.showAlert("EasySale", null, "Não foi possível atualizar o produto, tente novamente.", AlertType.ERROR);
				}
			}
		}
	}

	public class Conectando
	{

		public Boolean doPostProduto()
		{
			String result;

			Produto produto = null;		
			ObjectMapper mapper = new ObjectMapper();
			HttpPost post = new HttpPost(UrlApi.getConexao() + "/api/produto");
			post.addHeader("content-Type", "application/json");

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"descricao\":\"" + removeAcento.removerAcento(tfDescricao.getText().toUpperCase().trim()) + "\", \n");
			json.append("\"quantidade\":" + Integer.parseInt(tfQuantidade.getText()) + ", \n");
			json.append("\"valor\":\"" + tfValor.getText() + "\" \n");
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

		public Boolean doPutProduto()
		{

			ObjectMapper mapper = new ObjectMapper();
			HttpPut put = new HttpPut(UrlApi.getConexao() + "/api/produto");
			put.addHeader("content-Type", "application/json");

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"id\":"+ id + ", \n");
			json.append("\"descricao\":\"" + removeAcento.removerAcento(tfDescricao.getText().toUpperCase().trim()) + "\", \n");
			json.append("\"quantidade\":" + Integer.parseInt(tfQuantidade.getText()) + ", \n");
			json.append("\"valor\":\"" + tfValor.getText() + "\" \n");
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

		tfDescricao.setText(null);;
		tfQuantidade.setText(null);
		tfValor.setText(null);

		if(Alterando.isAlterando() == true)
		{
			Main.MudancaTelas("reProdutos");
		}
		else
		{
			Main.MudancaTelas("consultCadastro");
		}

	}
}
