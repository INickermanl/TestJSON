package nickerman.com.testjson.retrofit;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMyAPI {

    @GET("/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json")
    Observable<ArrayMap<String, ArrayList<String>>> getCountries();

}
