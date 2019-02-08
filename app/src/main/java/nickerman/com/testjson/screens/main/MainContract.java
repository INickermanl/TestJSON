package nickerman.com.testjson.screens.main;

import java.util.List;

import nickerman.com.testjson.room.entity.Country;

public interface MainContract {
    interface View {

        void setAdaptor(List<Country> list);
        void notifyAdapter();
    }

    interface Presenter {
        void start(View view);

        void stop();
    }
}
