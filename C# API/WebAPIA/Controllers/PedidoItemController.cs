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
    public class PedidoItemController : ControllerBase
    {
        [HttpPost]
        public ActionResult<ReturnResponse> Post([FromBody] PedidoItemRequest request)
        {
            var pedidoItem = PedidoItemMapper.Mapper(request);
            PedidoItemRepository.Gravar(pedidoItem);

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Cadastrado com Sucesso",
            };

            return retorno;
        }
     
        [HttpDelete("{id}")]
        public ActionResult<ReturnResponse> Delete(int id)
        {

            PedidoItemRepository.Deletar(id);

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Deletado com Sucesso"
            };

            return retorno;
        }
    }
}
