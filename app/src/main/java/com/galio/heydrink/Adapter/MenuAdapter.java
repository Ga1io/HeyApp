package com.galio.heydrink.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galio.heydrink.Data.Menu;
import com.galio.heydrink.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private ArrayList<Menu> data = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mContext = parent.getContext();

        View view = inflater.inflate(R.layout.menu_item, parent, false);
        MenuViewHolder holder = new MenuViewHolder(view, mContext);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setMenu(ArrayList<Menu> data){
        this.data = data;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView menuImg;
        private TextView menuName;
        private TextView menuPrice;

        private Context context;
        private RelativeLayout relativeLayout;

        public MenuViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;

            menuImg= itemView.findViewById(R.id.menuImg);
            menuName = itemView.findViewById(R.id.menuName);
            menuPrice = itemView.findViewById(R.id.menuPrice);
            relativeLayout = itemView.findViewById(R.id.menuItemRelativeLayout);

            relativeLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.menuItemRelativeLayout:
                    Toast.makeText(context, "메뉴 클릭함", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        public void onBind(Menu menu){
            if (menu.img != -1){
                menuImg.setBackgroundResource(menu.img);
            }
            menuName.setText(menu.name);
            menuPrice.setText(menu.price);
        }
    }
}
