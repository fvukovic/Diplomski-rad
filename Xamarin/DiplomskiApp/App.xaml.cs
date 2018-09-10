using System;
using Xamarin.Forms;
using DiplomskiApp.Views;
using Xamarin.Forms.Xaml;

[assembly: XamlCompilation(XamlCompilationOptions.Compile)]
namespace DiplomskiApp
{
    public partial class App : Application
    {

        public App()
        {
            InitializeComponent();

            Current.Resources = new ResourceDictionary();
            Color xamarin_color = Color.FromHex("#ffffff"); 
            var navigationStyle = new Style(typeof(NavigationPage));
            var barBackgroundColorSetter = new Setter { Property = NavigationPage.BarBackgroundColorProperty, Value = xamarin_color };
            navigationStyle.Setters.Add(barBackgroundColorSetter);
            Current.Resources.Add(navigationStyle);
            MainPage =new NavigationPage(new LoginPage()); 
        }

        protected override void OnStart()
        {
            // Handle when your app starts
        }

        protected override void OnSleep()
        {
            // Handle when your app sleeps
        }

        protected override void OnResume()
        {
            // Handle when your app resumes
        }
    }
}
