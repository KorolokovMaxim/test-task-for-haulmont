package com.haulmont.testtask;

import com.haulmont.testtask.view.BankView;
import com.haulmont.testtask.view.ClientView;
import com.haulmont.testtask.view.CreditView;
import com.haulmont.testtask.view.OfferView;
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
        headerLayout.setMargin(true);
        headerLayout.setSpacing(true);

        Button bankButton = new Button("Банки", clickEvent -> getNavigator().navigateTo(BankView.NAME));
        bankButton.setHeight("100%");
        bankButton.addStyleName("borderless");

        Button clientButton = new Button("Клиенты", clickEvent -> getNavigator().navigateTo(ClientView.NAME));
        clientButton.setHeight("100%");
        clientButton.addStyleName("borderless");

        Button creditButton = new Button("Кредиты", clickEvent -> getNavigator().navigateTo(CreditView.NAME));
        creditButton.setHeight("100%");
        creditButton.addStyleName("borderless");

        Button offerButton = new Button("Предложение", clickEvent -> getNavigator().navigateTo(OfferView.NAME));
        offerButton.setHeight("100%");
        offerButton.addStyleName("borderless");

        headerLayout.addComponents(bankButton, clientButton, creditButton, offerButton);

        VerticalLayout viewLayout = new VerticalLayout();
        viewLayout.setSizeFull();
        viewLayout.setMargin(true);
        viewLayout.setSpacing(true);

        layout.addComponents(headerLayout, viewLayout);
        layout.setExpandRatio(viewLayout, 1f);

        ViewDisplay viewDisplay = new Navigator.ComponentContainerViewDisplay(viewLayout);
        Navigator navigator = new Navigator(this, viewDisplay);
        navigator.addView(BankView.NAME, new BankView());
        navigator.addView(ClientView.NAME, new ClientView());
        navigator.addView(CreditView.NAME, new CreditView());
        navigator.addView(OfferView.NAME , new OfferView());

        headerLayout.setStyleName("header-layout");


        setContent(layout);
    }
}