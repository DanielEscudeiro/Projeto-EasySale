using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebAPI.Responses
{
    public class PedidoResponse
    {
        public string Nr_Pedido { get; set; }
        public string DT_Pedido { get; set; }
        public string Tipo { get; set; }
        public ClienteResponse Cliente { get; set; }
        public List<PedidoItemResponse> Itens { get; set; }
    }
}
