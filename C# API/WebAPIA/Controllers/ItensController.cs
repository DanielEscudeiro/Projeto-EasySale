using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Mapper;
using WebAPI.Repositories;
using WebAPI.Responses;

namespace WebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ItensController : ControllerBase
    {
        [HttpGet("{Nr_Pedido}")]
        public ActionResult<List<Itens_VendaResponse>> Get(int Nr_Pedido)
        {
            var itens = Itens_VendaRepository.Buscar(Nr_Pedido).Select(p => Itens_VendaMapper.Mapper(p));

            return itens.ToList();
        }
    }
}
