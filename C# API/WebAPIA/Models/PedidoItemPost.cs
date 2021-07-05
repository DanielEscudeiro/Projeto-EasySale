using Dapper.Contrib.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebAPI.Models
{
    [Table("PedidoItem")]
    public class PedidoItemPost : BaseModel
    {
        [Key]
        public int Id { get; set; }
        public int Quantidade { get; set; }
        public decimal Valor_Unitario { get; set; }
        public int Id_Produto { get; set; }

    }
}
