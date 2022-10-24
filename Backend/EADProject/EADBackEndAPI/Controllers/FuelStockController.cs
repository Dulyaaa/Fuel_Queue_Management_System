using EADBackEndAPI.Models;
using EADBackEndAPI.Services;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EADBackEndAPI.Controllers
{
    public class FuelStockController : Controller
    {
        private readonly FuelStockService fuelStockService;

        public FuelStockController(FuelStockService fuelStockService)
        {
            this.fuelStockService = fuelStockService;
        }

        [HttpGet]
        [Route("GetAllFuelStock")]
        public async Task<List<FuelStockModel>> Get()
        {
            return await fuelStockService.GetAsync();
        }

        [HttpPost]
        [Route("SaveFuelStock")]
        public async Task<IActionResult> SaveFuelStock([FromBody] FuelStockModel fuelStockModel)
        {
            await fuelStockService.CreateAsync(fuelStockModel);
            return CreatedAtAction(nameof(Get), new { id = fuelStockModel.FuelStockId }, fuelStockModel);
        }

        [HttpPost]
        [Route("UpdateFuelStock")] 
        public async Task<IActionResult> UpdateFuelStock(FuelStockUpdateModel fuelStockUpdateModel)
        {
            await fuelStockService.UpdateAsync(fuelStockUpdateModel);
            return Ok();
        }
    }
}
