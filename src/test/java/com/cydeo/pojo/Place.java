package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
//    {
// *             "place name": "Fairfax",
// *             "longitude": "-77.3242",
// *             "post code": "22030",
// *             "latitude": "38.8458"
//            *         }
//
    @JsonProperty("place name")
    private String name;
@JsonProperty("longitude")
    private String longitude;

    @JsonProperty("post code")
    private int postCode;

    private float latitude;
}
