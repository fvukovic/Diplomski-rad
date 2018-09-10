using System;
using Android.Content;
using Android.Graphics.Drawables;
using DiplomskiApp;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(RoundedEntry), typeof(RoundendEntryRendererAndroid))]
             namespace DiplomskiApp
{
    public class RoundendEntryRendererAndroid : EntryRenderer
    {
        public RoundendEntryRendererAndroid(Context context) : base(context)
        {
        }

        protected override void OnElementChanged(ElementChangedEventArgs<Entry> e)
        {
            base.OnElementChanged(e);
            if (e.OldElement == null)
            {
                var gradientDrawable = new GradientDrawable();
                gradientDrawable.SetCornerRadius(10f);
                gradientDrawable.SetStroke(1, Android.Graphics.Color.Black);
                gradientDrawable.SetColor(Android.Graphics.Color.White);
                Control.SetBackground(gradientDrawable);
                Control.SetHeight(70);
                Control.SetPadding(50, Control.PaddingTop, Control.PaddingBottom, Control.PaddingLeft);
            }
        }
    }
}