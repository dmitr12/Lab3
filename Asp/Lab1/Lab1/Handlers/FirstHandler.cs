using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab1.Handlers
{
    public class FirstHandler : IHttpHandler
    {
        public bool IsReusable => false;

        public void ProcessRequest(HttpContext context)
        {
            string ParmA = context.Request.Params.Get("ParmA");
            string ParmB = context.Request.Params.Get("parmB");
            context.Response.Write($"{context.Request.RequestType}-Http-{context.Request.Path}:ParmA={ParmA}, ParmB={ParmB}");
        }
    }
}