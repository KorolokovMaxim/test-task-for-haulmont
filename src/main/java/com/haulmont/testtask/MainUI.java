package com.haulmont.testtask;

import com.haulmont.testtask.database.DatabaseHelper;
import com.haulmont.testtask.entity.Bank;
import com.haulmont.testtask.serivce.BankService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

       DatabaseHelper.getConnection();



        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        layout.addComponent(new Label("Main UI"));

        setContent(layout);
    }
}