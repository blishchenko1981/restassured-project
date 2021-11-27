package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@JsonIgnoreProperties(ignoreUnknown = true)
public class Driver {

    private String driverId;
    private String url;
    private String givenName;
    private String familyName;
    private String dateOfBirth;
    private String nationality;
}
