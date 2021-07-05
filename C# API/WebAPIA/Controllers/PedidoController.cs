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
    public class PedidoController : ControllerBase
    {
        [HttpGet]
        public ActionResult<List<PedidoResponse>> Get()
        {
            var produtos = PedidoRepository.Buscar().Select(p => PedidoMapper.Mapper(p)) ;

            return produtos.ToList();
        }

        [HttpGet("{Nr_Pedido}")]
        public ActionResult<PedidoResponse> Get(int Nr_Pedido)
        {
            var produto = PedidoMapper.Mapper(PedidoRepository.Buscar(Nr_Pedido).FirstOrDefault());

            return produto;
        }

        [HttpPost]
        public ActionResult<ReturnResponse> Post([FromBody] PedidoRequest request)
        {
            var pedido = PedidoMapper.Mapper(request);
            PedidoRepository.Gravar(pedido);

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Cadastrado com Sucesso",
            };

            return retorno;
        }

        [HttpPut]
        public ActionResult<ReturnResponse> Put([FromBody] Pedido request)
        {

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Atualizado com Sucesso"
            };

            return retorno;
        }

        [HttpDelete("{id}")]
        public ActionResult<ReturnResponse> Delete(string id)
        {
            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Deletado com Sucesso"
            };

            return retorno;
        }
    }
}
