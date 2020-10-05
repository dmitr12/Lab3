using System.Collections.Generic;

namespace JsonHelper
{
    public interface IPhoneDictionary
    {
        List<Record> GetRecords();
        void Create(Record item);
        void Update(Record item);
        void Delete(string name);
    }
}