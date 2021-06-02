package id.as.upnyk.deokstore.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.as.upnyk.deokstore.R;
import id.as.upnyk.deokstore.db.AppDatabase;
import id.as.upnyk.deokstore.db.TopUp;

public class CartFragment extends Fragment implements View.OnClickListener, CartView {

    private RecyclerView rvCart;
    private TextView tvTotalItem, tvTotalPrice;
    private Button btnPay;

    private CartListAdapter cartListAdapter;
    private CartPresenter cartPresenter;
    private AppDatabase appDatabase;
    private List<TopUp> unpaidOrder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCart = view.findViewById(R.id.rv_cart_list);
        tvTotalItem = view.findViewById(R.id.tv_cart_total_item);
        tvTotalPrice = view.findViewById(R.id.tv_cart_total_price);
        btnPay = view.findViewById(R.id.btn_cart_pay);

        cartListAdapter = new CartListAdapter(new ArrayList<>(), getActivity());
        cartPresenter = new CartPresenter(this);
        appDatabase = AppDatabase.getInstance(getActivity());

        btnPay.setOnClickListener(this);

        rvCart.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        rvCart.setHasFixedSize(true);
        rvCart.setAdapter(cartListAdapter);

        cartPresenter.getUnpaidOrder(appDatabase);
    }

    @Override
    public void onResume() {
        super.onResume();
        cartPresenter.getUnpaidOrder(appDatabase);
    }

    @Override
    public void onClick(View v) {
        if (v == btnPay)
            cartPresenter.paidAllOrder(appDatabase, unpaidOrder);
    }

    @Override
    public void onSuccessRetrieve(List<TopUp> topUpList) {
        cartListAdapter.setTopUpList(topUpList);
        cartListAdapter.notifyDataSetChanged();

        tvTotalItem.setText(String.valueOf(topUpList.size()));
        tvTotalPrice.setText(String.valueOf(getTotalPrice(topUpList)));
        unpaidOrder = topUpList;
    }

    @Override
    public void onSuccessPurchase() {
        Toast.makeText(getActivity(), "Purchase success", Toast.LENGTH_SHORT).show();
        cartPresenter.getUnpaidOrder(appDatabase);
    }

    private int getTotalPrice(List<TopUp> topUpList) {
        int result = 0;
        for (TopUp topUp : topUpList) {
            result += topUp.getAmount();
        }
        return result;
    }
}