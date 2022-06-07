package com.gambler.projekt_zespolowy_zaliczenie;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException, Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Simulator trading!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws FileNotFoundException {
        try {
            Scrapper.main();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CSVLoader.load();
        launch();
//        //BTC
//        ArrayList<List<String>> btcData = new ArrayList<>();
//        Scanner input = new Scanner(new File("bitcoin.csv"));
//        input.useDelimiter("\n");
//
//        while (input.hasNext()){
//            String nowalinia = input.nextLine();
//            List<String> temp = Arrays.asList(nowalinia.split(";"));
//            btcData.add(temp);
//        }
//
//        btcData.remove(0);
//        btcData.remove(1);
//
////        System.out.println("BTC:");
////        System.out.println(btcData);
//
//        //ETH
//        ArrayList<List<String>> ethData = new ArrayList<>();
//        input = new Scanner(new File("ethereum.csv"));
//
//        while (input.hasNext()){
//            String nowalinia = input.nextLine();
//            List<String> temp = Arrays.asList(nowalinia.split(";"));
//            ethData.add(temp);
//        }
//
//        ethData.remove(0);
//        ethData.remove(1);
//
////        System.out.println("ETH:");
////        System.out.println(ethData);
//
//        // LITECOIN
//        ArrayList<List<String>> liteData = new ArrayList<>();
//        input = new Scanner(new File("litecoin.csv"));
//
//        while (input.hasNext()){
//            String nowalinia = input.nextLine();
//            List<String> temp = Arrays.asList(nowalinia.split(";"));
//            liteData.add(temp);
//        }
//        liteData.remove(0);
//        liteData.remove(1);
//
////        System.out.println("lite:");
////        System.out.println(liteData);
//
//        // XRP
//        ArrayList<List<String>> xrpData = new ArrayList<>();
//        input = new Scanner(new File("xrp.csv"));
//
//        while (input.hasNext()){
//            String nowalinia = input.nextLine();
//            List<String> temp = Arrays.asList(nowalinia.split(";"));
//            xrpData.add(temp);
//        }
//        xrpData.remove(0);
//        xrpData.remove(1);
////        System.out.println("xrp:");
////        System.out.println(xrpData);
    }
}