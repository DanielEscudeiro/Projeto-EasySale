using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;
using WebAPI.Requests;
using WebAPI.Responses;

namespace WebAPI.Mapper
{
    public class Itens_VendaMapper
    {
        public static Itens_Venda Mapper(Itens_VendaRequest itens_VendaRequest)
        {
            return new Itens_Venda()
            {
                Nr_Pedido = itens_VendaRequest.Nr_Pedido,
                Id_Produto = itens_VendaRequest.Id_Produto,
                Descricao = itens_VendaRequest.Descricao,
                Quantidade = itens_VendaRequest.Quantidade,
                Valor_Total = itens_VendaRequest.Valor_Total            
            };
        }


        public static Itens_VendaResponse Mapper(Itens_Venda itens_Venda)
        {
            return new Itens_VendaResponse()
            {
                Nr_Pedido = itens_Venda.Nr_Pedido.ToString(),
                Id_Produto = itens_Venda.Id_Produto.ToString(),
                Descricao = itens_Venda.Descricao,
                Quantidade = itens_Venda.Quantidade.ToString(),
                Valor_Total = itens_Venda.Valor_Total

            };
        }
    }
}

