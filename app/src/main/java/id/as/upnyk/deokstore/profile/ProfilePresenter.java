package id.as.upnyk.deokstore.profile;

import java.util.List;

import id.as.upnyk.deokstore.db.AppDatabase;
import id.as.upnyk.deokstore.db.TopUp;

public class ProfilePresenter {

    private ProfileView profileView;

    public ProfilePresenter(ProfileView profileView) {
        this.profileView = profileView;
    }

    public void getPaidOrder(AppDatabase appDatabase) {
        List<TopUp> topUpList;
        topUpList = appDatabase.topUpDAO().getPaidTopUpList();
        profileView.onSuccessRetrieve(topUpList);
    }
}
