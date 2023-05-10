package com.zerodhatech.backoffice.models.breakdown;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BreakdownResponse {
    private String state;
    private ArrayList<BreakdownRow> result;
    private Object pagination;
}