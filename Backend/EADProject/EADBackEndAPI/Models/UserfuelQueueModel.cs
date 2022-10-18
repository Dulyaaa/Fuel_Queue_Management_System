using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EADBackEndAPI.Models
{
    public class UserfuelQueueModel
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string QueueId { get; set; }
        public string ShedId { get; set; }
        public string UserId { get; set; }
        public string ArrivalTime { get; set; }
        public string DepartureTime { get; set; }
        public string Status { get; set; }
    }
}
