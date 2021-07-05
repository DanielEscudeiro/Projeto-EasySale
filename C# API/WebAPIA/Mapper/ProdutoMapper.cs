using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;
using WebAPI.Requests;
using WebAPI.Responses;

namespace WebAPI.Mapper
{
    public class ProdutoMapper
    {
        public static Produto Mapper(ProdutoRequest produtoRequest)
        {
            return new Produto()
            {
                Id = produtoRequest.ID,
                Descricao = produtoRequest.Descricao,
                Quantidade = produtoRequest.Quantidade,
                Valor = produtoRequest.Valor
            };
        }

        public static ProdutoResponse Mapper(Produto produto)
        {
            return new ProdutoResponse()
            {
                ID = produto.Id.ToString(),
                Descricao = produto.Descricao,
                Quantidade = produto.Quantidade.ToString(),
                Valor = produto.Valor.ToString()
            };
        }
    }
}
