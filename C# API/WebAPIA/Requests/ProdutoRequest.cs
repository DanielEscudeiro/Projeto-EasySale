using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebAPI.Requests
{
    public class ProdutoRequest
    {
        public int ID { get; set; }
        public string Descricao { get; set; }
        public int Quantidade { get; set; }
        public string Valor { get; set; }
    }
}
