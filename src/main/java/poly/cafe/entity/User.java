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
public class User {

    private String username;
    private String password;
    private boolean enabled;
    private String fullname;
    @Builder.Default
    private String photo = "na.png";
    private boolean manager;

}
