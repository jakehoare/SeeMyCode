package com.machinelearningforsmallbusiness.seemycode;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

class FolderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ImageView mFileOrFolderIcon;
    private final TextView mFileOrFolderName;

    private HashMap<String, String> folder;
    private Context context;

    FolderHolder(Context context, View itemView) {

        super(itemView);

        this.context = context;

        this.mFileOrFolderIcon = (ImageView) itemView.findViewById(R.id.iv_file_or_folder_icon);
        this.mFileOrFolderName = (TextView) itemView.findViewById(R.id.tv_file_or_folder_name);

        itemView.setOnClickListener(this);
    }

    void bindFolder(HashMap<String, String> folder) {

        this.folder = folder;
        this.mFileOrFolderName.setText(folder.get("name"));
        //this.mFileOrFolderIcon.setImageResource(Integer.parseInt(folder.get("icon")));
    }

    @Override
    public void onClick(View v) {

        if (folder != null) {
            String type = folder.get("type");

            if (type == "file") {
                //Class destinationActivity = DisplayFileActivity.class;
                //Intent startChildActivityIntent = new Intent(context, destinationActivity);
                //Bundle extras = new Bundle();
                //extras.putString("EXTRA_URL", downloadUrl);
                //extras.putString("EXTRA_TITLE", problemName);
                //extras.putString("EXTRA_ICON", iconString);
                //startChildActivityIntent.putExtras(extras);
                //context.startActivity(startChildActivityIntent);
            } else {

            }
        }
    }
}