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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Adapter.DeliverInfoAdapter;
import com.example.heydongju.Data.DeliverInfoData;
import com.example.heydongju.R;
import com.example.heydongju.Server.SharedViewModel;

import java.util.Arrays;
import java.util.List;

public class DeliverMyListFragment extends Fragment  {
    private RelativeLayout home;
    private RelativeLayout mylist;
    private RelativeLayout search;
    private DeliverInfoAdapter adapter;
    private ImageView toggle;
    private SharedViewModel model;
    private String mode;

    private RelativeLayout mypage;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.deliver_mylist, container, false);
        home = (RelativeLayout) root.findViewById(R.id.home);
        mylist = (RelativeLayout) root.findViewById(R.id.mylist);
        search = (RelativeLayout) root.findViewById(R.id.search);
        mypage = (RelativeLayout) root.findViewById(R.id.mypage);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        toggle = (ImageView) root.findViewById(R.id.toggle);
        mode=model.getMode();
        mylist.setSelected(true);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new DeliverInfoAdapter();

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
                    Navigation.findNavController(view).navigate(R.id.action_nav_deliver_mylist_to_nav_deliver_home);

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

                    Navigation.findNavController(root).navigate(R.id.action_nav_deliver_mylist_to_nav_deliver_store);


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

                    Navigation.findNavController(root).navigate(R.id.action_nav_deliver_mylist_to_nav_deliver_mypage);


                }
            }
        });

        toggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                    Navigation.findNavController(root).navigate(R.id.action_nav_deliver_mylist_to_nav_customer_home);
                    Log.e("mode",mode);
                    model.setMode("customer");

            }
        });


        return root;
    }


}
