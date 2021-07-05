using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;

namespace WebAPI.Repositories
{
    public class FuncionarioRepository
    {
        public static void Gravar(Funcionario funcionario)
        {
            BaseRepository.Comand(funcionario);
        }
        public static void Atualizar(Funcionario funcionario)
        {
            BaseRepository.Comand(funcionario, true);
        }

        public static void Deletar(int id)
        {
            BaseRepository.Delete<Funcionario>(id);
        }

        public static List<Funcionario> Buscar(int codigo)
        {
            string sql = "Select * from Funcionarios";

            if (codigo > 0)
            {
                sql += " where Id = " + codigo;
            }

            var retorno = BaseRepository.QuerySQL<Funcionario>(sql, codigo);

            return retorno;

        }
    }
}
