using DependencyInjection.Interfaces;
using DependencyInjection.Models;
using Ninject.Modules;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DependencyInjection.Util
{
    public class NinjectRegistration : NinjectModule
    {
        public override void Load()
        {
            Bind<IRepository>().To<BookRepository>().WithPropertyValue("Context", new BookContext());
        }
    }
}