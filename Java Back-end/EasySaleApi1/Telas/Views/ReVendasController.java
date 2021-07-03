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

import Dominio.Pedido;
import Dominio.PedidoTabela;
import Dominio.UrlApi;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReVendasController {

	@FXML
	private TextField tfCodigo;

	@FXML
	private Button btPesquisar;

	@FXML
	private Button btVoltar;

	@FXML
	private Button btItens;

	@FXML
	private TableView<PedidoTabela> TableVendas;

	@FXML
	private TableColumn<PedidoTabela, Integer> tbCodigo;

	@FXML
	private TableColumn<PedidoTabela, String> tbData;

	@FXML
	private TableColumn<PedidoTabela, Integer> tbCodigoCliente;

	@FXML
	private TableColumn<PedidoTabela, String> tbNomeCliente;


	private ObservableList<PedidoTabela> listPedidoTabela = FXCollections.observableArrayList();

	Conectando conect = new Conectando();

	@FXML
	public void onBtPesquisarAction() {
		TableVendas.getItems().clear();

		if(tfCodigo.getText().isEmpty() || tfCodigo.getText().equals("%")) {
			initialize();
		}

		Pedido pedido = conect.doGetVenda(tfCodigo.getText());

		PedidoTabela v = new PedidoTabela(pedido.getNr_Pedido(), pedido.getdT_Pedido(), pedido.getCliente().getId(), pedido.getCliente().getNome());
		listPedidoTabela.add(v);

		tbCodigo.setCellValueFactory(new PropertyValueFactory<PedidoTabela, Integer>("nr_Pedido"));
		tbData.setCellValueFactory(new PropertyValueFactory<PedidoTabela, String>("dt_Pedido"));
		tbCodigoCliente.setCellValueFactory(new PropertyValueFactory<PedidoTabela, Integer>("id_Cliente"));
		tbNomeCliente.setCellValueFactory(new PropertyValueFactory<PedidoTabela, String>("nome_Cliente"));

		TableVendas.setItems(listPedidoTabela);
	}

	@FXML
	public void onBtItensAction() {

		if(TableVendas.getSelectionModel().isEmpty()) {
			Alerts.showAlert("EasySale", null, "Selecione uma coluna!", AlertType.ERROR);
		}
		else {

			reItensVendaController.setX(0);
			reItensVendaController.setCod_venda(String.valueOf(TableVendas.getSelectionModel().getSelectedItem().getNr_Pedido()));
			Main.FecharTela("reVendas");
			Main.MudancaTelas("reItensVenda");
		}
	}

	public void initialize() {

		ArrayList<Pedido> pedidoT = conect.doGetTodos();


		for(Pedido pedido : pedidoT)
		{
			PedidoTabela v = new PedidoTabela(pedido.getNr_Pedido(), pedido.getdT_Pedido(), pedido.getCliente().getId(), pedido.getCliente().getNome());
			listPedidoTabela.add(v);
		}

		tbCodigo.setCellValueFactory(new PropertyValueFactory<PedidoTabela, Integer>("nr_Pedido"));
		tbData.setCellValueFactory(new PropertyValueFactory<PedidoTabela, String>("dt_Pedido"));
		tbCodigoCliente.setCellValueFactory(new PropertyValueFactory<PedidoTabela, Integer>("id_Cliente"));
		tbNomeCliente.setCellValueFactory(new PropertyValueFactory<PedidoTabela, String>("nome_Cliente"));

		TableVendas.setItems(listPedidoTabela);

	}

	public class Conectando {

		public Pedido doGetVenda(String Pedido)
		{
			String result = null;
			Pedido pedido = null;

			HttpClient httpClient = HttpClientBuilder.create().build();

			ObjectMapper mapper = new ObjectMapper();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao() + "/api/pedido/" + Pedido);

			System.out.println(httpGet.getRequestLine());

			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				if(response != null)
				{
					HttpEntity resEntity = response.getEntity();
					result = EntityUtils.toString(resEntity);
					JSONObject obj = new JSONObject(result);
					pedido = mapper.readValue(obj.toString(),Pedido.class);
				}
				else
				{
					System.out.println("API SEM RETORNO");
				}
			}
			catch (Exception ex)
			{
				System.out.println(ex.getMessage() + "pp");
			}

			return pedido;
		}

		// TODO Auto-generated method stub
		public ArrayList<Pedido> doGetTodos()
		{
			String strResposta = "";

			ObjectMapper mapper = new ObjectMapper();
			ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao() + "/api/pedido");


			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity resEnt = response.getEntity();
				strResposta = EntityUtils.toString(resEnt);
				JSONArray obj = new JSONArray(strResposta);
				Pedido pedido;

				for(int i = 0; i < obj.length(); i++)
				{
					pedido = mapper.readValue(obj.getJSONObject(i).toString(), Pedido.class);
					pedidos.add(pedido);
				}

			}
			catch(ClientProtocolException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

			return pedidos;
		}
	}


	public void onBtVoltarAction() {

		Main.MudancaTelas("consultRelatorio");
	}

}
