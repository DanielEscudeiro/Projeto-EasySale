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

import com.fasterxml.jackson.databind.ObjectMapper;

import Dominio.Cliente;
import Dominio.ConsultaClienteTabela;
import Dominio.UrlApi;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsultaClientesController {

	@FXML
	private Button btBuscar;

	@FXML
	private Button btVoltar;

	@FXML
	private TableView<ConsultaClienteTabela> TableConsultCliente;

	@FXML
	private TableColumn<ConsultaClienteTabela, Integer> tbCodigo;

	@FXML
	private TableColumn<ConsultaClienteTabela, String> tbNome;

	private ObservableList<ConsultaClienteTabela> listConsultClienteTabela = FXCollections.observableArrayList();

	Conectando conect = new Conectando();
	
	@FXML
	public void onMouseEnteredAction() {
		
		TableConsultCliente.getItems().clear();
		ArrayList<Cliente> clienteT = conect.doGetTodos();

		for(Cliente cliente : clienteT)
		{
			ConsultaClienteTabela c = new ConsultaClienteTabela(cliente.getId(), cliente.getNome());
			listConsultClienteTabela.add(c);
		}

		tbCodigo.setCellValueFactory(new PropertyValueFactory<ConsultaClienteTabela, Integer>("id"));
		tbNome.setCellValueFactory(new PropertyValueFactory<ConsultaClienteTabela, String>("nome"));

		TableConsultCliente.setItems(listConsultClienteTabela);
	}

	public void initialize() {

		ArrayList<Cliente> clienteT = conect.doGetTodos();


		for(Cliente cliente : clienteT)
		{
			ConsultaClienteTabela c = new ConsultaClienteTabela(cliente.getId(), cliente.getNome());
			listConsultClienteTabela.add(c);
		}

		tbCodigo.setCellValueFactory(new PropertyValueFactory<ConsultaClienteTabela, Integer>("id"));
		tbNome.setCellValueFactory(new PropertyValueFactory<ConsultaClienteTabela, String>("nome"));

		TableConsultCliente.setItems(listConsultClienteTabela);

	}

	public class Conectando {

		public ArrayList<Cliente> doGetTodos()
		{
			String strResposta = "";

			ObjectMapper mapper = new ObjectMapper();
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao()+ "/api/cliente");


			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity resEnt = response.getEntity();
				strResposta = EntityUtils.toString(resEnt);
				JSONArray obj = new JSONArray(strResposta);
				Cliente cliente;
				for(int i = 0; i < obj.length(); i++)
				{
					cliente = mapper.readValue(obj.getJSONObject(i).toString(), Cliente.class);
					clientes.add(cliente);
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

			return clientes;
		}
	}

	@FXML
	public void onBtSelecionarAction() {

		VendaController.setNomeClienteRetorno(TableConsultCliente.getSelectionModel().getSelectedItem().getNome().toString());
		VendaController.setCodCliente(TableConsultCliente.getSelectionModel().getSelectedItem().getId().toString());
		
		Main.FecharTela("consultClientes");
		Main.MudancaTelas("Venda");
	}

	@FXML
	public void onBtVoltarAction() {

		Main.MudancaTelas("Venda");
	}
}
