using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;

namespace WebAPI.Repositories
{
    public class ProdutoRepository
    {
        public static void Gravar(Produto produto)
        {
            BaseRepository.Comand(produto);
        }
        public static void Atualizar(Produto produto)
        {
            BaseRepository.Comand(produto, true);
        }
        public static void Deletar(int id)
        {
            BaseRepository.Delete<Cliente>(id);
        }
        public static List<Produto> Buscar(int codigo)
        {
            string sql = "Select * from Produto";
            if (codigo > 0)
            {
                sql += " where id = " + codigo;
            }

            var retorno = BaseRepository.QuerySQL<Produto>(sql, codigo);

            return retorno;
        }
    }
}
