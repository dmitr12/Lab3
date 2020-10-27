using Dapper;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace TestDapper.Models
{
    public class UserRepository
    {
        string connectionString = ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString;
        public List<User> GetUsers()
        {
            List<User> users = new List<User>();
            using(IDbConnection db=new SqlConnection(connectionString))
            {
                users = db.Query<User>("select * from users").ToList();
            }
            return users;
        }

        public User Get(int id)
        {
            User user = null;
            using(IDbConnection db=new SqlConnection(connectionString))
            {
                user = db.Query<User>("select * from users where Id=@id", new { id }).FirstOrDefault();
            }
            return user;
        }

        public User Create(User user)
        {
            using(IDbConnection db=new SqlConnection(connectionString))
            {
                var query = "insert into users (Name, Age) values(@Name,@Age); select cast(scope_identity() as int)";
                int? userId = db.Query<int>(query, user).FirstOrDefault();
                user.Id = (int)userId;
            }
            return user;
        }

        public void Update(User user)
        {
            using(IDbConnection db=new SqlConnection(connectionString))
            {
                var sqlQuery = "UPDATE Users SET Name = @Name, Age = @Age WHERE Id = @Id";
                db.Execute(sqlQuery, new { user.Name, user.Age, user.Id });
            }
        }

        public void Delete(int id)
        {
            using(IDbConnection db=new SqlConnection(connectionString))
            {
                db.Execute("delete from users where Id=@id", new { id });
            }
        }
    }
}