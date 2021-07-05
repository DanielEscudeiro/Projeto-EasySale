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
using WebAPI.Responses;

namespace WebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class LoginController : ControllerBase
    {
        [HttpGet ("User:{usuario}/Password:{senha}")]
        public ActionResult<Login> Get(string usuario, string senha)
        {
            return LoginRepository.Buscar(usuario, senha).FirstOrDefault();
        }

        [HttpPost]
        public ActionResult<ReturnResponse> Post([FromBody] LoginRequest request)
        {
            var login= LoginMapper.Mapper(request);
            LoginRepository.Gravar(login);

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Cadastrado com Sucesso",
            };

            return retorno;
        }
    }
}
