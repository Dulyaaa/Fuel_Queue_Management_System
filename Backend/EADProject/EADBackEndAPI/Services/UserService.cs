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
    public class UserService : IUserService
    {

        private readonly IMongoCollection<UserModel> _UserCollection;
        public UserService(IOptions<MongoDBSettings> mongoDBSettings)
        {
            MongoClient client = new MongoClient(mongoDBSettings.Value.ConnectionURI);//connect to and communicate with MongoDB
            IMongoDatabase database = client.GetDatabase(mongoDBSettings.Value.DatabaseName);
            _UserCollection = database.GetCollection<UserModel>("User"); // Create MongoDB Collection name
        }

        public async Task<List<UserModel>> GetAsync()
        {
            return await _UserCollection.Find(new BsonDocument()).ToListAsync();
        }

        public async Task CreateAsync(UserModel userlist)
        {
            await _UserCollection.InsertOneAsync(userlist);
            return;
        }

        public async Task<UserModel> GetUserByName(string username)
        {
            var usersawait= await _UserCollection.Find(new BsonDocument()).ToListAsync();
            var user = usersawait.Where(x=>x.UserName == username).FirstOrDefault();
            return user;
        }

    }
}
