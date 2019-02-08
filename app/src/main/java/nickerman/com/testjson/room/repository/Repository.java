package nickerman.com.testjson.room.repository;

import java.util.List;

import io.reactivex.Flowable;
import nickerman.com.testjson.room.entity.Country;

public class Repository implements ICountryDataSource {


    private ICountryDataSource mLocalDaoSource;
    private static Repository mInstance;

    private Repository(ICountryDataSource mLocalDaoSource) {
        this.mLocalDaoSource = mLocalDaoSource;
    }

    public static Repository getInstance(ICountryDataSource mLocalDaoSource) {
        if (mInstance == null) {
            mInstance = new Repository(mLocalDaoSource);
        }
        return mInstance;
    }

    @Override
    public Flowable<Country> getCountryById(int id) {
        return this.mLocalDaoSource.getCountryById(id);
    }

    @Override
    public Flowable<List<Country>> getAllCountry() {
        return this.mLocalDaoSource.getAllCountry();
    }

    @Override
    public void insertCountry(Country... countries) {
        this.mLocalDaoSource.insertCountry(countries);
    }

    @Override
    public void updateCountry(Country... countries) {
        this.mLocalDaoSource.updateCountry(countries);
    }

    @Override
    public void deleteCountry(Country country) {
        this.mLocalDaoSource.deleteCountry(country);
    }

    @Override
    public void deleteAllCountry() {
        this.mLocalDaoSource.deleteAllCountry();
    }
}
