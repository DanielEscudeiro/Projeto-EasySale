using Dapper;
using Dapper.Contrib.Extensions;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;

namespace WebAPI.Repositories
{
    public class BaseRepository
    {
        public static List<T> QuerySQL<T>(string sql, object parameter = null)
        {
            List<T> orderDetail;
            using (var connection = new MySqlConnection("Server = localhost; Port = 3306; Database = sistemavendas; User Id = root; Password = masterkey"))
            {
                orderDetail = connection.Query<T>(sql, parameter).ToList();
            }
            return orderDetail;
        }


        public static void Delete<T>(int id) where T : BaseModel
        {
            using (var connection = new MySqlConnection("Server = localhost; Port = 3306; Database = sistemavendas; User Id = root; Password = masterkey"))
            {
                string query = $"select * from {typeof(T).Name} where id = " + id;
                var objeto = connection.Query<T>(query, new { id });
                connection.Delete(objeto);
            }
        }

        public static long Comand<T>(T objeto, bool editar = false, object parameter = null) where T : BaseModel
        {
            long retorno = 0;
            using (var connection = new MySqlConnection("Server = localhost; Port = 3306; Database = sistemavendas; User Id = root; Password = masterkey"))
            {
                if (editar)
                {
                    connection.Update(objeto);
                }
                else
                    retorno = connection.Insert(objeto);
            }

            return retorno;
        }


        public static List<T> ComandPedido<T>(T objeto, bool editar = false, object parameter = null) where T : BaseModel
        {
            List<T> orderDetail;
                
            using (var connection = new MySqlConnection("Server = localhost; Port = 3306; Database = sistemavendas; User Id = root; Password = masterkey"))
            {
                 connection.Execute(@"");
               
            }

            return null;
        }
    }
}