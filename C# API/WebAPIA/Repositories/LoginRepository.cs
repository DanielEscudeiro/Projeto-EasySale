using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;


namespace WebAPI.Repositories
{
    public class LoginRepository
    {
        public static void Gravar(Login usuario)
        {
            BaseRepository.Comand(usuario);
        }
        public static List<Login> Buscar(string Usuario, string Senha)
        {
            string sql = "Select * from usuario";

            sql += $" where Usuario = '{Usuario}' " +
                   $"   And Senha = '{Senha}'";                     
            

            var retorno = BaseRepository.QuerySQL<Login>(sql);

            return retorno;

        }
    }
}
