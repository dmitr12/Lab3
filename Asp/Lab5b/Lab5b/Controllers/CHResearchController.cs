using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab5b.Controllers
{
    [RoutePrefix("CHResearch")]
    public class CHResearchController : Controller
    {
        static int val = 1;


        [AcceptVerbs("GET")]
        [OutputCache(Duration =5)]
        [Route("AD")]
        public string AD()
        {
            val++;
            return val.ToString();
        }

        [AcceptVerbs("POST")]
        [Route("AP")]
        [OutputCache(Duration =7)]
        public string AP(int a, int b)
        {
            val = val + a + b;
            return val.ToString();
        }
    }
}