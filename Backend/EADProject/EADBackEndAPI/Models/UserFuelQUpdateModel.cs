using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EADBackEndAPI.Models
{
    public class UserFuelQUpdateModel
    {
        public string QueueId { get; set; }
        public string ArrivalTime { get; set; }
        public string DepartureTime { get; set; }
        public string Status { get; set; }
    }
}
