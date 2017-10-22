package com.machinelearningforsmallbusiness.seemycode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class FolderAdapter extends RecyclerView.Adapter<FolderHolder> {

    private final ArrayList<HashMap<String, String>> contents;
    private Context context;
    private int itemResource;

    public FolderAdapter(Context context, int itemResource, ArrayList<HashMap<String, String>> contents) {
        this.contents = contents;
        this.context = context;
        this.itemResource = itemResource;
    }

    @Override
    public FolderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(itemResource, parent, false);
        return new FolderHolder(context, view);
    }

    @Override
    public void onBindViewHolder(FolderHolder holder, int position) {
        HashMap<String, String> folder = contents.get(position);
        holder.bindFolder(folder);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}

