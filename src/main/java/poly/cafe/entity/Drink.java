/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
/**
 *
 * @author dtoan
 */
public class Drink {

    private String id;
    private String name;
    @Builder.Default
    private String image = "na.png";
    private double unitPrice;
    private double discount;
    private boolean available;
    private String categoryId;

}
