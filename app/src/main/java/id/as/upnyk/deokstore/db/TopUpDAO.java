package id.as.upnyk.deokstore.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TopUpDAO {

    @Insert
    long insertData(TopUp topUp);

    @Query("SELECT * FROM top_up")
    List<TopUp> getTopUpList();

    @Query("SELECT * FROM top_up WHERE has_paid = 0")
    List<TopUp> getUnpaidTopUpList();

    @Query("SELECT * FROM top_up WHERE has_paid = 1")
    List<TopUp> getPaidTopUpList();

    @Update
    void updateTopUp(TopUp topUp);

    @Delete
    void deleteTopUp(TopUp topUp);
}
