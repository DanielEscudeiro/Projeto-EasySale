using Dapper.Contrib.Extensions;
using System;


namespace WebAPI.Models
{
    [Table("Usuario")]
    public class Login : BaseModel
    {
        [Key]
        public int ID { get; set; }
        public string Nome { get; set; }       
        public string Usuario { get; set; }
        public string Senha { get; set; }
    }
}
