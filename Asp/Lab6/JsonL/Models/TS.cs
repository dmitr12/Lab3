using System.ComponentModel.DataAnnotations;
using System.Web.Mvc;

namespace JsonL.Models
{
    public class TS
    {
        [HiddenInput(DisplayValue = false)]
        public int Id { get; set; }
        [Display(Name = "Фамилия")]
        [Required]
        public string Surname { get; set; }
        [Display(Name = "Телефон")]
        [Required]
        [RegularExpression(@"^[1-9]{1}[0-9]{6}$", ErrorMessage = "Необходимо ввести 7 цифр, начиная не с 0")]
        public string Phone { get; set; }
    }
}
