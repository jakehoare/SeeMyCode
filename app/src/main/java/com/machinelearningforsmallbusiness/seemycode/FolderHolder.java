package com.machinelearningforsmallbusiness.seemycode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

// Holder stores the data displayed by the adapter
class FolderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ImageView mFileOrFolderIcon;
    private final TextView mFileOrFolderName;

    private HashMap<String, String> folder;
    private Context context;

    FolderHolder(Context context, View itemView) {

        super(itemView);

        this.context = context;

        this.mFileOrFolderIcon = itemView.findViewById(R.id.iv_file_or_folder_icon);
        this.mFileOrFolderName = itemView.findViewById(R.id.tv_file_or_folder_name);

        itemView.setOnClickListener(this);
    }

    void bindFileOrFolder(HashMap<String, String> folder) {

        this.folder = folder;
        this.mFileOrFolderName.setText(folder.get("name"));
        this.mFileOrFolderIcon.setImageResource(Integer.parseInt(folder.get("icon")));
    }

    @Override
    public void onClick(View v) {

        if (folder != null) {
            String type = folder.get("type");

            if (type.equals("file")) {
                // When clicked on a file, start a new activity to display the file contents
                Class destinationActivity = DisplayCodeActivity.class;
                Intent startChildActivityIntent = new Intent(context, destinationActivity);
                startChildActivityIntent.putExtra(Intent.EXTRA_TEXT, folder.get("path"));
                context.startActivity(startChildActivityIntent);

            } else {

                // When clicke on a folder, update the path, get new folder contents and update view
                String path = folder.get("path");
                View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
                TextView mCurrentPath = rootView.findViewById(R.id.tv_current_path);
                mCurrentPath.setText(path);

                RecyclerView mFolderContentsView = rootView.findViewById(R.id.lv_path_contents);
                FolderManager.showFolderContents(path, context, mFolderContentsView);
            }
        }
    }
}