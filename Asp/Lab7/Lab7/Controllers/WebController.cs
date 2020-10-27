using Lab7.Interfaces;
using Lab7.Models;
using Ninject;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Lab7.Controllers
{
    public class WebController : ApiController
    {
        private string status;
        private TSContext repo = new TSContext();

        public IEnumerable<TS> GetAllTS()
        {
            return repo.GetTSList();
        }

        public TS GetTS(int id)
        {
            return repo.GetItem(id);
        }

        [HttpPost]
        public void AddTS(TS ts)
        {
            if (!ModelState.IsValid)
            {
                var message = string.Join(" | ", ModelState.Values.SelectMany(v => v.Errors).Select(e => e.ErrorMessage));
                throw new Exception("Модель не валидна: " + message);
            }
            else
            {
                repo.Add(ts, out status);
                foreach (var d in ModelState.Select(x => x.Value.Errors).Where(y => y.Count > 0).ToList()) ;
                if (status != "ok")
                    throw new Exception(status);
            }       
        }

        public void DeleteTS(int id)
        {
            repo.Delete(id,out status);
        }

        [HttpPut]
        public void EditTs(TS ts)
        {
            if (!ModelState.IsValid)
            {
                var message = string.Join(" | ", ModelState.Values.SelectMany(v => v.Errors).Select(e => e.ErrorMessage));
                throw new Exception("Модель не валидна: " + message);
            }
            else
            {
                repo.Edit(ts, out status);
                foreach (var d in ModelState.Select(x => x.Value.Errors).Where(y => y.Count > 0).ToList()) ;
                if (status != "ok")
                    throw new Exception(status);
            }
        }
    }
}
