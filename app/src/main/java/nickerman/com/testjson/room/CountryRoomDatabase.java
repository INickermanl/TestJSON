package nickerman.com.testjson.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import nickerman.com.testjson.room.dao.CountryDAO;
import nickerman.com.testjson.room.entity.Country;

@Database(entities = {Country.class}, version = 1, exportSchema = false)
public abstract class CountryRoomDatabase extends RoomDatabase {

    public abstract CountryDAO countryDAO();

    private static CountryRoomDatabase INSTANCE;


    public static CountryRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CountryRoomDatabase.class,
                    "local"
            ).fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
