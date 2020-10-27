using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using TestWebApi.Models;

namespace TestWebApi.Controllers
{
    public class HomeController : Controller
    {
        ReservationRepository repository = ReservationRepository.Current;

        public ViewResult Index()
        {
            return View();
        }
    }
}