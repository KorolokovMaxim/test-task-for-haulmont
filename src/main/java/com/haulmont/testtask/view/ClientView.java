package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;

import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.serivce.BankService;
import com.haulmont.testtask.serivce.ClientService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.List;

public class ClientView extends VerticalLayout implements View {

    public static final String NAME = "clients";

    private Grid<Client> clientGrid = new Grid<>(Client.class);

    ClientService cs = new ClientService(Config.getInstance());
    BankService bs = new BankService(Config.getInstance());

    private Button addBtn;
    private Button editBtn;
    private Button deleteBtn;


    public ClientView(){
        buildView();
        setupListeners();
    }

    private void buildView() {

        Label label = new Label("Клиенты");
        label.setWidth("100%");

        clientGrid.removeAllColumns();
        clientGrid.addColumn(Client::getFIO).setCaption("ФИО");
        clientGrid.addColumn(client -> bs.findById(client.getBankID()).getName()).setCaption("БАНК");
        clientGrid.addColumn(Client::getPhoneNumber).setCaption("Номер телефона");
        clientGrid.addColumn(Client::getEmail).setCaption("E-MAIL");
        clientGrid.addColumn(Client::getPassport).setCaption("№ ПАСПОРТА");
        clientGrid.setSizeFull();

        HorizontalLayout btnLayout = new HorizontalLayout();

        btnLayout.setSpacing(true);

        addBtn = new Button("ADD");
        editBtn = new Button("EDIT");
        deleteBtn = new Button("DELETE");
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);

        btnLayout.addComponents(addBtn, editBtn, deleteBtn);
        setMargin(true);
        setSpacing(true);
        setSizeFull();
        addComponents(label,clientGrid, btnLayout);
        setExpandRatio(clientGrid, 1f);
    }

    private void setupListeners() {
        try {
            clientGrid.addSelectionListener(valueChangerEvent -> {
                if (!clientGrid.asSingleSelect().isEmpty()) {
                    editBtn.setEnabled(true);
                    deleteBtn.setEnabled(true);
                } else {
                    editBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                }

            });

            addBtn.addClickListener(clickEvent ->
                    getUI().addWindow(new ClientWindow(clientGrid, false)));

            editBtn.addClickListener(clickEvent ->
                    getUI().addWindow(new ClientWindow(clientGrid, true)));

            deleteBtn.addClickListener(clickEvent -> {
                if (!clientGrid.asSingleSelect().isEmpty()) {
                    cs.deleteOne(clientGrid.asSingleSelect().getValue());
                    updateGrid();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void updateGrid() {
        List<Client> banks = cs.getAll();
        clientGrid.setItems(banks);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        updateGrid();
    }
}
