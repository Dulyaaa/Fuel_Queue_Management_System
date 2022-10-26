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
    public class ShedService : IShedService
    {
        private readonly IMongoCollection<ShedDetailsModel> _playlistCollection;
        public ShedService(IOptions<MongoDBSettings> mongoDBSettings)
        {
            MongoClient client = new MongoClient(mongoDBSettings.Value.ConnectionURI);//connect to and communicate with MongoDB
            IMongoDatabase database = client.GetDatabase(mongoDBSettings.Value.DatabaseName);
            _playlistCollection = database.GetCollection<ShedDetailsModel>("Shed");// Create MongoDB Collection name
        }

        //Get all details of station
        public async Task<List<ShedDetailsModel>> GetAsync()
        {
            return await _playlistCollection.Find(new BsonDocument()).ToListAsync();
        }

        //save new station details
        public async Task CreateAsync(ShedDetailsModel playlist)
        {
            await _playlistCollection.InsertOneAsync(playlist);
            return;
        }

        //update station details
        public async Task UpdateAsync(ShedSample shedSample)
        {
            FilterDefinition<ShedDetailsModel> filter = Builders<ShedDetailsModel>.Filter.Eq(x => x.Id, shedSample.ShedId);
            UpdateDefinition<ShedDetailsModel> update = Builders<ShedDetailsModel>.Update.Set(x => x.City, shedSample.City);
            await _playlistCollection.UpdateOneAsync(filter, update);
            return;
        }
        public async Task DeleteAsync(string id)
        {
            FilterDefinition<ShedDetailsModel> filter = Builders<ShedDetailsModel>.Filter.Eq("Id", id);
            await _playlistCollection.DeleteOneAsync(filter);
            return;

        }

    }
}
