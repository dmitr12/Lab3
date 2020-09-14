using Lab4.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab4.Controllers
{
    public class DictController : Controller
    {
        public ActionResult Index()
        {
            List<TS> list;
            using(TSContext db=new TSContext())
            {
                list = db.TSses.OrderBy(item => item.Surname).ToList();
            }
            return View(list);
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
                using(TSContext db=new TSContext())
                {
                    if (db.TSses.Any(item => item.Surname.ToLower() == model.Surname.ToLower()))
                    {
                        ModelState.AddModelError("", $"В справочнике уже есть фамилия {model.Surname}");
                        return View(model);
                    }
                    if (db.TSses.Any(item => item.Phone == model.Phone))
                    {
                        ModelState.AddModelError("", $"В справочнике уже есть номер {model.Phone}");
                        return View(model);
                    }
                    db.TSses.Add(model);
                    db.SaveChanges();
                }    
            }
            else
                return View(model);
            return RedirectToAction("Index");
        }

        public ActionResult Update(int id)
        {
            TS ts;
            using(TSContext db=new TSContext())
            {
                ts = db.TSses.Find(id);
            }
            return View(ts);
        }

        [HttpPost]
        [ActionName("Update")]
        public ActionResult UpdateSave(TS model)
        {
            if (ModelState.IsValid)
            {
                using(TSContext db=new TSContext())
                {
                    if (db.TSses.Any(item => item.Surname.ToLower() == model.Surname.ToLower() && item.Phone == model.Phone))
                        return RedirectToAction("Index");
                    if (db.TSses.Any(item => item.Surname.ToLower() == model.Surname.ToLower() && item.Id != model.Id))
                    {
                        ModelState.AddModelError("", $"В справочнике уже есть фамилия {model.Surname}");
                        return View(model);
                    }
                    if (db.TSses.Any(item => item.Phone == model.Phone && item.Id != model.Id))
                    {
                        ModelState.AddModelError("", $"В справочнике уже есть номер {model.Phone}");
                        return View(model);
                    }
                    db.Entry(model).State = System.Data.Entity.EntityState.Modified;
                    db.SaveChanges();
                }           
            }
            else
                return View(model);
            return RedirectToAction("Index");
        }

        public ActionResult Delete(int id)
        {
            TS ts;
            using (TSContext db = new TSContext())
                ts = db.TSses.Find(id);
            return View(ts);
        }

        [HttpPost]
        [ActionName("Delete")]
        public ActionResult DeleteSave(TS model)
        {
            using(TSContext db=new TSContext())
            {
                db.TSses.Remove(db.TSses.Find(model.Id));
                db.SaveChanges();
            }
            return RedirectToAction("Index");
        }
    }
}