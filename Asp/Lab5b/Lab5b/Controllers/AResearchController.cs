using Lab5b.Filters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab5b.Controllers
{
    [RoutePrefix("AResearch")]
    public class AResearchController : Controller
    {
        [TestActionFilter]
        [Route("AA")]
        public string AA()
        {
            return "Метод AA()";
        }

        [TestResultFilter]
        [Route("AF")]
        public string AF()
        {
            return "Метод AF()";
        }

        [TestExceptionFilter]
        [Route("AE")]
        public string AE()
        {
            int[] someArray = new int[2];
            for (int i = 0; i < 5; i++)
                someArray[i] = i + 1;
            return "method AE() executed succesfully";
        }
    }
}