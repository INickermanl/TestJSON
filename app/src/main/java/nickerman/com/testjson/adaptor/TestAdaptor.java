package nickerman.com.testjson.adaptor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nickerman.com.testjson.R;
import nickerman.com.testjson.room.entity.Country;

public class TestAdaptor extends RecyclerView.Adapter<TestAdaptor.ViewHolder> {

    private List<Country> list;

    public TestAdaptor(List<Country> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adaptor, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if (i == list.size() - 1){
            viewHolder.test.setText(list.get(i).getCountry() + i);
        }else {
            viewHolder.test.setText(list.get(i).getCountry());
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView test;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            test = itemView.findViewById(R.id.test_text_view);
        }
    }
}
