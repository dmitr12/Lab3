using JsonL.Interfaces;
using JsonL.Models;
using Newtonsoft.Json;
using NLog;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JsonL
{
    public class JsonRepository : IPhoneDictionary
    {
        private string path=Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "file.json");
        static int count=0;

        public JsonRepository()
        {
            Debug.WriteLine($"объект JsonRepository создан, count={++count}");
        }
        public void Add(TS ts, out string status)
        {
            var listData = new List<TS>();
            if (File.Exists(path))
            {
                listData = GetTSList();
                if (listData.Any(item => item.Surname.ToLower() == ts.Surname.ToLower()))
                {
                    status = $"В справочнике уже есть фамилия {ts.Surname}";
                    return;
                }
                if (listData.Any(item => item.Phone == ts.Phone))
                {
                    status = $"В справочнике уже есть номер {ts.Phone}";
                    return;
                }
            }
            listData = listData.OrderBy(item => item.Id).ToList();
            if (listData.Count == 0)
                ts.Id = 1;
            else
                ts.Id = listData.Last().Id + 1;
            listData.Add(ts);
            File.WriteAllText(path, JsonConvert.SerializeObject(listData));
            status = "ok";
        }

        public void Delete(int id, out string status)
        {
            List<TS> list = GetTSList();
            if (list.Count > 0)
            {
                list.Remove(list.Where(item => item.Id == id).ToList()[0]);
                status = "ok";
            }
            else
                status = "Записей нет";
            File.WriteAllText(path, JsonConvert.SerializeObject(list));
        }

        public void Edit(TS ts, out string status)
        {
            var listData = new List<TS>();
            if (File.Exists(path))
            {
                listData = GetTSList();
                if (listData.Any(item => item.Surname.ToLower() == ts.Surname.ToLower() && item.Id != ts.Id))
                {
                    status = $"В справочнике уже есть фамилия {ts.Surname}";
                    return;
                }
                if (listData.Any(item => item.Phone == ts.Phone && item.Id != ts.Id))
                {
                    status = $"В справочнике уже есть номер {ts.Phone}";
                    return;
                }
            }
            for (int i = 0; i < listData.Count; i++)
            {
                if (listData[i].Id == ts.Id)
                {
                    if (listData[i].Surname.ToLower() == ts.Surname.ToLower() &&
                        listData[i].Phone == ts.Phone)
                    {
                        status = "ok";
                        return;
                    }
                    listData.Remove(listData[i]);
                    break;
                }
            }
            listData.Add(ts);
            File.WriteAllText(path, JsonConvert.SerializeObject(listData));
            status = "ok";
        }

        public TS GetItem(int id)
        {
            return GetTSList().Where(item => item.Id == id).ToList()[0];
        }

        public List<TS> GetTSList()
        {
            List<TS> list=null;
            if (File.Exists(path))
            {
                var jsonData = File.ReadAllText(path);
                list = JsonConvert.DeserializeObject<List<TS>>(jsonData) ?? new List<TS>();
            }
            return list;
        }
    }
}
