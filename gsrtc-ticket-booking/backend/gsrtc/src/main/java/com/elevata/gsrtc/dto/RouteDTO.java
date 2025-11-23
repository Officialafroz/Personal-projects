package com.elevata.gsrtc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RouteDTO {
    private int routeId;
    private String routeName;
    private String startingPoint;
    private String endingPoint;
    private String classType;
    private int depotId;
}
