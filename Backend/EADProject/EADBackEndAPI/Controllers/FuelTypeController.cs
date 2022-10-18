using EADBackEndAPI.Models;
using EADBackEndAPI.Services;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EADBackEndAPI.Controllers
{
    public class FuelTypeController : Controller
    {
        private readonly FuelTypeService fuelTypeService;

        public FuelTypeController(FuelTypeService fuelTypeService)
        {
            this.fuelTypeService = fuelTypeService;
        }

        [HttpGet]
        [Route("GetAllFuelType")]
        public async Task<List<FuelTypeModel>> Get()
        {
            return await fuelTypeService.GetAsync();
        }

        [HttpPost]
        [Route("SaveFuelType")]
        public async Task<IActionResult> Post([FromBody] FuelTypeModel fuelTypeModel)
        {
            await fuelTypeService.CreateAsync(fuelTypeModel);
            return CreatedAtAction(nameof(Get), new { id = fuelTypeModel.FuelTypeId }, fuelTypeModel);
        }
    }
}
