package com.machinelearningforsmallbusiness.seemycode;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class AssetUtilities {

    static ArrayList<HashMap<String, String>> getFolderContents(String path,
                                                                       Context context) {

        String[] folderContentsArray = null;
        try {
            folderContentsArray = context.getAssets().list(path);
        } catch (IOException e) {
            // TODO handle this exception
        }

        ArrayList<HashMap<String, String>> folderContentsList = new ArrayList<>();
        for (String item : folderContentsArray) {
            HashMap<String, String> folder = new HashMap<>();
            folder.put("name", item);
            folder.put("path", path + "/" + item);
            if (item.indexOf('.') == -1) {
                folder.put("type", "folder");
                // folder.put("icon", "1234");
            } else {
                folder.put("type", "file");
                // folder.put("icon", "1234");
            }
            folderContentsList.add(folder);
        }

        return folderContentsList;
    }

    static void showFolderContents(ArrayList<HashMap<String, String>> folderContentsList,
                                          RecyclerView folderContentsView,
                                          Context context) {

        // roll this into getFolderContents??
        FolderAdapter newAdapter = new FolderAdapter(context, R.layout.list_item, folderContentsList);
        folderContentsView.swapAdapter(newAdapter, false);

    }

}
