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
    public class FuelStockService : IFuelStockService
    {
        private readonly IMongoCollection<FuelStockModel> _FuelStockCollection;
        public FuelStockService(IOptions<MongoDBSettings> mongoDBSettings)
        {
            MongoClient client = new MongoClient(mongoDBSettings.Value.ConnectionURI);
            IMongoDatabase database = client.GetDatabase(mongoDBSettings.Value.DatabaseName);
            _FuelStockCollection = database.GetCollection<FuelStockModel>("FuelStock");
        }

        public async Task<List<FuelStockModel>> GetAsync()
        {
            return await _FuelStockCollection.Find(new BsonDocument()).ToListAsync();
        }

        public async Task CreateAsync(FuelStockModel fuelStockModel)
        {
            await _FuelStockCollection.InsertOneAsync(fuelStockModel);
            return;
        }
    }
}
