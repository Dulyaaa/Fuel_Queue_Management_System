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

        [HttpGet]
        [Route("GetAllUserQueue")]
        public async Task<List<UserfuelQueueModel>> Get()
        {
            return await userfuelQueueService.GetAsync();
        }

        [HttpPost]
        [Route("SaveUserQueue")]
        public async Task<IActionResult> Post([FromBody] UserfuelQueueModel userfuelQueueModel)
        {
            await userfuelQueueService.CreateAsync(userfuelQueueModel);
            return CreatedAtAction(nameof(Get), new { id = userfuelQueueModel.QueueId }, userfuelQueueModel);
        }

        [HttpPost]
        [Route("UpdateUserFuelQueue")]
        public async Task<IActionResult> UpdateUserFuelQueue(UserFuelQUpdateModel userFuelQUpdateModel)
        {
            await userfuelQueueService.UpdateAsync(userFuelQUpdateModel);
            return Ok();
        }
    }
}
