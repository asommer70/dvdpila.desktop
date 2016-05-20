package com.thehoick.dvdpila;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.ocpsoft.prettytime.PrettyTime;
import retrofit2.Call;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class DvdController {
    private int mPageIndex;
    private String mDvdId;
    private Dvd mDvd;

    @FXML
    Button backBtn;
    @FXML
    Text mTitle;
    @FXML
    Text mCreated;
    @FXML
    Text mRating;
    @FXML
    Text mAbstractTxt;
    @FXML
    ImageView mImageView;
    @FXML
    MediaView mMediaView;

    public void initialize() {
        System.out.println("mDvdId: " + mDvdId);

        backBtn.setOnAction(event -> {
            Node source = (Node)event.getTarget();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                Parent parent = (Parent)loader.load();
                MainController controller = loader.getController();
                Scene scene = new Scene(parent, 800, 700);
                Stage stage = (Stage)source.getScene().getWindow();
                stage.setScene(scene);

                // TODO:as look at using @Override for the initialize() method to correctly set the mPageIndex.
                controller.setPageIndex(mPageIndex);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void initData(String dvdId, int pageIndex) {
        mPageIndex = pageIndex;
        mDvdId = dvdId;
        System.out.println("dvdId: " + dvdId + " pageIndex: " + pageIndex);

        String[] parts = dvdId.split(":");
        mDvdId = parts[1];

        getDvd();
        mTitle.setText(mDvd.getTitle());
        mAbstractTxt.setText(mDvd.getAbstractTxt());
        PrettyTime p = new PrettyTime();
        mCreated.setText(p.format(mDvd.getCreatedAt()));
        mRating.setText((String) mDvd.getRating());

        Media media = new Media(mDvd.getFileUrl());
        MediaPlayer player = new MediaPlayer(media);
        mMediaView.setMediaPlayer(player);
        if (media.getError() == null) {
            media.setOnError(new Runnable() {
                public void run() {
                    // Handle asynchronous error in Media object.
                }
            });
            try {
                player = new MediaPlayer(media);
                if (player.getError() == null) {
                    player.setOnError(new Runnable() {
                        public void run() {
                            // Handle asynchronous error in MediaPlayer object.
                        }
                    });
                    mMediaView.setOnError(new EventHandler() {
                        @Override
                        public void handle(Event event) {

                        }
                    });
                } else {
                    // Handle synchronous error creating MediaPlayer.
                }
            } catch (Exception mediaPlayerException) {
                // Handle exception in MediaPlayer constructor.
            }
        } else {
            // Handle synchronous error creating Media.
        }

        Image image = new Image(mDvd.getImageUrl());
        mImageView.setImage(image);
        mImageView.setFitWidth(170);
        mImageView.setPreserveRatio(true);
        mImageView.setSmooth(true);
        mImageView.setCache(true);
        mImageView.setId("image:" + String.valueOf(mDvd.getId()));
    }

    private void getDvd() {
        if (mDvdId != null) {
            DvdsService dvdsService = DvdsService.retrofit.create(DvdsService.class);
            Call<Dvd> call = dvdsService.getDvdData(mDvdId);

            try {
                mDvd = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


