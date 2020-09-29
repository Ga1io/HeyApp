package com.galio.heydrink.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.galio.heydrink.Data.DeliverOrder;
import com.galio.heydrink.Data.Order;
import com.galio.heydrink.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CustomerAdapter extends MainAdapter {

    public class CustomerViewHolder extends MainViewHolder {

        private TextView[] destName = new TextView[2];
        private TextView[] destTime = new TextView[2];
        private ImageView[] dest = new ImageView[2];

        public CustomerViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            destName[0] = itemView.findViewById(R.id.dest1Name);
            destName[1] = itemView.findViewById(R.id.dest2Name);
            destTime[0] = itemView.findViewById(R.id.dest1Time);
            destTime[1] = itemView.findViewById(R.id.dest2Time);

            Random random = new Random();

            for (int i = 0; i < 2; i++) {
                String destImg = "building" + Integer.toString(random.nextInt(3) + 1);
                String name = "dest" + Integer.toString(i + 1);
                String buildingName = name + "Name";
                String time = name + "Time";

                destName[i] = itemView.findViewById(itemView.getResources().
                        getIdentifier(buildingName, "id", context.getPackageName()));
                destTime[i] = itemView.findViewById(itemView.getResources().
                        getIdentifier(time, "id", context.getPackageName()));
                dest[i] = itemView.findViewById(itemView.getResources().
                        getIdentifier(name, "id", context.getPackageName()));

                dest[i].setBackgroundResource(itemView.getResources().
                        getIdentifier(destImg, "drawable", context.getPackageName()));
            }
        }

        public void setUI(DeliverOrder deliverOrder) {
            ArrayList<String> destinations = deliverOrder.getDestinations();
            HashMap<String, String> orderDestTime = deliverOrder.getDestTime();

            if (destinations.size() == 1) {
                String destinationName = destinations.get(0);

                destName[0].setVisibility(View.INVISIBLE);
                destTime[0].setVisibility(View.INVISIBLE);
                dest[0].setVisibility(View.INVISIBLE);

                destName[1].setText(destinationName);
                destTime[1].setText(orderDestTime.get(destinationName));

            } else if (destinations.size() == 2) {
                destName[0].setVisibility(View.VISIBLE);
                destTime[0].setVisibility(View.VISIBLE);
                dest[0].setVisibility(View.VISIBLE);

                for (int i = 0; i < destinations.size(); i++) {
                    String destinationName = destinations.get(i);

                    destName[i].setText(destinationName);
                    destTime[i].setText(orderDestTime.get(destinationName));
                }
            }
        }
    }

    public CustomerAdapter() {
        orders = new ArrayList<>();
    }

    public CustomerAdapter(ArrayList<Order> deliverOrders) {
        this.orders = deliverOrders;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.customer_item, parent, false);
        CustomerViewHolder holder = new CustomerViewHolder(view, context);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        DeliverOrder deliverOrder = (DeliverOrder) orders.get(position);

        ((CustomerViewHolder) holder).setUI(deliverOrder);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
