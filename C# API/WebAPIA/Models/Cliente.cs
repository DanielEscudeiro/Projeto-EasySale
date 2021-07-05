using Dapper.Contrib.Extensions;
using System;

namespace WebAPI.Models
{
    [Table("Cliente")]
    public class Cliente : BaseModel
    {
        [Key]
        public int Id { get; set; }
        public string Nome { get; set; }
        public string Endereco { get; set; }
        public string Bairro { get; set; }
        public string Email { get; set; }
        public string DT_Nascimento { get; set; }
    }
}
