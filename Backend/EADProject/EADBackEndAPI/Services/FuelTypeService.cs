using EADBackEndAPI.Interface;
using EADBackEndAPI.Models;
using EADBackEndAPI.Services.Common;
using Microsoft.Extensions.Options;
using MongoDB.Bson;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EADBackEndAPI.Services
{
    public class FuelTypeService : IFuelType
    {
        private readonly IMongoCollection<FuelTypeModel> _FuelTypeCollection;
        public FuelTypeService(IOptions<MongoDBSettings> mongoDBSettings)
        {
            MongoClient client = new MongoClient(mongoDBSettings.Value.ConnectionURI);
            IMongoDatabase database = client.GetDatabase(mongoDBSettings.Value.DatabaseName);
            _FuelTypeCollection = database.GetCollection<FuelTypeModel>("FuelType");
        }

        public async Task<List<FuelTypeModel>> GetAsync()
        {
            return await _FuelTypeCollection.Find(new BsonDocument()).ToListAsync();
        }

        public async Task CreateAsync(FuelTypeModel fuelTypeModel)
        {
            await _FuelTypeCollection.InsertOneAsync(fuelTypeModel);
            return;
        }
    }
}
