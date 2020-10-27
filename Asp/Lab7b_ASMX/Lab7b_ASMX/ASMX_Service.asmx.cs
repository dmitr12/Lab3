using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace Lab7b_ASMX
{
    /// <summary>
    /// Сводное описание для ASMX_Service
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Чтобы разрешить вызывать веб-службу из скрипта с помощью ASP.NET AJAX, раскомментируйте следующую строку. 
    // [System.Web.Script.Services.ScriptService]
    public class ASMX_Service : System.Web.Services.WebService
    {
        string status;
        TSContext repo = new TSContext();

        [WebMethod]
        public TS[] GetAllTS()
        {
            return repo.GetTSList().ToArray();
        }

        [WebMethod]
        public string AddTS(TS ts)
        {
            repo.Add(ts, out status);
            return status;
        }

        [WebMethod]
        public string EditTS(TS ts)
        {
            repo.Edit(ts, out status);
            return status;
        }

        [WebMethod]
        public void DeleteTS(int id)
        {
            repo.Delete(id, out status);
        }
    }
}
