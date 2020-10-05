using DependencyInjection.Interfaces;
using DependencyInjection.Models;
using Ninject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace DependencyInjection.Controllers
{
    public class HomeController : Controller
    {

        BookRepository repo;
        public HomeController()
        {
            repo = new BookRepository();
        }
        public HomeController(IRepository repo)
        {
            this.repo = repo;
        }

        public ActionResult Index()
        {
            return View(repo.List());
        }
    }
}