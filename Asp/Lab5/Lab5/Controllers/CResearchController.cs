using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace Lab5.Controllers
{
    public class CResearchController : Controller
    { 
        public string C01()
        {
            string s = $"<b>Метод запроса:</b><br/>{Request.HttpMethod}<br/>";
            if(Request.QueryString.Count>0)
            {
                s += $"<b>query-параметры:</b><br/>";
                foreach (var key in Request.QueryString.AllKeys)
                    s += $"{key}:{Request.QueryString.Get(key)}<br/>";
            }
            s += $"<b>uri:</b><br/>{Request.Url.AbsoluteUri}";
            s += "<br/><b>Заголовки запроса:</b><br/>";
            foreach (string k in Request.Headers.Keys)
                s += $"{k}:{Request.Headers.Get(k)}<br/>";
            s += "<b>Заголовки ответа:</b><br/>";
            foreach (string k in Response.Headers.Keys)
                s += $"{k}:{Response.Headers.Get(k)}<br/>";
            if (Request.ContentLength > 0)
            {
                s += "<b>Тело:</b><br/>";
                var buffer = Request.BinaryRead(Request.ContentLength);
                var t = Encoding.UTF8.GetString(buffer);
                s += t;
            }        
            return s;
        }

        public string C02()
        {
            string s = $"<b>Статус-код:</b><br/>{Response.StatusCode}";
            s += "<br/><b>Заголовки запроса:</b><br/>";
            foreach (string k in Request.Headers.Keys)
                s += $"{k}:{Request.Headers.Get(k)}<br/>";
            s += "<b>Заголовки ответа:</b><br/>";
            foreach (string k in Response.Headers.Keys)
                s += $"{k}:{Response.Headers.Get(k)}<br/>";
            if (Request.ContentLength > 0)
            {
                s += "<b>Тело:</b><br/>";
                var buffer = Request.BinaryRead(Request.ContentLength);
                var t = Encoding.UTF8.GetString(buffer);
                s += t;
            }
            return s;
        }
    }
}