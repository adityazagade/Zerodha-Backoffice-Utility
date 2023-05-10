package com.zerodhatech.backoffice.models.breakdown;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BreakdownRow {
    private String tradingsymbol;
    private String client_id;
    private String instrument_id;
    private String exchange;
    private double quantity;
    private double price;
    private Date order_execution_time;
    private String trade_id;
    private String order_id;
    private boolean pledged;
    private String trade_type;
    private Object external_trade_type;
    private ArrayList<Integer> tags;
}