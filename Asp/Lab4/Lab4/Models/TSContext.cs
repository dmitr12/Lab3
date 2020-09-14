using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Lab4.Models
{
    public class TSContext:DbContext
    {
        public DbSet<TS> TSses { get; set; }
    }
}