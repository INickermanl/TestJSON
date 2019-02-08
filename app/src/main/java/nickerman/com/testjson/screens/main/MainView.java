package nickerman.com.testjson.screens.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import nickerman.com.testjson.R;
import nickerman.com.testjson.adaptor.TestAdaptor;
import nickerman.com.testjson.base.App;
import nickerman.com.testjson.room.entity.Country;

public class MainView implements MainContract.View {

    private View root;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;


    public MainView(View root) {
        this.root = root;
        initView();
    }

    private void initView() {
        recyclerView = root.findViewById(R.id.recycler_view);
    }

    @Override
    public void setAdaptor(List<Country> list) {
        LinearLayoutManager llm = new LinearLayoutManager(App.getInstance(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        adapter = new TestAdaptor(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }
}
