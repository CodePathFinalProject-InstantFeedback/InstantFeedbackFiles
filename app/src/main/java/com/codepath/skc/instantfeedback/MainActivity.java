package com.codepath.skc.instantfeedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.codepath.skc.instantfeedback.Fragments.ProfileFragment;
import com.codepath.skc.instantfeedback.Fragments.StreamFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MainActivity";
    public static final int REQUEST_CODE=42;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        //queryPosts();
        final FragmentManager fragmentManager = getSupportFragmentManager();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_stream:
                        Toast.makeText(MainActivity.this, "In Home", Toast.LENGTH_SHORT).show();
                        fragment=new StreamFragment();
                        break;
                    case R.id.add:
                        Toast.makeText(MainActivity.this, "In Compose", Toast.LENGTH_SHORT).show();
                        fragment=new ProfileFragment();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "In Profile", Toast.LENGTH_SHORT).show();
                        fragment=new StreamFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.action_stream);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        if (item.getItemId()==R.id.add) {
            //Toast.makeText(this, "compose!", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,AddActivity.class);
            startActivityForResult(intent,REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

