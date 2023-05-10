package com.zerodhatech.backoffice.models.holdings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IsinBreakdown {
    private String cap;
    private String sector;
    private String weightage;
    private String instrument_id;
    private String tradingsymbol;
    private String instrument_type;
}
