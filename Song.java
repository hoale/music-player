//import java.util.Comparator;

public class Song /*implements Comparable<Song>*/{
    
    private String name;
    private String composer;
    private String singer;
    private String filePath;
    
    public Song() {
    }
    
    public Song(String name, String composer, String singer, String filePath) {
        this.name = name;
        this.composer = composer;
        this.singer = singer;
        this.filePath = filePath;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getComposer() {
        return this.composer;
    }
    
    public void setComposer(String composer) {
        this.composer = composer;
    }
    
    public String getSinger() {
        return this.singer;
    }
    
    public void setSinger(String singer) {
        this.singer = singer;
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    /*
    public int compareTo(Song compareSong) {
        Song otherSong = (Song)compareSong;
        return this.name.compareTo(otherSong.getName());
        
    }
    */
    
}