package com.example.heydongju.Adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Data.DeliverInfoData;
import com.example.heydongju.MainActivity;
import com.example.heydongju.R;

import java.util.ArrayList;

public class DeliverInfoAdapter extends RecyclerView.Adapter<DeliverInfoAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    public ArrayList<DeliverInfoData> listData = new ArrayList<>();
    private Context context;
    // Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deliverinfo, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position), position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    public void addItem(DeliverInfoData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RelativeLayout hideRecyclerView;
        private ImageView deliverPicture;
        private ImageView orderBtn;
        private TextView deliverName;
        private TextView train;
        private RatingBar deliverStar;
        private RatingBar deliverCups;
        private TextView time;
        private DeliverInfoData data;
        private int position;

        ItemViewHolder(View itemView) {
            super(itemView);
            hideRecyclerView=itemView.findViewById(R.id.hideRecyclerView);
            deliverPicture=itemView.findViewById(R.id.avater);
            deliverName=itemView.findViewById(R.id.deliverName);
            deliverStar=itemView.findViewById(R.id.deliverStar);
            deliverCups=itemView.findViewById(R.id.deliverCups);
            orderBtn=itemView.findViewById(R.id.orderBtn);
            time=itemView.findViewById(R.id.time);
            train=itemView.findViewById(R.id.train);
            hideRecyclerView=itemView.findViewById(R.id.hideRecyclerView);

        }

        void onBind(DeliverInfoData data, int position) {
            this.data = data;
            this.position = position;

            //deliverName.setText(data.getDeliverName());
            deliverName.setText("박동훈");
            deliverStar.setRating(5);
            deliverCups.setRating(5);
            time.setText("11:45");

           // deliverStar.setRating(data.getStar());


            changeVisibility(selectedItems.get(position));

            hideRecyclerView.setOnClickListener(this);
            train.setOnClickListener(this);
            orderBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.train:
                    if (selectedItems.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItems.delete(position);
                    } else {
                        // 직전의 클릭됐던 Item의 클릭상태를 지움
                        selectedItems.delete(prePosition);
                        // 클릭한 Item의 position을 저장
                        selectedItems.put(position, true);
                    }
                    notifyItemChanged(position);

                    // 해당 포지션의 변화를 알림
                    if (prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    prePosition = position;
                    break;
                case R.id.orderBtn:
                    Navigation.findNavController(v).navigate(R.id.action_nav_customer_home_to_nav_customer_select_store);

                    break;
            }
        }
        /**
         * 클릭된 Item의 상태 변경
         * @paramisExpandedItem을 펼칠 것인지 여부
         */


        private void changeVisibility(final boolean isExpanded) {
            // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
            int dpValue = 300;
            float d = context.getResources().getDisplayMetrics().density;
            int height = (int) (dpValue * d);

            // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            // Animation이 실행되는 시간, n/1000초
            va.setDuration(100);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // value는 height 값
                    int value = (int) animation.getAnimatedValue();
                    // imageView의 높이 변경
                    hideRecyclerView.getLayoutParams().height = value;
                    hideRecyclerView.requestLayout();
                    // imageView가 실제로 사라지게하는 부분
                    hideRecyclerView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
            });
            // Animation start
            va.start();
        }
    }
}