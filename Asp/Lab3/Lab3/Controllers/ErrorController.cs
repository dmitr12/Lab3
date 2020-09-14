using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab3.Controllers
{
    public class ErrorController : Controller
    {
        public ActionResult NotFound()
        {
            Response.StatusCode = 404;
            ViewBag.Method = Request.HttpMethod;
            ViewBag.Url = Request.Url.Host+":"+Request.Url.Port+Request.RawUrl.Substring(0);
            return View();
        }
    }
}