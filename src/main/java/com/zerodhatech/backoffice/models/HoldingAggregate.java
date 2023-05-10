package com.zerodhatech.backoffice.models;

import com.zerodhatech.backoffice.models.breakdown.BreakdownRow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HoldingAggregate {
    private String instrumentId;
    private String tradingSymbol;
    private BigDecimal quantity;
    private BigDecimal averagePrice;

    public HoldingAggregate(BreakdownRow breakdownRow) {
        this.tradingSymbol = breakdownRow.getTradingsymbol();
        this.instrumentId = breakdownRow.getInstrument_id();
        this.quantity = BigDecimal.valueOf(breakdownRow.getQuantity());
        this.averagePrice = BigDecimal.valueOf(breakdownRow.getPrice());
    }

    public void addBreakdown(BreakdownRow breakdownRow) {
        var price = BigDecimal.valueOf(breakdownRow.getPrice());
        var quantity = BigDecimal.valueOf(breakdownRow.getQuantity());
        var newQuantity = this.quantity.add(quantity);
        this.averagePrice = this.averagePrice.multiply(this.quantity)
                                             .add(price.multiply(quantity))
                                             .divide(newQuantity, 2, RoundingMode.HALF_UP);
        this.quantity = newQuantity;
    }

    @Override
    public String toString() {
        return "%s\t%s\t%s".formatted(tradingSymbol, quantity, averagePrice);
    }
}
