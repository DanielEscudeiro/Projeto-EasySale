using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;

namespace WebAPI.Repositories
{
    public class Itens_VendaRepository
    {
        public static List<Itens_Venda> Buscar(int codigo)
        {
            string sql = "SELECT * FROM itens_venda";

            if (codigo > 0)
            {
                sql += " where Nr_Pedido = " + codigo;
            }

            var retorno = BaseRepository.QuerySQL<Itens_Venda>(sql, codigo);

            return retorno;

        }
    }
}
