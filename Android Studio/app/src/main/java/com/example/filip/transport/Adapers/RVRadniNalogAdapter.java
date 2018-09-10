package com.example.filip.transport.Adapers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.entities.RadniNalog;
import com.example.filip.transport.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class RVRadniNalogAdapter extends RecyclerView.Adapter<RVRadniNalogAdapter.RadniNalogViewHolder> {
    public static class RadniNalogViewHolder extends RecyclerView.ViewHolder{

       public CardView cv;
        TextView ponudaNaziv;
        RelativeLayout item ;
        ImageView image;
        int visina;
        Context context;
        LinearLayout zaDodat;
        LinearLayout dodani;
        Button button;


        RadniNalogViewHolder(View itemView,Context context) {
            super(itemView);
            cv = (CardView)itemView.findViewById( R.id.card_view);
            ponudaNaziv = (TextView)itemView.findViewById(R.id.nazivfirme);
            item = (RelativeLayout) itemView.findViewById(R.id.item);
            image = (ImageView) itemView.findViewById(R.id.image);
            zaDodat = (LinearLayout) itemView.findViewById(R.id.dodani);
            dodani = (LinearLayout) itemView.findViewById(R.id.grey);
            button = (Button) itemView.findViewById(R.id.angry_btn);
            this.context= context;
        }
    }


    List<RadniNalog> ponuda;
    Context context;

    public RVRadniNalogAdapter(List<RadniNalog> ponuda, Context context){
        this.ponuda = ponuda;
        this.context=context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RadniNalogViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.svi_nalozi_item, viewGroup, false);
        RadniNalogViewHolder pvh = new RadniNalogViewHolder(v,context);
        return pvh;
    }



    @Override
    public void onBindViewHolder(final RadniNalogViewHolder PonudeViewHolder, int i) {


        switch (ponuda.get(i).getStatus() )
        {
            case 1 :
                System.out.print("bio sam");
                PonudeViewHolder.image.setBackgroundResource(R.drawable.green_status);
                PonudeViewHolder.button.setVisibility(View.INVISIBLE);
                break;

            case 2:
                System.out.print("bio sam");
                PonudeViewHolder.image.setBackgroundResource(R.drawable.red_status);
                PonudeViewHolder.button.setText("POTVRDI PRIMITAK");
                break;
            case 3:
                System.out.print("bio sam");
                PonudeViewHolder.image.setBackgroundResource(R.drawable.braun_status);
                LayoutInflater inflater = (LayoutInflater)      PonudeViewHolder.context.getSystemService(LAYOUT_INFLATER_SERVICE);
                View childLayout = inflater.inflate(R.layout.layout_grey,
                        PonudeViewHolder.dodani);
                PonudeViewHolder.zaDodat.addView(childLayout);

                    break;
        }

        final ArrayList<RadniNalog> ponudaArrayList=new ArrayList<RadniNalog>();
        ponudaArrayList.add(ponuda.get(i));

        PonudeViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = v.getHeight();
                ViewGroup.LayoutParams params =  PonudeViewHolder.item.getLayoutParams();
                if(a<200) {
                    params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    PonudeViewHolder.visina = PonudeViewHolder.item.getHeight();
                }
                else {
                    params.height = PonudeViewHolder.visina;
                }
                    PonudeViewHolder.item.setLayoutParams(params);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ponuda.size();
    }
}
