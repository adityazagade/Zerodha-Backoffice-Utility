package com.zerodhatech.backoffice.models.tags;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tag {
    private int id;

    @SerializedName("client_id")
    private String clientId;

    @SerializedName("tag_name")
    private String tagName;

    private String category;

    private String status;

    private String description;

    private String colour;

    @SerializedName("new_colour")
    private String newColour;
}
