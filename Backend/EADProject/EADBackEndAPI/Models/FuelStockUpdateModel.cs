using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EADBackEndAPI.Models
{
    public class FuelStockUpdateModel
    {
        public string FuelStockId { get; set; }
        public string FuelType { get; set; }
        public string Stock { get; set; }
        public string ArrivalTime { get; set; }
        public string FinishTime { get; set; }
    }
}
