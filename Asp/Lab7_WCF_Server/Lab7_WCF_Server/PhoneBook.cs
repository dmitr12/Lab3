using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace Lab7_WCF_Server
{
    // ПРИМЕЧАНИЕ. Команду "Переименовать" в меню "Рефакторинг" можно использовать для одновременного изменения имени класса "PhoneBook" в коде и файле конфигурации.
    public class PhoneBook : IPhoneBook
    {
        string status;
        TSContext repo = new TSContext();
        public string AddTS(TS ts)
        {
            repo.Add(ts, out status);
            return status;
        }

        public void DeleteTS(int id)
        {
            repo.Delete(id, out status);
        }

        public string EditTS(TS ts)
        {
            repo.Edit(ts, out status);
            return status;
        }

        public TS[] GetAllTS()
        {
            return repo.GetTSList().ToArray();
        }
    }
}
