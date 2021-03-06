package com.esragungor.gyk401.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.esragungor.gyk401.R;
import com.esragungor.gyk401.adapters.DiyetOgunAdapter;
import com.esragungor.gyk401.models.DiyetOgun;

import java.util.ArrayList;

public class DiyetListesiFragment extends Fragment {

ListView lv_yemek;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_yemek_listesi,container,false);
        lv_yemek=view.findViewById(R.id.lv_yemeklist);
        ArrayList<DiyetOgun>diyetOgunListesi=new ArrayList<>();
        diyetOgunListesi.add(new DiyetOgun(R.drawable.menu_1,"kivi,kiraz,armut","600C"));
        diyetOgunListesi.add(new DiyetOgun(R.drawable.menu_2,"kivi,tavuk,armut","700C"));
        diyetOgunListesi.add(new DiyetOgun(R.drawable.menu_3,"kivi,pilav,çilek","900C"));
        diyetOgunListesi.add(new DiyetOgun(R.drawable.menu_4,"ceviz,köfte,armut","1000"));

        DiyetOgunAdapter adapter=new DiyetOgunAdapter(getActivity(),diyetOgunListesi,inflater);
        lv_yemek.setAdapter(adapter);
        return view;
    }
}
