package nickerman.com.testjson.room.repository;

import java.util.List;

import io.reactivex.Flowable;
import nickerman.com.testjson.room.dao.CountryDAO;
import nickerman.com.testjson.room.entity.Country;

public class CountryDataSource implements ICountryDataSource {

    private CountryDAO countryDAO;
    private static CountryDataSource mCountryDataSourceINSTANCE;

    public CountryDataSource(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    public static CountryDataSource getInstance(CountryDAO countryDAO) {
        if (mCountryDataSourceINSTANCE == null) {
            mCountryDataSourceINSTANCE = new CountryDataSource(countryDAO);
        }
        return mCountryDataSourceINSTANCE;
    }

    @Override
    public Flowable<Country> getCountryById(int id) {
        return this.countryDAO.getCountryById(id);
    }

    @Override
    public Flowable<List<Country>> getAllCountry() {
        return this.countryDAO.getAllCountry();
    }

    @Override
    public void insertCountry(Country... countries) {
        this.countryDAO.insertCountry(countries);
    }

    @Override
    public void updateCountry(Country... countries) {
        this.countryDAO.updateCountry(countries);
    }

    @Override
    public void deleteCountry(Country country) {
        this.countryDAO.deleteCountry(country);
    }

    @Override
    public void deleteAllCountry() {
        this.countryDAO.deleteAllCountry();
    }
}
