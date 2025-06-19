/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao;

import java.util.List;

/**
 *
 * @author dtoan
 * @param <T> Truy xuất dữ liệu tổng quát
 * @param <ID>
 */
public interface CrudDAO<T, ID> {

    T create(T entity);

    void update(T entity);

    void deleteById(ID id);

    List<T> findAll();

    T findByID(ID id);

}
