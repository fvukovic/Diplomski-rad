package com.example.filip.transport.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.database.entities.RadniNalog;
import com.example.filip.transport.Adapers.RVRadniNalogAdapter;
import com.example.filip.transport.Helpers.Baza;
import com.example.filip.transport.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Filip on 6.2.2017..
 */

public class SviRadniNalozi extends Fragment   {

    List<RadniNalog> lista = new ArrayList<>();
    public  RecyclerView rv;
    private StringRequest stringRequest;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.svi_radni_nalozi, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        stringRequest = new StringRequest(Request.Method.POST, "http://perductor.hr/aplikacija/rest/radninalozi.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("TUUUU"+response);
                try {
                    JSONArray jsonArr = new JSONArray(response);
                    List<RadniNalog> novaLista= new ArrayList<>();
                    for (int i = 0; i < jsonArr.length(); i++)
                    {
                        JSONObject jsonObj = jsonArr.getJSONObject(i);
                        RadniNalog novi = new RadniNalog();
                        novi.setId(jsonObj.getInt("id"));
                        novi.setDatum(jsonObj.getString("orderdate"));
                        novi.setKraj(jsonObj.getString("route"));
                        novi.setStatus(jsonObj.getInt("status"));
                        novi.setModel(jsonObj.getString("route"));
                        novaLista.add(novi);
                    }


                    Collections.shuffle(novaLista);
                    RVRadniNalogAdapter adapter = new RVRadniNalogAdapter(novaLista,getContext());
                    rv = (RecyclerView) rootView.findViewById(R.id.rv);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(llm);
                    rv.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), (CharSequence) error.networkResponse,Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("driver","1");
                return params;
                }
        };
        System.out.println(stringRequest);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


        return rootView;
    }

}
