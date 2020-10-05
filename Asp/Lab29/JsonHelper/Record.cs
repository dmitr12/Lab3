using System.ComponentModel.DataAnnotations;
using Newtonsoft.Json;

namespace JsonHelper
{
    [JsonObject(Title = nameof(Record))]
    public class Record
    {
        [Display(Name = "Name")]
        [JsonProperty(PropertyName = nameof(Name))]
        public string Name { get; set; }

        [Display(Name = "Phone Number")]
        [JsonProperty(PropertyName = nameof(PhoneNumber))]
        public string PhoneNumber { get; set; }
    }
}