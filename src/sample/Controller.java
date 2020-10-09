package sample;

import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.lang.management.PlatformLoggingMXBean;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    public Button prevSongButton;
    public Button nextSongButton;
    public Button playPause;
    public Button stop;
    public Button muteUnmuteButton;
    public Button volumeDown;
    public Button volumeUp;
    public Button uploadButton;
    public JFXSlider seekbar;
    public Label duration;

    public MediaView mv;
    String source;
    public Media media;
    public MediaPlayer mediaPlayer;
    Image playButtonImage,pauseButtonImage,muteButtonImage,unmuteButtonImage;
    boolean songPlaying=false;
    Thread currSong;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        source = new File("src\\Songs\\playVideo.mp4").toURI().toString();
        media =  new Media(source);
        mediaPlayer = new MediaPlayer(media);
        mv.setMediaPlayer(mediaPlayer);
        seekbar.setValue(0);
        playButtonImage=new Image(getClass().getResourceAsStream("..\\Images\\Play.jpg"));
        pauseButtonImage=new Image(getClass().getResourceAsStream("..\\Images\\Pause.jpg"));
        muteButtonImage=new Image(getClass().getResourceAsStream("..\\Images\\Mute.jpg"));
        unmuteButtonImage=new Image(getClass().getResourceAsStream("..\\Images\\NotMute.jpg"));
        duration.setText("0:00");
    }

    public void uploadSong()
    {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Open a mp3 file");
        File selectedFile=fileChooser.showOpenDialog(null);
        source=new File(String.valueOf(selectedFile.getAbsoluteFile())).toURI().toString();
        media=new Media(source);
        mediaPlayer=new MediaPlayer(media);
    }

    synchronized public void getToAnySongLocation()
    {
        if (mediaPlayer.getStatus()==MediaPlayer.Status.PAUSED)
        {
            mediaPlayer.play();
            playPause.setGraphic(new ImageView(pauseButtonImage));
        }
        Double loc=seekbar.getValue();
        mediaPlayer.seek(Duration.millis((loc*media.getDuration().toMillis())/100));
        long insec= (long) mediaPlayer.getCurrentTime().toSeconds();
        long min=insec/60;
        long sec=insec%60;
        if (sec/10==0)
        {
            duration.setText(min+":0"+sec);
        }
        else
        {
            sec=Math.round((sec*100.0)/100.0);
            duration.setText(min+":"+sec);
        }
    }

    public void goToPrevSong()
    {

    }

    public void setNextSongButton()
    {

    }

    public void muteOrUnmute()
    {
        if (mediaPlayer.isMute()==true)
        {
            muteUnmuteButton.setGraphic(new ImageView(unmuteButtonImage));
            mediaPlayer.setMute(false);
        }
        else
        {
            muteUnmuteButton.setGraphic(new ImageView(muteButtonImage));
            mediaPlayer.setMute(true);
        }
    }

    public void playOrPause()
    {
        if (mediaPlayer.getStatus()== MediaPlayer.Status.PLAYING)
        {
            playPause.setGraphic(new ImageView(playButtonImage));
            mediaPlayer.pause();
        }
        else
        {
            playPause.setGraphic(new ImageView(pauseButtonImage));
            if (songPlaying==false)
            {
                songPlaying=true;
                currSong=new Thread(this::updateCurrSongLoc);
                currSong.start();
            }
            mediaPlayer.play();
        }
    }

    public void updateCurrSongLoc()
    {
        while(songPlaying)
        {
            Platform.runLater(()->{
                seekbar.setValue((mediaPlayer.getCurrentTime().toSeconds()/ media.getDuration().toSeconds()) *100);
                long insec= (long) mediaPlayer.getCurrentTime().toSeconds();
                long min=insec/60;
                long sec=insec%60;
                if (sec/10==0)
                {
                    duration.setText(min+":0"+sec);
                }
                else
                {
                    sec=Math.round((sec*100.0)/100.0);
                    duration.setText(min+":"+sec);
                }});
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {

            }
        }
        currSong.stop();
    }

    public void stopCurrSong()
    {
        mediaPlayer.stop();
        songPlaying=false;
        playPause.setGraphic(new ImageView(playButtonImage));
        duration.setText("0:00");
        seekbar.setValue(0);
        currSong.stop();
    }

    public void increaseVolume()
    {
        if (mediaPlayer.getVolume()<1.0)
        {
            mediaPlayer.setVolume(mediaPlayer.getVolume()+0.125);
        }
    }

    public void decreaseVolume()
    {
        if (mediaPlayer.getVolume()>0)
        {
            mediaPlayer.setVolume(mediaPlayer.getVolume()-0.125);
        }
    }
}
