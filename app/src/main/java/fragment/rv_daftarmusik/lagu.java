package fragment.rv_daftarmusik;

import android.os.Parcel;
import android.os.Parcelable;

public class lagu implements Parcelable {
    private String[] songTitles = {"Music01", "Music02", "Music03", "Music04", "Music05", "Music06", "Music07", "Music08", "Music09", "Music10", "Music11", "Music12", "Music13", "Music14", "Music15", "Music16"};
    public lagu() {}

    public lagu(String[] songTitles) {
        this.songTitles = songTitles;
    }


    protected lagu(Parcel in) {
        songTitles = in.createStringArray(); // Membaca array string
    }

    public String[] getSongTitles() {
        return songTitles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(songTitles);
    }

    public static final Parcelable.Creator<lagu> CREATOR = new Parcelable.Creator<lagu>() {
        @Override
        public lagu createFromParcel(Parcel source) {
            return new lagu(source);
        }

        @Override
        public lagu[] newArray(int size) {
            return new lagu[size];
        }
    };
}
