package com.example.heydongju.Common;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.heydongju.R;
import com.example.heydongju.Server.SharedViewModel;

public class MypageFragment extends Fragment {
    private RelativeLayout home;
    private RelativeLayout mylist;
    private RelativeLayout search;
    private RelativeLayout mypage;

    private ImageView recent;
    private ImageView orderhistory;
    private ImageView deliverhistory;

    private TextView agree;
    private TextView monthorder;
    private TextView monthdeliver;
    private TextView comment;
    private TextView status;
    private TextView faq;
    private ImageView toggle;
    private SharedViewModel model;
    private String mode;

    //agree
    //comment
    //deliverhistory
    //faq
    //mothdeliver
    //monthorder
    //recent
    //orderhistory
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.common_mypage, container, false);
        home = (RelativeLayout) root.findViewById(R.id.home);
        mylist = (RelativeLayout) root.findViewById(R.id.mylist);
        search = (RelativeLayout) root.findViewById(R.id.search);
        mypage = (RelativeLayout) root.findViewById(R.id.mypage);
        toggle = (ImageView) root.findViewById(R.id.toggle);
        mypage.setSelected(true);

        recent = (ImageView) root.findViewById(R.id.recent);
        orderhistory = (ImageView) root.findViewById(R.id.orderhistory);
        deliverhistory = (ImageView) root.findViewById(R.id.deliverhistory);
        agree = (TextView) root.findViewById(R.id.agree);
        monthdeliver = (TextView) root.findViewById(R.id.monthdeliver);
        monthorder = (TextView) root.findViewById(R.id.monthorder);
        comment = (TextView) root.findViewById(R.id.comment);
        status = (TextView) root.findViewById(R.id.status);
        faq = (TextView) root.findViewById(R.id.faq);

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        mode=model.getMode();




        recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_customer_mypage_to_nav_common_recent);
            }
        });
        orderhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_customer_mypage_to_nav_common_orderhistory);
            }
        });
        deliverhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_customer_mypage_to_nav_common_deliverhistory);
            }
        });
        monthdeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_customer_mypage_to_monthdeliver);
            }
        });
        monthorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_customer_mypage_to_nav_monthorder);
            }
        });
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_customer_mypage_to_nav_agree);
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_customer_mypage_to_nav_comment);
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_customer_mypage_to_nav_status);
            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_customer_mypage_to_nav_faq);
            }
        });

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


                    if(mode=="customer")
                        Navigation.findNavController(view).navigate(R.id.action_nav_customer_mypage_to_nav_customer_home);
                    else
                        Navigation.findNavController(view).navigate(R.id.action_nav_deliver_mypage_to_nav_deliver_home);

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


                    if(mode=="customer")
                        Navigation.findNavController(view).navigate(R.id.action_nav_common_mypage_to_nav_customer_mylist);
                    else
                        Navigation.findNavController(view).navigate(R.id.action_nav_deliver_mypage_to_nav_deliver_mylist);

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


                    if(mode=="customer")
                        Navigation.findNavController(root).navigate(R.id.action_nav_customer_mypage_to_nav_customer_st);
                    else
                        Navigation.findNavController(root).navigate(R.id.action_nav_deliver_mypage_to_nav_deliver_store);


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



                }
            }
        });

        toggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                if(mode.equals("customer")) {
                    Navigation.findNavController(root).navigate(R.id.action_nav_customer_mypage_to_nav_deliver_home);
                    Log.e("mode",mode);
                    model.setMode("deliver");
                }
                else if(mode.equals("deliver")){
                    Navigation.findNavController(root).navigate(R.id.action_nav_deliver_mypage_to_nav_customer_home);
                    Log.e("mode",mode);
                    model.setMode("customer");
                }
            }
        });

        return root;
    }


}
