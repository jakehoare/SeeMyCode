package com.machinelearningforsmallbusiness.seemycode;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mFolderContentsView;
    private TextView mCurrentPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String path;
        if (savedInstanceState != null)
            path = savedInstanceState.getString("path");
        else
            path = getString(R.string.root_path);

        setContentView(R.layout.activity_main);

        mCurrentPath = findViewById(R.id.tv_current_path);
        mCurrentPath.setText(path);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mFolderContentsView = findViewById(R.id.lv_path_contents);
        mFolderContentsView.setLayoutManager(layoutManager);

        AssetUtilities.showFolderContents(path, MainActivity.this, mFolderContentsView);
    }

    // Handle menu item click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        if (itemThatWasClickedId == R.id.action_feedback) {
            sendFeedback();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Add the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        String path = mCurrentPath.getText().toString();
        if (!path.equals(getString(R.string.root_path))) {
            // Update the path to the parent folder
            path = path.substring(0, path.lastIndexOf('/'));
            mCurrentPath.setText(path);
            AssetUtilities.showFolderContents(path, MainActivity.this, mFolderContentsView);
        }
        else    // Exit app if at from root
            super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current path
        savedInstanceState.putString("path", mCurrentPath.getText().toString());

        // Call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    //Start a new activity for sending a feedback email
    private void sendFeedback() {
        Uri uri = Uri.parse(getString(R.string.mail_feedback_email));
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO, uri);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_title));
        startActivity(mailIntent);
    }

}
