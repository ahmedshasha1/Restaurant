package com.resturant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChefsDto extends BaseEntityDto{
    private String spec;
    private String faceLink;
    private String tweLink;
    private String instaLink;
}
