package com.haulmont.testtask.serivce;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.dao.BankDAO;

import com.haulmont.testtask.entity.Bank;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankService implements BankDAO {

    private final Connection db;

    public BankService(Config config){
        this.db = config.getDb();
    }


    @Override
    public void createOne(Bank bank) {
        String sql = "INSERT INTO BANK (ID, NAME) values (?, ?)";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            setEntity(bank , pstmt);
            if (pstmt.executeUpdate() != 1) {
                throw new IllegalArgumentException(Bank.class.getSimpleName() + "Error: createOne");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bank> getAll() {
        List<Bank> listOfBank = new ArrayList<>();
        String sql = "SELECT ID, NAME FROM BANK";
        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Bank bank = new Bank();
                bank.setId(rs.getString("ID"));
                bank.setName(rs.getString("NAME"));
                listOfBank.add(bank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfBank;
    }

    @Override
    public Bank findById(String id) {

        ResultSet rs = null;
        Bank bank = new Bank();
        String sql = "SELECT ID, NAME FROM BANK WHERE id = ?";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bank.setId(rs.getString("ID"));
                bank.setName(rs.getString("NAME"));

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
        return bank;
    }

    @Override
    public void updateOne(Bank bank) {

        String sql = "UPDATE BANK SET NAME = ? WHERE ID = ?";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, bank.getName());
            ps.setString(2, bank.getId());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Bank.class.getSimpleName() + "Error: updateOne");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteOne(Bank bank) {
        String sql = "DELETE FROM BANK WHERE ID=?";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, bank.getId());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Bank.class.getSimpleName() + " Error: deleteOne");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setEntity(Bank bank, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, bank.getId());
        pstmt.setString(2, bank.getName());
    }


}
