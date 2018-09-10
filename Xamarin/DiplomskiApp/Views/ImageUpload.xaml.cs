using System;
using System.Collections.Generic;
using System.Net.Http;
using Plugin.Media;
using Plugin.Media.Abstractions;
using Plugin.Permissions;
using Plugin.Permissions.Abstractions;
using Xamarin.Forms;

namespace DiplomskiApp.Views
{
    public partial class ImageUpload : ContentPage
    {

        private MediaFile mediaFile;
        public ImageUpload()
        {
            InitializeComponent();
        }

        private async void PickPhotoClicked(object sender, EventArgs e)
        {
            var status = await CrossPermissions.Current.CheckPermissionStatusAsync(Permission.Camera);
            if (status != PermissionStatus.Granted)
            {
                if (await CrossPermissions.Current.ShouldShowRequestPermissionRationaleAsync(Permission.Camera))
                {
                    await DisplayAlert("Camera Permission", "Allow SavR to access your camera", "OK");
                }

                var results = await CrossPermissions.Current.RequestPermissionsAsync(new[] { Permission.Camera });
                status = results[Permission.Camera];
            }

            await CrossMedia.Current.Initialize();

            if (!CrossMedia.Current.IsPickPhotoSupported)
            {
                await DisplayAlert("No PickPhoto", ":( No PickPhoto avaiblable.", "OK");
                return;
            }
            mediaFile = await CrossMedia.Current.PickPhotoAsync();
            if (mediaFile == null)
            {
                FileImage.Source = ImageSource.FromStream(() => {
                    return mediaFile.GetStream();
                });
            }

        }

        private async void TakePhotoClicked(object sender, EventArgs e){
            await CrossMedia.Current.Initialize();

            if(!CrossMedia.Current.IsCameraAvailable || !CrossMedia.Current.IsTakePhotoSupported){
                await DisplayAlert("No Camera", ": No camera available", "OK");
                return;
            }
            mediaFile = await CrossMedia.Current.TakePhotoAsync(new StoreCameraMediaOptions
            {
                Directory = "Sample",
                Name = "myImage.jpg"
            });
            if (mediaFile == null)
                return;
            FileImage.Source = ImageSource.FromStream(() =>
            {
                return mediaFile.GetStream();
            });
        }
        private async void UploadFileClicked(object sender, EventArgs e)
        {
            var content = new MultipartFormDataContent();
            content.Add(new StreamContent(mediaFile.GetStream()), "\"file\"", $"\"{mediaFile.Path}\"");
            var httpClient = new HttpClient();
            var uploadServiceBaseAddress = "http://transport.virtualka.prolink.hr/rest/slike.php";
            var httpResponseMessage = await httpClient.PostAsync(uploadServiceBaseAddress, content);
        }
    }
}
