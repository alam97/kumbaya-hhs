package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;

import java.io.IOException;


public class FertilizerChoiceController {

    private void goToVideo(String crop) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/fertilizerVideoScene.fxml"));
        FertilizerVideoController fertilizerVideoController = new FertilizerVideoController(crop);
        loader.setController(fertilizerVideoController);
        App.setRoot(loader);
    }

    private void goToList(String image, String list, String crop) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/fertilizerList.fxml"));
        FertilizerListController fertilizerListController = new FertilizerListController(image, list, crop);
        loader.setController(fertilizerListController);
        App.setRoot(loader);
    }

    @FXML
    private ImageView video;
    @FXML
    private ImageView back;
    @FXML
    private ImageView home;
    @FXML
    private ImageView list;
    @FXML
    private ImageView speaker_video;
    @FXML
    private ImageView speaker_list;
    @FXML
    private Text crop;

    private boolean SP;
    private boolean MZ;
    private boolean SB;

    FertilizerChoiceController(boolean SP, boolean MZ, boolean SB) {
        this.SP = SP;
        this.MZ = MZ;
        this.SB = SB;
    }


    public void initialize() {

        if (SP) {
            crop.setText("Sweet Potato");
            speaker_video.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Sweet potato_fertilizers_Video.mp3").toString());
                mp3.play();
            });
            speaker_video.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Sweet potato_fertilizers_Video.mp3").toString());
                mp3.play();
            });
            speaker_list.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Sweet potato_fertilizers_list_of_fertilizers.mp3").toString());
                mp3.play();
            });
            speaker_list.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Sweet potato_fertilizers_list_of_fertilizers.mp3").toString());
                mp3.play();
            });
            video.setOnMouseClicked((event) -> {
                try {
                    goToVideo("SP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            video.setOnTouchPressed((event) -> {
                try {
                    goToVideo("SP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            list.setOnMouseClicked((event) -> {
                try {
                    goToList("images/prices_box_sweet_potato.png", "images/list of fertilizers sweet potato.png", "SP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            list.setOnTouchPressed((event) -> {
                try {
                    goToList("images/prices_box_sweet_potato.png", "images/list of fertilizers sweet potato.png", "SP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        if (MZ) {
            crop.setText("Maize");
            speaker_video.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Maize_fertilizers_Video.mp3").toString());
                mp3.play();
            });
            speaker_video.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Maize_fertilizers_Video.mp3").toString());
                mp3.play();
            });
            speaker_list.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Maize_fertilizers_list_of_ferilizers.mp3").toString());
                mp3.play();
            });
            speaker_list.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Maize_fertilizers_list_of_ferilizers.mp3").toString());
                mp3.play();
            });
            video.setOnMouseClicked((event) -> {
                try {
                    goToVideo("MZ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            video.setOnTouchPressed((event) -> {
                try {
                    goToVideo("MZ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            list.setOnMouseClicked((event) -> {
                try {
                    goToList("images/prices_box_maize.png", "images/list of fertilizers maize.png", "MZ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            list.setOnTouchPressed((event) -> {
                try {
                    goToList("images/prices_box_maize.png", "images/list of fertilizers maize.png", "MZ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }

        if (SB) {
            crop.setText("Soybean");
            speaker_video.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Soybean_ferilizers_Video.mp3").toString());
                mp3.play();
            });
            speaker_video.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Soybean_ferilizers_Video.mp3").toString());
                mp3.play();
            });
            speaker_list.setOnMouseClicked((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Soybean_fertilizers_list_of_fertilizers.mp3").toString());
                mp3.play();
            });
            speaker_list.setOnTouchPressed((event) -> {
                AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Soybean_fertilizers_list_of_fertilizers.mp3").toString());
                mp3.play();
            });
            video.setOnMouseClicked((event) -> {
                try {
                    goToVideo("SB");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            video.setOnTouchPressed((event) -> {
                try {
                    goToVideo("SB");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            list.setOnMouseClicked((event) -> {
                try {
                    goToList("images/prices_box_soybeans.png", "images/list of fertilizers Soy beans.png", "SB");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            list.setOnTouchPressed((event) -> {
                try {
                    goToList("images/prices_box_soybeans.png", "images/list of fertilizers Soy beans.png", "SB");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        back.setOnMouseClicked((event) -> {
            try {
                App.setRoot("fertilizerPaneScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        back.setOnTouchPressed((event) -> {
            try {
                App.setRoot("fertilizerPaneScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        home.setOnMouseClicked((event) -> {
            try {
                App.switchToMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        home.setOnTouchPressed((event) -> {
            try {
                App.switchToMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}