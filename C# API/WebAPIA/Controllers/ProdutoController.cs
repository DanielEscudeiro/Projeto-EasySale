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
    public class ProdutoController : ControllerBase
    {
        [HttpGet]
        public ActionResult<List<ProdutoResponse>> Get()
        {
            var produtos = ProdutoRepository.Buscar(0).Select(p => ProdutoMapper.Mapper(p));

            return produtos.ToList();
        }

        [HttpGet("{id}")]
        public ActionResult<ProdutoResponse> Get(int id)
        {
            var produto = ProdutoMapper.Mapper(ProdutoRepository.Buscar(id).FirstOrDefault());

            return produto;
        }

        [HttpPost]
        public ActionResult<ReturnResponse> Post([FromBody] ProdutoRequest request)
        {
            var produto = ProdutoMapper.Mapper(request);
            ProdutoRepository.Gravar(produto);

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Cadastrado com Sucesso",
            };

            return retorno;
        }

        [HttpPut]
        public ActionResult<ReturnResponse> Put([FromBody] Produto request)
        {
            ProdutoRepository.Atualizar(request);
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
            ProdutoRepository.Deletar(id);

            var retorno = new ReturnResponse()
            {
                Code = 200,
                Message = "Registro Deletado com Sucesso"
            };

            return retorno;
        }

    }
}
