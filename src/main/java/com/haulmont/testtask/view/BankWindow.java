package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Bank;
import com.haulmont.testtask.serivce.BankService;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;

import java.util.List;

public class BankWindow extends Window {

    private Grid<Bank> grid = new Grid<>(Bank.class);
    private boolean edit;
    private Button ok;
    private Button cancel;

    private TextField name;

    private Bank bank;

    private Binder<Bank> binder = new Binder<>(Bank.class);

    public BankWindow(Grid<Bank> grid, boolean edit){
        this.grid = grid;
        this.edit = edit;
        buildWindow();
        setupListeners();
    }


    private void buildWindow(){
        setStyleName("modal-window");
        setWidth("550px");
        setHeight("440px");
        setModal(true);
        setResizable(false);
        center();


        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);

        FormLayout formLayout = new FormLayout();
        formLayout.setSizeFull();
        formLayout.setMargin(false);
        formLayout.setSpacing(true);

        name = new TextField("Название");
        name.setMaxLength(50);
        name.setWidth("100%");
        name.setRequiredIndicatorVisible(true);
        binder.forField(name)
                .withValidator(s -> s != null && !s.isEmpty(), "Введите название банка" )
                .asRequired()
                .bind(Bank::getName , Bank::setName);

        formLayout.addComponent(name);

        HorizontalLayout btnLayout = new HorizontalLayout();
        btnLayout.setSpacing(true);

        ok = new Button("OK");
        ok.setWidth("150px");
        cancel = new Button("Cancel");
        cancel.setWidth("150px");

        btnLayout.addComponents(ok, cancel);

        layout.addComponents(formLayout , btnLayout);
        layout.setExpandRatio(formLayout , 1f);
        layout.setComponentAlignment(btnLayout , Alignment.BOTTOM_CENTER);
        setContent(layout);
    }

    private void setupListeners(){
        if(edit){
            setCaption("Редактирование");
            if(!grid.asSingleSelect().isEmpty()){
                try {
                    bank = grid.asSingleSelect().getValue();
                    binder.setBean(bank);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }else {
            setCaption("Добавление");
            name.focus();
        }

        ok.addClickListener(clickEvent -> {
           if(binder.validate().isOk()){
               try {
                   Bank bankWrite = new Bank();
                   bankWrite.setName(name.getValue());
                   BankService bs = new BankService(Config.getInstance());
                   if(edit){
                       bs.updateOne(bank);
                   }else{
                       bs.createOne(bankWrite);
                   }
                   List<Bank> banks = bs.getAll();
                   grid.setItems(banks);
               }catch (Exception e){
                   e.printStackTrace();
               }
               close();
           }
        });

        cancel.addClickListener(clickEvent -> close());
    }



}
