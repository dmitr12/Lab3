using Lab6.Resolvers;
using Ninject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab6.App_Start
{
    public class ResolverConfig
    {
        public static void RegisterServices()
        {
            DependencyResolver.SetResolver(new NinjectDependencyResolver(new StandardKernel()));
        }
    }
}