package id.as.upnyk.deokstore;

import android.os.Parcel;
import android.os.Parcelable;

public class GameModel implements Parcelable {
    private int logo;
    private String title;
    private String genre;

    public GameModel(int logo, String title, String genre) {
        this.logo = logo;
        this.title = title;
        this.genre = genre;
    }

    protected GameModel(Parcel in) {
        logo = in.readInt();
        title = in.readString();
        genre = in.readString();
    }

    public static final Creator<GameModel> CREATOR = new Creator<GameModel>() {
        @Override
        public GameModel createFromParcel(Parcel in) {
            return new GameModel(in);
        }

        @Override
        public GameModel[] newArray(int size) {
            return new GameModel[size];
        }
    };

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(logo);
        dest.writeString(title);
        dest.writeString(genre);
    }
}
