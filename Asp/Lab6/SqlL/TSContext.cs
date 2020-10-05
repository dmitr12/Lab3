using JsonL.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SqlL
{
    public class TSContext : DbContext
    {
        public TSContext(string connectionString) : base(connectionString) { }
        public DbSet<TS> TSses { get; set; }
    }
}
