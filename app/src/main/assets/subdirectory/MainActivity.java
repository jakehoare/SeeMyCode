package com.machinelearningforsmallbusiness.seemycode;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //private RecyclerView mFolderContentsView;
    private ArrayList<HashMap<String, String>> allProblemsList = new ArrayList<>();
    //private GetProblemsFragment mGetProblemsFragment;
    private static final String TAG_FRAGMENT = "get_problems_fragement";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //mProblemListView = (RecyclerView) findViewById(R.id.lv_path_contents);
        //mProblemListView.setLayoutManager(layoutManager);

        //FragmentManager fm = getSupportFragmentManager();
        //mGetProblemsFragment = (GetProblemsFragment) fm.findFragmentByTag(TAG_FRAGMENT);

        // If the fragment is null then create it, which performs the AsyncTask and
        // returns and displays allProblemsList

        //if (mGetProblemsFragment == null) {
        //    mGetProblemsFragment = new GetProblemsFragment();
        //    fm.beginTransaction().add(mGetProblemsFragment, TAG_FRAGMENT).commit();
        //} else {
            // Retrieve problem lists from fragment and display filteredProblemList
            // https://developer.android.com/guide/topics/resources/runtime-changes.html
        //    allProblemsList = mGetProblemsFragment.getAllProblems();
        //    filteredProblemList = mGetProblemsFragment.getFilteredProblems();
        //    updateProblemList(filteredProblemList);
        //}
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

    /********* UTILITIES *********/
    private void showFileContents(int filteredIndex) {
        //String downloadUrl = filteredProblemList.get(filteredIndex).get("download_url");
        //String problemName = filteredProblemList.get(filteredIndex).get("name");
        //String iconString = filteredProblemList.get(filteredIndex).get("icon");
        Context context = MainActivity.this;
        //Class destinationActivity = DisplayCodeActivity.class;
        //Intent startChildActivityIntent = new Intent(context, destinationActivity);
        Bundle extras = new Bundle();
        //extras.putString("EXTRA_URL", downloadUrl);
        //extras.putString("EXTRA_TITLE", problemName);
        //extras.putString("EXTRA_ICON", iconString);
        //startChildActivityIntent.putExtras(extras);
        //startActivity(startChildActivityIntent);
    }

    private void updateProblemList(ArrayList<HashMap<String, String>> problemList) {
        if (problemList == null)
            return;
        //ProblemAdapter newAdapter = new ProblemAdapter(this, R.layout.list_item, problemList);
        //mProblemListView.swapAdapter(newAdapter, false);
    }

    //Start a new activity for sending a feedback email
    private void sendFeedback() {
        Uri uri = Uri.parse(getString(R.string.mail_feedback_email));
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO, uri);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_title));
        startActivity(mailIntent);
    }

}
