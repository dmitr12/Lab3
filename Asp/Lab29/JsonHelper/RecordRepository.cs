using Newtonsoft.Json;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using JsonHelper.Helpers;

namespace JsonHelper
{
    public class RecordRepository : IPhoneDictionary
    {
        public List<Record> GetRecords()
        {
            var content = RecordsFileHelper.GetRecordsContent();
            return JsonConvert.DeserializeObject<List<Record>>(content);
        }

        public void Create(Record record)
        {
            var records = GetRecords();
            records.Add(record);
            WriteRecords(records);
        }

        public void Update(Record record)
        {
            var records = GetRecords().Where(r => r.Name != record.Name).ToList();
            records.Add(record);
            WriteRecords(records);
        }

        public void Delete(string name)
        {
            WriteRecords(GetRecords().Where(r => r.Name != name));
        }

        private static void WriteRecords(IEnumerable<Record> records)
        {
            var parsedRecords = JsonConvert.SerializeObject(records);
            using (var streamWriter = new StreamWriter(RecordsFileHelper.GetFilePath()))
            {
                streamWriter.Write(parsedRecords);
            }
        }
    }
}
