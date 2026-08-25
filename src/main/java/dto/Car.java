package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String location;
    private String manufacture;
    private String model;
    private int year;
    private String fuel;
    private int seats;
    private String carClass;
    private String serialNumber;
    private double price;
    private String about;
}
