using JsonL.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JsonL.Interfaces
{
    public interface IPhoneDictionary
    {
        List<TS> GetTSList();
        TS GetItem(int id);
        void Add(TS ts, out string status);
        void Edit(TS ts, out string status);
        void Delete(int id, out string status);
    }
}
