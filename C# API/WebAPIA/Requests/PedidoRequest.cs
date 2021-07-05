using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebAPI.Requests
{
    public class PedidoRequest
    {
        public int Nr_Pedido { get; set; }
        public string DT_Pedido { get; set; }
        public string Tipo { get; set; }
        public int Id_Cliente { get; set; }
        // public ClienteRequest Cliente { get; set; }
        public List<PedidoItemRequest> Itens { get; set; }
    }
}


