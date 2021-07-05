using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;

namespace WebAPI.Repositories
{
    public class ClienteRepository
    {
        public static void Gravar(Cliente cliente)
        {
            BaseRepository.Comand(cliente);
        }
        public static void Atualizar(Cliente cliente)
        {
            BaseRepository.Comand(cliente, true);
        }

        public static void Deletar(int id)
        {
            BaseRepository.Delete<Cliente>(id);
        }

        public static List<Cliente> Buscar(int codigo)
        {
            string sql = "Select * from Cliente";

            if (codigo  > 0)
            {
                sql += " where Id = " + codigo;
            }
           
            var retorno = BaseRepository.QuerySQL<Cliente>(sql, codigo);

            return retorno;
        
        }
    }
}
