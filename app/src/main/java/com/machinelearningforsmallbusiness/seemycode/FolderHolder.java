package com.machinelearningforsmallbusiness.seemycode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
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

            if (type.equals("file")) {
                // TODO launch child activity to display the code
                AssetUtilities.showFileContents(1234);

            } else {

                // TODO update the path, get new folder contents and update view
                ArrayList<HashMap<String, String>> test =
                        AssetUtilities.getFolderContents(folder.get("path"), v.getContext());
                View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
                RecyclerView rv = rootView.findViewById(R.id.lv_path_contents);

                AssetUtilities.showFolderContents(test,
                        rv,
                        context);


            }
        }
    }
}