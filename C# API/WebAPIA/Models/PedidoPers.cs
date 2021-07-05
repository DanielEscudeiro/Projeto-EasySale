using Dapper.Contrib.Extensions;
using System;
using System.Collections.Generic;

namespace WebAPI.Models
{
    public class PedidoPers : BaseModel
    {
        public int Nr_Pedido { get; set; }
        public string DT_Pedido { get; set; }
        public string Tipo { get; set; }
        public int id_Cliente { get; set; }
    }

    public class PedidoItemPers : BaseModel
    {
        public int Id { get; set; }
        public int nr_pedido { get; set; }
        public int Quantidade { get; set; }
        public decimal Valor_Unitario { get; set; }
        public int Id_Produto { get; set; }
    }
}
