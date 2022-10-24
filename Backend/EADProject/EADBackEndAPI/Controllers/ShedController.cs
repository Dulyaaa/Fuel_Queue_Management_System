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

        public ShedController(ShedService shedService)
        {
            this.shedService = shedService;
        }

        [HttpGet]
        [Route("GetAll")]
        public async Task<List<ShedDetailsModel>> Get()
        {
            return await shedService.GetAsync();
        }

        [HttpPost]
        [Route("Save")]
        public async Task<IActionResult> Post([FromBody] ShedDetailsModel playlist)
        {
            await shedService.CreateAsync(playlist);
            return CreatedAtAction(nameof(Get), new { id = playlist.Id }, playlist);
        }

      
        [HttpDelete]
        [Route("Delete")]
        public async Task<IActionResult> Delete(string id)
        {
            await shedService.DeleteAsync(id);
            return NoContent();
        }


        [HttpPost]
        [Route("Update")]
        public async Task<IActionResult> Update(ShedSample shedSample)
        {
            await shedService.UpdateAsync(shedSample);
            return Ok();
        }

        /*      [HttpGet]
                [Route("GetDetailsbyID")]
                public async Task<IActionResult> GetDetailsbyID(string shedId)
                {
                    return await shedService.DetailsbyID(shedId);
                }*/

/*        [HttpGet]
        [Route("getByName")]
        public async Task<IActionResult> GetByName(string city)
        {
            try
            {
                var product = await shedService.GetByName(city);
                if (product == null)
                {
                    return Json("No product found!");
                }
                return Json(product);
            }
            catch (Exception ex)
            {
                return Json(ex.ToString());

            }

        }*/

    }
}
