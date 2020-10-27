using Lab7.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab7.Interfaces
{
    public interface IPhone
    {
        List<TS> GetTSList();
        TS GetItem(int id);
        void Add(TS ts, out string status);
        void Edit(TS ts, out string status);
        void Delete(int id, out string status);
    }
}