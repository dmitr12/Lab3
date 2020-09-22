using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace Lab5
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

            routes.MapRoute(
                name: "MV3",
                url: "V3/{controller}/X/{action}",
                defaults: new { controller = "MResearch", action = "M03" }
            );

            routes.MapRoute(
               name: "V3",
               url: "V3/",
               defaults: new { controller = "MResearch", action = "M03" }
           );

            routes.MapRoute(
               name: "M01v2",
               url: "V2/{controller}/{action}",
               defaults: new { controller = "MResearch", action = "M02"}
           );

            routes.MapRoute(
               name: "CR",
               url: "CResearch/{action}",
               defaults: new { controller = "CResearch", action = "C01" }
           );

            routes.MapRoute(
               name: "M01",
               url: "{controller}/{action}/{id}",
               defaults: new { controller = "MResearch", action = "M01", id = UrlParameter.Optional }
           );
        }
    }
}
