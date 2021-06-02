package id.as.upnyk.deokstore.cart;

import java.util.List;

import id.as.upnyk.deokstore.db.TopUp;

public interface CartView {
    void onSuccessRetrieve(List<TopUp> topUpList);
    void onSuccessPurchase();
}
