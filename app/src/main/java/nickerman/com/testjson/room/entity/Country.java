package nickerman.com.testjson.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "country")
public class Country {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "country")
    String country;

    @ColumnInfo(name = "cities")
    String cities;

    @Ignore
    public Country(String country, String cities) {
        this.country = country;
        this.cities = cities;
    }

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }
}
