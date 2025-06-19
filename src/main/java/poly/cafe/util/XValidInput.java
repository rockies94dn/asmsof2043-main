/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;

/**
 *
 * @author dtoan
 */
public class XValidInput {

    private static final String datePattern = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/([0-9]{4})$";
    private static final String numberPattern = "^\\d+(\\.\\d+)?$";

    //Hàm kiểm tra chuỗi nhập vào có rỗng hoặc chỉ toàn khoảng trắng
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    //Hàm kiểm tra chuỗi có nhập đúng là số (cả thực hoặc nguyên) hay không, không chấp nhận số âm
    public static boolean isNumber(String str) {
        return str.matches(numberPattern);
    }

    //Hàm kiểm tra chuỗi nhập có đúng định dạng ngày tháng yêu cầu của chương trình không
    public static boolean isDateFormat(String str) {
        return str.matches(datePattern);
    }
    
    public static boolean isDuplicate(String inputStr, String dataStr) {
        return !inputStr.equalsIgnoreCase(dataStr);
    }
    
}
