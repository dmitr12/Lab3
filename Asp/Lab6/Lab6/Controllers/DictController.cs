using JsonL.Interfaces;
using JsonL.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab6.Controllers
{
    public class DictController : Controller
    {

        public ActionResult Test()
        {
            ViewBag.Fruits = new string[] { "Яблоко", "Апельсин", "Груша" };
            ViewBag.Cities = new string[] { "Москва", "Лондон", "Париж" };

            string message = "Это HTML-элемент: <input>";

            return View((object)message);
        }


        private IPhoneDictionary repos;

        public DictController(IPhoneDictionary repos)
        {
            this.repos = repos;
        }

        public ActionResult Index()
        {
            return View(repos.GetTSList().OrderBy(ts=>ts.Surname));
        }

        public ActionResult Add()
        {
            return View();
        }

        [HttpPost]
        [ActionName("Add")]
        public ActionResult AddSave(TS model)
        {
            if (ModelState.IsValid)
            {
                string status;
                repos.Add(model, out status);
                if (status != "ok")
                {
                    ModelState.AddModelError("", status);
                    return View(model);
                }
            }
            else
                return View(model);
            return RedirectToAction("Index");
        }

        public ActionResult Update(int id)
        {
            return View(repos.GetItem(id));
        }

        [HttpPost]
        [ActionName("Update")]
        public ActionResult UpdateSave(TS model)
        {
            if (ModelState.IsValid)
            {
                string status;
                repos.Edit(model, out status);
                if (status != "ok")
                {
                    ModelState.AddModelError("", status);
                    return View(model);
                }
            }
            else
                return View(model);
            return RedirectToAction("Index");
        }

        public ActionResult Delete(int id)
        {
            return View(repos.GetItem(id));
        }

        [HttpPost]
        [ActionName("Delete")]
        public ActionResult DeleteSave(TS model)
        {
            string status;
            repos.Delete(model.Id, out status);
            if (status != "ok")
            {
                ModelState.AddModelError("", status);
                return View(model);
            }
            return RedirectToAction("Index");
        }
    }
}