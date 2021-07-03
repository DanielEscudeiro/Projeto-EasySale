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

import Dominio.ItensTabela;
import Dominio.Itens_Venda;
import Dominio.UrlApi;
import Dominio.VendaTabela;
import Dominio.pedido_item;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class reItensVendaController {

	@FXML
	private Button btVoltar;

	@FXML
	public Label lbValorTotal;

	@FXML
	private TableView<ItensTabela> TableItensVenda;

	@FXML
	private TableColumn<ItensTabela, Integer> tbCodigo;

	@FXML
	private TableColumn<ItensTabela, String> tbDescricao;

	@FXML
	private TableColumn<ItensTabela, String> tbValorUnitario;

	@FXML
	private TableColumn<ItensTabela, Integer> tbQuantidade;

	@FXML
	private TableColumn<ItensTabela, String> tbValorTotal;

	public static int x;


	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		reItensVendaController.x = x;
	}

	private ObservableList<ItensTabela> listItensTabela = FXCollections.observableArrayList();

	Conectando conect = new Conectando();
	public static String Cod_venda;
	public static String getCod_venda() {
		return Cod_venda;
	}

	public static void setCod_venda(String cod_venda) {
		Cod_venda = cod_venda;
	}

	

	@FXML
	public void onMouseEnteredAction() {

		if(x == 0)
		{
			double vTot = 0.00;
			
			TableItensVenda.getItems().clear();
			ArrayList<Itens_Venda> itemV = conect.doGetTodos(Cod_venda);

			for(Itens_Venda venda : itemV)
			{
				String vUnit = String.valueOf(Double.parseDouble(venda.getValor_Total()) / venda.getQuantidade());
				vTot = vTot + Double.parseDouble(venda.getValor_Total());

				ItensTabela i = new ItensTabela(venda.getId_Produto(), venda.getDescricao(), vUnit, venda.getQuantidade(), venda.getValor_Total());
				listItensTabela.add(i);
			}

			tbCodigo.setCellValueFactory(new PropertyValueFactory<ItensTabela, Integer>("Id_Produto"));
			tbDescricao.setCellValueFactory(new PropertyValueFactory<ItensTabela, String>("Descricao"));
			tbValorUnitario.setCellValueFactory(new PropertyValueFactory<ItensTabela, String>("valor_Unitario"));
			tbQuantidade.setCellValueFactory(new PropertyValueFactory<ItensTabela, Integer>("Quantidade"));
			tbValorTotal.setCellValueFactory(new PropertyValueFactory<ItensTabela, String>("Valor_Total"));

			TableItensVenda.setItems(listItensTabela);
			lbValorTotal.setText(String.valueOf(vTot));
			x++;
		}
	}

	public void initialize() {

	}

	public class Conectando {

		public ArrayList<Itens_Venda> doGetTodos(String cod_pedido)
		{
			String strResposta = "";

			ObjectMapper mapper = new ObjectMapper();
			ArrayList<Itens_Venda> itens = new ArrayList<Itens_Venda>();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao() + "/api/Itens/" + cod_pedido);


			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity resEnt = response.getEntity();
				strResposta = EntityUtils.toString(resEnt);
				JSONArray obj = new JSONArray(strResposta);
				Itens_Venda item;

				for(int i = 0; i < obj.length(); i++)
				{
					item = mapper.readValue(obj.getJSONObject(i).toString(), Itens_Venda.class);
					itens.add(item);
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

			return itens;
		}
	}

	@FXML
	public void onBtVoltarAction() {

		Main.MudancaTelas("reVendas");
	}
}
