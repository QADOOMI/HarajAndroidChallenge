package text.ocr.mostafa.harajchallenge.main.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import text.ocr.mostafa.harajchallenge.R;
import text.ocr.mostafa.harajchallenge.databinding.ActivityMainBinding;
import text.ocr.mostafa.harajchallenge.main.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HarajItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMainLayout.toolbar);

        MainViewModel viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication())
                .create(MainViewModel.class);

        initViews();

        viewModel.getHaraj()
                .observe(this, adapter::addHarajItems);

        try {
            InputStream stream = getAssets().open("data.json");
            viewModel.fetchHarajItems(stream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        RecyclerView harajItemsList = binding.appBarMainLayout.harajDataList;
        harajItemsList.setHasFixedSize(false);
        harajItemsList.setNestedScrollingEnabled(false);
        harajItemsList.setLayoutManager(new LinearLayoutManager(
                harajItemsList.getContext(), RecyclerView.VERTICAL, false
        ));
        adapter = new HarajItemsAdapter(this, new ArrayList<>());
        harajItemsList.post(() -> harajItemsList.setAdapter(adapter));

        //drawerOpened = false;
        //drawerOpened = true;
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout,
                binding.appBarMainLayout.toolbar, R.string.action_settings, R.string.app_name) {

            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
                //drawerOpened = false;
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
                //drawerOpened = true;
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
    }
}