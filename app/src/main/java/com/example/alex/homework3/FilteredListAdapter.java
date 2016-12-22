package com.example.alex.homework3;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kate on 18.12.2016.
 */
public class FilteredListAdapter extends RecyclerView.Adapter<FilteredListAdapter.ViewHolder> implements Filterable {

    private List<ApplicationInfo> applicationInfoList;
    private ArrayList<ApplicationInfo> filteredList;
    private List<ApplicationInfo> originalObjects = new ArrayList<>();
    Context context;
    private ListFilter filter = new ListFilter();
    PackageManager pm;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView txt;

        public ViewHolder(View v) {
            super(v);
            txt = (TextView) v.findViewById(R.id.app_item_txt);
            img = (ImageView) v.findViewById(R.id.app_item_image);
        }
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            applicationInfoList.clear();
            filteredList.clear();
            FilterResults filterResults = new FilterResults();

            if (constraint != null || constraint.length() != 0) {
                for (ApplicationInfo elem : originalObjects) {
                    if (elem.loadLabel(pm).toString().toLowerCase().contains(constraint.toString().toLowerCase())) {

                        filteredList.add(elem);
                    }
                }
            }

            filterResults.values = filteredList;
            filterResults.count = filteredList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            applicationInfoList.addAll(((List<ApplicationInfo>) results.values));
            notifyDataSetChanged();
        }
    }

    public FilteredListAdapter(Context context, PackageManager pm, List<ApplicationInfo> applicationInfoList){
        this.context = context;
        this.applicationInfoList = applicationInfoList;
        this.pm = pm;
        originalObjects = new ArrayList<>(applicationInfoList);
        filteredList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_auto, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(ViewHolder holder, final int pos){
        holder.txt.setText(applicationInfoList.get(pos).loadLabel(pm).toString());
        holder.img.setImageDrawable(applicationInfoList.get(pos).loadIcon(pm));
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchApp(applicationInfoList.get(pos));
            }
        });
    }
    public void launchApp(ApplicationInfo applicationInfo) {
        Intent launchIntent = pm.getLaunchIntentForPackage(applicationInfo.packageName);
        if (launchIntent != null){
            context.startActivity(launchIntent);
        }
    }
@Override
    public int getItemCount() {return applicationInfoList.size(); }


@Override
public  Filter getFilter() { return filter;}
}