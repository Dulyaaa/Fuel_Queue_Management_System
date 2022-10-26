using EADBackEndAPI.Models;
using EADBackEndAPI.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EADBackEndAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ShedController : ControllerBase
    {
        private readonly ShedService shedService;

        //initating srvice through constructor
        public ShedController(ShedService shedService)
        {
            this.shedService = shedService;
        }

        //API for Get all Shed Details
        [HttpGet]
        [Route("GetAll")]
        public async Task<List<ShedDetailsModel>> Get()
        {
            return await shedService.GetAsync();
        }

        //API for create new Shed Details
        [HttpPost]
        [Route("Save")]
        public async Task<IActionResult> Post([FromBody] ShedDetailsModel playlist)
        {
            await shedService.CreateAsync(playlist);
            return CreatedAtAction(nameof(Get), new { id = playlist.Id }, playlist);
        }

        //API for delete sheds
        [HttpDelete]
        [Route("Delete")]
        public async Task<IActionResult> Delete(string id)
        {
            await shedService.DeleteAsync(id);
            return NoContent();
        }


        //API for update Shed Details
        [HttpPost]
        [Route("Update")]
        public async Task<IActionResult> Update(ShedSample shedSample)
        {
            await shedService.UpdateAsync(shedSample);
            return Ok();
        }

    }
}
