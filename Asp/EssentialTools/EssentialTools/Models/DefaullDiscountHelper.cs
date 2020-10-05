using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace EssentialTools.Models
{
    public class DefaullDiscountHelper : IDiscountHelper
    {
        public decimal discountSize;

        public DefaullDiscountHelper(decimal discountSize)
        {
            this.discountSize = discountSize;
        }

        public decimal ApplyDiscount(decimal totalParam)
        {
            return (totalParam - (discountSize / 100m * totalParam));   
        }
    }
}