package id.as.upnyk.deokstore.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.as.upnyk.deokstore.R;
import id.as.upnyk.deokstore.cart.CartListAdapter;
import id.as.upnyk.deokstore.db.AppDatabase;
import id.as.upnyk.deokstore.db.TopUp;

public class ProfileFragment extends Fragment implements ProfileView{

    private RecyclerView rvRecentPurchase;
    private RecentPurchaseListAdapter recentPurchaseListAdapter;
    private AppDatabase appDatabase;
    private ProfilePresenter profilePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvRecentPurchase = view.findViewById(R.id.rv_recent_purchase_list);
        recentPurchaseListAdapter = new RecentPurchaseListAdapter(new ArrayList<>(), getActivity());
        profilePresenter = new ProfilePresenter(this);
        appDatabase = AppDatabase.getInstance(getActivity());

        rvRecentPurchase.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, true));
        rvRecentPurchase.setHasFixedSize(true);
        rvRecentPurchase.setAdapter(recentPurchaseListAdapter);

        profilePresenter.getPaidOrder(appDatabase);
    }

    @Override
    public void onResume() {
        super.onResume();
        profilePresenter.getPaidOrder(appDatabase);
    }

    @Override
    public void onSuccessRetrieve(List<TopUp> topUpList) {
        recentPurchaseListAdapter.setTopUpList(topUpList);
        recentPurchaseListAdapter.notifyDataSetChanged();
    }
}