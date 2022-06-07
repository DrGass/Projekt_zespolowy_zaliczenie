package com.gambler.projekt_zespolowy_zaliczenie;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrapper{

    public static String updateLink(String currency){
        String start = currency.substring(0,currency.indexOf("=")+1 );
        String end = currency.substring(currency.indexOf("&"));
        currency = start + java.time.LocalDate.now() + end;
        return currency;
    }

    public static void main() throws IOException{
        String [] currencies = {
                "https://www.coingecko.com/en/coins/bitcoin/historical_data?end_date=&start_date=2022-04-06",
                "https://www.coingecko.com/en/coins/ethereum/historical_data?end_date=&start_date=2022-04-06",
                "https://www.coingecko.com/en/coins/xrp/historical_data?end_date=&start_date=2022-04-06",
                "https://www.coingecko.com/en/coins/litecoin/historical_data?end_date=&start_date=2022-04-06"};


        for (String page : currencies)
        {
            page = updateLink(page);
            System.out.println("Scraping: " + page);

            Document doc = Jsoup.connect(page).get();
            Elements table = doc.getElementsByTag("tr");
//            System.out.println("Table tr elements: " + table.select("tr").size());
            String currency = page.substring(35);
            currency = currency.substring(0, currency.indexOf("/"));
//            System.out.println(currency);

            File csvFile = new File(currency + ".csv");
            PrintWriter out = new PrintWriter(csvFile);

            for (Element day : table) {
//            System.out.println(day);
                Elements info = day.getElementsByTag("td");
                out.print("\n");
                for (Element slot : info) {
//                System.out.println(slot.text().substring(1));
                    out.print(slot.text().substring(1) + ";");
                }
            }
            out.close();
        }
        System.out.println("Scraped websites successfully");
    }
}