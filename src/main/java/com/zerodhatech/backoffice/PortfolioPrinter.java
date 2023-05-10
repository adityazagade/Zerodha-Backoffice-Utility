package com.zerodhatech.backoffice;

import com.zerodhatech.backoffice.models.HoldingAggregate;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class PortfolioPrinter {
    private final Map<String, List<HoldingAggregate>> portfolioByTags;

    public void print(String tagName) {
        System.out.println("--------------------");
        System.out.println("--------------------");
        printPortfolio(tagName);
    }

    private void printPortfolio(String tag) {
        var holdingAggregates = portfolioByTags.get(tag);
        if (holdingAggregates != null) {
            for (var holdingAggregate : holdingAggregates) {
                System.out.println(holdingAggregate);
            }
        }
    }
}
