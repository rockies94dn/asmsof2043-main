package poly.cafe.util;

import javax.swing.JOptionPane;

public class XDialog {

    public static void message(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void alert(String message) {
        XDialog.alert(message, "Alert");
    }

    public static void alert(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static boolean confirm(String message) {
        return XDialog.confirm(message, "Confirm");
    }

    public static boolean confirm(String message, String title) {
        int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return (result == JOptionPane.YES_OPTION);
    }

    public static String prompt(String message) {
        return XDialog.prompt(message, "Prompt");
    }

    public static String prompt(String message, String title) {
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
