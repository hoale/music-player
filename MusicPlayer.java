//import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
 

import com.fpt.aptech.sound.SoundPlayer;

public class MusicPlayer {
    
    private Scanner sc = new Scanner(System.in);
    private SoundPlayer soundPlayer = new SoundPlayer();
    private Song currentSong;
    private PlayList playList;
    private String status = "stop";
    
    public static void main(String[] args) {
        //List<Song> songs = new LinkedList<Song>();
        
        Song song1 = createNewSong("Anh Muon Em Song Sao","Chi Dan", "Bao Anh", "songs/Anh Muon Em Song Sao - Bao Anh.mp3");
        Song song2 = createNewSong("Grenade", "Bruno Mars", "Bruno Mars", "songs/Grenade.mp3"); 
        Song song3 = createNewSong("It Will Rain", "Bruno Mars", "Bruno Mars", "songs/It Will Rain - Bruno Mars.mp3");
        Song song4 = createNewSong("Just Give Me A Reason", "Unknown", "Pink,Nate Ruess", "songs/Just Give Me A Reason.mp3");       
        Song song5 = createNewSong("Loi Noi Doi Chan That", "Justatee ft. Kimmese", "Justatee ft. Kimmese", "songs/Loi Noi Doi Chan That.mp3");
        Song song6 = createNewSong("Love The Way You Lie","Skylar Grey", "Skylar Grey Ft. Eminem", "songs/Love The Way You Lie - Skylar Grey Ft. Eminem.mp3");
        
       /*
        songs.add(new Song("Anh Muon Em Song Sao","Chi Dan", "Bao Anh", "songs/Anh Muon Em Song Sao - Bao Anh.mp3"));
        songs.add(new Song("Grenade", "Bruno Mars", "Bruno Mars", "songs/Grenade.mp3")); 
        songs.add(new Song("It Will Rain", "Bruno Mars", "Bruno Mars", "songs/It Will Rain - Bruno Mars.mp3"));
        songs.add(new Song("Just Give Me A Reason", "Unknown", "Pink,Nate Ruess", "songs/Just Give Me A Reason.mp3"));       
        songs.add(new Song("Loi Noi Doi Chan That", "Justatee ft. Kimmese", "Justatee ft. Kimmese", "songs/Loi Noi Doi Chan That.mp3"));
        songs.add(new Song("Love The Way You Lie","Skylar Grey", "Skylar Grey Ft. Eminem", "songs/Love The Way You Lie - Skylar Grey Ft. Eminem.mp3"));
        
        java.util.Collections.sort(songs);
        
        for (Song p : songs) {
            System.out.println(p);
        }*/
        
        
        PlayList playList = new PlayList();
        playList.addSong(song1);
        playList.addSong(song2);
        playList.addSong(song3);
        playList.addSong(song4);
        playList.addSong(song5);
        playList.addSong(song6);
        
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setPlayList(playList);
       
        
    }
    
    
    public void setPlayList(PlayList playList) {
        this.playList = playList;
        this.start();
    }
    
    private void start() {
        while (true) {
            List<Song> songList = playList.getSongList();
            String choice = showPlayList();
            if (isInteger(choice)) {
                int songNumber = Integer.parseInt(choice);
                if (1 <= songNumber && songNumber <= songList.size()){
                    currentSong = songList.get(songNumber - 1);
                    this.play();
                }
            } else {
                switch (choice) {
                    case "p":
                        if (status.equals("play")) {
                            pause();
                        } else if (status.equals("pause")) {
                            resume();
                        } else {
                           play(); 
                        }
                        break;
                    case ".":
                        stop();
                        break;
                        
                }
            }
            
            
            
            
        }
    }
    
    private void play() {
        if (this.currentSong != null) {
            soundPlayer.play(currentSong.getFilePath());
            this.status = "play";
        }
        
    }
    
    private void pause() {
        soundPlayer.pause();
        this.status = "pause";
    }
    
    private void resume() {
        soundPlayer.resume();
        this.status = "play";
    }
    
    private void stop() {
        soundPlayer.stop();
        this.status = "stop";
    }
    
    private String showPlayList() {
        System.out.println("---Commands---\n");
        System.out.println("p - Play (Pause - Resume)\n");
        System.out.println(". - Stop\n");
        System.out.println("---Song List---\n");
        List<Song> songList = playList.getSongList();
        
        Song indexSong;
        int songNumber;
        String current = "";
        for(int i = 0; i < songList.size(); i++) {
            indexSong = songList.get(i);
            songNumber = i + 1;
            if (this.currentSong != null && this.currentSong.getName() != null &&
                this.currentSong.getName().equals(indexSong.getName())) {
                current = "*";     
            }
            System.out.println(songNumber + " - " + indexSong.getName() + " - " + indexSong.getSinger() + " " + current);
            current = "";
            
        }
        return readString();
        
    }
    
    private String readString() {
        while (true) {
           return sc.nextLine();
        }
    }
    
    private boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch (NumberFormatException e) { 
            return false; 
        }
        // only got here if we didn't return false
        return true;
    }
    
    private static Song createNewSong(String name, String composer, String singer, String filePath) {
        Song song = new Song();
        song.setName(name);
        song.setComposer(composer);
        song.setSinger(singer);
        song.setFilePath(filePath);
        return song;
    }
    
 
}