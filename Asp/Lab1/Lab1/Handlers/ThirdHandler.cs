using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab1.Handlers
{
    public class ThirdHandler : IHttpHandler
    {
        public bool IsReusable => false;

        public void ProcessRequest(HttpContext context)
        {
            if (context.Request.RequestType == "GET")
                context.Response.Redirect("html/task5.html");
            if (context.Request.RequestType == "POST")
            {
                int x, y;
                if (int.TryParse(context.Request.Params.Get("x"), out x) && int.TryParse(context.Request.Params.Get("y"), out y))
                {
                    context.Response.Write(x * y);
                }
                else
                    context.Response.Write("Нельзя перемножить нечисловые значения");
            }
        }
    }
}