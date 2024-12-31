package com.qhiran.uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MusicAdapter adapter;
    private ImageView temukanMusikImage;
    private ImageButton btnPlay;
    private ArrayList<MusicItem> musicList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MusicAdapter(musicList);
        recyclerView.setAdapter(adapter);

        // SearchView Logic
        temukanMusikImage = findViewById(R.id.temukanMusikmuText);
        btnPlay = findViewById(R.id.play_button);
        SearchView searchView = findViewById(R.id.search_input);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                temukanMusikImage.setVisibility(View.GONE);
                searchMusic(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    temukanMusikImage.setVisibility(View.VISIBLE);
                } else {
                    temukanMusikImage.setVisibility(View.GONE);
                }
                return false;
            }
        });
        btnPlay.setOnClickListener(v -> {
            // Intent untuk berpindah ke MainActivity
            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }



    private void searchMusic(String query) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.searchMusic(query).enqueue(new Callback<MusicResponse>() {
            @Override
            public void onResponse(Call<MusicResponse> call, Response<MusicResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    musicList.clear();
                    musicList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SearchActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MusicResponse> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

