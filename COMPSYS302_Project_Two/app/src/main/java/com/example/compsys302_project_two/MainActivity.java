package com.example.compsys302_project_two;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupTransition();
        }
        setContentView(R.layout.activity_main);


        // Populate top picks
        ArrayList<Item> topPicksList = DataProvider.getTopPicks();

        TopPickAdaptor topPicksAdaptor = new TopPickAdaptor(
                this, R.layout.top_pick_layout, topPicksList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView topPicksList_id = (RecyclerView) findViewById(R.id.top_picks_view);
        topPicksList_id.setLayoutManager(layoutManager);
        topPicksList_id.setAdapter(topPicksAdaptor);


        // Populate categories
        List<Category> list = DataProvider.getCategories();

        CategoryAdapter categoryAdapter = new CategoryAdapter(
                this, R.layout.category_layout, list);
        ListView list_id = (ListView) findViewById(R.id.categoriesList);
        list_id.setAdapter(categoryAdapter);

//        // ITEM ADAPTER
//        ItemAdapter itemsAdapter = new ItemAdapter(this,R.layout.item_layout, list);
//
//        ListView list_id = (ListView) findViewById(R.id.category_list_view);
//        list_id.setAdapter(itemsAdapter);
//        // <---
    }

        // Placeholder for update icon
    public void updateTopPicks (View view) {

    }

    // Placeholder method for debugging details activity (access through actionbar icon)
    public boolean startDetailsActivity (MenuItem item) {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
        return true;
    }

    // Setup activity transition settings
    public void setupTransition() {
        Transition transition;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            transition = new Slide();
            transition.setDuration(2000);
            getWindow().setEnterTransition(transition);
//            getWindow().setExitTransition(transition); // Does not do anything!!!
        }
    }

    // Protection override to not return to (albeit finished) splash.
    @Override
    public void onBackPressed() {
        finish();
    }
}