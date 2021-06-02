package id.as.upnyk.deokstore.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TopUp.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TopUpDAO topUpDAO();
    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context){
        if(appDatabase==null)
            appDatabase= Room.databaseBuilder(context, AppDatabase.class, "DeokStoreDB").allowMainThreadQueries().build();
        return appDatabase;
    }
}
