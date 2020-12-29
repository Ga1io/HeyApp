package com.example.heydongju.Deliver;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Adapter.AdvertiseAdapter;
import com.example.heydongju.Adapter.DeliverInfoAdapter;
import com.example.heydongju.Adapter.StoreRecommendAdapter;
import com.example.heydongju.Data.AdvertiseData;
import com.example.heydongju.Data.DeliverInfoData;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.R;
import com.example.heydongju.Server.SharedViewModel;

import java.util.Arrays;
import java.util.List;

public class DeliverHomeFragment extends Fragment  {
    private DeliverInfoAdapter adapter;
    private StoreRecommendAdapter adapter2;
    private AdvertiseAdapter adapter3;
    private RelativeLayout home;
    private RelativeLayout mylist;
    private RelativeLayout search;
    private RelativeLayout mypage;
    private ImageView toggle;
    private SharedViewModel model;
    private String mode;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.deliver_home, container, false);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        home = (RelativeLayout) root.findViewById(R.id.home);
        mylist = (RelativeLayout) root.findViewById(R.id.mylist);
        search = (RelativeLayout) root.findViewById(R.id.search);
        mypage = (RelativeLayout) root.findViewById(R.id.mypage);
        toggle = (ImageView) root.findViewById(R.id.toggle);
        home.setSelected(true);
        mode=model.getMode();
        Log.e("model",mode);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isSelected()){
                }
                else{
                    view.setSelected(true);
                    mylist.setSelected(false);
                    search.setSelected(false);
                    mypage.setSelected(false);

                }
            }
        });
        mylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isSelected()){
                }
                else{
                    view.setSelected(true);
                    home.setSelected(false);
                    mypage.setSelected(false);
                    search.setSelected(false);

                    Navigation.findNavController(view).navigate(R.id.action_nav_deliver_home_to_nav_deliver_mylist);

                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isSelected()){
                }
                else{
                    view.setSelected(true);
                    home.setSelected(false);
                    mypage.setSelected(false);
                    mylist.setSelected(false);

                    Navigation.findNavController(root).navigate(R.id.action_nav_deliver_home_to_nav_deliver_store);


                }
            }
        });
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isSelected()){
                }
                else{
                    view.setSelected(true);
                    home.setSelected(false);
                    mylist.setSelected(false);
                    search.setSelected(false);

                    Navigation.findNavController(view).navigate(R.id.action_nav_deliver_home_to_nav_deliver_mypage);


                }
            }
        });
        toggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                    Navigation.findNavController(root).navigate(R.id.action_nav_deliver_home_to_nav_customer_home);
                    Log.e("mode",mode);
                    model.setMode("customer");

            }
        });





        return root;
    }



}
