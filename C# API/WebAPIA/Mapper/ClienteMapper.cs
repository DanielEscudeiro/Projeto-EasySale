using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;
using WebAPI.Requests;
using WebAPI.Responses;

namespace WebAPI.Mapper
{
    public static class ClienteMapper
    {
        public static Cliente Mapper(ClienteRequest clienteRequest)
        {
            return new Cliente()
            {
                Id = clienteRequest.ID,
                Nome = clienteRequest.Nome,
                Email = clienteRequest.Email,
                DT_Nascimento = clienteRequest.DT_Nascimento,
                Bairro = clienteRequest.Bairro,
                Endereco = clienteRequest.Endereco                
            };
        }


        public static ClienteResponse Mapper(Cliente cliente)
        {
            return new ClienteResponse()
            {
                Id = cliente.Id.ToString(),
                Nome = cliente.Nome,
                Email = cliente.Email,
                DT_Nascimento = cliente.DT_Nascimento.ToString(),
                Bairro = cliente.Bairro,
                Endereco = cliente.Endereco
            };
        }
    }
}
