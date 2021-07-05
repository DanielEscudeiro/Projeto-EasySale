using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebAPI.Responses
{
    public class ProdutoResponse
    {
        public string ID { get; set; }
        public string Descricao { get; set; }
        public string Quantidade { get; set; }
        public string Valor { get; set; }
    }
}
