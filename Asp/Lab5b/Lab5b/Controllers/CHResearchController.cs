using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Runtime.Caching;
using System.Web;
using System.Web.Caching;
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
        
        public string AP(int a, int b)
        {
            var aC = HttpContext.Cache["a"];
            var bC = HttpContext.Cache["b"];
            if (aC == null)
                HttpContext.Cache.Add("a", a, null, DateTime.Now.AddSeconds(10), Cache.NoSlidingExpiration, System.Web.Caching.CacheItemPriority.Normal, null);
            if (bC == null)
                HttpContext.Cache.Add("b", b, null, DateTime.Now.AddSeconds(10), Cache.NoSlidingExpiration, System.Web.Caching.CacheItemPriority.Normal, null);
            return $"a:{HttpContext.Cache["a"]}, b:{HttpContext.Cache["b"]}";
        }
    }
}