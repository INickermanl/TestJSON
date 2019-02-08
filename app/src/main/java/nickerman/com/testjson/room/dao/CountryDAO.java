package nickerman.com.testjson.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import nickerman.com.testjson.room.entity.Country;

@Dao
public interface CountryDAO {


    @Query("SELECT * FROM country WHERE id = :id")
    Flowable<Country> getCountryById(int id);

    @Query("SELECT * FROM country")
    Flowable<List<Country>> getAllCountry();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountry(Country... countries);

    @Update
    void updateCountry(Country... countries);

    @Delete
    void deleteCountry(Country country);

    @Query("DELETE FROM country")
    void deleteAllCountry();
}
