using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;
using WebAPI.Requests;
using WebAPI.Responses;

namespace WebAPI.Mapper
{
    public class PedidoMapper
    {
        public static PedidoPost Mapper(PedidoRequest pedidoRequest)
        {
            return new PedidoPost()
            {
                Nr_Pedido = pedidoRequest.Nr_Pedido,
                Tipo = pedidoRequest.Tipo,
                DT_Pedido = pedidoRequest.DT_Pedido,
                Id_Cliente = pedidoRequest.Id_Cliente,
                Itens = pedidoRequest.Itens.Select(p => PedidoItemMapper.Mapper(p)).ToList(),
              
            };
        }

        public static PedidoResponse Mapper(Pedido pedido)
        {
            return new PedidoResponse()
            {
                Nr_Pedido = pedido.Nr_Pedido.ToString(),
                Tipo = pedido.Tipo.ToString(),
                DT_Pedido = pedido.DT_Pedido.ToString(),
                Cliente = ClienteMapper.Mapper(pedido.Cliente),
                Itens = pedido.Itens.Select(p => PedidoItemMapper.Mapper(p)).ToList()
            };
        }

    }
}
