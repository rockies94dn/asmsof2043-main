/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;

import poly.cafe.entity.User;

/**
 *
 * @author dtoan
 */
public class XAuth {

    public static User user = User.builder()
            .username("user1@gmail.com")
            .password("123")
            .enabled(false)
            .manager(false)
            .fullname("DefaultUser")
            .photo("na.png")
            .build(); // biến user này sẽ được thay thế sau khi đăng nhập

    public static boolean isLogin() {
        return user != null;
    }

    public static boolean isManager() {
        return isLogin() && user.isManager();
    }

    public static void clear() {
        user = null;
    }
}
