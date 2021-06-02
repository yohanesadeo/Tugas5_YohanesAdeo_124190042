package id.as.upnyk.deokstore.cart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.as.upnyk.deokstore.R;
import id.as.upnyk.deokstore.addupdateorder.AddUpdateOrderActivity;
import id.as.upnyk.deokstore.addupdateorder.AddUpdateView;
import id.as.upnyk.deokstore.db.TopUp;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private List<TopUp> topUpList;
    private Context context;

    public CartListAdapter(List<TopUp> topUpList, Context context) {
        this.topUpList = topUpList;
        this.context = context;
    }

    public void setTopUpList(List<TopUp> topUpList) {
        this.topUpList = topUpList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return topUpList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGame, tvAmount, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGame = itemView.findViewById(R.id.tv_list_item_order_game);
            tvAmount = itemView.findViewById(R.id.tv_list_item_order_amount);
            tvStatus = itemView.findViewById(R.id.tv_item_list_order_status);
        }

        public void bind(int position) {
            tvGame.setText(topUpList.get(position).getGameName());
            tvAmount.setText(String.valueOf(topUpList.get(position).getAmount()));
            tvStatus.setText(topUpList.get(position).isHasPaid() ? "Paid" : "Unpaid");

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, AddUpdateOrderActivity.class);
                intent.putExtra("EXTRA_TOP_UP", topUpList.get(position));
                context.startActivity(intent);
            });
        }
    }
}
