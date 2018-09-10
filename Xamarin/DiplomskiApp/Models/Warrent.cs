using System;
namespace DiplomskiApp.Models
{
    public class Warrent
    {
        public string id { get; set;}
        public string orderdate{ get; set;}
        public string loadingdate { get; set;}
        public string unloadingdate { get; set;}
        public string route { get; set;}
        public string status { get; set;}
        public string note { get; set;}
        public bool isVisible { get; set; }
        public string image { get;  set;}

        public Warrent()
        {
            this.isVisible = false; 
        }
    }
}
