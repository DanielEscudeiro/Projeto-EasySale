using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;

namespace WebAPI.Repositories
{
    public class PedidoItemRepository
    {
        public static void Gravar(PedidoItem pedidoItem)
        {
            BaseRepository.Comand(pedidoItem);
        }
        public static void Atualizar(PedidoItem pedidoItem)
        {
            BaseRepository.Comand(pedidoItem, true);
        }
        public static void Deletar(int id)
        {
            BaseRepository.Delete<PedidoItem>(id);
        }
    }
}
