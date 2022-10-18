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

        public UserController(UserService userService)
        {
            this.userService = userService;
        }

        [HttpGet]
        [Route("GetAllUser")]
        public async Task<List<UserModel>> Get()
        {
            return await userService.GetAsync();
        }

        [HttpPost]
        [Route("SaveUser")]
        public async Task<IActionResult> Post([FromBody] UserModel userModel)
        {
            await userService.CreateAsync(userModel);
            return CreatedAtAction(nameof(Get), new { id = userModel.UserId }, userModel);
        }
    }
}
