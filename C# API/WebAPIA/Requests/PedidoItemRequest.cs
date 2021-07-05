using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebAPI.Requests
{
    public class PedidoItemRequest
    {
        public int Id { get; set; }
        public int Id_Produto { get; set; }
        public int Quantidade { get; set; }
        public decimal Valor_Unitario { get; set; }

        //public ProdutoRequest Produto { get; set; }
    }
}

