package Dominio;

public class UrlApi {
	public static String Conexao = "Http://webapivendas.ddns.net:9000"; //localhost: http://127.0.0.1:9000//PC Alysson: Http://webapivendas.ddns.net:9000

	public static String getConexao() {
		return Conexao;
	}

	public static void setConexao(String conexao) {
		Conexao = conexao;
	}
	

}
