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
            MongoClient client = new MongoClient(mongoDBSettings.Value.ConnectionURI);
            IMongoDatabase database = client.GetDatabase(mongoDBSettings.Value.DatabaseName);
            _playlistCollection = database.GetCollection<ShedDetailsModel>("Shed");
        }

        public async Task<List<ShedDetailsModel>> GetAsync()
        {
            return await _playlistCollection.Find(new BsonDocument()).ToListAsync();
        }

        public async Task CreateAsync(ShedDetailsModel playlist)
        {
            await _playlistCollection.InsertOneAsync(playlist);
            return;
        }
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

/*        public async Task<List<ShedDetailsModel>> DetailsbyID(string shedId)
        {
            return await _playlistCollection.Find(new BsonDocument()).ToListAsync();
        }*/
    }
}
