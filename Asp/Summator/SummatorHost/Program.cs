using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace SummatorHost
{
    class Program
    {
        static void Main()
        {
            using(var host=new ServiceHost(typeof(Summator.Summator)))
            {
                host.Open();
                Console.WriteLine("Хост запущен...");
                Console.ReadLine();
            }
        }
    }
}
