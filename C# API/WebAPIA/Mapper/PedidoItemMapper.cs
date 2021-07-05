using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;
using WebAPI.Repositories;
using WebAPI.Requests;
using WebAPI.Responses;

namespace WebAPI.Mapper
{
    public class PedidoItemMapper
    {
        public static PedidoItem Mapper(PedidoItemRequest PedidoItemRequest)
        {
            return new PedidoItem()
            {
                Id = PedidoItemRequest.Id,
                Id_Produto = PedidoItemRequest.Id_Produto,
                //Produto = ProdutoMapper.Mapper(PedidoItemRequest.Produto),
                Quantidade = PedidoItemRequest.Quantidade,
                Valor_Unitario = PedidoItemRequest.Valor_Unitario
            };
        }

        public static PedidoItemResponse Mapper(PedidoItem PedidoItem)
        {
            return new PedidoItemResponse()
            {
                Id = PedidoItem.Id.ToString(),
                Id_Produto = PedidoItem.Id_Produto.ToString(),
                //Produto = ProdutoMapper.Mapper(PedidoItem.Produto),
                Produto = ProdutoMapper.Mapper(ProdutoRepository.Buscar(PedidoItem.Id_Produto)[0]),
                Quantidade = PedidoItem.Quantidade.ToString(),
                Valor_Unitario = PedidoItem.Valor_Unitario.ToString()
            };
        }
    }
}
