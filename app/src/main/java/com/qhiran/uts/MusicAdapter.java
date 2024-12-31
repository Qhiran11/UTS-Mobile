package com.qhiran.uts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private List<MusicItem> musicList;

    public MusicAdapter(List<MusicItem> musicList) {
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Menggunakan layout daftar_lagu.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daftar_lagu, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        MusicItem item = musicList.get(position);

        // Mengisi data ke ViewHolder
        holder.title.setText(item.getTitle());
        holder.artist.setText(item.getArtist().getName());
        holder.image.setImageResource(R.drawable.note); // Placeholder image
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public static class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView title, artist;
        ImageView image;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);

            // Menghubungkan dengan elemen di XML
            title = itemView.findViewById(R.id.judul_lagu);
            artist = itemView.findViewById(R.id.artist_name); // Tambahkan di XML jika belum ada
            image = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
