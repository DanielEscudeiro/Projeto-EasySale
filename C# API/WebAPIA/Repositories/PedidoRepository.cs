using Dapper;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;

namespace WebAPI.Repositories
{
    public class PedidoRepository
    {
        public static void Gravar(PedidoPost pedido)
        {
            long codigoPedido;
            PedidoPers pedidoPers = new PedidoPers
            {
                DT_Pedido = pedido.DT_Pedido,
                id_Cliente = pedido.Id_Cliente,
                Tipo = pedido.Tipo
            };

            codigoPedido = BaseRepository.Comand(pedidoPers);

            foreach(PedidoItem pItem in pedido.Itens)
            {
                PedidoItemPers pedidoItemPers = new PedidoItemPers
                {
                    Id_Produto = pItem.Id_Produto,
                    nr_pedido = Convert.ToInt32(codigoPedido),
                    Quantidade = pItem.Quantidade,
                    Valor_Unitario = pItem.Valor_Unitario
                };
                BaseRepository.Comand(pedidoItemPers);
            }
        }
        public static void Atualizar(Pedido pedido)
        {
            BaseRepository.Comand(pedido, true);
        }
        public static List<Pedido> BuscarCopia(int nr_Pedido = 0, int clienteId = 0)
        {
            string sql = @"Select  p.Nr_Pedido,
		                           p.Data,
		                           p.Tipo,
                                   p.id_cliente,
		                           c.id,
		                           c.Nome,
		                           c.Email,
		                           c.Dt_Nascimento
                              from pedido P
                              join cliente c on p.id_cliente = c.Id
                              join pedidoItem pi on p.Nr_Pedido = pi.Nr_Pedido";

            if (nr_Pedido > 0)
            {
                sql += " where Nr_Pedido = @Nr_Pedido";
            }

            if (clienteId > 0)
            {
                if (sql.Contains("where"))
                {
                    sql += " and id_Cliente = @Id_Cliente";
                }
                else
                    sql += " where id_Cliente = @Id_Cliente";
            }

            List<Pedido> orderDetail;
            // List<PedidoItem> pedidoItemProd;

            using (var connection = new MySqlConnection("Server = localhost; Port = 3306; Database = sistemavendas; User Id = root; Password = masterkey"))
            {
                orderDetail = connection.Query<Pedido, Cliente, Pedido>(sql, map: mapPropiedades, splitOn: "id", param: new { Nr_Pedido = nr_Pedido, ID_Cliente = clienteId }).ToList();
                //pedidoItemProd = connection.Query<PedidoItem, Produto, PedidoItem>(sql, map: mapPropiedadesProd, splitOn: "id_produto", param: new { Nr_Pedido = nr_Pedido }).ToList();
            }

            const string sqlItem = @"Select *from pedidoitem where Nr_Pedido = @Nr_Pedido";
            //const string sqlProd = @"Select *from produto where id = @Id_Produto";

            foreach (var item in orderDetail)
            {

                using (var connection = new MySqlConnection("Server = localhost; Port = 3306; Database = sistemavendas; User Id = root; Password = masterkey"))
                {
                    item.Itens.AddRange(connection.Query<PedidoItem>(sqlItem, param: new { Nr_Pedido = item.Nr_Pedido }).ToList());
                    //((PedidoItem)item.Itens[item.Itens.length - 1]).Produto;
                    //((Produto)item.Itens[item.Itens.Length - 1]).PedidoItem;
                }
            }

            return orderDetail;
        }

        public static List<Pedido> Buscar(int nr_Pedido = 0, int clienteId = 0)
        {
            string sql = @"Select  p.Nr_Pedido,
		                           p.DT_Pedido,
		                           p.Tipo,
                                   p.id_cliente,
		                           c.id,
		                           c.Nome,
		                           c.Email,
		                           c.Dt_Nascimento
                              from pedidoperss P
                              join cliente c on p.id_cliente = c.Id";

            if (nr_Pedido > 0)
            {
                sql += " where Nr_Pedido = @Nr_Pedido";
            }

            if (clienteId > 0)
            {
                if (sql.Contains("where"))
                {
                    sql += " and id_Cliente = @Id_Cliente";
                }
                else
                    sql += " where id_Cliente = @Id_Cliente";
            }

            List<Pedido> orderDetail;
            // List<PedidoItem> pedidoItemProd;

            using (var connection = new MySqlConnection("Server = localhost; Port = 3306; Database = sistemavendas; User Id = root; Password = masterkey"))
            {
                orderDetail = connection.Query<Pedido, Cliente, Pedido>(sql, map: mapPropiedades, splitOn: "id", param: new { Nr_Pedido = nr_Pedido, ID_Cliente = clienteId }).ToList();
                //pedidoItemProd = connection.Query<PedidoItem, Produto, PedidoItem>(sql, map: mapPropiedadesProd, splitOn: "id_produto", param: new { Nr_Pedido = nr_Pedido }).ToList();
            }

            string sqlItem = "SELECT p.ID, p.Nr_Pedido, p.Quantidade, p.Id_Produto, p.Valor_Unitario, PR.Id, pr.Descricao, pr.Quantidade, pr.Valor";
                sqlItem += " FROM pedidoitemperss p";
                sqlItem += " INNER JOIN PRODUTO pr ON pr.id = p.id_produto";
                sqlItem += " where Nr_Pedido = @Nr_Pedido";

            //const string sqlProd = @"Select *from produto where id = @Id_Produto";

            foreach (var item in orderDetail)
            {

                using (var connection = new MySqlConnection("Server = localhost; Port = 3306; Database = sistemavendas; User Id = root; Password = masterkey"))
                {
                    item.Itens.AddRange(connection.Query<PedidoItem, Produto, PedidoItem>(sqlItem, map: mapProduto, param: new { Nr_Pedido = item.Nr_Pedido }).ToList());
                    //((PedidoItem)item.Itens[item.Itens.length - 1]).Produto;
                    //((Produto)item.Itens[item.Itens.Length - 1]).PedidoItem;
                }
            }

            return orderDetail;
        }

        private static Pedido mapPropiedades(Pedido pedido, Cliente cliente)
        {
            pedido.Cliente = cliente;

            return pedido;
        }

        private static PedidoItem mapProduto(PedidoItem pedidoItem, Produto produto)
        {
            pedidoItem.Produto = produto;

            return pedidoItem;
        }

    }
}
