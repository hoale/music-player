
//import java.util.Comparator;
import java.io.File;


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
    
    public static boolean isValidName(String name) {
        if(name == null) {
            return false;
        } else if(name.length() < 1) {
            return false;
        } 
        return true;
    }
    
    public static boolean isValidFilePath(String filePath) {
        File f = new File(filePath);
        return f.exists();  
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