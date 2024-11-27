package fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qhiran.uts.MainActivity;
import com.qhiran.uts.R;
import fragment.rv_daftarmusik.adapter;
import fragment.rv_daftarmusik.lagu;

import java.util.ArrayList;

public class home extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<lagu> songList = new ArrayList<>();

    public home() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_daftarlagu);
        recyclerView.setHasFixedSize(true);

        songList.add(new lagu()); // Menambahkan instance lagu
        showRecyclerList();

        return view;
    }

    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter listlaguAdapter = new adapter(songList);
        recyclerView.setAdapter(listlaguAdapter);
        listlaguAdapter.setOnItemClickCallback(this::pilih);
    }

    private void pilih(String songTitle) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).playSelectedSong(songTitle);
        }
    }


}
