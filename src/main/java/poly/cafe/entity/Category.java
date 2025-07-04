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
public class Category {

    private String id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
