package text.ocr.mostafa.harajchallenge.details;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import text.ocr.mostafa.harajchallenge.R;
import text.ocr.mostafa.harajchallenge.databinding.ActivityDetailsBinding;
import text.ocr.mostafa.harajchallenge.model.HarajItem;
import text.ocr.mostafa.harajchallenge.utils.DateUtils;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    private AppCompatImageView itemImage;
    private AppCompatTextView itemTitle;
    private AppCompatTextView itemDateTime;
    private MaterialButton itemLocation;
    private MaterialButton itemSellerName;
    private AppCompatTextView bodyText;

    public static final String HARAJ_ITEM = "HarajItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        HarajItem harajItem = getIntent().getParcelableExtra(HARAJ_ITEM);

        initViews();

        Glide.with(itemImage.getContext())
                .asBitmap()
                .load(harajItem.getThumbURL())
                .centerCrop()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .into(itemImage);

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm a", Locale.getDefault());

        runOnUiThread(() -> {
            itemTitle.setText(harajItem.getTitle());
            itemDateTime.setText(dateFormat.format(DateUtils.getDateFromMilli(harajItem.getDate())));
            itemLocation.setText(harajItem.getCity());
            itemSellerName.setText(harajItem.getUsername());
            bodyText.setText(harajItem.getBody());
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.share_ic){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        itemImage = binding.harajItemImage;
        itemTitle = binding.harajItemTitle;
        itemDateTime = binding.harajDatetimeTitle;
        itemLocation = binding.harajItemLocation;
        itemSellerName = binding.harajSellerName;
        bodyText = binding.harajBodyTitle;
    }

}