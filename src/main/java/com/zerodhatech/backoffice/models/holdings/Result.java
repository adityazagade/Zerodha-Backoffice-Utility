package com.zerodhatech.backoffice.models.holdings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private ArrayList<EquityHolding> eq;
    private ArrayList<MfHolding> mf;
    private boolean has_breakdown;
}