package com.example.heydongju.Adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Data.CustomerOrderData;
import com.example.heydongju.Data.DeliverInfoData;
import com.example.heydongju.Data.MenuData;
import com.example.heydongju.MainActivity;
import com.example.heydongju.R;

import java.util.ArrayList;

import static com.example.heydongju.Customer.CustomerAmountFragment.totalPrice;

public class AmountAdapter extends RecyclerView.Adapter<AmountAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    public ArrayList<MenuData> listData = new ArrayList<>();
    private Context context;
    // Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;
    public int priceSum=0;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amount, parent, false);
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

    public void addItem(MenuData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView drinkName;
        private TextView drinkPrice;
        private RelativeLayout main;
        private RelativeLayout hideRecyclerView;
        private RelativeLayout whole;
        CardView less;
        CardView more;
        TextView prnumber;
        private int position;
        MainActivity activity = (MainActivity)context;

        private RelativeLayout relative1;
        private RelativeLayout relative2;
        private RelativeLayout relative3;
        private RelativeLayout relative4;
        private RelativeLayout relative5;
        private RelativeLayout relative6;
        private RelativeLayout relative7;
        private RelativeLayout relative8;

        private TextView optiontext1;
        private TextView optiontext2;
        private TextView optiontext3;
        private TextView optiontext4;
        private TextView optiontext5;
        private TextView optiontext6;
        private TextView optiontext7;
        private TextView optiontext8;

        private CheckBox option1;
        private CheckBox option2;
        private CheckBox option3;
        private CheckBox option4;
        private CheckBox option5;
        private CheckBox option6;
        private CheckBox option7;
        private CheckBox option8;

        private MenuData data;

        ItemViewHolder(View itemView) {
            super(itemView);
            main=itemView.findViewById(R.id.layout_food);
            hideRecyclerView=itemView.findViewById(R.id.hideRecyclerView);
            drinkPrice=itemView.findViewById(R.id.drink_price);
            drinkName=itemView.findViewById(R.id.drink_name);
            less=itemView.findViewById(R.id.less);
            more=itemView.findViewById(R.id.more);
            prnumber=itemView.findViewById(R.id.prnumber);
            whole=itemView.findViewById(R.id.layout_whole);
            prnumber=itemView.findViewById(R.id.prnumber);



        }

        void onBind(MenuData data, int position) {
            this.data = data;
            this.position = position;

            //deliverName.setText(data.getDeliverName());
            drinkName.setText(data.name);
            drinkPrice.setText(String.valueOf(data.price));
            prnumber.setText(String.valueOf(data.amount));



            if(data.getOptions()!=null){

            }

            for(int i=0; i<listData.size(); i++){
                listData.get(i).setSelected(false);
            }
            // deliverStar.setRating(data.getStar());selectedItems
            if(data.selected==true){
                whole.setSelected(true);
            }else{
                whole.setSelected(false);
            }




            main.setOnClickListener(this);
            changeVisibility(selectedItems.get(position));
            hideRecyclerView.setOnClickListener(this);
            less.setOnClickListener(this);
            more.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_food:
                    if (selectedItems.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItems.delete(position);
                        data.setSelected(false);
                    } else {
                        // 직전의 클릭됐던 Item의 클릭상태를 지움
                        selectedItems.delete(prePosition);
                        // 클릭한 Item의 position을 저장
                        selectedItems.put(position, true);
                        for(int i=0; i<listData.size(); i++){
                            listData.get(i).setSelected(false);
                        }
                        data.setSelected(true);
                    }
                    notifyItemChanged(position);

                    // 해당 포지션의 변화를 알림
                    if (prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    prePosition = position;
                    break;
                case R.id.orderBtn:
                    Navigation.findNavController(v).navigate(R.id.action_nav_customer_home_to_nav_train);

                    break;
                case R.id.less:
                    if(data.amount>0){
                        data.amount=data.amount-1;
                    }
                    prnumber.setText(String.valueOf(data.amount));

                    priceSum=0;
                    for(int i=0; i<listData.size(); i++){
                        priceSum=priceSum+listData.get(i).getAmount()*listData.get(i).getPrice();
                        Log.e(String.valueOf(i)+"아이템", listData.get(i).getAmount()+"개 "+listData.get(i).getPrice()+"원");
                    }

                    Log.e("가격", String.valueOf(priceSum));

                    totalPrice.setText(String.valueOf(priceSum));

                    break;
                case R.id.more:
                    data.amount=data.amount+1;
                    prnumber.setText(String.valueOf(data.amount));

                    priceSum=0;

                    for(int i=0; i<listData.size(); i++){
                        priceSum=priceSum+listData.get(i).getAmount()*listData.get(i).getPrice();
                        Log.e(String.valueOf(i)+"아이템", listData.get(i).getAmount()+"개 "+listData.get(i).getPrice()+"원");
                    }

                    Log.e("가격", String.valueOf(priceSum));
                    totalPrice.setText(String.valueOf(priceSum));

                    break;
            }
        }
        /**
         * 클릭된 Item의 상태 변경
         * @paramisExpandedItem을 펼칠 것인지 여부
         */


        private void changeVisibility(final boolean isExpanded) {
            // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
            int dpValue = 100;
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