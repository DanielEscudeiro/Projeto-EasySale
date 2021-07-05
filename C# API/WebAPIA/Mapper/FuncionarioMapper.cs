using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;
using WebAPI.Requests;
using WebAPI.Responses;

namespace WebAPI.Mapper
{
    public class FuncionarioMapper
    {
        public static Funcionario Mapper(FuncionarioRequest funcionarioRequest)
        {
            return new Funcionario()
            {
                Id = funcionarioRequest.ID,
                Nome = funcionarioRequest.Nome,
                Endereco = funcionarioRequest.Endereco,
                Email = funcionarioRequest.Email,
                Bairro = funcionarioRequest.Bairro,
                Telefone = funcionarioRequest.Telefone,
                DT_Nascimento = funcionarioRequest.DT_Nascimento
            };
        }

        public static FuncionarioResponse Mapper(Funcionario funcionario)
        {
            return new FuncionarioResponse()
            {
                Id = funcionario.Id.ToString(),
                Nome = funcionario.Nome,
                Endereco = funcionario.Endereco,
                Email = funcionario.Email,
                Bairro = funcionario.Bairro,
                Telefone = funcionario.Telefone,
                DT_Nascimento = funcionario.DT_Nascimento
            };
        }
    }
}
