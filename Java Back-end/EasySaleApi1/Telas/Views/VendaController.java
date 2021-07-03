package Views;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.prism.paint.Color;

import Dominio.Alterando;
import Dominio.Produto;
import Dominio.UrlApi;
import Dominio.Usuario;
import Dominio.UsuarioNome;
import Dominio.VendaTabela;
import Dominio.pedido_item;
import Util.GerarHash;
import Util.TextFieldFormatter;
import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VendaController {

	@FXML
	public TextField tfNome;

	@FXML
	public Label lbValorTotal;

	@FXML
	private TextField tfValorUnitario;

	@FXML
	private TextField tfQuantidade;

	@FXML
	private TextField tfDtPedido;

	@FXML
	private TextField tfDescricao;

	@FXML
	private TextField tfCodigo;

	@FXML
	private TextField tfValorTotal;

	@FXML
	private TableView<VendaTabela> TableVenda;

	@FXML
	private TableColumn<VendaTabela, String> tbCodigo;

	@FXML
	private TableColumn<VendaTabela, String> tbDescricao;

	@FXML
	private TableColumn<VendaTabela, String> tbValorUnitario;

	@FXML
	private TableColumn<VendaTabela, String> tbQuantidade;

	@FXML
	private TableColumn<VendaTabela, String> tbValorTotal;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btPesquisar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btDeletar;

	private ObservableList<VendaTabela> listVendaTabela = FXCollections.observableArrayList();


	Conectando conect = new Conectando();

	final ArrayList<pedido_item> itens_pedido = new ArrayList<pedido_item>();

	double valor = 0.0;

	public static String codCliente;

	public static String getCodCliente() {
		return codCliente;
	}

	public static void setCodCliente(String codCliente) {
		VendaController.codCliente = codCliente;
	}

	public static String nomeClienteRetorno;

	public static String getNomeClienteRetorno() {
		return nomeClienteRetorno;
	}

	public static void setNomeClienteRetorno(String nomeClienteRetorno) {
		VendaController.nomeClienteRetorno = nomeClienteRetorno;
	}

	double valorTotalVenda;

	@FXML
	public void tfDataKeyRelased() {

		TextFieldFormatter dt = new TextFieldFormatter();
		dt.setMask("##/##/####");
		dt.setCaracteresValidos("0123456789");
		dt.setTf(tfDtPedido);
		dt.formatter();

	}

	@FXML
	public void tfQuantidadeKeyRelased(){

		if (!tfQuantidade.getText().isEmpty()) {

			double valorTotal;
			valorTotal = valor * Integer.parseInt(tfQuantidade.getText());
			if(tfQuantidade.getText().contains("-"))
			{
				tfValorTotal.setText("0.0");
			}
			else {
				tfValorTotal.setText(String.valueOf(valorTotal));
			}
		}			
		else
		{
			tfValorTotal.setText(null);
		}
	}

	public void initialize() {
		tfNome.setEditable(false); 
		tfValorTotal.setEditable(false);
		tfValorUnitario.setEditable(false);
		tfDescricao.setEditable(false);
		tfQuantidade.setEditable(false);

		tfCodigo.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
			{

				if (oldPropertyValue)
				{
					if (!tfCodigo.getText().isEmpty())
					{
						Produto produtoProd  =  conect.doGetProduto(tfCodigo.getText());

						tfDescricao.setText(produtoProd.getDescricao());
						tfValorUnitario.setText(String.valueOf(produtoProd.getValor()));
						valor = Double.parseDouble(tfValorUnitario.getText());
						tfQuantidade.setText("0");
						tfQuantidade.setEditable(true);
						tfQuantidade.requestFocus();
					}

				}
			}
		});

		tfQuantidade.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
			{

				if(oldPropertyValue)
				{

					if(!tfQuantidade.getText().isEmpty())
					{
						if (Integer.parseInt(tfQuantidade.getText()) != 0)
						{
							if(Integer.parseInt(tfQuantidade.getText()) > 0)
							{


								double valorTotal;
								valorTotal = valor * Integer.parseInt(tfQuantidade.getText());
								tfValorTotal.setText(String.valueOf(valorTotal));


								pedido_item item = new pedido_item();
								item.setId_Produto(Integer.parseInt(tfCodigo.getText()));
								item.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
								item.setValor_Unitario(tfValorTotal.getText());

								itens_pedido.add(item);


								VendaTabela v = new VendaTabela(tfCodigo.getText(), tfDescricao.getText(), tfValorUnitario.getText(), tfQuantidade.getText(), tfValorTotal.getText());
								listVendaTabela.add(v);


								tbCodigo.setCellValueFactory(new PropertyValueFactory<VendaTabela, String>("id"));
								tbDescricao.setCellValueFactory(new PropertyValueFactory<VendaTabela, String>("descricao"));
								tbValorUnitario.setCellValueFactory(new PropertyValueFactory<VendaTabela, String>("valor"));
								tbQuantidade.setCellValueFactory(new PropertyValueFactory<VendaTabela, String>("quantidade"));
								tbValorTotal.setCellValueFactory(new PropertyValueFactory<VendaTabela, String>("valorTotal"));

								TableVenda.setItems(listVendaTabela);

								tfCodigo.clear();
								tfDescricao.clear();
								tfValorUnitario.clear();
								tfQuantidade.clear();
								tfValorTotal.clear();
								lbValorTotal.equals(null);

								valorTotalVenda = valorTotalVenda + valorTotal;
								lbValorTotal.setText(String.valueOf(valorTotalVenda));

								tfQuantidade.setEditable(false);
								tfCodigo.requestFocus();
							}
						}
					}
				}                                                                                                                                                                                                                                                                                                                                                           
			}
		});
	}

	@FXML
	public void onMouseMovedAction() {
		tfNome.setText(nomeClienteRetorno);
	}
	@FXML
	public void onBtSalvarAction()
	{
		if((nomeClienteRetorno == "") || tfDtPedido.getText().isEmpty() || itens_pedido.isEmpty()) {

			Alerts.showAlert("EasySale", null, "O nome do cliente, a data do pedido e os itens da venda devem ser preenchidos!", AlertType.ERROR);
		}
		else {

			if(Alterando.isAlterando() == false)
			{
				Conectando conect = new Conectando();

				if (conect.doPostVenda() == true)
				{
					tfNome.clear();

					if(listVendaTabela.size() != 1)
					{
						for(int i = -1; i <= listVendaTabela.size(); i++) 
						{		
							listVendaTabela.remove(0);
						}
					}
					else
					{
						listVendaTabela.remove(0);
					}

					itens_pedido.removeAll(itens_pedido);

					TableVenda.refresh();

					nomeClienteRetorno = "";

					tfCodigo.clear();
					tfDescricao.clear();
					tfValorUnitario.clear();
					tfQuantidade.clear();
					tfValorTotal.clear();
					tfDtPedido.clear();
					lbValorTotal.setText(null);

					Alerts.showAlert("EasySale", null, "A venda foi cadastrada com sucesso", AlertType.CONFIRMATION);

					//Mensagem cadastrado com sucesso

				}
				else {
					Alerts.showAlert("EasySale", null, "A venda não foi cadastrada no sistema, tente novamente e verifique se todos os campos estão preenchidos!", AlertType.ERROR);
				}
			}
		}
	}

	@FXML
	public void onBtPesquisarAction()
	{
		Main.MudancaTelas("consultClientes");
	}

	@FXML
	public void onBtDeletarAction()
	{
		if(TableVenda.getSelectionModel().isEmpty()) {
			Alerts.showAlert("EasySale", null, "Selecione uma coluna!", AlertType.ERROR);
		}
		else {

			valorTotalVenda = valorTotalVenda - Double.parseDouble(TableVenda.getSelectionModel().getSelectedItem().getValorTotal());

			itens_pedido.remove(TableVenda.getSelectionModel().getSelectedIndex());

			listVendaTabela.remove(TableVenda.getSelectionModel().getSelectedIndex());

			TableVenda.refresh();

			lbValorTotal.setText(String.valueOf(valorTotalVenda));
		}

	}

	public class Conectando
	{

		public Boolean doPostVenda()
		{

			//pedido_item item = new pedido_item();


			ObjectMapper mapper = new ObjectMapper();
			HttpPost post = new HttpPost(UrlApi.getConexao()+ "/api/pedido");

			post.addHeader("content-Type", "application/json");
			try {
				StringBuilder json = new StringBuilder();
				json.append("{");
				json.append("\"dt_pedido\":\"" + tfDtPedido.getText() + "\", \n");
				json.append("\"tipo\":\"" + "V" + "\", \n");
				json.append("\"id_cliente\":" +  Integer.parseInt(codCliente) + ", \n");
				json.append("\"itens\":["); 


				int ind = 0;
				for(pedido_item i : itens_pedido)
				{
					ind++;
					json.append("{");
					json.append("\"id_produto\":" + i.getId_Produto() + ", \n");
					json.append("\"quantidade\":" + i.getQuantidade() + ", \n");
					json.append("\"valor_unitario\":" + i.getValor_Unitario() + "\n");						

					if(ind < itens_pedido.size())
					{
						json.append("},");
					}
					else
					{
						json.append("}");
					}

				} 


				json.append("] \n");
				json.append("}");

				post.setEntity(new StringEntity(json.toString()));
				HttpClient httpClient = HttpClients.createDefault();
				HttpResponse response;
				response = httpClient.execute(post);

				if( response.getStatusLine().getStatusCode() == 200)
				{
					return true;	
				}
			}
			catch(Exception ex) {

				Alerts.showAlert("EasySale", null, "A venda não foi cadastrada no sistema, verifique sua conexão com a internet e tente novamente!", AlertType.ERROR);
			}
			return false;	
		}

		public Produto doGetProduto(String codProduto)
		{
			String result = null;
			Produto produto = null;

			HttpClient httpClient = HttpClientBuilder.create().build();

			ObjectMapper mapper = new ObjectMapper();
			HttpGet httpGet = new HttpGet(UrlApi.getConexao() + "/api/produto/" + codProduto);

			System.out.println(httpGet.getRequestLine());

			try
			{
				HttpResponse response = httpClient.execute(httpGet);
				if(response != null)
				{
					HttpEntity resEntity = response.getEntity();
					result = EntityUtils.toString(resEntity);
					JSONObject obj = new JSONObject(result);
					produto = mapper.readValue(obj.toString(), Produto.class);
				}
				else
				{
					//System.out.println("Erro com API Nada Encontrado"); Aqui tera criar outro metodo pois ele mapeia os produto dentro de produto , mas por vias das duvidas faz um teste
				}
			}
			catch (Exception ex)
			{
				//System.out.println(ex.getMessage() + "   TESTE");
			}

			return produto;

		}
	}

	@FXML
	public void onBtVoltarAction() {

		Main.MudancaTelas("menu");
	}
}

