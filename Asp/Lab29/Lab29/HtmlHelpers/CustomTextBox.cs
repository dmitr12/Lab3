using System;
using System.Linq.Expressions;
using System.Web.Mvc;
using System.Web.Mvc.Html;

namespace Lab29.HtmlHelpers
{
    public static class CustomTextBox
    {
        public static MvcHtmlString CustomBox<TModel, TProperty>(
            this HtmlHelper<TModel> htmlHelper,
            Expression<Func<TModel, TProperty>> expression,
            object htmlAttributes)
        {
            return htmlHelper.TextBoxFor(expression, null, htmlAttributes);
        }
    }
}