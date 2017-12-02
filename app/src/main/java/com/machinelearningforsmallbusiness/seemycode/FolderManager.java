package com.machinelearningforsmallbusiness.seemycode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Build a list of files and folders in this folder
class FolderManager {

    static void showFolderContents(String path, Context context, RecyclerView folderContentsView) {

        // Get an array of the names of folders and files
        String[] folderContentsArray = null;
        try {
            folderContentsArray = context.getAssets().list(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // For each folder or file, create a mapping to store name, path, type and icon
        ArrayList<HashMap<String, String>> folderContentsList = new ArrayList<>();
        for (String item : folderContentsArray) {
            HashMap<String, String> folder = new HashMap<>();
            folder.put("name", item);
            folder.put("path", path + "/" + item);
            if (item.indexOf('.') == -1) {
                folder.put("type", "folder");
                folder.put("icon", Integer.toString(R.mipmap.ic_folder));
            } else {
                folder.put("type", "file");
                folder.put("icon", Integer.toString(R.mipmap.ic_file));
            }
            folderContentsList.add(folder);
        }

        FolderAdapter newAdapter = new FolderAdapter(context, R.layout.list_item, folderContentsList);
        folderContentsView.swapAdapter(newAdapter, false);
    }

}
