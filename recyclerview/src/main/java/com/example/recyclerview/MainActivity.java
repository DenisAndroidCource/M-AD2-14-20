package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    interface ListItemActionListener{
        void onItemClicked(int number);
    }

    private static final List<Integer> itemList;
    static {
        itemList = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            itemList.add(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.itemList);
        recyclerView.setAdapter(new ItemListAdapter(itemList, new ListItemActionListener() {
            @Override
            public void onItemClicked(int number) {
                Log.d("LISTENER", String.valueOf(number));
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    static class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {

        private List<Integer> items;
        private ListItemActionListener listItemActionListener;

        public ItemListAdapter(List<Integer> items, ListItemActionListener listItemActionListener) {
            this.items = items;
            this.listItemActionListener = listItemActionListener;
        }

        public void setItems(List<Integer> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_number_title, parent, false);
            return new ItemViewHolder(view, listItemActionListener);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items != null ? items.size() : 0;
        }

        static class ItemViewHolder extends RecyclerView.ViewHolder {

            private ImageView imageView;
            private TextView textView;
            private ListItemActionListener listItemActionListener;

            public ItemViewHolder(@NonNull View itemView, ListItemActionListener listItemActionListener) {
                super(itemView);
                imageView = itemView.findViewById(R.id.viewIcon);
                textView = itemView.findViewById(R.id.textView);
                this.listItemActionListener = listItemActionListener;
            }

            public void bind(final int number) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listItemActionListener != null){
                            listItemActionListener.onItemClicked(number);
                        }
                    }
                });
                textView.setText(String.valueOf(number));
            }
        }
    }
}
