using EADBackEndAPI.Models;
using EADBackEndAPI.Services;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EADBackEndAPI.Controllers
{
    public class UserController : Controller
    {
        private readonly UserService userService;

        //initating srvice through constructor
        public UserController(UserService userService)
        {
            this.userService = userService;
        }

        //API for get all users in DB
        [HttpGet]
        [Route("GetAllUser")]
        public async Task<List<UserModel>> Get()
        {
            return await userService.GetAsync();
        }

        //API for create new user
        [HttpPost]
        [Route("SaveUser")]
        public async Task<IActionResult> Post([FromBody] UserModel userModel)
        {
            await userService.CreateAsync(userModel);
            return CreatedAtAction(nameof(Get), new { id = userModel.UserId }, userModel);
        }

        //API for get the user details by name and password
        [HttpGet]
        [Route("GetUserByName")]
        public async Task<UserModel> GetUserByName(string username, string password)
        {
            return await userService.GetUserByName(username, password);
        }
    }
}
