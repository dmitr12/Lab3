using Lab7.Resolvers;
using Ninject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab7.App_Start
{
    public class ResolveConfig
    {
        public static void RegisterServices()
        {
            DependencyResolver.SetResolver(new NinjectDependencyResolver(new StandardKernel()));
        }
    }
}