using System;
using System.Collections.Generic;
using DiplomskiApp.Views;
using Xamarin.Forms;

namespace DiplomskiApp
{
    public partial class MenuSlide : MasterDetailPage
    {
        public MenuSlide()
        {
            NavigationPage.SetHasBackButton(this, false);
            InitializeComponent();     
            NavigationPage.SetHasBackButton(this, false);
            Detail = new Warrents();  

        }

        void openMenu(object sender, System.EventArgs e)
        { 
            if(IsPresented==true){
                IsPresented = false;
            }else{
                IsPresented = true;
            }
        } 

        void goToWarents(object sender, System.EventArgs e){
            Detail = new Warrents();
            IsPresented = false;
        }

        void goToUpload(object sender, System.EventArgs e)
        {
            Detail = new ImageUpload();
            IsPresented = false;
        }
    }
}
