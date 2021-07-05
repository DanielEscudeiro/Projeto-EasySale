using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebAPI.Requests
{
    public class Itens_VendaRequest
    {
        public int Nr_Pedido { get; set; }
        public int Id_Produto { get; set; }
        public string Descricao { get; set; }
        public int Quantidade { get; set; }
        public string Valor_Total { get; set; }
    }
}
