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
import Dominio.Funcionario;
import Dominio.FuncionarioTabela;
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

public class ReFuncionarioController {
	
	@FXML
	private TextField tfCodigo;

	@FXML
	private Button btPesquisar;
	
	@FXML
	private Button btVoltar;
	
	@FXML
	private Button btAlterar;

	@FXML
	private TableView<FuncionarioTabela> TableFuncionarios;

	@FXML
	private TableColumn<FuncionarioTabela, Integer> tbCodigo;

	@FXML
	private TableColumn<FuncionarioTabela, String> tbNome;
	
	@FXML
	private TableColumn<FuncionarioTabela, String> tbEmail;
	
	@FXML
	private TableColumn<FuncionarioTabela, String> tbTelefone;

	@FXML
	private TableColumn<FuncionarioTabela, String> tbEndereco;

	@FXML
	private TableColumn<FuncionarioTabela, String> tbBairro;

	@FXML
	private TableColumn<FuncionarioTabela, String> dT_Nascimento;
	

	private ObservableList<FuncionarioTabela> listFuncionariosTabela = FXCollections.observableArrayList();
	public static int entrou;

	public static int getEntrou() {
		return entrou;
	}

	public static void setEntrou(int entrou) {
		ReFuncionarioController.entrou = entrou;
	}
	
	Conectando conect = new Conectando();
	
	@FXML
	public void onMouseEnteredAction() {
		if(entrou == 1)
		{
			TableFuncionarios.getItems().clear();
			ArrayList<Funcionario> funcionarioT = conect.doGetTodos();


			for(Funcionario funcionario : funcionarioT)
			{
				FuncionarioTabela f = new FuncionarioTabela(funcionario.getId(), funcionario.getNome(), funcionario.getEndereco(), funcionario.getBairro(), funcionario.getEmail(), funcionario.getTelefone(), funcionario.getdT_Nascimento());
				listFuncionariosTabela.add(f);
			}

			tbCodigo.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, Integer>("id"));
			tbNome.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("nome"));
			tbEndereco.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("endereco"));
			tbBairro.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("bairro"));
			tbEmail.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("email"));
			tbTelefone.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("telefone"));
			dT_Nascimento.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("dT_Nascimento"));

			TableFuncionarios.setItems(listFuncionariosTabela);
		}
	}
	
	@FXML
	public void onBtAlterarAction() {
		
		
		if(TableFuncionarios.getSelectionModel().isEmpty()) {
			Alerts.showAlert("EasySale", null, "Selecione uma coluna!", AlertType.ERROR);
		}
		else {

			CadFuncionarioController.setId(TableFuncionarios.getSelectionModel().getSelectedItem().getId());
			CadFuncionarioController.setNome(TableFuncionarios.getSelectionModel().getSelectedItem().getNome());
			CadFuncionarioController.setEndereco(TableFuncionarios.getSelectionModel().getSelectedItem().getEndereco());
			CadFuncionarioController.setBairro(TableFuncionarios.getSelectionModel().getSelectedItem().getBairro());
			CadFuncionarioController.setEmail(TableFuncionarios.getSelectionModel().getSelectedItem().getEmail());
			CadFuncionarioController.setDataNascimento(TableFuncionarios.getSelectionModel().getSelectedItem().getDT_Nascimento());
			CadFuncionarioController.setTelefone(TableFuncionarios.getSelectionModel().getSelectedItem().getTelefone());

			Main.FecharTela("reFuncionarios");
			Main.MudancaTelas("CadastroFuncionario");
			Alterando.setAlterando(true);
		}


	}
	
	@FXML
	public void onBtPesquisarAction() {
		TableFuncionarios.getItems().clear();
		
		if(tfCodigo.getText().isEmpty() || tfCodigo.getText().equals("%")) {
			initialize();
		}
		
		Funcionario funcionario = conect.doGetFuncionario(tfCodigo.getText());
		
		FuncionarioTabela f = new FuncionarioTabela(funcionario.getId(), funcionario.getNome(), funcionario.getEndereco(), funcionario.getBairro(), funcionario.getEmail(), funcionario.getTelefone(), funcionario.getdT_Nascimento());
		listFuncionariosTabela.add(f);
	
		tbCodigo.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, Integer>("id"));
		tbNome.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("nome"));
		tbEndereco.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("endereco"));
		tbBairro.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("bairro"));
		tbEmail.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("email"));
		tbTelefone.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("telefone"));
		dT_Nascimento.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("dT_Nascimento"));

		TableFuncionarios.setItems(listFuncionariosTabela);
	}
	
	
	public void initialize() {
	
		ArrayList<Funcionario> funcionarioT = conect.doGetTodos();


		for(Funcionario funcionario : funcionarioT)
		{
			FuncionarioTabela f = new FuncionarioTabela(funcionario.getId(), funcionario.getNome(), funcionario.getEndereco(), funcionario.getBairro(), funcionario.getEmail(), funcionario.getTelefone(), funcionario.getdT_Nascimento());
			listFuncionariosTabela.add(f);
		}

		tbCodigo.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, Integer>("id"));
		tbNome.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("nome"));
		tbEndereco.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("endereco"));
		tbBairro.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("bairro"));
		tbEmail.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("email"));
		tbTelefone.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("telefone"));
		dT_Nascimento.setCellValueFactory(new PropertyValueFactory<FuncionarioTabela, String>("dT_Nascimento"));

		TableFuncionarios.setItems(listFuncionariosTabela);
		
	}
	
	public class Conectando {

		public Funcionario doGetFuncionario(String Funcionario)
		{
			String result = null;
			Funcionario funcionario = null;

			HttpClient httpClient = HttpClientBuilder.create().build();

			ObjectMapper mapper = new ObjectMapper();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao()+ "/api/funcionario/" + Funcionario);

			System.out.println(httpGet.getRequestLine());

			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				if(response != null)
				{
					HttpEntity resEntity = response.getEntity();
					result = EntityUtils.toString(resEntity);
					JSONObject obj = new JSONObject(result);
					funcionario = mapper.readValue(obj.toString(),Funcionario.class);
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

			return funcionario;
		}

		// TODO Auto-generated method stub
		public ArrayList<Funcionario> doGetTodos()
		{
			String strResposta = "";

			ObjectMapper mapper = new ObjectMapper();
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao()+ "/api/funcionario");


			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity resEnt = response.getEntity();
				strResposta = EntityUtils.toString(resEnt);
				JSONArray obj = new JSONArray(strResposta);
				Funcionario funcionario;
				for(int i = 0; i < obj.length(); i++)
				{
					funcionario = mapper.readValue(obj.getJSONObject(i).toString(), Funcionario.class);
					funcionarios.add(funcionario);
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

			return funcionarios;
		}
	}
	
	public void onBtVoltarAction() {
		
		Main.MudancaTelas("consultRelatorio");
	}
}
