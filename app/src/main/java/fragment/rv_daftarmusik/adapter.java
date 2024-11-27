package fragment.rv_daftarmusik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qhiran.uts.R;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    private ArrayList<lagu> daftarlagu;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public adapter(ArrayList<lagu> daftarlagu) {
        this.daftarlagu = daftarlagu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_lagu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String[] songTitles = daftarlagu.get(0).getSongTitles();
        holder.textView.setText(songTitles[position]);
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickCallback != null) {
                onItemClickCallback.onItemClicked(songTitles[position]); // Kirim judul lagu
            }
        });
    }

    public interface OnItemClickCallback {
        void onItemClicked(String songTitle);
    }


    @Override
    public int getItemCount() {
        // Mengambil jumlah lagu dari daftar pertama
        return daftarlagu.get(0).getSongTitles().length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.judul_lagu);
        }
    }
}
