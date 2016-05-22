package com.thehoick.dvdpila;


import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.*;

public class MainController {
    public int mPageIndex;
    private List<Dvd> mDvds;
    private int mMaxPages = 0;
    private Map<String, String> mOptions = new HashMap<String, String>();
    private Pagination mPager;

    public void setPageIndex(int pageIndex) {
        mMainPane.getChildren().removeAll();

        if (mDvds == null) {
            Label noDvds = new Label("Unable to get DVDs...");
            mMainPane.setCenter(noDvds);
        } else {
            mPageIndex = pageIndex;
            mPager = new Pagination(mMaxPages, mPageIndex);
            mPager.setPageFactory(new Callback<Integer, Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    mMainPane.getChildren().removeAll();
                    mOptions.put("page", String.valueOf(pageIndex + 1));
                    getDvds(mOptions);
                    GridPane grid = getDvdGrid();
                    grid = addDvdsToGrid(grid);
                    mMainPane.setCenter(grid);
                    // Need to return something, and when trying to return the GridPane things were all jacked up.
                    return new VBox();
                }
            });

            mPager.setId("dvdPager");
            mBottom.getChildren().add(mPager);
        }
    }

    @FXML
    private BorderPane mMainPane;
    @FXML
    private GridPane mMainGrid;
    @FXML
    private HBox mBottom;

    public void initialize() {
        mOptions.put("page", "1");
        getDvds(mOptions);
    }

    @FXML
    private void getDvds(Map<String, String> options) {
        PilaBuilder pg = new PilaBuilder();
        Retrofit.Builder builder = pg.getBuilder();
        Retrofit retrofit = builder.build();
        PilaService pilaService = retrofit.create(PilaService.class);
        Call<Dvds> call = pilaService.getDvds(options);

        Settings settings = new Settings();
        try {
            Dvds dvds = call.execute().body();
            if (mMaxPages == 0) {
                mMaxPages = dvds.getMaxPages();
            }
            mDvds = dvds.getDvds();
        } catch (IOException e) {
            System.err.println("Couldn't connect to DVD Pila! URL...");
            e.printStackTrace();
        }
    }

    private GridPane getDvdGrid() {
        GridPane grid = new GridPane();
        for (int i = 0; i < 5; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setHgrow(Priority.SOMETIMES);
            col.setMinWidth(10.0);
            col.setPrefWidth(100.0);
            grid.getColumnConstraints().add(col);
        }

        for (int i = 0; i < 2; i++) {
            RowConstraints row = new RowConstraints();
            row.setMinHeight(10.0);
            row.setPrefHeight(40.0);
            row.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(row);
        }

        grid.setVgap(20);
        grid.setPadding(new Insets(20, 10, 0, 10));
        grid.setGridLinesVisible(false);
        grid.setId("mainGrid");

        return grid;
    }

    private GridPane addDvdsToGrid(GridPane grid) {
        int rowCount = 0;
        int colCount = 0;
        grid.getChildren().removeAll();
        for (Dvd dvd : mDvds) {
            VBox dvdBox = getDvdVBox(dvd);

            grid.add(dvdBox, colCount, rowCount);
            colCount++;
            if (colCount >= 5) {
                rowCount++;
                colCount = 0;
            }
        }
        grid.setOnMouseClicked(event -> {
            Node source = (Node)event.getTarget();
            if (source instanceof LabeledText) {
                source = source.getParent();
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dvd.fxml"));
                Parent parent = (Parent)loader.load();
                DvdController controller = loader.  getController();
                controller.initData(source.getId(), mPager.getCurrentPageIndex());

                Scene scene = new Scene(parent, 800, 700);
                Stage stage = (Stage)source.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return grid;
    }

    private VBox getDvdVBox(Dvd dvd) {
        Image image = new Image(dvd.getImageUrl());
        ImageView dvdImage = new ImageView();
        dvdImage.setImage(image);
        dvdImage.setFitWidth(130);
        dvdImage.setPreserveRatio(true);
        dvdImage.setSmooth(true);
        dvdImage.setCache(true);
        dvdImage.setId("image:" + String.valueOf(dvd.getId()));

        Label dvdTitle = new Label();
        dvdTitle.setText(dvd.getTitle());
        dvdTitle.setId("title:" + String.valueOf(dvd.getId()));

        VBox dvdBox = new VBox();
        dvdBox.setPadding(new Insets(10, 10, 10, 10));
        dvdBox.setSpacing(10);
        dvdBox.setAlignment(Pos.CENTER);
        dvdBox.getChildren().addAll(dvdImage, dvdTitle);
        dvdBox.setId("vbox:" + String.valueOf(dvd.getId()));
        return dvdBox;
    }

    @FXML
    public void showSettingsDialog() throws IOException {
        Dialog<ButtonType> settingsDialog = new Dialog<>();
        settingsDialog.initOwner(mMainPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/settings.fxml"));

        settingsDialog.getDialogPane().setContent(fxmlLoader.load());

        settingsDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        settingsDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = settingsDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            SettingsController settingsController = fxmlLoader.getController();
            settingsController.processResults();

            mDvds = null;
            mBottom.getChildren().removeAll(mPager);
            getDvds(mOptions);
            setPageIndex(0);
        }
    }

}
