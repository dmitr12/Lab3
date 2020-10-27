using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace Lab7_WCF_Server
{
    // ПРИМЕЧАНИЕ. Команду "Переименовать" в меню "Рефакторинг" можно использовать для одновременного изменения имени интерфейса "IPhoneBook" в коде и файле конфигурации.
    [ServiceContract]
    public interface IPhoneBook
    {
        [OperationContract]
        TS[] GetAllTS();

        [OperationContract]
        string AddTS(TS ts);

        [OperationContract]
        string EditTS(TS ts);

        [OperationContract]
        void DeleteTS(int id);
    }
}
