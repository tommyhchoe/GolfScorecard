package com.example.tommychoe.golfscorecard.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tommychoe.golfscorecard.R;
import com.example.tommychoe.golfscorecard.models.Hole;
import com.example.tommychoe.golfscorecard.models.Scorecard;

public class MainActivity extends ListActivity {

    public static final String PREFS_FILE = "PREFS_FILE";
    public static final String KEY_STROKECOUNT = "KEY_STROKECOUNT";
    private Scorecard mScorecard;
    private Hole[] mHoles;

    private ListAdapter mListAdapter;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mScorecard = new Scorecard(mSharedPreferences);
        mHoles = mScorecard.getHoles();

        mListAdapter = new ListAdapter(this, mHoles);
        setListAdapter(mListAdapter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        for (int i = 0; i < mHoles.length; i++){
            mEditor.putInt(KEY_STROKECOUNT + i, mHoles[i].getCount());
        }
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_clear_strokes){
            mEditor.clear();
            mEditor.apply();
            for(Hole hole : mHoles){
                hole.setCount(0);
            }
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



























