using JsonL.Interfaces;
using JsonL.Models;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SqlL
{
    public class SqlRepository : IPhoneDictionary
    {
        TSContext context;
        static int count=0;

        public SqlRepository(string connectionString)
        {
            context = new TSContext(connectionString);
            Debug.WriteLine($"объект SqlRepository создан, count={++count}");
        }

        public void Add(TS ts, out string status)
        {
            if (context.TSses.Any(item => item.Surname.ToLower() == ts.Surname.ToLower()))
            {
                status = $"В справочнике уже есть фамилия {ts.Surname}";
                return;
            }
            if (context.TSses.Any(item => item.Phone == ts.Phone))
            {
                status = $"В справочнике уже есть номер {ts.Phone}";
                return;
            }
            context.TSses.Add(ts);
            context.SaveChanges();
            status = "ok";
        }

        public void Delete(int id, out string status)
        {
            context.TSses.Remove(GetItem(id));
            context.SaveChanges();
            status = "ok";
        }

        public void Edit(TS ts, out string status)
        {
            if (context.TSses.Any(item => item.Surname.ToLower() == ts.Surname.ToLower() && item.Phone == ts.Phone))
            {
                status = "ok";
                return;
            }
            if (context.TSses.Any(item => item.Surname.ToLower() == ts.Surname.ToLower() && item.Id != ts.Id))
            {
                status = $"В справочнике уже есть фамилия {ts.Surname}";
                return;
            }
            if (context.TSses.Any(item => item.Phone == ts.Phone && item.Id != ts.Id))
            {
                status = $"В справочнике уже есть номер {ts.Phone}";
                return;
            }
            context.Entry(ts).State = System.Data.Entity.EntityState.Modified;
            context.SaveChanges();
            status = "ok";
        }

        public TS GetItem(int id)
        {
            return context.TSses.Find(id);
        }

        public List<TS> GetTSList()
        {
            return context.TSses.OrderBy(item => item.Surname).ToList();
        }
    }
}
