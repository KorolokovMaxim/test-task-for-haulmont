package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Bank;
import com.haulmont.testtask.serivce.BankService;
import com.vaadin.data.Binder;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.sql.SQLException;

public class BankCRUD extends VerticalLayout {


    private BankService bankService = new BankService(Config.getInstance());

    private TextField name = new TextField();
    private Bank bank;
    private Binder<Bank> binder = new Binder<>();

    private Button add= new Button("ADD");
    private Button delete = new Button("DELETE");
    private Button update = new Button("UPDATE");

    public BankCRUD(Bank bank , BankView bankView){
        this.bank = bank;
        setVisible(false);
        setWidthUndefined();
        HorizontalLayout layout = new HorizontalLayout();
        addClickListeners(bankView);
    }

    private void addClickListeners(BankView bankView) {
        add.addClickListener(e -> {
            try {
                if(fieldCheck()){
                   addBank();
                   bankView.updateGrid();
                   this.setVisible(false);
                   name.clear();
                }else{
                    this.bank = bank;
                    setBank(bank);
                }
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        });
    }

    private void setBank(Bank bank) {
        name.setValue(bank.getName());

    }

    private void addBank() throws SQLException {
        Bank bank = new Bank();
        bank.setName(name.getValue());
        bankService.updateOne(bank);
    }


    private boolean fieldCheck(){
        if(name.isEmpty()){
            add.setComponentError(new UserError("Field mismatch"));
            return false;
        }else{
            add.setComponentError(null);
            return true;
        }
    }
}
