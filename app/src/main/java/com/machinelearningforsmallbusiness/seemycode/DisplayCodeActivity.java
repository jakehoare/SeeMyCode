package com.machinelearningforsmallbusiness.seemycode;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.pddstudio.highlightjs.HighlightJsView;
import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// Display the contents of a file
public class DisplayCodeActivity extends AppCompatActivity {

    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycode);

        // Get the path of the file from the parent intent, and display it n TextView
        TextView mDisplayPath = findViewById(R.id.tv_file_path);
        Intent intentThatStartedThisActivity = getIntent();
        filePath = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
        mDisplayPath.setText(filePath);

        // Get the file extension to determine how to display it
        String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1,
                filePath.length());

        if (fileExtension.equals("png")) {
            // Display images
            ImageView mDisplayImage = findViewById(R.id.iv_image_file);
            try
            {
                // Get input stream
                InputStream ims = getAssets().open(filePath);
                // Load image as Drawable and set to DENSITY_HIGH
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inDensity = DisplayMetrics.DENSITY_HIGH;
                Drawable d = Drawable.createFromResourceStream(getResources(), null, ims, filePath, opts);

                // Set image in ImageView
                mDisplayImage.setImageDrawable(d);
                ims.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return;
        }

        HighlightJsView mDisplayCode = findViewById(R.id.hjsv_code);
        StringBuilder codeFile = new StringBuilder();
        // Build a string with the text of the file
        try {
            InputStream is = getAssets().open(filePath);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
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

        // Syntax highlighting the code
        // see https://github.com/PDDStudio/highlightjs-android
        mDisplayCode.setTheme(Theme.GITHUB);
        com.pddstudio.highlightjs.models.Language language = Language.JAVA;
        if (fileExtension.equals("xml"))
            language = Language.XML;
        mDisplayCode.setHighlightLanguage(language);
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
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, filePath);
        startActivity(mailIntent);
    }

    // Add the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_file, menu);
        return true;
    }

}
