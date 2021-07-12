package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Bank;
import com.haulmont.testtask.serivce.BankService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class BankView extends VerticalLayout implements View {


    public static final String NAME = "";

    private Grid<Bank> bankGrid = new Grid<>(Bank.class);

    private Button addBtn;
    private Button editBtn;
    private Button deleteBtn;

    BankService bs = new BankService(Config.getInstance());

    public BankView() {
        buildView();
        setupListeners();
    }

    private void buildView() {
        bankGrid.removeAllColumns();
        bankGrid.addColumn(Bank::getName).setCaption("Название");
        bankGrid.setSizeFull();

        HorizontalLayout btnLayout = new HorizontalLayout();

        btnLayout.setSpacing(true);

        addBtn = new Button("ADD");
        editBtn = new Button("EDIT");
        deleteBtn = new Button("DELETE");

        btnLayout.addComponents(addBtn, editBtn, deleteBtn);
        setMargin(true);
        setSpacing(true);
        setSizeFull();
        addComponents(bankGrid, btnLayout);
        setExpandRatio(bankGrid, 1f);
    }


    private void setupListeners() {
        bankGrid.addSelectionListener(valueChangerEvent -> {

            if (!bankGrid.asSingleSelect().isEmpty()) {
                editBtn.setEnabled(true);
                deleteBtn.setEnabled(true);
            } else {
                editBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
            }

        });

        addBtn.addClickListener(clickEvent ->
                getUI().addWindow(new BankWindow(bankGrid, false)));

        editBtn.addClickListener(clickEvent ->
                getUI().addWindow(new BankWindow(bankGrid, true)));

        deleteBtn.addClickListener(clickEvent -> {
            if (!bankGrid.asSingleSelect().isEmpty()) {
                bs.deleteOne(bankGrid.asSingleSelect().getValue());
                updateGrid();
            }
        });

    }

    private void updateGrid() {
        List<Bank> banks = bs.getAll();
        bankGrid.setItems(banks);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        updateGrid();
    }
}
