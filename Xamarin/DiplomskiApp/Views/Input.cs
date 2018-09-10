using System;

using Xamarin.Forms;

namespace DiplomskiApp.Views
{
    public class Input : ContentPage
    {
        public Input()
        {
            Content = new StackLayout
            {
                Children = {
                    new Label { Text = "Hello ContentPage" }
                }
            };
        }
    }
}

