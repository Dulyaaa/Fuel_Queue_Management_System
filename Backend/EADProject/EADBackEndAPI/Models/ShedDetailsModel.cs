using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;

namespace EADBackEndAPI.Models
{
    public class ShedDetailsModel
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string ShedId { get; set; }
        public string City { get; set; }
        public string ShedName { get; set; }
    }
}
