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
            MongoClient client = new MongoClient(mongoDBSettings.Value.ConnectionURI);//connect to and communicate with MongoDB
            IMongoDatabase database = client.GetDatabase(mongoDBSettings.Value.DatabaseName);
            _FuelStockCollection = database.GetCollection<FuelStockModel>("FuelStock");// Create MongoDB Collection name
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

        //Service method for update fuel stock details
        public async Task UpdateAsync(FuelStockUpdateModel fuelStockUpdateModel)
        {
            FilterDefinition<FuelStockModel> filter = Builders<FuelStockModel>.Filter.Eq(x => x.FuelStockId, fuelStockUpdateModel.FuelStockId);
            UpdateDefinition<FuelStockModel> update = Builders<FuelStockModel>.Update.Set(x => x.ArrivalTime, fuelStockUpdateModel.ArrivalTime)
                                                                                             .Set(x => x.FinishTime, fuelStockUpdateModel.FinishTime)
                                                                                             .Set(x => x.FuelType, fuelStockUpdateModel.FuelType)
                                                                                             .Set(x => x.Stock, fuelStockUpdateModel.Stock);
            await _FuelStockCollection.UpdateManyAsync(filter, update);
            return;
        }
    }
}
