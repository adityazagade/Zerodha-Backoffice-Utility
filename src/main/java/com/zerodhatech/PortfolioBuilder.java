package com.zerodhatech;

import com.zerodhatech.backoffice.BackendOfficeConnect;
import com.zerodhatech.backoffice.models.HoldingAggregate;
import com.zerodhatech.backoffice.models.breakdown.BreakdownRow;
import com.zerodhatech.backoffice.models.holdings.EquityHolding;
import com.zerodhatech.backoffice.models.tags.Tag;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.zerodhatech.Constants.*;

@Log4j2
public class PortfolioBuilder {
    public Map<String, List<HoldingAggregate>> buildPortfolio() throws IOException, KiteException {
        var portfolioByTag = new HashMap<String, List<HoldingAggregate>>();

        // create backend office connect object
        BackendOfficeConnect console = new BackendOfficeConnect(null, false);
        console.login(COOKIES, CSRF_TOKEN);

        log.info("Getting all tags");
        var tags = console.getTags();
        var tagMapByName = tags.stream().collect(Collectors.toMap(Tag::getId, Function.identity()));

        log.info("Getting all holdings as of today");
        var portfolioHoldings = console.getHoldings(LocalDate.now());

        log.info("Getting breakdown for each holding");

        var equityHoldings = portfolioHoldings.getResult().getEq();
        for (var equityHolding : equityHoldings) {
            sleep();
            log.info("Getting breakdown for {}", equityHolding.getTradingsymbol());
            ArrayList<BreakdownRow> breakdown = getEquityBreakdown(console, equityHolding);
            for (BreakdownRow breakdownRow : breakdown) {
                Optional.ofNullable(breakdownRow).ifPresent(row -> {
                    var breakdownTags = row.getTags();
                    if (breakdownTags.size() > 1) {
                        log.warn("Multiple tags found for " + equityHolding.getTradingsymbol());
                    }
                    for (Integer tag : breakdownTags) {
                        var tagId = tagMapByName.get(tag);
                        if (tagId != null) {
                            var holdingAggregateList = portfolioByTag.computeIfAbsent(tagId.getTagName(), k -> new ArrayList<>());
                            holdingAggregateList.stream()
                                    .filter(holdingAggregate -> holdingAggregate.getInstrumentId().equals(equityHolding.getInstrumentId()))
                                    .findFirst()
                                    .ifPresentOrElse(holdingAggregate -> holdingAggregate.addBreakdown(breakdownRow), () -> holdingAggregateList.add(new HoldingAggregate(breakdownRow)));
                        } else {
                            log.error("Tag not found: " + tag);
                        }
                    }
                });
            }
        }
        log.info("Done");
        return portfolioByTag;
    }

    private static ArrayList<BreakdownRow> getEquityBreakdown(BackendOfficeConnect console, EquityHolding equityHolding) throws IOException, KiteException {
        var breakdownResponse = console.getBreakDown(equityHolding.getInstrumentId(), "EQ");
        return breakdownResponse.getResult();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
