using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using TestProj.Models;

namespace TestProj.Controllers
{
    public class HomeController : Controller
    {
        BookContext db = new BookContext();

        public string Index()
        {
            object controller = RouteData.Values["id"] ?? "ban";
            return controller.ToString();
        }

        [Route("{id:int}/{name}")]
        public string Test(int id, string name)
        {
            return id.ToString() + ". " + name;
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
    }
}