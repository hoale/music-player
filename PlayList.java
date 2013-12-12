
import java.util.List;
import java.util.ArrayList;

public class PlayList {
    
    private List<Song> songList = new ArrayList<Song>();
    
    public void addSong(Song song) {
        this.songList.add(song);
    }
    
    public void deleteSong(Song song) {
        this.songList.remove(song);
    }
    
    public List<Song> getSongList() {
        return this.songList;
    }
    
}