package com.qhiran.uts;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fragment.deskripsi;
import fragment.home;
import fragment.rv_daftarmusik.lagu;
import fragment.tambah;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private int currentTrack = 0; // Indeks lagu saat ini
    private int[] tracks = {R.raw.music01, R.raw.music02, R.raw.music03, R.raw.music04, R.raw.music05, R.raw.music06, R.raw.music07, R.raw.music08, R.raw.music09, R.raw.music10, R.raw.music11, R.raw.music12, R.raw.music13, R.raw.music14, R.raw.music15, R.raw.music16};
    private String[] songTitles;
    private TextView tvSongTitle; // Untuk menampilkan judul lagu
    private ImageButton btnPlayPause;

    private BottomNavigationView navigasi;
    private void switchFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lagu daftarLagu = new lagu();
        songTitles = daftarLagu.getSongTitles();

        tvSongTitle = findViewById(R.id.tvSongTitle);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        ImageButton btnPrev = findViewById(R.id.btnPrev);
        ImageButton btnNext = findViewById(R.id.btnNext);

        mediaPlayer = MediaPlayer.create(this, tracks[currentTrack]);
        updateSongTitle();

        btnPlayPause.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btnPlayPause.setImageResource(R.drawable.play); // Ganti ikon jadi Play
            } else {
                mediaPlayer.start();
                btnPlayPause.setImageResource(R.drawable.pause); // Ganti ikon jadi Pause
            }
        });

        btnNext.setOnClickListener(v -> {changeTrack(1);});
        btnPrev.setOnClickListener(v -> {changeTrack(-1);});

        navigasi = findViewById(R.id.navigation);
        navigasi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                if (item.getItemId() == R.id.navigation_home) {
                    switchFragment(new home());
                    return true;
                } else if (item.getItemId() == R.id.navigation_deskripsi) {
                    switchFragment(new deskripsi());
                    return true;
                } else if (item.getItemId() == R.id.navigation_tambah) {
                    switchFragment(new tambah());
                    return true;
                } else {
                    return false;
                }
            }
        });
        if (savedInstanceState == null) {
            navigasi.setSelectedItemId(R.id.navigation_home); // Set default item (Home)
        }
    }

    public void playSelectedSong(String songTitle) {
        int selectedTrackIndex = -1;
        // Cari indeks lagu berdasarkan judul
        for (int i = 0; i < songTitles.length; i++) {
            if (songTitles[i].equals(songTitle)) {
                selectedTrackIndex = i;
                break;
            }
        }

        if (selectedTrackIndex != -1) {
            // Ganti lagu jika ditemukan
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

            currentTrack = selectedTrackIndex;
            mediaPlayer = MediaPlayer.create(this, tracks[currentTrack]);
            mediaPlayer.start();


            updateSongTitle();
            btnPlayPause.setImageResource(R.drawable.pause);
        }
    }


    private void changeTrack(int direction) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        // Update indeks lagu saat ini
        currentTrack = (currentTrack + direction + tracks.length) % tracks.length;

        // Set lagu baru
        mediaPlayer = MediaPlayer.create(this, tracks[currentTrack]);
        mediaPlayer.start();

        // Perbarui UI
        updateSongTitle();
        btnPlayPause.setImageResource(R.drawable.pause); // Pastikan ikon jadi Pause


    }

    // Metode untuk memperbarui judul lagu di UI
    private void updateSongTitle() {
        tvSongTitle.setText(songTitles[currentTrack]);
    }

    public String[] getSongTitles() {
        return songTitles;
    }

    public String getCurrentSongTitle() {
        return songTitles[currentTrack];
    }

    @Override
    protected void onDestroy() {
        // Pastikan MediaPlayer dilepas saat aplikasi ditutup
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}
