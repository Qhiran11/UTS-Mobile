package fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qhiran.uts.MainActivity;
import com.qhiran.uts.R;

public class deskripsi extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deskripsi, container, false);

        // Ambil referensi ke MainActivity
        MainActivity mainActivity = (MainActivity) getActivity();

        // Ambil judul lagu jika MainActivity tidak null
        if (mainActivity != null) {
            String currentSongTitle = mainActivity.getCurrentSongTitle();

            // Contoh: Set nilai ke TextView di fragment
            TextView tvjudul = view.findViewById(R.id.judul_title);
            tvjudul.setText(currentSongTitle);

            TextView tvDescription = view.findViewById(R.id.deskripsi_msk);
            TextView tvLyric = view.findViewById(R.id.lyric);

            if (currentSongTitle.equals("Music01")) {
                tvDescription.setText(R.string.deskripsi_music1);
                tvLyric.setText(R.string.lirik1);
            } else if (currentSongTitle.equals("Music02")) {
                tvDescription.setText(R.string.deskripsi_music2);
                tvLyric.setText(R.string.lirik2);
            } else if (currentSongTitle.equals("Music03")) {
                tvDescription.setText(R.string.deskripsi_music3);
                tvLyric.setText(R.string.lirik3);
            } else if (currentSongTitle.equals("Music04")) {
                tvDescription.setText(R.string.deskripsi_music4);
                tvLyric.setText(R.string.lirik4);
            } else if (currentSongTitle.equals("Music05")) {
                tvDescription.setText(R.string.deskripsi_music5);
                tvLyric.setText(R.string.lirik5);
            } else if (currentSongTitle.equals("Music06")) {
                tvDescription.setText(R.string.deskripsi_music6);
                tvLyric.setText(R.string.lirik6);
            } else if (currentSongTitle.equals("Music07")) {
                tvDescription.setText(R.string.deskripsi_music7);
                tvLyric.setText(R.string.lirik7);
            } else if (currentSongTitle.equals("Music08")) {
                tvDescription.setText(R.string.deskripsi_music8);
                tvLyric.setText(R.string.lirik8);
            } else if (currentSongTitle.equals("Music09")) {
                tvDescription.setText(R.string.deskripsi_music9);
                tvLyric.setText(R.string.lirik9);
            } else if (currentSongTitle.equals("Music10")) {
                tvDescription.setText(R.string.deskripsi_music10);
                tvLyric.setText(R.string.lirik10);
            } else if (currentSongTitle.equals("Music11")) {
                tvDescription.setText(R.string.deskripsi_music11);
                tvLyric.setText(R.string.lirik11);
            } else if (currentSongTitle.equals("Music12")) {
                tvDescription.setText(R.string.deskripsi_music12);
                tvLyric.setText(R.string.lirik12);
            } else if (currentSongTitle.equals("Music13")) {
                tvDescription.setText(R.string.deskripsi_music13);
                tvLyric.setText(R.string.lirik13);
            } else if (currentSongTitle.equals("Music14")) {
                tvDescription.setText(R.string.deskripsi_music14);
                tvLyric.setText(R.string.lirik14);
            } else if (currentSongTitle.equals("Music15")) {
                tvDescription.setText(R.string.deskripsi_music15);
                tvLyric.setText(R.string.lirik15);
            } else if (currentSongTitle.equals("Music16")) {
                tvDescription.setText(R.string.deskripsi_music16);
                tvLyric.setText(R.string.lirik16);
            }

        }

        return view;
    }
}