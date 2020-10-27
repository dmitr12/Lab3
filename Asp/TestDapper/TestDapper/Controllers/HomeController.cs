using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using TestDapper.Models;

namespace TestDapper.Controllers
{
    public class HomeController : Controller
    {
        UserRepository repo = new UserRepository();

        public ActionResult Index()
        {
            return View(repo.GetUsers());
        }

        public ActionResult Details(int id)
        {
            User user = repo.Get(id);
            if (user != null)
                return View(user);
            return HttpNotFound();
        }

        public ActionResult Create()
        {
            return View();
        }

        public ActionResult Add(User user)
        {
            repo.Create(user);
            return RedirectToAction("Index");
        }

        public ActionResult Edit(int id)
        {
            User user = repo.Get(id);
            if (user != null)
                return View(user);
            return HttpNotFound();
        }

        [HttpPost]
        public ActionResult Edit(User user)
        {
            repo.Update(user);
            return RedirectToAction("Index");
        }

        [HttpGet]
        public ActionResult ConfirmDelete(int id)
        {
            User user = repo.Get(id);
            if (user != null)
                return View(user);
            return HttpNotFound();
        }
        [HttpPost]
        public ActionResult Delete(int id)
        {
            repo.Delete(id);
            return RedirectToAction("Index");
        }
    }
}