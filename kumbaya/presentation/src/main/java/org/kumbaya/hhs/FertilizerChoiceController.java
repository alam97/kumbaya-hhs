package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;

import java.io.IOException;


public class FertilizerChoiceController extends MainViewsSwitcher{

    @FXML
    private ImageView video;
    @FXML
    private ImageView back;
    @FXML
    private ImageView home;
    @FXML
    private ImageView list;
    @FXML
    private  ImageView speaker_video;
    @FXML
    private ImageView speaker_list;
    @FXML
    private Text crop;

    private boolean SP;
    private boolean MZ;
    private boolean SB;

    public FertilizerChoiceController(boolean SP, boolean MZ, boolean SB) {
        this.SP = SP;
        this.MZ = MZ;
        this.SB = SB;
    }

    public void initialize() {

        if(SP){
            crop.setText("Sweet Potato");
            speaker_video.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Sweet potato_fertilizers_Video.mp3").toString());
                mp3.play();});
            speaker_video.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Sweet potato_fertilizers_Video.mp3").toString());
                mp3.play();});
            speaker_list.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Sweet potato_fertilizers_list_of_fertilizers.mp3").toString());
                mp3.play();});
            speaker_list.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Sweet potato_fertilizers_list_of_fertilizers.mp3").toString());
                mp3.play();});
        }

        if(MZ){
            crop.setText("Maize");
            speaker_video.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Maize_fertilizers_Video.mp3").toString());
                mp3.play();});
            speaker_video.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Maize_fertilizers_Video.mp3").toString());
                mp3.play();});
            speaker_list.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Maize_fertilizers_list_of_ferilizers.mp3").toString());
                mp3.play();});
            speaker_list.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Maize_fertilizers_list_of_ferilizers.mp3").toString());
                mp3.play();});

        }

        if(SB){
            crop.setText("Soybean");
            speaker_video.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Soybean_ferilizers_Video.mp3").toString());
                mp3.play();});
            speaker_video.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Soybean_ferilizers_Video.mp3").toString());
                mp3.play();});
            speaker_list.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Soybean_fertilizers_list_of_fertilizers.mp3").toString());
                mp3.play();});
            speaker_list.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Soybean_fertilizers_list_of_fertilizers.mp3").toString());
                mp3.play();});
        }

        back.setOnMouseClicked((event) -> {    // lambda expression
            try {
                App.setRoot("fertilizerPaneScene");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        back.setOnTouchPressed((event) -> {    // lambda expression
            try {
                App.setRoot("fertilizerPaneScene");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        home.setOnMouseClicked((event) -> {    // lambda expression
            try {
                App.setRoot("mainScene");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        home.setOnTouchPressed((event) -> {    // lambda expression
            try {
                App.setRoot("mainScene");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });

    }
}
