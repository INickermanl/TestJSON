package nickerman.com.testjson.screens.main;

import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import nickerman.com.testjson.retrofit.IMyAPI;
import nickerman.com.testjson.retrofit.RetrofitClient;
import nickerman.com.testjson.room.CountryRoomDatabase;
import nickerman.com.testjson.room.entity.Country;
import nickerman.com.testjson.room.repository.CountryDataSource;
import nickerman.com.testjson.room.repository.Repository;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private Retrofit retrofit;
    private IMyAPI iMyAPI;
    private CompositeDisposable subscriptions;
    private List<Country> countryList = new ArrayList<>();
    private Repository mRepository;
    private ArrayMap<String, ArrayList<String>> listArrayMap;

    private Handler handler;


    public MainPresenter(CountryRoomDatabase countryRoomDatabase) {

        this.mRepository = Repository.getInstance(CountryDataSource.getInstance(countryRoomDatabase.countryDAO()));
        retrofit = RetrofitClient.getInstance();
        iMyAPI = retrofit.create(IMyAPI.class);


        handler = new Handler();
        subscriptions = new CompositeDisposable();
        initView();
    }

    @Override
    public void start(MainContract.View view) {
        this.view = view;

        initData();

    }


    private void initView() {

        iMyAPI.getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ArrayMap<String, ArrayList<String>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        subscriptions.add(d);
                    }

                    @Override
                    public void onNext(ArrayMap<String, ArrayList<String>> stringArrayListArrayMap) {


                        Disposable d = Observable.create(new ObservableOnSubscribe<Object>() {
                            @Override
                            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {

                                mRepository.getAllCountry()
                                        .subscribe(new Consumer<List<Country>>() {
                                            @Override
                                            public void accept(List<Country> countries) throws Exception {

                                                for (int i = 0; i < stringArrayListArrayMap.size(); i++) {
                                                    if (countries.size() != 0) {

                                                    } else {
                                                        ArrayList<String> listCity = stringArrayListArrayMap.valueAt(i);


                                                        Country country = new Country();


                                                        country.setCountry(stringArrayListArrayMap.keyAt(i));


                                                        if (listCity.size() >= 0 && listCity != null)
                                                            country.setCities(listCity.get(0));
                                                        else
                                                            country.setCities("Test");


                                                        //countryList.add(country);
                                                        Log.d("TAG", countryList.size() + " ");

                                                        mRepository.insertCountry(country);
                                                    }
                                                }
                                            }
                                        });
                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe();

                        subscriptions.add(d);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    private void initData() {

        view.setAdaptor(countryList);

        Disposable d = mRepository.getAllCountry()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<List<Country>>() {
                    @Override
                    public void accept(List<Country> countries) throws Exception {
                        onGetAllWordSuccess(countries);
                    }
                });

        subscriptions.add(d);
    }

    private void onGetAllWordSuccess(List<Country> translateWords) {
        countryList.clear();
        countryList.addAll(translateWords);
        view.notifyAdapter();
    }

    @Override
    public void stop() {
    }
}
