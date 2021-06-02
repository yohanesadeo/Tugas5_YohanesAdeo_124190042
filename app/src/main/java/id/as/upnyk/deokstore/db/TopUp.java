package id.as.upnyk.deokstore.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "top_up")
public class TopUp implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "game_name")
    private String gameName;

    @ColumnInfo(name = "amount")
    private int amount;

    @ColumnInfo(name = "has_paid")
    private boolean hasPaid;

    public TopUp() {
    }

    protected TopUp(Parcel in) {
        id = in.readInt();
        gameName = in.readString();
        amount = in.readInt();
        hasPaid = in.readByte() != 0;
    }

    public static final Creator<TopUp> CREATOR = new Creator<TopUp>() {
        @Override
        public TopUp createFromParcel(Parcel in) {
            return new TopUp(in);
        }

        @Override
        public TopUp[] newArray(int size) {
            return new TopUp[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(gameName);
        dest.writeInt(amount);
        dest.writeByte((byte) (hasPaid ? 1 : 0));
    }
}
