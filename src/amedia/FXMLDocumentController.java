/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amedia;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private MediaPlayer mediaplayer;
    
    
    @FXML
    private MediaView mediav;
    private String filepath;
    
    @FXML
    private Slider slider;
    
    @FXML
    private Slider seeslider;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser filechooser=new FileChooser();
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select a file(*.mp4)", "*mp4");
        filechooser.getExtensionFilters().add(filter);
        File file=filechooser.showOpenDialog(null);
        filepath=file.toURI().toString();
        
        if(filepath!=null){
            Media media=new Media(filepath);
            MediaPlayer mediaplayer=new MediaPlayer(media);
            mediav.setMediaPlayer(mediaplayer);
            
            DoubleProperty width=mediav.fitWidthProperty();
            DoubleProperty height=mediav.fitHeightProperty();
            width.bind(Bindings.selectDouble(mediav.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediav.sceneProperty(), "height"));
            
            slider.setValue(mediaplayer.getVolume() * 100);
            slider.valueProperty().addListener(new InvalidationListener(){
                @Override
                public void invalidated(Observable observable) {
                    mediaplayer.setVolume(slider.getValue()/100);
                }
               
            });
            
           /* mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                                
                  seeslider.setValue(newValue.toNanos());
                }
            });*/
            
            
            mediaplayer.play();
            
        }
    }
    
    @FXML
    private void playv(ActionEvent event){
        mediaplayer=mediav.getMediaPlayer();
        mediaplayer.play();
        mediaplayer.setRate(1);
    }
    @FXML
    private void pausev(ActionEvent event){
        mediaplayer=mediav.getMediaPlayer();
        mediaplayer.pause();
    }
    @FXML
    private void stopv(ActionEvent event){
        mediaplayer=mediav.getMediaPlayer();
        mediaplayer.stop();
    }
    @FXML
    private void fastv(ActionEvent event){
        mediaplayer=mediav.getMediaPlayer();
        mediaplayer.setRate(1.5);
    }
    @FXML
    private void vfastv(ActionEvent event){
        mediaplayer=mediav.getMediaPlayer();
        mediaplayer.setRate(2);
    }
    @FXML
    private void slowv(ActionEvent event){
        mediaplayer=mediav.getMediaPlayer();
        mediaplayer.setRate(.5);
    }
    @FXML
    private void vslowv(ActionEvent event){
        mediaplayer=mediav.getMediaPlayer();
        mediaplayer.setRate(.2);
    }
    @FXML
    private void exitv(ActionEvent event){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
