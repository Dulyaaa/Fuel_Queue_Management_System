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
    public class UserfuelQueueService : IUserfuelQueue
    {
        private readonly IMongoCollection<UserfuelQueueModel> _UserQueueCollection;
        public UserfuelQueueService(IOptions<MongoDBSettings> mongoDBSettings)
        {
            MongoClient client = new MongoClient(mongoDBSettings.Value.ConnectionURI);
            IMongoDatabase database = client.GetDatabase(mongoDBSettings.Value.DatabaseName);
            _UserQueueCollection = database.GetCollection<UserfuelQueueModel>("UserfuelQueue");
        }

        public async Task<List<UserfuelQueueModel>> GetAsync()
        {
            return await _UserQueueCollection.Find(new BsonDocument()).ToListAsync();
        }

        public async Task CreateAsync(UserfuelQueueModel userfuelQueueModel)
        {
            await _UserQueueCollection.InsertOneAsync(userfuelQueueModel);
            return;
        }
    }
}
