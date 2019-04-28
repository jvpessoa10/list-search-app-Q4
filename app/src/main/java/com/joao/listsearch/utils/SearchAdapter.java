package com.joao.listsearch.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.joao.listsearch.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {

    private LayoutInflater inflater;
    Context context;
    ArrayList<String> items;
    ArrayList<String> fullList;

    public SearchAdapter(Context context,ArrayList<String> items) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.items  = items;
        this.fullList = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item, viewGroup, false);
        SearchViewHolder holder = new SearchViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder searchViewHolder, int i) {
        searchViewHolder.text.setText(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                Log.d("QUERY",constraint.toString());
                ArrayList<String> result = new ArrayList<>();

                if(constraint == null ||  constraint.length() == 0){
                    Log.d("Zerou","true");
                    Log.d("list",fullList.toString());

                    result.addAll(fullList);
                }else{
                    String query = constraint.toString();

                    for (String item: fullList) {

                        if(FilterUtils.checkTypos(item, query) || FilterUtils.checkJumbled(item,query)) {
                            Log.d("Achou","true");
                            result.add(item);
                        }
                    }

                }

                FilterResults results = new FilterResults();

                results.values = result;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                items.clear();
                items.addAll((ArrayList<String>)results.values);

                notifyDataSetChanged();
            }
        };
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public SearchViewHolder(View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.item_text);
        }
    }
}
