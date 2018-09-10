        using System;
        using System.Collections.Generic;
        using System.Collections.ObjectModel;
        using System.Net.Http;
        using System.Text;
        using System.Threading.Tasks;
        using DiplomskiApp.Models;
        using Xamarin.Forms;
        using Newtonsoft.Json;
        using System.Diagnostics;
        using static Newtonsoft.Json.JsonConvert;
    using Plugin.Media;
    using Plugin.Media.Abstractions;

    namespace DiplomskiApp.Views
        {
    public partial class Warrents : ContentPage
            {
            private MediaFile mediaFile;
        public int elementHeight = 11;

                public ObservableCollection<Warrent> warrents { get; set; }
                private Warrent _oldWarrent;

                public Warrents()
                {

                    warrents = new ObservableCollection<Warrent>(); 
                    // In the Real World, we would actually do something...
                    // For this example, we're just going to (asynchronously) wait 100ms.

                    InitializeComponent();
                    fetchWwarrents();
                    warrentList.ItemsSource = warrents;
                }

                private async Task fetchWwarrents()
                {
                    using (var client = new HttpClient())
                    {
                        HttpContent content = null;
                        var dict = new Dictionary<string, string>();
                        dict.Add("driver", "1");
                        var json = SerializeObject(dict);
                        content = new StringContent(json, Encoding.UTF8, "application/json");
                        Stopwatch sw = new Stopwatch();
                        sw.Start();

                        using (var response = await client.PostAsync("http://perductor.hr/aplikacija/rest/radninalozi.php", content))
                        {
                            var responseStr = await response.Content.ReadAsStringAsync();
                            sw.Stop();

                            Console.WriteLine("Elapsed={0}", sw.Elapsed);
                            var items = JsonConvert.DeserializeObject<List<Warrent>>(responseStr);
                            foreach (Warrent obj in items)
                            {
                                obj.isVisible = false;
                            if(obj.status=="1"){
                                obj.image = "green_status.png";
                            }else if (obj.status == "2")
                            {
                                obj.image = "braun_status.png";
                            }else if (obj.status == "3")
                            {
                                obj.image = "red_status.png";
                            }
                                warrents.Add(obj);
                            }
                            if ("" != (responseStr))
                            {
                                Debug.WriteLine(responseStr);
                            }
                            else
                            {
                                Debug.WriteLine(responseStr);
                            }
                        }
                    }
                }
                void Handle_ItemTapped(object sender, Xamarin.Forms.ItemTappedEventArgs e)
                {
                    var product = e.Item as Warrent;
                    HideOrShowProduct(product);
                }

                private void HideOrShowProduct(Warrent warrent)
                {          
                    if(_oldWarrent == warrent){
                        warrent.isVisible = !warrent.isVisible;
                        updateProduct(warrent);
                    }else{
                        if(_oldWarrent !=null){
                            
                            _oldWarrent.isVisible = false;
                            updateProduct(_oldWarrent);

                        }
                        warrent.isVisible = true;
                        updateProduct(warrent);
                    }
                _oldWarrent = warrent;
                }

                private void updateProduct(Warrent warrent)
                {
                    var index = warrents.IndexOf(warrent);
                    warrents.Remove(warrent);
                    warrents.Insert(index,warrent);       
                }
            }
        }