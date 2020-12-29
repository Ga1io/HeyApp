package com.example.heydongju.Common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.R;
import com.example.heydongju.Server.SharedViewModel;

public class ChooseFragment extends Fragment {


    ImageView deliver;
    ImageView customer;
    private RecyclerView recyclerView;

    private SharedViewModel model;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.common_choose, container, false);


        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        deliver = root.findViewById(R.id.deliver);
        customer = root.findViewById(R.id.order);

        deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("mode", "deliver");
                model.setMode("deliver");
                Navigation.findNavController(view).navigate(R.id.action_nav_choose_to_nav_login,bundle);
            }
        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("mode", "customer");
                model.setMode("customer");
                Navigation.findNavController(view).navigate(R.id.action_nav_choose_to_nav_customer_home,bundle);
            }
        });

        return root;
    }
}