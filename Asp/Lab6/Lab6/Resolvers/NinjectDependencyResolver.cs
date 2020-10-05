using JsonL;
using JsonL.Interfaces;
using SqlL;
using Ninject;
using Ninject.Web.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab6.Resolvers
{
    public class NinjectDependencyResolver : IDependencyResolver
    {
        private IKernel kernel;

        public NinjectDependencyResolver(IKernel kernel)
        {
            this.kernel = kernel;
            AddBindings();
        }

        private void AddBindings()
        {
            kernel.Bind<IPhoneDictionary>().To<JsonRepository>();
            //kernel.Bind<IPhoneDictionary>().To<SqlRepository>().WithConstructorArgument("connectionString", @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename='|DataDirectory|\TSStore.mdf';Integrated Security=True");
        }

        public object GetService(Type serviceType)
        {
            return kernel.TryGet(serviceType);
        }

        public IEnumerable<object> GetServices(Type serviceType)
        {
            return kernel.GetAll(serviceType);
        }
    }
}