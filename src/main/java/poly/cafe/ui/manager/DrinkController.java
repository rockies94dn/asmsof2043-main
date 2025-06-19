/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.ui.manager;

import poly.cafe.dao.CrudController;
import poly.cafe.entity.Bill;
import poly.cafe.entity.Drink;

/**
 *
 * @author dtoan
 */
public interface DrinkController extends CrudController<Drink> {

    void chooseFile();

    void setBill(Bill bill); // nhận bill từ BillJDialog

    @Override
    void open(); // hiển thị loại và đồ uống

    void fillCategories(); // tải và hiển thị loại đồ uống

    void fillDrinks(); // tải và hiển thị đồ uống

    void addDrinkToBill(); // thêm đồ uống vào bill

}
