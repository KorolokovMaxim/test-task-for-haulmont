package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Credit;
import com.haulmont.testtask.serivce.BankService;
import com.haulmont.testtask.serivce.CreditService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.List;

public class CreditView extends VerticalLayout implements View {

    public static final String NAME = "credits";

    CreditService creditService = new CreditService(Config.getInstance());
    BankService bs = new BankService(Config.getInstance());

    private Grid<Credit> creditGrid = new Grid<>(Credit.class);

    private Button addBtn;
    private Button editBtn;
    private Button deleteBtn;


    public CreditView() {
        buildView();
        setupListeners();
    }

    private void buildView() {

        Label label = new Label("Кредиты");
        label.setWidth("100%");

        creditGrid.removeAllColumns();
        creditGrid.addColumn(Credit::getName).setCaption("Название кредита");
        creditGrid.addColumn(credit -> bs.findById(credit.getBankID()).getName()).setCaption("Банк кредитор");
        creditGrid.addColumn(Credit::getLimit).setCaption("Лимит по кредиту");
        creditGrid.addColumn(Credit::getInterestRate).setCaption("Процентная ставка");
        creditGrid.setSizeFull();

        HorizontalLayout btnLayout = new HorizontalLayout();
        addBtn = new Button("ADD");
        editBtn = new Button("EDIT");
        deleteBtn = new Button("DELETE");
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);

        btnLayout.addComponents(addBtn, editBtn, deleteBtn);
        setMargin(true);
        setSpacing(true);
        setSizeFull();
        addComponents(label,creditGrid, btnLayout);
        setExpandRatio(creditGrid, 1f);

    }

    private void setupListeners() {
        try {
            creditGrid.addSelectionListener(selectionEvent -> {
                if (!creditGrid.asSingleSelect().isEmpty()) {
                    editBtn.setEnabled(true);
                    deleteBtn.setEnabled(true);
                } else {
                    editBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                }
            });

            addBtn.addClickListener(clickEvent ->
                    getUI().addWindow(new CreditWindow(creditGrid, false)));

            editBtn.addClickListener(clickEvent ->
                    getUI().addWindow(new CreditWindow(creditGrid, true)));

            deleteBtn.addClickListener(clickEvent -> {
                if (!creditGrid.asSingleSelect().isEmpty()) {
                    creditService.deleteOne(creditGrid.asSingleSelect().getValue());
                    updateGrid();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void updateGrid() {
        List<Credit> credits = creditService.getAll();
        creditGrid.setItems(credits);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        updateGrid();
    }

}
