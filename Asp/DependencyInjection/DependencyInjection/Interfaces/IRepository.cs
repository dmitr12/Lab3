using DependencyInjection.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DependencyInjection.Interfaces
{
    public interface IRepository
    {
        void Save(Book b);
        IEnumerable<Book> List();
        Book Get(int id);
    }
}