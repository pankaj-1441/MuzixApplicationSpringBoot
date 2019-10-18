package com.stackroute.MuzixApplication.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    @ApiModelProperty(value = "ID of the track")
    @Id
    private int trackId;
    @ApiModelProperty(value = "Name of the track")
    private String trackName;
    @ApiModelProperty(value = "Comments about the track")
    private String trackComments;
}
