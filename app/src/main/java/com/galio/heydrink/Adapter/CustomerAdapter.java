package com.galio.heydrink.Adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.galio.heydrink.Data.DeliverOrder;
import com.galio.heydrink.Data.Order;
import com.galio.heydrink.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

    public class CustomerAdapter extends MainAdapter {
        private SparseBooleanArray selectedItems = new SparseBooleanArray();
        private int prePosition = -1;

    public class CustomerViewHolder extends MainViewHolder implements View.OnClickListener {
        private Context context;
        private RelativeLayout wholeLayout;

        private TextView[] destName = new TextView[2];
        private TextView[] destTime = new TextView[2];
        private ImageView[] dest = new ImageView[2];

        private RelativeLayout hideView;
        private ImageButton foldBtn;
        private ImageButton orderBtn;

        private int position;

        public CustomerViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            this.context = context;
            wholeLayout =itemView.findViewById(R.id.customerItemRecyclerView);
            hideView = itemView.findViewById(R.id.hideRecyclerView);
            foldBtn = itemView.findViewById(R.id.foldBtn);
            orderBtn = itemView.findViewById(R.id.orderBtn);
            Random random = new Random();
            random.setSeed(System.currentTimeMillis());

            for (int i = 0; i < 2; i++) {
                String destImg = "building" + Integer.toString((random.nextInt(100) % 3) + 1);
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

            wholeLayout.setOnClickListener(this);
            foldBtn.setOnClickListener(this);
            orderBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.customerItemRecyclerView:
                    if (selectedItems.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItems.delete(position);
                    } else {
                        // 직전의 클릭됐던 Item의 클릭상태를 지움
                        selectedItems.delete(prePosition);
                        // 클릭한 Item의 position을 저장
                        selectedItems.put(position, true);
                    }
                    // 해당 포지션의 변화를 알림
                    if (prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    prePosition = position;
                    break;

                case R.id.foldBtn:
                    if (selectedItems.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItems.delete(position);
                    }
                    // 해당 포지션의 변화를 알림
                    if (prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    prePosition = position;


                case R.id.orderBtn:
                    Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_customer_select_store);
                    break;
            }
        }

        public void setUI(DeliverOrder deliverOrder, int position) {
            ArrayList<String> destinations = deliverOrder.getDestinations();
            HashMap<String, String> orderDestTime = deliverOrder.getDestTime();
            this.position = position;

            changeVisibility(selectedItems.get(position));

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

        private void changeVisibility(final boolean isExpanded) {
            // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
            int dpValue = 300;
            float d = context.getResources().getDisplayMetrics().density;
            int height = (int) (dpValue * d);

            // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            // Animation이 실행되는 시간, n/1000초
            va.setDuration(300);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // value는 height 값
                    int value = (int) animation.getAnimatedValue();
                    // imageView의 높이 변경
                    hideView.getLayoutParams().height = value;
                    hideView.requestLayout();
                    // imageView가 실제로 사라지게하는 부분
                    hideView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
            });
            // Animation start
            va.start();
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

        ((CustomerViewHolder) holder).setUI(deliverOrder, position);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
