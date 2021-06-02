package id.as.upnyk.deokstore.addupdateorder;

import android.os.AsyncTask;

import id.as.upnyk.deokstore.db.AppDatabase;
import id.as.upnyk.deokstore.db.TopUp;

public class AddUpdatePresenter {

    private AddUpdateView addUpdateView;

    public AddUpdatePresenter(AddUpdateView addUpdateView) {
        this.addUpdateView = addUpdateView;
    }

    public void insertOrder(AppDatabase appDatabase, String game, int amount){
        TopUp newTopUp = new TopUp();
        newTopUp.setGameName(game);
        newTopUp.setAmount(amount);
        newTopUp.setHasPaid(false);
        new InsertOrderAsync(appDatabase, newTopUp).execute();
    }

    private class InsertOrderAsync extends AsyncTask<Void, Void, Long> {

        AppDatabase appDatabase;
        TopUp topUp;
        public InsertOrderAsync(AppDatabase appDatabase, TopUp topUp) {
            this.appDatabase = appDatabase;
            this.topUp = topUp;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.topUpDAO().insertData(topUp);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            addUpdateView.onSuccessAdd();
        }

    }

    public void deleteOrder(AppDatabase appDatabase, TopUp topUp) {
        new DeleteOrderAsync(appDatabase, topUp).execute();
    }

    private class DeleteOrderAsync extends AsyncTask<Void, Void, Void>{
        private AppDatabase appDatabase;
        private TopUp topUp;

        public DeleteOrderAsync(AppDatabase appDatabase, TopUp topUp) {
            this.appDatabase = appDatabase;
            this.topUp = topUp;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDatabase.topUpDAO().deleteTopUp(topUp);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            addUpdateView.onSuccessDelete();
        }
    }

    public void updateOrder(AppDatabase appDatabase, TopUp topUp) {
        new UpdateOrderAsync(appDatabase, topUp).execute();
    }

    private class UpdateOrderAsync extends AsyncTask<Void, Void, Void>{
        private AppDatabase appDatabase;
        private TopUp topUp;

        public UpdateOrderAsync(AppDatabase appDatabase, TopUp topUp) {
            this.appDatabase = appDatabase;
            this.topUp = topUp;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDatabase.topUpDAO().updateTopUp(topUp);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            addUpdateView.onSuccessUpdate();
        }
    }
}
