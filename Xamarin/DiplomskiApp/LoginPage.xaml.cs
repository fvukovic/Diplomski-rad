using System;
using System.Collections.Generic; 
using Xamarin.Forms;

namespace DiplomskiApp
{
    public partial class LoginPage : ContentPage
    {
        
		void Login_Clicked(object sender, System.EventArgs e)
		{
            Navigation.PushAsync(new MenuSlide());
            
		}

        public LoginPage()
        {
            InitializeComponent();
            NavigationPage.SetHasNavigationBar(this, false);
        }
    }
}
