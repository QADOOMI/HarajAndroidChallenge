package text.ocr.mostafa.harajchallenge.main.views;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Locale;

import text.ocr.mostafa.harajchallenge.databinding.HarajItemLayoutBinding;
import text.ocr.mostafa.harajchallenge.details.DetailsActivity;
import text.ocr.mostafa.harajchallenge.model.HarajItem;

public class HarajItemsAdapter extends RecyclerView.Adapter<HarajItemsAdapter.HarajHolder> {

    private AppCompatActivity activity;
    private final List<HarajItem> harajItemList;

    public HarajItemsAdapter(AppCompatActivity activity, List<HarajItem> harajItemList) {
        this.activity = activity;
        this.harajItemList = harajItemList;
    }

    public void addHarajItems(@NonNull List<HarajItem> harajItems) {
        int oldSize = harajItemList.size();

        harajItemList.clear();
        notifyItemRangeRemoved(0, oldSize);

        harajItemList.addAll(harajItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HarajHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HarajItemLayoutBinding binding = HarajItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );

        return new HarajHolder(binding, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull HarajItemsAdapter.HarajHolder holder, int position) {
        holder.bindHarajItem(harajItemList.get(holder.getBindingAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return harajItemList.size();
    }

    public static class HarajHolder extends RecyclerView.ViewHolder {
        private final HarajItemLayoutBinding binding;
        private final AppCompatImageView image;
        private final MaterialButton comments;
        private final AppCompatActivity activity;

        public HarajHolder(@NonNull HarajItemLayoutBinding binding, AppCompatActivity activity) {
            super(binding.harajImage.getRootView());

            this.activity = activity;
            image = binding.harajImage;
            comments = binding.itemCommentsCount;

            this.binding = binding;
        }

        public void bindHarajItem(@NonNull HarajItem harajItem) {
            binding.setHarajItem(harajItem);
            binding.harajItemCard.setOnClickListener(v -> {
                Intent intent = new Intent(activity, DetailsActivity.class);
                intent.putExtra(DetailsActivity.HARAJ_ITEM, harajItem);
                activity.startActivity(intent);
            });

            if (harajItem.getCommentCount() == 0) {
                comments.setVisibility(View.INVISIBLE);

            } else {
                comments.setText(String.format(Locale.US, "(%d)", harajItem.getCommentCount()));
                comments.setVisibility(View.VISIBLE);
            }

            Glide.with(image.getContext())
                    .asBitmap()
                    .load(harajItem.getThumbURL())
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .into(image);
        }
    }
}
