using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace Host
{
    class Program
    {
        static void Main(string[] args)
        {
            using(var host=new ServiceHost(typeof(Lab7_WCF_Server.PhoneBook)))
            {
                host.Open();
                Console.WriteLine("Хост запущен...");
                Console.ReadLine();
            }
        }
    }
}
