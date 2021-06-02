package id.as.upnyk.deokstore.profile;

import java.util.List;

import id.as.upnyk.deokstore.db.TopUp;

public interface ProfileView {
    void onSuccessRetrieve(List<TopUp> topUpList);
}
