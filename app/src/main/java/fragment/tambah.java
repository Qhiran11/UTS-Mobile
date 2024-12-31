package fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qhiran.uts.MainActivity;
import com.qhiran.uts.MusicAdapter2;
import com.qhiran.uts.R;

import java.util.ArrayList;

public class tambah extends Fragment {

    private RecyclerView recyclerView;
    private MusicAdapter2 musicAdapter;
    private ArrayList<String> musicList = new ArrayList<>();
    private ActivityResultLauncher<Intent> folderPickerLauncher;

    public tambah() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tambah, container, false);

        // Initialize components
        folderPickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                Uri folderUri = result.getData().getData();
                loadMusicFromFolder(folderUri);
            }
        });

        ImageButton btnSelectFolder = view.findViewById(R.id.btnSelectFolder);
        recyclerView = view.findViewById(R.id.rvMusicList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        musicAdapter = new MusicAdapter2(musicList, songUri -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).playSongFromUri(Uri.parse(songUri));
            }
        });



        recyclerView.setAdapter(musicAdapter);

        btnSelectFolder.setOnClickListener(v -> openFolderPicker());

        return view;
    }

    private void openFolderPicker() {
        // Launch the folder picker
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        folderPickerLauncher.launch(intent);
    }

    private void loadMusicFromFolder(Uri folderUri) {
        // Handle the folder URI and scan for files
        ArrayList<String> mp3Files = new ArrayList<>();
        try {
            // Retrieve the documents in the folder
            String folderPath = getFolderPathFromUri(folderUri);
            if (folderPath != null) {
                Uri documentUri = DocumentsContract.buildDocumentUriUsingTree(folderUri, DocumentsContract.getTreeDocumentId(folderUri));

                // Use a content resolver to query files within the folder
                ContentResolver contentResolver = getActivity().getContentResolver();
                Uri filesUri = DocumentsContract.buildChildDocumentsUriUsingTree(documentUri, DocumentsContract.getDocumentId(documentUri));

                // Query the files
                try (Cursor cursor = contentResolver.query(filesUri, null, null, null, null)) {
                    if (cursor != null && cursor.moveToFirst()) {
                        int nameIndex = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME);
                        int mimeTypeIndex = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_MIME_TYPE);

                        do {
                            String fileName = cursor.getString(nameIndex);
                            String mimeType = cursor.getString(mimeTypeIndex);

                            if (mimeType != null && mimeType.startsWith("audio/") && fileName.endsWith(".mp3")) {
                                mp3Files.add(fileName);
                            }
                        } while (cursor.moveToNext());

                        musicList.clear();
                        musicList.addAll(mp3Files);
                        musicAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "Tidak ada file MP3 dalam folder ini", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(getActivity(), "Folder tidak valid", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Gagal memuat file", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to get the folder path from URI
    private String getFolderPathFromUri(Uri uri) {
        try {
            // Try to resolve the path of the folder URI
            return uri.getPath(); // You can't directly get a real path here
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
