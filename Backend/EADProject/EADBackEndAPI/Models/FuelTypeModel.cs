using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EADBackEndAPI.Models
{
    public class FuelTypeModel
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string FuelTypeId { get; set; }
        public string FuelTypeName { get; set; }
    }
}
