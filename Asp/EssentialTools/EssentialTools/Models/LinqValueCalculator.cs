using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace EssentialTools.Models
{
    public class LinqValueCalculator:IValueCalculator
    {
        private IDiscountHelper discounter;

        public LinqValueCalculator(IDiscountHelper discount)
        {
            discounter = discount;
        }
        public decimal ValueProducts(IEnumerable<Product> products) => discounter.ApplyDiscount(products.Sum(p=>p.Price));
    }
}