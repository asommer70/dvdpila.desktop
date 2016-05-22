package com.thehoick.dvdpila;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ocpsoft.prettytime.PrettyTime;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;

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
        mRating.setText((String) mDvd.getRating().toString());

        Media media = new Media(mDvd.getFileUrl());
        MediaPlayer player = new MediaPlayer(media);
        mMediaView.setMediaPlayer(player);

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
            PilaBuilder pg = new PilaBuilder();
            Retrofit.Builder builder = pg.getBuilder();
            Retrofit retrofit = builder.build();
            PilaService pilaService = retrofit.create(PilaService.class);
            Call<Dvd> call = pilaService.getDvdData(mDvdId);

            try {
                mDvd = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


