using Autofac;
using Autofac.Integration.Mvc;
using DependencyInjection.Interfaces;
using DependencyInjection.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace DependencyInjection.Util
{
    public class AutofacConfig
    {
        public static void ConfigureContainer()
        {
            //Получаем экземпляр контейнера
            var builder = new ContainerBuilder();

            //Регистрация контроллера в текущей сборке
            builder.RegisterControllers(typeof(MvcApplication).Assembly);

            //Регистрация сопоставления типов
            builder.RegisterType<BookRepository>().As<IRepository>().WithParameter("context", new BookContext());

            //Создаем новый контейнер с теми зависимостями, которые определены выше
            var container = builder.Build();

            //Установка сопоставления зависимостей
            DependencyResolver.SetResolver(new AutofacDependencyResolver(container));
        }
    }
}