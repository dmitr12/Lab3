using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab5b.Filters
{
    public class TestResultFilter : FilterAttribute, IResultFilter
    {
        public void OnResultExecuted(ResultExecutedContext filterContext)
        {
            filterContext.HttpContext.Response.Write("<br/>Вызов OnResultExecuted()-вызывается после возвращения методом действия результата");
        }

        public void OnResultExecuting(ResultExecutingContext filterContext)
        {
            filterContext.HttpContext.Response.Write("Вызов OnResultExecuting()-вызывается до возвращения методом действия результата<br/>");
        }
    }
}