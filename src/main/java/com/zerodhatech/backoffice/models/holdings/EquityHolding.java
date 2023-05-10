package com.zerodhatech.backoffice.models.holdings;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquityHolding {
    private String client_id;
    private String isin;
    @SerializedName("instrument_id")
    private String instrumentId;
    private String instrument_type;
    private String tradingsymbol;
    private String holdings_date;
    private Object failure_date;
    private Object isin_breakdown;
    private String sector;
    private String cap;
    private double weight;
    private double buy_average;
    private double close_price;
    private double ltp;
    private double holdings_buy_value;
    private double closing_value;
    private int quantity_t1;
    private double quantity_available;
    private double quantity_long_term;
    private int quantity_pledged_for_loan;
    private int quantity_pledged_for_margin;
    private int quantity_discrepant;
    private double total_quantity;
    private double unrealized_profit;
    private double unrealized_profit_percentage;
}
