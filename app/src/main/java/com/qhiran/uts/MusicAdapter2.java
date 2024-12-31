package com.qhiran.uts;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicAdapter2 extends RecyclerView.Adapter<MusicAdapter2.MusicViewHolder> {

    private final List<String> musicList;
    private final OnMusicClickListener onMusicClickListener;

    public interface OnMusicClickListener {
        void onMusicClick(String songTitle);
    }

//    public MusicAdapter2(List<String> musicList) {
//        this.musicList = musicList;
//
//    }

    public MusicAdapter2(List<String> musicList, OnMusicClickListener onMusicClickListener) {
        this.musicList = musicList;
        this.onMusicClickListener = onMusicClickListener;
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_lagu, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicViewHolder holder, int position) {
        String musicUri = musicList.get(position);
        Uri uri = Uri.parse(musicUri);

        String songTitle = uri.getLastPathSegment(); // Ambil nama file dari Uri
        holder.musicName.setText(songTitle);
        // Set listener ketika musiknya di klik
        holder.itemView.setOnClickListener(v -> onMusicClickListener.onMusicClick(musicUri));
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public static class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView musicName;

        public MusicViewHolder(View itemView) {
            super(itemView);
            musicName = itemView.findViewById(R.id.judul_lagu);
        }
    }
}
