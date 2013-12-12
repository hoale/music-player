//import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
 

import com.fpt.aptech.sound.SoundPlayer;

public class MusicPlayer {
    
    private Scanner sc = new Scanner(System.in);
    private SoundPlayer soundPlayer = new SoundPlayer();
    private Song currentSong;
    private static PlayList playList = new PlayList();
    private String status = "stop";
    private List<Song> songList = playList.getSongList();
    
    public static void main(String[] args) {
        
        Song song1 = createNewSong("Anh Muon Em Song Sao","Chi Dan", "Bao Anh", "songs/Anh Muon Em Song Sao - Bao Anh.mp3");
        Song song2 = createNewSong("Grenade", "Bruno Mars", "Bruno Mars", "songs/Grenade.mp3"); 
        Song song3 = createNewSong("It Will Rain", "Bruno Mars", "Bruno Mars", "songs/It Will Rain - Bruno Mars.mp3");
        Song song4 = createNewSong("Just Give Me A Reason", "Unknown", "Pink,Nate Ruess", "songs/Just Give Me A Reason.mp3");       
        Song song5 = createNewSong("Loi Noi Doi Chan That", "Justatee ft. Kimmese", "Justatee ft. Kimmese", "songs/Loi Noi Doi Chan That.mp3");
        Song song6 = createNewSong("Love The Way You Lie","Skylar Grey", "Skylar Grey Ft. Eminem", "songs/Love The Way You Lie - Skylar Grey Ft. Eminem.mp3");
       
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
            //List<Song> songList = playList.getSongList();
            String choice = showPlayList();
            if (isInteger(choice)) {
                int songNumber = Integer.parseInt(choice);
                if (1 <= songNumber && songNumber <= songList.size()){
                    currentSong = songList.get(songNumber - 1);
                    this.play();
                }
            } else {
                switch (choice) {
                    case "x":
                        System.exit(0);
                        break;
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
                    case "+":
                        addSongToPlayList();
                        break;
                    case "e":
                        editSong();
                        break;
                    case "-":
                        deleteSong();
                        break;
                    case "s":
                        searchSong();
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
        System.out.println("---Commands---");
        System.out.println("\tx - Exit");
        System.out.println("\tp - Play (Pause - Resume)");
        System.out.println("\t. - Stop");
        System.out.println("\t+ - Add a Song to Song List"); 
        System.out.println("\te - Edit a Song in Song List");
        System.out.println("\t- - Delete a Song from Song List");
        System.out.println("\ts - Search a Song from Song List");
        System.out.println("---Song List---");
        
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
    
    private void addSongToPlayList() {
        String songName = "";
        while (!Song.isValidName(songName)) {
            System.out.println("Enter the song's name: ");
            songName = sc.nextLine();
        }
        
        System.out.println("Enter the composer's name: ");
        String composer = sc.nextLine();
        
        System.out.println("Enter the singer's name: ");
        String singer = sc.nextLine();
        
        String filePath = "";
        while(!Song.isValidFilePath(filePath)) {
            System.out.println("Enter file's path: ");
            filePath = sc.nextLine();
        }
        
        System.out.println("Do you really want to add this song ?(Y/N): ");
        String answer = sc.nextLine();
        if(isYesAnswer(answer)) {
            Song song = new Song(songName, composer, singer, filePath);
            playList.addSong(song);
        }
          
    }
    
    private void editSong() {
        int choiceNum;
        Song searchSong;
        System.out.println("Enter song's number to edit: ");
        choiceNum = sc.nextInt();
        sc.nextLine();
        if (choiceNum > 0 && choiceNum <= songList.size()) {
            searchSong = songList.get(choiceNum - 1);
            String songName = "";
            do {  
                System.out.println("Enter new song's name:  ");
                songName = sc.nextLine();   
            }while (!Song.isValidName(songName));
        
            System.out.println("Enter new composer's name: ");
            String composer = sc.nextLine();
            
            System.out.println("Enter new singer's name: ");
            String singer = sc.nextLine();
            
            System.out.println("change song:\n + song's name: " + songName + "\n + composer: " + composer + "\n + singer: " + singer);
        
            System.out.println("Do you really want to edit this song ?(Y/N): ");
            String answer = sc.nextLine();
            if(isYesAnswer(answer)) {
                    searchSong.setName(songName);
                    searchSong.setComposer(composer);
                    searchSong.setSinger(singer);
            }
                
        } else {
           
            System.out.println("number's song is out of range!!!\n");
        }
    }
    
    private void deleteSong() {
        int choiceNum;
        Song searchSong;
        System.out.println("Enter song's number to delete: ");
        choiceNum = sc.nextInt();
        sc.nextLine();
        if (choiceNum > 0 && choiceNum <= songList.size()) {
            searchSong = songList.get(choiceNum - 1);
            System.out.println("Your choice: " + searchSong.getName() + " - " + searchSong.getSinger());
            
            System.out.println("Do you really want to delete this song ?(Y/N): ");
            String answer = sc.nextLine();
            if(isYesAnswer(answer)) {
                playList.deleteSong(searchSong);
            }
        }
    }
    
    private void searchSong() {
        String songName;
        Song searchSong;
        System.out.println("Enter song's name  to search: ");
        songName = sc.nextLine();
        
        boolean found = false;
        int i = 0;
        while (!found && i < songList.size()) {
            if (songList.get(i).getName().equals(songName)) {
                searchSong = songList.get(i);
                System.out.println("Searching Song: " + (i+1) + " - " + searchSong.getName() + " - " + searchSong.getSinger());
                found = true;
            } else {
                i++;
            }
        }
        
        if (!found) {
            System.out.println("Can't not be found the Song");
        }
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
    
    private boolean isYesAnswer(String answer) {
        if(answer.equals("y") || answer.equals("Y")) {
          return true;  
        } else {
          return false;
        }
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