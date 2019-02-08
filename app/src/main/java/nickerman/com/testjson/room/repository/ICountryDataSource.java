package nickerman.com.testjson.room.repository;

import java.util.List;

import io.reactivex.Flowable;
import nickerman.com.testjson.room.entity.Country;

public interface ICountryDataSource {


    Flowable<Country> getCountryById(int id);

    Flowable<List<Country>> getAllCountry();

    void insertCountry(Country... countries);

    void updateCountry(Country... countries);

    void deleteCountry(Country country);

    void deleteAllCountry();
}
