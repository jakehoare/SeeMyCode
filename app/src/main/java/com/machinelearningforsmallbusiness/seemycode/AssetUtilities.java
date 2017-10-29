package com.machinelearningforsmallbusiness.seemycode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class AssetUtilities {

    static void showFolderContents(String path, Context context, RecyclerView folderContentsView) {

        String[] folderContentsArray = null;
        try {
            folderContentsArray = context.getAssets().list(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
