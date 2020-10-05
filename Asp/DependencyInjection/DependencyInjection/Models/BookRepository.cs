using DependencyInjection.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DependencyInjection.Models
{
    public class BookRepository : IDisposable, IRepository
    {
        private BookContext db=new BookContext();

        public void Save(Book b)
        {
            db.Books.Add(b);
            db.SaveChanges();
        }

        public IEnumerable<Book> List() => db.Books;

        public Book Get(int id) => db.Books.Find(id);

        protected void Dispose(bool disposing)
        {
            if (disposing)
            {
                if (db != null)
                {
                    db.Dispose();
                    db = null;
                }
            }
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}