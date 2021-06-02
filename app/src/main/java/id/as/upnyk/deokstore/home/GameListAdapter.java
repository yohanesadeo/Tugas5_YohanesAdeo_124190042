package id.as.upnyk.deokstore.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.as.upnyk.deokstore.GameModel;
import id.as.upnyk.deokstore.R;
import id.as.upnyk.deokstore.addupdateorder.AddUpdateOrderActivity;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    private Context context;
    private List<GameModel> games;

    public GameListAdapter(Context context, List<GameModel> games) {
        this.context = context;
        this.games = games;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameListAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivGameLogo;
        TextView tvGameTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGameLogo = itemView.findViewById(R.id.iv_list_item_game_logo);
            tvGameTitle = itemView.findViewById(R.id.tv_list_item_game_title);
        }

        public void bind(int position) {
            Glide.with(context)
                    .load(games.get(position).getLogo())
                    .into(ivGameLogo);
            tvGameTitle.setText(games.get(position).getTitle());
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, AddUpdateOrderActivity.class);
                intent.putExtra("EXTRA_GAME", games.get(position));
                context.startActivity(intent);
            });
        }
    }
}
