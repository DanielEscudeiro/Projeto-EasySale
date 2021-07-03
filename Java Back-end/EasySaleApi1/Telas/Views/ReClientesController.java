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
import Dominio.Cliente;
import Dominio.ClienteTabela;
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

public class ReClientesController {


	@FXML
	private TextField tfCodigo;

	@FXML
	private Button btPesquisar;

	@FXML
	private Button btVoltar;

	@FXML
	private Button btAlterar;

	@FXML
	private TableView<ClienteTabela> TableClientes;

	@FXML
	private TableColumn<ClienteTabela, Integer> tbCodigo;

	@FXML
	private TableColumn<ClienteTabela, String> tbNome;

	@FXML
	private TableColumn<ClienteTabela, String> tbEndereco;

	@FXML
	private TableColumn<ClienteTabela, String> tbBairro;

	@FXML
	private TableColumn<ClienteTabela, String> tbEmail;

	@FXML
	private TableColumn<ClienteTabela, String> dT_Nascimento;


	private ObservableList<ClienteTabela> listClienteTabela = FXCollections.observableArrayList();

	public static int entrou = 1;

	public static int getEntrou() {
		return entrou;
	}

	public static void setEntrou(int entrou) {
		ReClientesController.entrou = entrou;
	}

	Conectando conect = new Conectando();


	@FXML
	public void onMouseEnteredAction() {
		if(entrou == 1)
		{
			TableClientes.getItems().clear();
			ArrayList<Cliente> clienteT = conect.doGetTodos();


			for(Cliente cliente : clienteT)
			{
				ClienteTabela c = new ClienteTabela(cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getBairro(), cliente.getEmail(), cliente.getdT_Nascimento());
				listClienteTabela.add(c);
			}

			tbCodigo.setCellValueFactory(new PropertyValueFactory<ClienteTabela, Integer>("id"));
			tbNome.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("nome"));
			tbEndereco.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("endereco"));
			tbBairro.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("bairro"));
			tbEmail.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("email"));
			dT_Nascimento.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("dT_Nascimento"));

			TableClientes.setItems(listClienteTabela);
			
		}
	}

	@FXML
	public void onBtAlterarAction() {

		if(TableClientes.getSelectionModel().isEmpty()) {
			Alerts.showAlert("EasySale", null, "Selecione uma coluna!", AlertType.ERROR);
		}
		else {

			CadClientesController.setId(TableClientes.getSelectionModel().getSelectedItem().getId());
			CadClientesController.setNome(TableClientes.getSelectionModel().getSelectedItem().getNome());
			CadClientesController.setEndereco(TableClientes.getSelectionModel().getSelectedItem().getEndereco());
			CadClientesController.setBairro(TableClientes.getSelectionModel().getSelectedItem().getBairro());
			CadClientesController.setEmail(TableClientes.getSelectionModel().getSelectedItem().getEmail());
			CadClientesController.setDtNascimento(TableClientes.getSelectionModel().getSelectedItem().getDT_Nascimento());

			Main.FecharTela("reClientes");
			Main.MudancaTelas("CadastroCliente");
			Alterando.setAlterando(true);
		}


	}

	@FXML
	public void onBtPesquisarAction() {
		TableClientes.getItems().clear();

		if(tfCodigo.getText().isEmpty() || tfCodigo.getText().equals("%")) {
			initialize();
		}

		Cliente cliente = conect.doGetCliente(tfCodigo.getText());

		ClienteTabela c = new ClienteTabela(cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getBairro(), cliente.getEmail(), cliente.getdT_Nascimento());
		listClienteTabela.add(c);

		tbCodigo.setCellValueFactory(new PropertyValueFactory<ClienteTabela, Integer>("id"));
		tbNome.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("nome"));
		tbEndereco.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("endereco"));
		tbBairro.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("bairro"));
		tbEmail.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("email"));
		dT_Nascimento.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("dT_Nascimento"));

		TableClientes.setItems(listClienteTabela);
	}


	public void initialize() {



		ArrayList<Cliente> clienteT = conect.doGetTodos();


		for(Cliente cliente : clienteT)
		{
			ClienteTabela c = new ClienteTabela(cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getBairro(), cliente.getEmail(), cliente.getdT_Nascimento());
			listClienteTabela.add(c);
		}

		tbCodigo.setCellValueFactory(new PropertyValueFactory<ClienteTabela, Integer>("id"));
		tbNome.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("nome"));
		tbEndereco.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("endereco"));
		tbBairro.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("bairro"));
		tbEmail.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("email"));
		dT_Nascimento.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("dT_Nascimento"));

		TableClientes.setItems(listClienteTabela);

		TableClientes.refresh();

	}

	public class Conectando {

		public Cliente doGetCliente(String Cliente)
		{
			String result = null;
			Cliente cliente = null;

			HttpClient httpClient = HttpClientBuilder.create().build();

			ObjectMapper mapper = new ObjectMapper();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao()+ "/api/cliente/" + Cliente);

			System.out.println(httpGet.getRequestLine());

			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				if(response != null)
				{
					HttpEntity resEntity = response.getEntity();
					result = EntityUtils.toString(resEntity);
					JSONObject obj = new JSONObject(result);
					cliente = mapper.readValue(obj.toString(),Cliente.class);
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

			return cliente;
		}

		// TODO Auto-generated method stub
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
				//e.printStackTrace();
			}
			catch(IOException e)
			{
				//e.printStackTrace();
			}

			return clientes;
		}
	}

	public void onBtVoltarAction() {

		Main.MudancaTelas("consultRelatorio");
	}
}

