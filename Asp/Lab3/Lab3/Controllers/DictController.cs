using Lab3.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.IO;
using System.Web.Mvc;

namespace Lab3.Controllers
{
    public class DictController : Controller
    {

        public ActionResult Index()
        {
            List<TS> list = new List<TS>();
            if (System.IO.File.Exists($"{Request.PhysicalApplicationPath}file.json"))
            {
                var jsonData = System.IO.File.ReadAllText($"{Request.PhysicalApplicationPath}file.json");
                list = JsonConvert.DeserializeObject<List<TS>>(jsonData) ?? new List<TS>();
            }
            return View(list.OrderBy(item=>item.Surname));
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
                string path = $"{Request.PhysicalApplicationPath}file.json";
                var listData = new List<TS>();
                if (System.IO.File.Exists(path))
                {
                    var jsonData = System.IO.File.ReadAllText(path);
                    listData = JsonConvert.DeserializeObject<List<TS>>(jsonData) ?? new List<TS>();
                    if (listData.Any(item => item.Surname.ToLower() == model.Surname.ToLower()))
                    {
                        ModelState.AddModelError("", $"В справочнике уже есть фамилия {model.Surname}");
                        return View(model);
                    }
                    if (listData.Any(item => item.Phone == model.Phone))
                    {
                        ModelState.AddModelError("", $"В справочнике уже есть номер {model.Phone}");
                        return View(model);
                    }
                }
                listData = listData.OrderBy(item => item.Id).ToList();
                if (listData.Count == 0)
                    model.Id = 1;
                else
                    model.Id = listData.Last().Id+1;
                listData.Add(model);
                System.IO.File.WriteAllText(path, JsonConvert.SerializeObject(listData));
            }
            else
                return View(model);
            return RedirectToAction("Index");
        }

        public ActionResult Update(int id)
        {
            List<TS> list = new List<TS>();
            if (System.IO.File.Exists($"{Request.PhysicalApplicationPath}file.json"))
            {
                var jsonData = System.IO.File.ReadAllText($"{Request.PhysicalApplicationPath}file.json");
                list = JsonConvert.DeserializeObject<List<TS>>(jsonData) ?? new List<TS>();
            }
            else
                return RedirectToAction("Index");
            return View((list.Where(item=>item.Id==id).ToList()[0]));
        }

        [HttpPost]
        [ActionName("Update")]
        public ActionResult UpdateSave(TS model)
        {
            if (ModelState.IsValid)
            {
                string path = $"{Request.PhysicalApplicationPath}file.json";
                var listData = new List<TS>();
                if (System.IO.File.Exists(path))
                {
                    var jsonData = System.IO.File.ReadAllText(path);
                    listData = JsonConvert.DeserializeObject<List<TS>>(jsonData) ?? new List<TS>();
                    if (listData.Any(item => item.Surname.ToLower() == model.Surname.ToLower() && item.Phone == model.Phone))
                        return RedirectToAction("Index");
                    if (listData.Any(item => item.Surname.ToLower() == model.Surname.ToLower() && item.Id!=model.Id))
                    {
                        ModelState.AddModelError("", $"В справочнике уже есть фамилия {model.Surname}");
                        return View(model);
                    }
                    if (listData.Any(item => item.Phone == model.Phone && item.Id != model.Id))
                    {
                        ModelState.AddModelError("", $"В справочнике уже есть номер {model.Phone}");
                        return View(model);
                    }
                }
                for (int i = 0; i < listData.Count; i++)
                {
                    if (listData[i].Id == model.Id)
                    {
                        if (listData[i].Surname.ToLower() == model.Surname.ToLower() &&
                            listData[i].Phone == model.Phone)
                            return RedirectToAction("Index");
                        listData.Remove(listData[i]);
                        break;
                    }
                }       
                listData.Add(model);
                System.IO.File.WriteAllText(path, JsonConvert.SerializeObject(listData));
            }
            else
                return View(model);
            return RedirectToAction("Index");
        }

        public ActionResult Delete(int id)
        {
            List<TS> list = new List<TS>();
            if (System.IO.File.Exists($"{Request.PhysicalApplicationPath}file.json"))
            {
                var jsonData = System.IO.File.ReadAllText($"{Request.PhysicalApplicationPath}file.json");
                list = JsonConvert.DeserializeObject<List<TS>>(jsonData) ?? new List<TS>();
            }
            else
                return RedirectToAction("Index");
            return View((list.Where(item => item.Id == id).ToList()[0]));
        }

        [HttpPost]
        [ActionName("Delete")]
        public ActionResult DeleteSave(TS model)
        {
            List<TS> list = new List<TS>();
            if (System.IO.File.Exists($"{Request.PhysicalApplicationPath}file.json"))
            {
                var jsonData = System.IO.File.ReadAllText($"{Request.PhysicalApplicationPath}file.json");
                list = JsonConvert.DeserializeObject<List<TS>>(jsonData) ?? new List<TS>();
            }
            list.Remove(list.Where(item => item.Id == model.Id).ToList()[0]);
            System.IO.File.WriteAllText($"{Request.PhysicalApplicationPath}file.json", JsonConvert.SerializeObject(list));
            return RedirectToAction("Index");
        }
    }
}