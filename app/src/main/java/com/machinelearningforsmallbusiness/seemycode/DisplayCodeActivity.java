package com.machinelearningforsmallbusiness.seemycode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.pddstudio.highlightjs.HighlightJsView;
import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DisplayCodeActivity extends AppCompatActivity {

    private String TAG = DisplayCodeActivity.class.getSimpleName();
    private HighlightJsView mDisplayCode;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycode);

        mDisplayCode = (HighlightJsView) findViewById(R.id.hjsv_code);
        TextView mDisplayPath = (TextView) findViewById(R.id.tv_file_path);

        Intent intentThatStartedThisActivity = getIntent();

        filePath = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);

        mDisplayPath.setText(filePath);

        StringBuilder codeFile = new StringBuilder();

        File file = new File(filePath);

        try {
            InputStream is = getAssets().open(filePath);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            //BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                codeFile.append(line);
                codeFile.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        String codeFile;
        try {
            InputStream is = getAssets().open(filePath);

            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            codeFile = new String(buffer);


        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }
        */

        // https://github.com/PDDStudio/highlightjs-android
        mDisplayCode.setTheme(Theme.GITHUB);
        // TODO find the correct language
        mDisplayCode.setHighlightLanguage(Language.PYTHON);
        mDisplayCode.setSource(codeFile.toString());
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

    //Start a new activity for sending a feedback email
    private void sendFeedback() {
        Uri uri = Uri.parse(getString(R.string.mail_feedback_email));
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO, uri);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT,
                filePath);
        startActivity(mailIntent);
    }

    // Add the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_file, menu);
        return true;
    }

}
