package bd.edu.seu.cookify.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bd.edu.seu.cookify.R;
import bd.edu.seu.cookify.model.RecipeAdapter;
import bd.edu.seu.cookify.model.RecipeItem;

public class BrowseActivity extends AppCompatActivity {

    private RecipeAdapter adapter; // uses RecipeItem + Glide
    private final List<RecipeItem> all = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_recipe);

        RecyclerView recycler = findViewById(R.id.recyclerRecipes);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeAdapter(this);
        recycler.setAdapter(adapter);

        Spinner spinner = findViewById(R.id.spinnerCulture);
        setupSpinner(spinner);

        EditText search = findViewById(R.id.editSearch);
        // Detect taps on the drawableEnd (search icon) and trigger filtering
        search.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                if (search.getCompoundDrawables()[DRAWABLE_RIGHT] != null) {
                    int drawableWidth = search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width();
                    if (event.getX() >= (search.getWidth() - search.getPaddingRight() - drawableWidth)) {
                        performNameFilter(search.getText().toString());
                        return true;
                    }
                }
            }
            return false;
        });
        search.setOnEditorActionListener((v, actionId, keyEvent) -> {
            performNameFilter(search.getText().toString());
            return true;
        });

        setupBottomNav();
        fetchAll();

        spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                if ("Select".equalsIgnoreCase(selected) || "All".equalsIgnoreCase(selected)) {
                    adapter.filterByCulture("All");
                } else {
                    adapter.filterByCulture(selected);
                }
            }
            @Override public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });
    }

    private void setupSpinner(Spinner spinner) {
        List<String> cultures = new ArrayList<>();
        FirebaseFirestore.getInstance()
                .collection("cultures")
                .get()
                .addOnSuccessListener(snap -> {
                    cultures.clear();
                    cultures.add("Select");
                    for (com.google.firebase.firestore.QueryDocumentSnapshot d : snap) {
                        String name = d.getString("name");
                        if (name != null && !name.trim().isEmpty()) {
                            cultures.add(name.trim());
                        }
                    }
                    cultures.add("All");

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cultures);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arrayAdapter);
                    spinner.setSelection(0);
                })
                .addOnFailureListener(e -> {
                    Log.e("BrowseActivity", "Failed to load cultures", e);
                    Toast.makeText(this, "Failed to load cultures", Toast.LENGTH_SHORT).show();
                    // Fallback minimal options so UI remains usable
                    List<String> fallback = Arrays.asList("Select", "All");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fallback);
                    spinner.setAdapter(arrayAdapter);
                    spinner.setSelection(0);
                });
    }

    private void performNameFilter(String text) {
        if (TextUtils.isEmpty(text)) {
            adapter.filterByName("");
        } else {
            adapter.filterByName(text);
        }
    }

    private void setupBottomNav() {
        ImageView navHome = findViewById(R.id.navHome);
        ImageView navBrowse = findViewById(R.id.navBrowse);
        ImageView navSubstitute = findViewById(R.id.navSubstitute);
        ImageView navPantry = findViewById(R.id.navPantry);


        navHome.setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));
        navBrowse.setOnClickListener(v -> {});
        navSubstitute.setOnClickListener(v -> startActivity(new Intent(this, SubstituteActivity.class)));
        navPantry.setOnClickListener(v -> startActivity(new Intent(this, PantryActivity.class)));
    }

    private void fetchAll() {
        FirebaseFirestore.getInstance()
                .collection("recipes")
                .get()
                .addOnSuccessListener(snap -> {
                    all.clear();
                    for (QueryDocumentSnapshot d : snap) {
                        RecipeItem item = new RecipeItem(
                                d.getId(),
                                d.getString("name"),
                                d.getString("imageUrl"),
                                d.getString("culture")
                        );
                        all.add(item);
                    }
                    adapter.setItems(all);
                })
                .addOnFailureListener(e -> {
                    android.util.Log.e("BrowseActivity", "Failed to load recipes", e);
                    android.widget.Toast.makeText(this, "Failed to load recipes", android.widget.Toast.LENGTH_SHORT).show();
                });
    }
}