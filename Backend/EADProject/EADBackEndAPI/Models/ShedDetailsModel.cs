using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;

namespace EADBackEndAPI.Models
{
    public class ShedDetailsModel
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; }
        public string City { get; set; }
        public string UserId { get; set; }
        public string ShedName { get; set; }
        public string AvgTime { get; set; }
        public string QueueLength { get; set; }
    }
}
