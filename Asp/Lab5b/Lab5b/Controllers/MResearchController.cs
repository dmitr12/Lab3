using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab5b.Controllers
{
    [RoutePrefix("it")]
    public class MResearchController : Controller
    {
        [AcceptVerbs("GET")]
        [Route("{n:int}/{string}")]
        public string M01(int n, string @string)
        {
            return $"{Request.HttpMethod}:M01:{n}:{@string}";
        }

        [AcceptVerbs("GET", "POST")]
        [Route("{b:bool}/{letters:alpha}")]
        public string M02(bool b, string letters)
        {
            return $"{Request.HttpMethod}:M02:{b}:{letters}";
        }

        [AcceptVerbs("DELETE","GET")]
        [Route("{f:float}/{string:length(2,5)}")]
        public string M03(float f, string @string)
        {
            return $"{Request.HttpMethod}:M03:{f}:{@string}";
        }

        [HttpPost]
        [Route(@"{mail:regex([a-z0-9]+\@[a-z]+)}")]
        public string M04(string mail)
        {
            return $"{Request.HttpMethod}:M04:{mail}";
        }

        [HttpPut]
        [Route("{letters:alpha:length(3,4)}/{n:range(100,200)}")]
        public string M04(string letters, int n)
        {
            return $"{Request.HttpMethod}:M04:{letters}:{n}";
        }

       
    }
}