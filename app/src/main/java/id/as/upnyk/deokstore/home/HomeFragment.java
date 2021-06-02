package id.as.upnyk.deokstore.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.as.upnyk.deokstore.DummyData;
import id.as.upnyk.deokstore.R;
import id.as.upnyk.deokstore.home.GameListAdapter;

public class HomeFragment extends Fragment {

    private RecyclerView rvGameList;
    private GameListAdapter gameListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvGameList = view.findViewById(R.id.rv_game_list);
        gameListAdapter = new GameListAdapter(getActivity(), DummyData.generateGames());

        rvGameList.setHasFixedSize(true);
        rvGameList.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        rvGameList.setAdapter(gameListAdapter);
    }
}