using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Lab6.Helpers
{
    public static class CustomHelper
    {
        public static MvcHtmlString CreateTxtBox(this HtmlHelper html,string name, string type,string defValue="", string htmlClass="")
        {
            TagBuilder txtB = new TagBuilder("input");
            txtB.MergeAttribute("type", "text");
            txtB.MergeAttributes(new Dictionary<string, string>()
            {
                {"type", type},
                {"name", name},
                {"value",defValue},
            });
            txtB.AddCssClass(htmlClass);
            return new MvcHtmlString(txtB.ToString());
        }
    }
}