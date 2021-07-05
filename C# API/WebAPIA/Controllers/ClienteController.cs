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
    public class ClienteController : ControllerBase
    {
        [HttpGet]
        public ActionResult<List<Cliente>> Get()
        {
            return ClienteRepository.Buscar(0);
        }

        [HttpGet("{id}")]
        public ActionResult<Cliente> Get(int id)
        {
            return ClienteRepository.Buscar(id).FirstOrDefault();
        }

        [HttpPost]
        public ActionResult<ReturnResponse> Post([FromBody] ClienteRequest request)
        {
            var cliente = ClienteMapper.Mapper(request);
            ClienteRepository.Gravar(cliente);

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Cadastrado com Sucesso",
            };

            return retorno;
        }

        [HttpPut]
        public ActionResult<ReturnResponse> Put([FromBody] Cliente request)
        {
            ClienteRepository.Atualizar(request);
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

            ClienteRepository.Deletar(id);

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Deletado com Sucesso"
            };

            return retorno;
        }
    }
}
