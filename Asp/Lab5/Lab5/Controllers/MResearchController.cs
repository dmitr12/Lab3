using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab5.Controllers
{
    public class MResearchController : Controller
    {
        public string M01()
        {
            return $"{Request.HttpMethod}:M01";
        }

        public string M02()
        {
            return $"{Request.HttpMethod}:M02";
        }
        public string M03()
        {
            return $"{Request.HttpMethod}:M03";
        }

        public ActionResult MXX()
        {
            return View();
        }
    }
}