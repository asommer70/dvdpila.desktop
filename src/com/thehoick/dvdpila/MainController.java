package com.thehoick.dvdpila;


import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Callback;
import retrofit2.Call;

import java.io.IOException;
import java.util.*;

public class MainController {
    List<Dvd> mDvds;
    Properties mSettings;
    private int mMaxPages = 0;
    private Map<String, String> mOptions = new HashMap<String, String>();

    @FXML
    private BorderPane mMainPane;
    @FXML
    private GridPane mMainGrid;
    @FXML
    private HBox mBottom;

    public void initialize() {
        // Load Settings from XML.
        mSettings = Settings.getSettings();
        mOptions.put("page", "1");
        getDvds(mOptions);
        GridPane grid = getDvdGrid();
        grid = addDvdsToGrid(grid);
        mMainPane.setCenter(grid);

//        addDvdsToGrid();

        //Creates a Pagination control with an INDETERMINATE page count
        //and the current page index equal to zero
        Pagination pager = new Pagination(mMaxPages, 1);
//        pager.setPageFactory(new Callback<Integer, Node>() {
//            @Override
//            public Node call(Integer pageIndex) {
//                if (mOptions.get("page").equals("0")) {
//                    mOptions.put("page", "1");
//                    System.out.println("mOptions: " + mOptions.get("page"));
//                    getDvds(mOptions);
//                } else {
//                    mOptions.put("page", String.valueOf(pageIndex));
//                }
//
//                return addDvdsToGrid();
//            }
//        });
        mBottom.getChildren().add(pager);
    }

    @FXML
    public void getDvds(Map<String, String> options) {
        DvdsService dvdsService = DvdsService.retrofit.create(DvdsService.class);
        Call<Dvds> call = dvdsService.getDvds(options);

        try {
            Dvds dvds = call.execute().body();
            if (mMaxPages == 0) {
                mMaxPages = dvds.getMaxPages();
            }
            mDvds = dvds.getDvds();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        addDvdsToGrid();
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

        for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints();
            row.setMinHeight(10.0);
            row.setPrefHeight(30.0);
            row.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(row);
        }

        grid.setPadding(new Insets(40, 10, 10, 10));
        grid.setId("mainGrid");

        return grid;
    }

    private GridPane addDvdsToGrid(GridPane grid) {
        int rowCount = 0;
        int colCount = 0;
        for (Dvd dvd : mDvds) {
            VBox dvdBox = getDvdVBox(dvd);

            grid.add(dvdBox, colCount, rowCount);
            colCount++;
            if (colCount >= 5) {
                rowCount++;
                colCount = 0;
            }
        }
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
        Label dvdTitle = new Label();
        dvdTitle.setText(dvd.getTitle());

        VBox dvdBox = new VBox();
        dvdBox.setPadding(new Insets(10, 10, 10, 10));
        dvdBox.setSpacing(20);
        dvdBox.setAlignment(Pos.CENTER);
        dvdBox.getChildren().addAll(dvdImage, dvdTitle);
        return dvdBox;
    }

    public int itemsPerPage() {
        return 10;
    }

    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {
//            TextArea text = new TextArea(textPages[i]);
//            text.setWrapText(true);
//            box.getChildren().add(text);
        }
        return box;
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
            System.out.println("OK pressed...");
            SettingsController settingsController = fxmlLoader.getController();
            settingsController.processResults();
        }
    }

}
