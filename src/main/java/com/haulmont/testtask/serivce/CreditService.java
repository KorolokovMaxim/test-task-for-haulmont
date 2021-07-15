package com.haulmont.testtask.serivce;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.dao.CrudDAO;
import com.haulmont.testtask.entity.Credit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditService implements CrudDAO<Credit,String> {

    private final Connection db;

    public CreditService(Config config) {
        this.db = config.getDb();
    }

    @Override
    public void createOne(Credit credit) {
        String sql = "INSERT INTO CREDIT (ID, NAME, BANK_ID, LIMIT, INTEREST_RATE) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, credit.getId());
            ps.setString(2, credit.getName());
            ps.setString(3, credit.getBankID());
            ps.setString(4, credit.getLimit());
            ps.setString(5, credit.getInterestRate());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Credit.class.getSimpleName() + " Error: createOne");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Credit> getAll() {
        List<Credit> listOfCredit = new ArrayList<>();
        String sql = "SELECT * FROM CREDIT";
        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Credit credit = new Credit();
                credit.setId(rs.getString("ID"));
                credit.setName(rs.getString("NAME"));
                credit.setBankID(rs.getString("BANK_ID"));
                credit.setLimit(rs.getString("LIMIT"));
                credit.setInterestRate(rs.getString("INTEREST_RATE"));
                listOfCredit.add(credit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCredit;
    }

    @Override
    public Credit findById(String id) {
        ResultSet rs = null;
        Credit credit = new Credit();
        String sql = "SELECT * FROM CREDIT WHERE id = ?";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                credit.setId(rs.getString("ID"));
                credit.setName(rs.getString("NAME"));
                credit.setBankID(rs.getString("BANK_ID"));
                credit.setLimit(rs.getString("LIMIT"));
                credit.setInterestRate(rs.getString("INTEREST_RATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return credit;
    }

    @Override
    public void updateOne(Credit credit) {
        String sql = "UPDATE CREDIT SET NAME = ?, BANK_ID = ?, LIMIT = ?, INTEREST_RATE = ? WHERE ID = ?";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, credit.getName());
            ps.setObject(2, credit.getBankID());
            ps.setString(3, credit.getLimit());
            ps.setString(4, credit.getInterestRate());
            ps.setString(5, credit.getId());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Credit.class.getSimpleName() + " Error: updateOne");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOne(Credit credit) {
        String sql = "DELETE FROM CREDIT WHERE ID=?";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, credit.getId());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Credit.class.getSimpleName() + " Error: deleteOne");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public String getBank(String id) {
//        ResultSet rs = null;
//        Bank bank = new Bank();
//        String sql = "SELECT ID, NAME FROM BANK WHERE id = ?";
//        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
//            pstmt.setString(1, id);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                bank.setId(rs.getString("ID"));
//                bank.setName(rs.getString("NAME"));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return bank.getName();
//    }
}
