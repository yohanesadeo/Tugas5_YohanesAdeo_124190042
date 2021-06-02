package id.as.upnyk.deokstore.cart;

import android.os.AsyncTask;

import java.util.List;

import id.as.upnyk.deokstore.db.AppDatabase;
import id.as.upnyk.deokstore.db.TopUp;

public class CartPresenter {

    private CartView cartView;

    public CartPresenter(CartView cartView) {
        this.cartView = cartView;
    }

    public void getUnpaidOrder(AppDatabase appDatabase) {
        List<TopUp> topUpList;
        topUpList = appDatabase.topUpDAO().getUnpaidTopUpList();
        cartView.onSuccessRetrieve(topUpList);
    }

    public void paidAllOrder(AppDatabase appDatabase, List<TopUp> unpaidOrder) {
        new PaidAllOrderAsync(appDatabase, unpaidOrder).execute();
    }

    private class PaidAllOrderAsync extends AsyncTask<Void, Void, Void> {
        private AppDatabase appDatabase;
        private List<TopUp> topUpList;

        public PaidAllOrderAsync(AppDatabase appDatabase, List<TopUp> topUpList) {
            this.appDatabase = appDatabase;
            this.topUpList = topUpList;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (TopUp topUp : topUpList) {
                topUp.setHasPaid(true);
                appDatabase.topUpDAO().updateTopUp(topUp);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            cartView.onSuccessPurchase();
        }
    }
}
