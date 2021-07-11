package com.haulmont.testtask;

import com.haulmont.testtask.entity.Bank;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.serivce.BankService;
import com.haulmont.testtask.serivce.ClientService;
import com.haulmont.testtask.view.BankView;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.sql.SQLException;



@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {


        BankService bs = new BankService(Config.getInstance());
        ClientService cs = new ClientService(Config.getInstance());
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        try {
            BankView bankView = new BankView(layout);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Client e : cs.getAll()){
            System.out.println(e);
        }

        System.out.println();
        System.out.println();
        System.out.println();


        Client client = new Client();
        client.setFIO("Хрущев Никита Сергеевич");
        client.setBankID(bs.findById("f71040da-dfde-4939-a6d6-b66eb50946c1").getId());
        client.setPhoneNumber("89371458754");
        client.setEmail("chrusch@gmail.com");
        client.setPassport("123332");


        cs.createOne(client);


        System.out.println();
        System.out.println();
        System.out.println();

        for (Client e : cs.getAll()){
            System.out.println(e);
        }
//        layout.addComponent(new Label("Main UI"));

        setContent(layout);
    }
}