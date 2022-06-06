package com.gambler.projekt_zespolowy_zaliczenie;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
public class Controller{
    public CheckBox idCryptocurrency_BTC;
    public CheckBox idCryptocurrency_ETH;
    public CheckBox idCryptocurrency_XRP;
    public CheckBox idCryptocurrency_Litecoin;
    public Button start;
    public Button inwestuj;
    public Button wyplata;

    @FXML
    private void changeBTC()
   {
       if(idCryptocurrency_BTC.isSelected())
       {
            idCryptocurrency_ETH.setSelected(false);
            idCryptocurrency_XRP.setSelected(false);
            idCryptocurrency_Litecoin.setSelected(false);
       }


   }

    @FXML
    private void changeETH()
   {
       if(idCryptocurrency_ETH.isSelected())
       {
           idCryptocurrency_BTC.setSelected(false);
           idCryptocurrency_XRP.setSelected(false);
           idCryptocurrency_Litecoin.setSelected(false);
       }
   }

    @FXML
    private void changeXRP()
    {
        if(idCryptocurrency_XRP.isSelected())
        {
             idCryptocurrency_BTC.setSelected(false);
             idCryptocurrency_ETH.setSelected(false);
            idCryptocurrency_Litecoin.setSelected(false);
        }
    }

    @FXML
    private void changeLitecoin()
    {
        if(idCryptocurrency_Litecoin.isSelected())
        {
            idCryptocurrency_BTC.setSelected(false);
            idCryptocurrency_ETH.setSelected(false);
            idCryptocurrency_XRP.setSelected(false);
        }
    }

    public void onStartAction(ActionEvent actionEvent)
    {
        start.setOnAction(this::onStartAction);
    }


}