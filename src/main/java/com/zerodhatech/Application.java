package com.zerodhatech;

import com.zerodhatech.backoffice.PortfolioPrinter;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class Application {
    public static void main(String[] args) {
        PortfolioBuilder portfolioBuilder = new PortfolioBuilder();
        try {
            var portfolioByTags = portfolioBuilder.buildPortfolio();
            PortfolioPrinter printer = new PortfolioPrinter(portfolioByTags);
            printer.print("cm_momentum");
            printer.print("gold");
            printer.print("cm_low_vol");
        } catch (IOException | KiteException e) {
            throw new RuntimeException(e);
        }
    }
}
