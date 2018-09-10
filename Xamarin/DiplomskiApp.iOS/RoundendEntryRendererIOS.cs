using System;

using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

             [assembly: ExportRenderer(typeof(RoundedEntry),typeof(RoundendEntryRendererIOS))]
namespace DiplomskiApp.iOS
{
    public class RoundendEntryRendererIOS : EntryRenderer
    {
        protected override void OnElementChanged(ElementChangedEventArgs<Entry> e)
        {
            base.OnElementChanged(e);
            if(e.OldElement==null){
                Control.Layer.CornerRadius = 4;
                Control.Layer.BorderWidth = 3f;
                Control.Layer.BorderColor = Color.Pink;
            }
        }

    }
}

 