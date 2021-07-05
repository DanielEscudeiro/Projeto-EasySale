using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;

namespace WebAPI.Controllers
{
    [Route("")]
    [ApiController]

    public class Inicio
    {
        private const string V = "Olá Seja Bem Vindo";

        [HttpGet]
        public ActionResult<string>  Get()
        {
            return V;
        }


    }
}
