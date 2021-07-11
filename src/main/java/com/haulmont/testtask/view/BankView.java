package com.haulmont.testtask.view;


import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Bank;
import com.haulmont.testtask.serivce.BankService;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankView extends VerticalLayout {

    Grid<Bank> grid = new Grid<>(Bank.class);
    private Button add = new Button("Add");
    BankService bs = new BankService(Config.getInstance());

    public BankView(VerticalLayout layout) throws SQLException {
        List<Bank> bankList = new ArrayList<>();
        bankList = bs.getAll();


        grid.setItems(bankList);
        grid.setColumns("id", "name");
        grid.removeColumn("id");
        layout.addComponent(grid);
        layout.addComponent(add);

    }

    public void updateGrid() throws SQLException{
        grid.setItems(bs.getAll());
    }


}
