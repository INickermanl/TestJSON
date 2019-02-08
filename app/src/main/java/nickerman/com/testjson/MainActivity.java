package nickerman.com.testjson;

import android.os.Bundle;
import android.view.View;

import nickerman.com.testjson.base.BaseActivity;
import nickerman.com.testjson.room.CountryRoomDatabase;
import nickerman.com.testjson.screens.main.MainContract;
import nickerman.com.testjson.screens.main.MainPresenter;
import nickerman.com.testjson.screens.main.MainView;

public class MainActivity extends BaseActivity {

    private View root;
    private MainContract.View view;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CountryRoomDatabase countryRoomDatabase = CountryRoomDatabase.getInstance(this);
        root = findViewById(R.id.root);
        view = new MainView(root);
        presenter = new MainPresenter(countryRoomDatabase);

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start(view);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }
}
