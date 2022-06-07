package com.gambler.projekt_zespolowy_zaliczenie;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Controller extends Application {
    int statusLine = 0;
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    XYChart.Series series3 = new XYChart.Series();
    XYChart.Series series4 = new XYChart.Series();
    private float bTCCashin = 0, eTHCashin = 0, xRPCashin = 0, litecoinCashin = 0, startmoney = 0, balance = 0, nowMoney = 0;
    private int bTCCurrentStep=0,eTHCurrentStep=0,xRPCurrentStep=0,litecoinCurrentStep=0,it=0;
    private Boolean chartinitialized=false;
    ArrayList<List<String>> btcData = new ArrayList<>();
    ArrayList<List<String>> ethData = new ArrayList<>();
    ArrayList<List<String>> liteData = new ArrayList<>();
    ArrayList<List<String>> xrpData = new ArrayList<>();

    @FXML
    private LineChart<String, Number> lBarChart = new LineChart<String, Number>(xAxis, yAxis);
    @FXML
    private LineChart<String, Number> lBarChartETH = new LineChart<String, Number>(xAxis, yAxis);
    @FXML
    private LineChart<String, Number> lBarChartXRP = new LineChart<String, Number>(xAxis, yAxis);
    @FXML
    private LineChart<String, Number> lBarChartLC = new LineChart<String, Number>(xAxis, yAxis);
    @FXML
    private TextField BTCValue;
    @FXML
    private TextField ETHValue;
    @FXML
    private TextField XRPValue;
    @FXML
    private TextField LitecoinValue;
    @FXML
    private TextField ChangeLitecoin;
    @FXML
    private TextField ChangeXRP;
    @FXML
    private TextField ChangeETH;
    @FXML
    private TextField ChangeBTC;
    @FXML
    private TextField StartMoney;
    @FXML
    private CheckBox BTCCB;
    @FXML
    private CheckBox ETHCB;
    @FXML
    private CheckBox XRPCB;
    @FXML
    private CheckBox LitecoinCB;
    @FXML
    private Label CurrentMoney;
    @FXML
    private Label Balance;
    @FXML
    private Label ErrorLog;
    @FXML
    private Button StartButton;
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnNext;
    @FXML
    private Label labelStatus;

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @FXML
    protected void onStartButton() throws FileNotFoundException {

        if(StartButton.getText() != "Stop"){
            System.out.println(StartButton.getText());
            loadCSV();
            System.out.println(liteData);
            String tmp = StartMoney.getText();
            if (tmp != "")
                startmoney = Float.parseFloat(tmp);
            if (startmoney < 1) {
                ErrorLog.setText("Niepoprawny kapitał początkowy");
            } else {
                ErrorLog.setText("");
                StartButton.setText("Stop");
                StartMoney.setDisable(true);
                CurrentMoney.setText(Float.toString(startmoney));
            }
        }
        else {
            StartButton.setText("Start");
            AlertBox.display("Koniec inwestycji. ","Twoje saldo końcowe: " + CurrentMoney.getText());
            StartMoney.setDisable(false);
            StartMoney.setText("");
        }
    }

    @FXML
    protected void onInvestButton() {
        float payin=0;
        if (BTCCB.isSelected() && BTCValue.getText() != ""){
            String tmp=BTCValue.getText();
            bTCCashin+=Float.parseFloat(tmp);
            payin+=Float.parseFloat(tmp);
        }
        if (ETHCB.isSelected() && ETHValue.getText() != ""){
            String tmp=ETHValue.getText();
            eTHCashin+=Float.parseFloat(tmp);
            payin+=Float.parseFloat(tmp);
        }
        if (XRPCB.isSelected() && XRPValue.getText() != ""){
            String tmp=XRPValue.getText();
            xRPCashin+=Float.parseFloat(tmp);
            payin+=Float.parseFloat(tmp);
        }
        if (LitecoinCB.isSelected() && LitecoinValue.getText() != ""){
            String tmp=LitecoinValue.getText();
            litecoinCashin+=Float.parseFloat(tmp);
            payin+=Float.parseFloat(tmp);
        }
        float ctmp=Float.parseFloat(CurrentMoney.getText());
        ctmp-=payin;
        CurrentMoney.setText(Float.toString(ctmp));
        ctmp=Float.parseFloat(Balance.getText());
        ctmp-=payin;
        Balance.setText(Float.toString(ctmp));
    }

    @FXML
    protected void onCashoutButton() {
        float ftmp=Float.parseFloat(CurrentMoney.getText());
        if (BTCCB.isSelected()){
            ftmp+=bTCCashin;
            bTCCashin=0;
        }
        if (ETHCB.isSelected()){
            ftmp+=eTHCashin;
            eTHCashin=0;
        }
        if (XRPCB.isSelected()){
            ftmp+=xRPCashin;
            xRPCashin=0;
        }
        if (LitecoinCB.isSelected()){
            ftmp+=litecoinCashin;
            litecoinCashin=0;
        }
        CurrentMoney.setText(Float.toString(ftmp));
        Balance.setText(Float.toString(Float.parseFloat(CurrentMoney.getText())-Float.parseFloat(StartMoney.getText())));
    }

    @FXML
    protected void OnNextStepClicked() {
        it++;
        float change=0;
        bTCCurrentStep--;
        eTHCurrentStep--;
        xRPCurrentStep--;
        litecoinCurrentStep--;
        if(bTCCurrentStep<1)
        {
            bTCCurrentStep=btcData.size()-1;
        }
        if(eTHCurrentStep<1)
        {
            eTHCurrentStep=ethData.size()-1;
        }
        if(xRPCurrentStep<1)
        {
            xRPCurrentStep=xrpData.size()-1;
        }
        if(litecoinCurrentStep<1) {
            litecoinCurrentStep = liteData.size() - 1;
        }
        float ftmp1,ftmp2;
        List tmp=btcData.get(bTCCurrentStep);
        ftmp1=Float.parseFloat(tmp.get(2).toString().replaceAll(",",""));
        ftmp2=Float.parseFloat(tmp.get(3).toString().replaceAll(",",""));
        change=(ftmp2)/(ftmp1);
        ChangeBTC.setText(Float.toString((change-1)*100));
        System.out.println("ssss");
        System.out.println(bTCCashin);
        bTCCashin*=change;


        tmp=ethData.get(eTHCurrentStep);
        ftmp1=Float.parseFloat(tmp.get(2).toString().replaceAll(",",""));
        ftmp2=Float.parseFloat(tmp.get(3).toString().replaceAll(",",""));
        change=(ftmp2)/(ftmp1);
        ChangeETH.setText(Float.toString((change-1)*100));
        eTHCashin*=change;

        tmp=xrpData.get(xRPCurrentStep);
        ftmp1=Float.parseFloat(tmp.get(2).toString().replaceAll(",",""));
        ftmp2=Float.parseFloat(tmp.get(3).toString().replaceAll(",",""));
        change=(ftmp2)/(ftmp1);
        ChangeXRP.setText(Float.toString((change-1)*100));
        xRPCashin*=change;

        tmp=liteData.get(litecoinCurrentStep);
        ftmp1=Float.parseFloat(tmp.get(2).toString().replaceAll(",",""));
        ftmp2=Float.parseFloat(tmp.get(3).toString().replaceAll(",",""));
        change=(ftmp2)/(ftmp1);
        ChangeLitecoin.setText(Float.toString((change-1)*100));
        litecoinCashin*=change;
        ChangeLitecoin.setDisable(true);
        ChangeXRP.setDisable(true);
        ChangeETH.setDisable(true);
        ChangeBTC.setDisable(true);

        if(!chartinitialized)
        {
            initializeChart();
            chartinitialized=true;
        }
            series1.setName("BTC");
            series2.setName("ETH");
            series3.setName("XRP");
            series4.setName("Lite");
            tmp=btcData.get(bTCCurrentStep);
            series1.getData().add(new XYChart.Data("D"+it, Float.parseFloat(tmp.get(2).toString().replaceAll(",","."))));
            tmp=ethData.get(eTHCurrentStep);
            series2.getData().add(new XYChart.Data("D"+it, Float.parseFloat(tmp.get(2).toString().replaceAll(",",""))));
            tmp=xrpData.get(xRPCurrentStep);
            series3.getData().add(new XYChart.Data("D"+it, Float.parseFloat(tmp.get(2).toString())));
            tmp=liteData.get(litecoinCurrentStep);
            series4.getData().add(new XYChart.Data("D"+it, Float.parseFloat(tmp.get(2).toString())));
    }
    protected void loadCSV() throws FileNotFoundException {
        Scanner input = new Scanner(new File("bitcoin.csv"));
        input.useDelimiter("\n");
        while (input.hasNext()){
            String nowalinia = input.nextLine();
            List<String> temp = Arrays.asList(nowalinia.split(";"));
            btcData.add(temp);
        }
        btcData.remove(0);
        btcData.remove(1);
        //ETH
        input = new Scanner(new File("ethereum.csv"));
        while (input.hasNext()){
            String nowalinia = input.nextLine();
            List<String> temp = Arrays.asList(nowalinia.split(";"));
            ethData.add(temp);
        }
        ethData.remove(0);
        ethData.remove(1);
        // LITECOIN
        input = new Scanner(new File("litecoin.csv"));
        while (input.hasNext()){
            String nowalinia = input.nextLine();
            List<String> temp = Arrays.asList(nowalinia.split(";"));
            liteData.add(temp);
        }
        liteData.remove(0);
        liteData.remove(1);
        // XRP
        input = new Scanner(new File("xrp.csv"));
        while (input.hasNext()){
            String nowalinia = input.nextLine();
            List<String> temp = Arrays.asList(nowalinia.split(";"));
            xrpData.add(temp);
        }
        xrpData.remove(0);
        xrpData.remove(1);
    }
    protected void initializeChart(){
        xAxis.setAutoRanging(false);
        xAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);
        /*lBarChart.getData().addAll(series1,series2,series3,series4);*/
        lBarChart.getData().add(series1);
        lBarChartETH.getData().add(series2);
        lBarChartXRP.getData().add(series3);
        lBarChartLC.getData().add(series4);

    }

    @FXML
    protected void onbtnNext(){
        ChangeScene("next");
    }

    @FXML
    protected void onbtnPrev(){
        ChangeScene("prev");
    }

    protected void ChangeScene(String status){
        if(status == "prev" && statusLine>0) statusLine--;
        else if( status == "next" && statusLine<3) statusLine++;

        switch (statusLine){
            case 0:
                lBarChart.setVisible(true);
                lBarChartETH.setVisible(false);
                lBarChartXRP.setVisible(false);
                lBarChartLC.setVisible(false);
                btnPrev.setDisable(true);
                labelStatus.setText("BTC");
                break;
            case 1:
                lBarChart.setVisible(false);
                lBarChartETH.setVisible(true);
                lBarChartXRP.setVisible(false);
                lBarChartLC.setVisible(false);
                btnPrev.setDisable(false);
                labelStatus.setText("ETH");
                break;
            case 2:
                lBarChart.setVisible(false);
                lBarChartETH.setVisible(false);
                lBarChartXRP.setVisible(true);
                lBarChartLC.setVisible(false);
                btnNext.setDisable(false);
                labelStatus.setText("XRP");
                break;
            case 3:
                lBarChart.setVisible(false);
                lBarChartETH.setVisible(false);
                lBarChartXRP.setVisible(false);
                lBarChartLC.setVisible(true);
                btnNext.setDisable(true);
                labelStatus.setText("LiteCoin");
                break;
        }
    }
}