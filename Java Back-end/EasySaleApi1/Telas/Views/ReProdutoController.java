package Views;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import Dominio.Alterando;
import Dominio.Produto;
import Dominio.ProdutoTabela;
import Dominio.UrlApi;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReProdutoController {
	
	@FXML
	private TextField tfCodigo;

	@FXML
	private Button btPesquisar;
	
	@FXML
	private Button btVoltar;
	
	@FXML
	private Button btAlterar;

	@FXML
	private TableView<ProdutoTabela> TableProdutos;

	@FXML
	private TableColumn<ProdutoTabela, Integer> tbCodigo;

	@FXML
	private TableColumn<ProdutoTabela, String> tbDescricao;

	@FXML
	private TableColumn<ProdutoTabela, Integer> tbQuantidade;

	@FXML
	private TableColumn<ProdutoTabela, Double> tbValor;
	

	private ObservableList<ProdutoTabela> listProdutoTabela = FXCollections.observableArrayList();
	
	public static int entrou;

	public static int getEntrou() {
		return entrou;
	}

	public static void setEntrou(int entrou) {
		ReProdutoController.entrou = entrou;
	}
	
	Conectando conect = new Conectando();
	
	@FXML
	public void onMouseEnteredAction() {
		if(entrou == 1)
		{
			TableProdutos.getItems().clear();
			ArrayList<Produto> produtoT = conect.doGetTodos();


			for(Produto produto: produtoT)
			{
				ProdutoTabela p = new ProdutoTabela(produto.getId(), produto.getDescricao(), produto.getQuantidade(), produto.getValor());
				listProdutoTabela.add(p);
			}


			tbCodigo.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, Integer>("id"));
			tbDescricao.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, String>("descricao"));
			tbQuantidade.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, Integer>("quantidade"));
			tbValor.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, Double>("valor"));

			TableProdutos.setItems(listProdutoTabela);
		}
	}
	
	@FXML
	public void onBtAlterarAction() {
		
		if(TableProdutos.getSelectionModel().isEmpty()) {
			Alerts.showAlert("EasySale", null, "Selecione uma coluna!", AlertType.ERROR);
		}
		else {

			CadProdutoController.setId(TableProdutos.getSelectionModel().getSelectedItem().getId());
			CadProdutoController.setDescricao(TableProdutos.getSelectionModel().getSelectedItem().getDescricao());
			CadProdutoController.setQuantidade(TableProdutos.getSelectionModel().getSelectedItem().getQuantidade());
			CadProdutoController.setValor(TableProdutos.getSelectionModel().getSelectedItem().getValor());

			Main.FecharTela("reProdutos");
			Main.MudancaTelas("CadastroProduto");
			Alterando.setAlterando(true);
		}
	}
	
	@FXML
	public void onBtPesquisarAction() {
		TableProdutos.getItems().clear();
		
		if(tfCodigo.getText().isEmpty() || tfCodigo.getText().equals("%")) {
			initialize();
		}
		
		Produto produto = conect.doGetProduto(tfCodigo.getText());
		
		ProdutoTabela p = new ProdutoTabela(produto.getId(), produto.getDescricao(), produto.getQuantidade(), produto.getValor());
		listProdutoTabela.add(p);
	
		tbCodigo.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, Integer>("id"));
		tbDescricao.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, String>("descricao"));
		tbQuantidade.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, Integer>("quantidade"));
		tbValor.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, Double>("valor"));

		TableProdutos.setItems(listProdutoTabela);
	}
	
	
	public void initialize() {
	
		ArrayList<Produto> produtoT = conect.doGetTodos();


		for(Produto produto: produtoT)
		{
			ProdutoTabela p = new ProdutoTabela(produto.getId(), produto.getDescricao(), produto.getQuantidade(), produto.getValor());
			listProdutoTabela.add(p);
		}


		tbCodigo.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, Integer>("id"));
		tbDescricao.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, String>("descricao"));
		tbQuantidade.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, Integer>("quantidade"));
		tbValor.setCellValueFactory(new PropertyValueFactory<ProdutoTabela, Double>("valor"));

		TableProdutos.setItems(listProdutoTabela);
		
	}
	
	public class Conectando {

		public Produto doGetProduto(String Produto)
		{
			String result = null;
			Produto produto = null;

			HttpClient httpClient = HttpClientBuilder.create().build();

			ObjectMapper mapper = new ObjectMapper();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao()+ "/api/produto/" + Produto);

			System.out.println(httpGet.getRequestLine());

			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				if(response != null)
				{
					HttpEntity resEntity = response.getEntity();
					result = EntityUtils.toString(resEntity);
					JSONObject obj = new JSONObject(result);
					produto = mapper.readValue(obj.toString(),Produto.class);
				}
				else
				{
					//System.out.println("API SEM RETORNO");
				}
			}
			catch (Exception ex)
			{
				//System.out.println(ex.getMessage() + "pp");
			}

			return produto;
		}

		// TODO Auto-generated method stub
		public ArrayList<Produto> doGetTodos()
		{
			String strResposta = "";

			ObjectMapper mapper = new ObjectMapper();
			ArrayList<Produto> produtos = new ArrayList<Produto>();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao()+ "/api/produto");


			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity resEnt = response.getEntity();
				strResposta = EntityUtils.toString(resEnt);
				JSONArray obj = new JSONArray(strResposta);
				Produto produto;
				for(int i = 0; i < obj.length(); i++)
				{
					produto = mapper.readValue(obj.getJSONObject(i).toString(), Produto.class);
					produtos.add(produto);
				}

			}
			catch(ClientProtocolException e)
			{
				//e.printStackTrace();
			}
			catch(IOException e)
			{
				//e.printStackTrace();
			}

			return produtos;
		}
	}
	
	public void onBtVoltarAction() {
		
		Main.MudancaTelas("consultRelatorio");
	}
}
