package bd.edu.seu.cookify.model;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import bd.edu.seu.cookify.R;

public class SubstituteAdapter extends RecyclerView.Adapter<SubstituteAdapter.VH> {

    private final Context context;
    private final List<SubstituteItem> allItems = new ArrayList<>();
    private final List<SubstituteItem> visibleItems = new ArrayList<>();

    public SubstituteAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<SubstituteItem> items) {
        allItems.clear();
        if (items != null) allItems.addAll(items);
        filterByName("");
    }

    public void filterByName(String query) {
        String q = query == null ? "" : query.trim().toLowerCase(Locale.getDefault());
        visibleItems.clear();
        if (TextUtils.isEmpty(q)) {
            visibleItems.addAll(allItems);
        } else {
            for (SubstituteItem it : allItems) {
                String name = it.getName() == null ? "" : it.getName();
                if (name.toLowerCase(Locale.getDefault()).contains(q)) {
                    visibleItems.add(it);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_substitute, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        SubstituteItem item = visibleItems.get(position);
        h.textName.setText(item.getName() == null ? "" : item.getName());
        h.textAmount.setText(item.getAmount() == null ? "" : item.getAmount());

        String url = item.getImageUrl();
        if (TextUtils.isEmpty(url)) {
            h.imageThumb.setImageResource(R.drawable.ic_launcher_foreground);
        } else {
            // Rounded corners via Picasso transformation
            Transformation r = new com.squareup.picasso.Transformation() {
                @Override public android.graphics.Bitmap transform(android.graphics.Bitmap source) {
                    float r = context.getResources().getDisplayMetrics().density * 12f;
                    android.graphics.Bitmap output = android.graphics.Bitmap.createBitmap(source.getWidth(), source.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
                    android.graphics.Canvas canvas = new android.graphics.Canvas(output);
                    final android.graphics.Paint paint = new android.graphics.Paint();
                    final android.graphics.Rect rect = new android.graphics.Rect(0, 0, source.getWidth(), source.getHeight());
                    final android.graphics.RectF rectF = new android.graphics.RectF(rect);
                    paint.setAntiAlias(true);
                    canvas.drawRoundRect(rectF, r, r, paint);
                    paint.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
                    canvas.drawBitmap(source, rect, rect, paint);
                    source.recycle();
                    return output;
                }
                @Override public String key() { return "rounded-12"; }
            };

            Picasso.get()
                    .load(url)
                    .resize(256, 256)
                    .centerCrop()
                    .transform(r)
                    .into(h.imageThumb);
        }
    }

    @Override
    public int getItemCount() { return visibleItems.size(); }

    static class VH extends RecyclerView.ViewHolder {
        final TextView textName;
        final TextView textAmount;
        final ImageView imageThumb;
        VH(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textAmount = itemView.findViewById(R.id.textAmount);
            imageThumb = itemView.findViewById(R.id.imageThumb);
        }
    }
}


