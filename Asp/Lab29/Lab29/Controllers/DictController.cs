using JsonHelper;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web.Mvc;

namespace Lab29.Controllers
{
    public class DictController : Controller
    {
        private readonly IPhoneDictionary _repository;

        public DictController(IPhoneDictionary repository)
        {
            _repository = repository;
        }

        [HttpGet]
        public ActionResult Index()
        {
            var records =_repository.GetRecords();
            return View(records);
        }

        [HttpGet]
        public ActionResult Create()
        {
            return View();
        }

        [HttpPost]
        [ActionName("Create")]
        public ActionResult CreateSave(Record record)
        {
            if (ModelState.IsValid)
            {
                var records = _repository.GetRecords();
                if (DoesRecordExist(record, records))
                {
                    ModelState.AddModelError("Name", "This record already exists.");
                    return View("Create", record);
                }
                _repository.Create(record);

                return RedirectToAction("Index");
            }
            return View("Create", record);
        }

        [HttpGet]
        public ActionResult Update(string name)
        {
            if (string.IsNullOrEmpty(name))
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }

            var record = _repository.GetRecords().FirstOrDefault(r => r.Name == name);
            if (record == null)
            {
                return HttpNotFound();
            }
            return View(record);
        }

        [HttpPost]
        [ActionName("Update")]
        public ActionResult UpdateSave(Record record)
        {
            if (ModelState.IsValid)
            {
                _repository.Update(record);
                return RedirectToAction("Index");
            }

            return View("Update", record);
        }

        [HttpGet]
        public ActionResult Delete(string name)
        {
            if (string.IsNullOrEmpty(name))
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }

            var record = _repository.GetRecords().FirstOrDefault(r => r.Name == name);
            if (record == null)
            {
                return HttpNotFound();
            }
            return View(record);
        }

        [HttpPost]
        [ActionName("Delete")]
        public ActionResult DeleteSave(string name)
        {
            _repository.Delete(name);
            return RedirectToAction("Index");
        }

        private bool DoesRecordExist(Record record, IEnumerable<Record> records)
        {
            if (records.Any(r => r.Name == record.Name))
            {
                ModelState.AddModelError("Name", "This record already exists.");
                return true;
            }

            return false;
        }
    }
}