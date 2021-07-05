using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAPI.Models;
using WebAPI.Requests;
using WebAPI.Responses;

namespace WebAPI.Mapper
{
    public class LoginMapper
    {
        public static Login Mapper(LoginRequest loginRequest)
        {
            return new Login()
            {
                ID = loginRequest.ID,
                Nome = loginRequest.Nome,
                Usuario = loginRequest.Usuario,
                Senha = loginRequest.Senha

            };
        }

        public static LoginResponse Mapper(Login login)
        {
            return new LoginResponse()
            {
                ID = login.ID.ToString(),
                Nome = login.Nome,
                Usuario = login.Usuario,
                Senha = login.Senha
                
            };
        }
    }
}
