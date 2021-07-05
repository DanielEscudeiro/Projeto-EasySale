using Dapper.Contrib.Extensions;
using System;
using System.Collections.Generic;

namespace WebAPI.Models
{
    public class Pedido : BaseModel
    {
        public Pedido()
        {
            Itens = new List<PedidoItem>();
        }

        public int Nr_Pedido { get; set; }
        public string DT_Pedido { get; set; }
        public string Tipo { get; set; }
        public Cliente Cliente { get; set; }
        public List<PedidoItem> Itens { get; set; }
    }
}
