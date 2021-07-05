using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebAPI.Responses
{
    public class Itens_VendaResponse
    {
        public string Nr_Pedido { get; set; }
        public string Id_Produto { get; set; }
        public string Descricao { get; set; }
        public string Quantidade { get; set; }
        public string Valor_Total { get; set; }
    }
}
