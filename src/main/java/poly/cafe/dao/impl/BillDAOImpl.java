/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import poly.cafe.dao.BillDAO;
import poly.cafe.entity.Bill;
import poly.cafe.util.XAuth;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author dtoan
 */
public class BillDAOImpl implements BillDAO {

    String createSql = "INSERT INTO Bills(Username, CardId, Checkin, Checkout, Status) VALUES (?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Bills SET Username = ?, CardId = ?, Checkin = ?, Checkout = ?, Status = ? WHERE Id = ?";
    String deleteSql = "DELETE FROM Bills WHERE Id = ?";
    String findAllSql = "SELECT * FROM Bills";
    String findByUsernameSql = "SELECT * FROM Bills WHERE Username = ?";
    String findByIdSql = "SELECT * FROM Bills WHERE Id = ?";
    String findByCardIdSql = "SELECT * FROM Bills WHERE CardId = ?";
    String findByTimeRangeSql = "SELECT * FROM Bills WHERE Checkin BETWEEN ? AND ? ORDER BY Checkin DESC";
    String findServicingByCardId = "SELECT * FROM Bills WHERE CardId = ?";
    String findAllUsingCardSql = "SELECT * FROM Bills WHERE Status = 0";

    @Override
    public int getNextBillId() {
        String sql = "SELECT MAX(Id) FROM Bills";
        int nextId = 1;
        try (
                Connection con = XJdbc.openConnection(); // hoặc dùng lớp kết nối của bạn
                 PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            if (rs.next()) {
                int currentMax = rs.getInt(1);
                if (!rs.wasNull()) {
                    nextId = currentMax + 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextId;
    }

    @Override
    public List<Bill> findByUserAndTimeRange(String username, Date begin, Date end) {
        String sql = "SELECT * FROM Bills WHERE Username=? AND Checkin BETWEEN ? AND ?";
        return XQuery.getBeanList(Bill.class, sql, username, begin, end);
    }

    @Override
    public List<Bill> findByUsername(String username) {
        return XQuery.getBeanList(Bill.class, findByUsernameSql);
    }

    @Override
    public List<Bill> findByCardId(Integer cardId) {
        return XQuery.getBeanList(Bill.class, findByCardIdSql);
    }

    @Override

    public List<Bill> findByTimeRange(Date begin, Date end) {
        return XQuery.getBeanList(Bill.class, findByTimeRangeSql, begin, end);
    }

//    @Override
//    public Bill create(Bill entity) {
//        Object[] values = {
//            entity.getId(),
//            entity.getUsername(),
//            entity.getCardId(),
//            entity.getCheckin(),
//            entity.getCheckout(),
//            entity.getStatus()
//        };
//        XJdbc.executeUpdate(createSql, values);
//        return entity;
//    }
    @Override
    public Bill create(Bill entity) {
        String sql = "INSERT INTO Bills (Username, CardId, Checkin, Checkout, Status) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = XJdbc.openConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getUsername());
            ps.setInt(2, entity.getCardId());
            ps.setTimestamp(3, new Timestamp(entity.getCheckin().getTime()));
            if (entity.getCheckout() != null) {
                ps.setTimestamp(4, new Timestamp(entity.getCheckout().getTime()));
            } else {
                ps.setNull(4, Types.TIMESTAMP);
            }
            ps.setInt(5, entity.getStatus());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1)); // Gán lại ID được sinh tự động
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void update(Bill entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getCardId(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus(),
            entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Bill> findAll() {
        return XQuery.getBeanList(Bill.class, findAllSql);
    }
    
     public List<Bill> findAllUsingCard() {
        return XQuery.getBeanList(Bill.class, findAllUsingCardSql);
    }

    @Override
    public Bill findByID(Long id) {
        return XQuery.getSingleBean(Bill.class, findByIdSql, id);
    }

    @Override
    public Bill findServicingByCardId(Integer cardId) {
        String sql = "SELECT * FROM Bills WHERE CardId=? AND Status=0";
        Bill bill = XQuery.getSingleBean(Bill.class, sql, cardId);
        if (bill == null) { // không tìm thấy -> tạo mới
            Bill newBill = new Bill();
            newBill.setCardId(cardId);
            newBill.setCheckin(new Date());
            newBill.setStatus(0); // đang phục vụ
            newBill.setUsername(XAuth.user.getUsername());
            bill = this.create(newBill); // insert
        }
        return bill;
    }

    public boolean hasActiveBill(long cardId) {
        String sql = "SELECT COUNT(*) FROM Bills WHERE CardId = ? AND Status = 0";
        try (
                ResultSet rs = XJdbc.executeQuery(sql, cardId)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
