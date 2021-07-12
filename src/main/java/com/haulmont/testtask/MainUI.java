package com.haulmont.testtask;

import com.haulmont.testtask.view.BankView;
import com.haulmont.testtask.view.ClientView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {


        String title = "Банки";
        getPage().setTitle(title);

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);

        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setHeight("56px");
        headerLayout.setWidth("100%");
        headerLayout.setMargin(false);
        headerLayout.setSpacing(true);

        Button bankButton = new Button("Банки", clickEvent -> getNavigator().navigateTo(BankView.NAME));
        bankButton.setHeight("100%");
        bankButton.addStyleName("borderless");

        Button clientButton = new Button("Клиенты", clickEvent -> getNavigator().navigateTo(ClientView.NAME));
        clientButton.setHeight("100%");
        clientButton.addStyleName("borderless");


        Label header = new Label("БАНКОКОМПАНИЯ");
        header.setWidth(null);

        headerLayout.addComponents(bankButton,clientButton ,  header);
        headerLayout.setComponentAlignment(header, Alignment.MIDDLE_RIGHT);
        headerLayout.setExpandRatio(header, 1f);

        VerticalLayout viewLayout = new VerticalLayout();
        viewLayout.setSizeFull();
        viewLayout.setMargin(false);
        viewLayout.setSpacing(true);

        layout.addComponents(headerLayout, viewLayout);
        layout.setExpandRatio(viewLayout, 1f);

        ViewDisplay viewDisplay = new Navigator.ComponentContainerViewDisplay(viewLayout);
        Navigator navigator = new Navigator(this, viewDisplay);
        navigator.addView(BankView.NAME, new BankView());
        navigator.addView(ClientView.NAME , new ClientView());


        headerLayout.setStyleName("header-layout");


        setContent(layout);
    }
}