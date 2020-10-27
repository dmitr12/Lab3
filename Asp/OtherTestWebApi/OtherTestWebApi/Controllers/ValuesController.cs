using OtherTestWebApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace OtherTestWebApi.Controllers
{
    public class ValuesController : ApiController
    {
        BookContext db = new BookContext();

        // GET: api/Values
        public IEnumerable<Book> Get()
        {
            return db.Books;
        }

        // GET: api/Values/5
        public Book Get(int id)
        {
            return db.Books.Find(id);
        }

        // POST: api/Values
        [HttpPost]
        public void CreateBook([FromBody]Book book)
        {
            db.Books.Add(book);
            db.SaveChanges();
        }

        // PUT: api/Values/5
        [HttpPut]
        public void EditBook(int id, [FromBody]Book book)
        {
            if (id == book.Id)
            {
                db.Entry(book).State = System.Data.Entity.EntityState.Modified;
                db.SaveChanges();
            }
        }

        // DELETE: api/Values/5
        public void DeleteBook(int id)
        {
            Book book = db.Books.Find(id);
            if (book != null)
            {
                db.Books.Remove(book);
                db.SaveChanges();
            }
        }
    }
}
