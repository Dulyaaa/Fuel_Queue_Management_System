using EADBackEndAPI.Models;
using EADBackEndAPI.Services;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EADBackEndAPI.Controllers
{
    public class UserfuelQueueController : Controller
    {
        private readonly UserfuelQueueService userfuelQueueService;

        public UserfuelQueueController(UserfuelQueueService userfuelQueueService)
        {
            this.userfuelQueueService = userfuelQueueService;
        }

        //API for get all user queue details in DB
        [HttpGet]
        [Route("GetAllUserQueue")]
        public async Task<List<UserfuelQueueModel>> Get()
        {
            return await userfuelQueueService.GetAsync();
        }

        //API for create new user queue
        [HttpPost]
        [Route("SaveUserQueue")]
        public async Task<IActionResult> Post([FromBody] UserfuelQueueModel userfuelQueueModel)
        {
            await userfuelQueueService.CreateAsync(userfuelQueueModel);
            return CreatedAtAction(nameof(Get), new { id = userfuelQueueModel.QueueId }, userfuelQueueModel);
        }

        //API for update user queue details
        [HttpPost]
        [Route("UpdateUserFuelQueue")]
        public async Task<IActionResult> UpdateUserFuelQueue(UserFuelQUpdateModel userFuelQUpdateModel)
        {
            await userfuelQueueService.UpdateAsync(userFuelQUpdateModel);
            return Ok();
        }

        //API for get the total vehicle count in a queue
        [HttpGet]
        [Route("GetVehicleCount")]
        public async Task<int> GetVehicleCount()
        {
            return await userfuelQueueService.GetVehicleCount();
        }
    }
}
