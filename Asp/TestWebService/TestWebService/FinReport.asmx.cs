using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace TestWebService
{
    /// <summary>
    /// Сводное описание для FinReport
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Чтобы разрешить вызывать веб-службу из скрипта с помощью ASP.NET AJAX, раскомментируйте следующую строку. 
    // [System.Web.Script.Services.ScriptService]
    public class FinReport : System.Web.Services.WebService
    {
        DataTable dt = new DataTable();

        [WebMethod]
        public string HelloWorld()
        {
            return "Привет всем!";
        }

        [WebMethod]
        public string Countries()
        {
            dt.Columns.Add("CountryName");
            dt.Columns.Add("Continent");
            dt.Rows.Add("Pakistan", "Asia");
            dt.Rows.Add("India", "Asia");
            dt.Rows.Add("Germany", "Europe");
            return JsonConvert.SerializeObject(dt);
        }
    }
}
