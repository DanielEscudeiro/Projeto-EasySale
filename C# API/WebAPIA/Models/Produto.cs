using Dapper.Contrib.Extensions;
using System;
using System.Collections.Generic;

namespace WebAPI.Models
{
    [Table("Produto")]
    public class Produto : BaseModel
    {
        [Key]
        public int Id { get; set; }
        public string Descricao { get; set; }
        public int Quantidade { get; set; }
        public string Valor { get; set; }

       
    }
}

