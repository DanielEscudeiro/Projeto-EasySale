using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Mapper;
using WebAPI.Models;
using WebAPI.Repositories;
using WebAPI.Requests;
using WebAPI.Responses;

namespace WebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FuncionarioController : ControllerBase
    {

        [HttpGet]
        public ActionResult<List<Funcionario>> Get()
        {
            return FuncionarioRepository.Buscar(0);
        }

        [HttpGet("{id}")]
        public ActionResult<Funcionario> Get(int id)
        {
            return FuncionarioRepository.Buscar(id).FirstOrDefault();
        }

        [HttpPost]
        public ActionResult<ReturnResponse> Post([FromBody] FuncionarioRequest request)
        {
            var funcionario = FuncionarioMapper.Mapper(request);
            FuncionarioRepository.Gravar(funcionario);

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Cadastrado com Sucesso",
            };

            return retorno;
        }

        [HttpPut]
        public ActionResult<ReturnResponse> Put([FromBody] Funcionario request)
        {
            FuncionarioRepository.Atualizar(request);
            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Atualizado com Sucesso"
            };

            return retorno;
        }

        [HttpDelete("{id}")]
        public ActionResult<ReturnResponse> Delete(int id)
        {

            FuncionarioRepository.Deletar(id);

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Deletado com Sucesso"
            };

            return retorno;
        }
    }
}
