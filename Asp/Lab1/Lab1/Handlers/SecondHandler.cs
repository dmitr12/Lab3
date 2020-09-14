using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab1.Handlers
{
    public class SecondHandler : IHttpHandler
    {
        public bool IsReusable => false;

        public void ProcessRequest(HttpContext context)
        {
            int x, y;
            int.TryParse(context.Request.Params.Get("x"), out x);
            int.TryParse(context.Request.Params.Get("y"), out y);
            try
            {
                context.Response.Write(x + y);
            }
            catch(Exception e)
            {
                context.Response.Write(e.Message);
            }
        }
    }
}