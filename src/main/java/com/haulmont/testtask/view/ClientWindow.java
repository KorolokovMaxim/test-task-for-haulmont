package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Bank;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.serivce.BankService;
import com.haulmont.testtask.serivce.ClientService;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class ClientWindow extends Window {

    private Grid<Client> grid;
    private boolean edit;
    private Button ok;
    private Button cancel;
    private TextField fio;
    private TextField bankID;
    private TextField phoneNumber;
    private TextField email;
    private TextField passport;
    private ComboBox<String> bankComboBox;

    BankService bs = new BankService(Config.getInstance());
    private Client client;

    private Binder<Client> binder = new Binder<>(Client.class);

    public ClientWindow(Grid<Client> grid , boolean edit){
        this.grid = grid;
        this.edit = edit;
        buildWindow();
        fillBanksComboBox();
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

        fio = new TextField("ФИО");
        fio.setMaxLength(50);
        fio.setWidth("100%");
        fio.setRequiredIndicatorVisible(true);
        binder.forField(fio)
                .withValidator(s -> s != null && !s.isEmpty(), "Введите Ваши Ф.И.О" )
                .asRequired()
                .bind(Client::getFIO , Client::setFIO);

        bankComboBox = new ComboBox<String>("Банки");
        bankComboBox.setTextInputAllowed(false);
        bankComboBox.setPlaceholder("Выберите банк");
        bankComboBox.setWidth("100%");
        binder.forField(bankComboBox)
                .asRequired()
                .bind(Client::getBankID, Client::setBankID);

        phoneNumber = new TextField("Номер телефона");
        phoneNumber.setMaxLength(12);
        phoneNumber.setWidth("100%");
        phoneNumber.setRequiredIndicatorVisible(true);
        binder.forField(phoneNumber)
                .withValidator(s -> s != null && !s.isEmpty() , "Введите номер" )
                .withValidator(new RegexpValidator("Номер не корректен.", "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$"))
                .asRequired()
                .bind(Client::getPhoneNumber , Client::setPhoneNumber);

        email = new TextField("Email");
        email.setMaxLength(50);
        email.setWidth("100%");
        email.setRequiredIndicatorVisible(true);
        binder.forField(email)
                .withValidator(s -> s != null && !s.isEmpty(), "Введите email" )
//                .withValidator(new RegexpValidator("Email не корректне" ,"^^[A-Za-z0-9+_.-]+@(.+)$" ))
                .asRequired()
                .bind(Client::getEmail , Client::setEmail);

        passport = new TextField("Номер паспорта");
        passport.setMaxLength(50);
        passport.setWidth("100%");
        passport.setRequiredIndicatorVisible(true);
        binder.forField(passport)
                .withValidator(s -> s != null && !s.isEmpty(), "Введите номер паспорта" )
                .asRequired()
                .bind(Client::getPassport , Client::setPassport);


        formLayout.addComponents(fio, bankComboBox,phoneNumber,email,passport);

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

    private void fillBanksComboBox(){
        try {
            List<String > banksId = new ArrayList<>();
            for (Bank bank : bs.getAll()){
                banksId.add(bank.getId());
            }

            bankComboBox.setItems(banksId);
            bankComboBox.setItemCaptionGenerator(s -> bs.findById(s).getName());



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setupListeners() {
        if (edit) {
            setCaption("Редактирование клинта");
            if (!grid.asSingleSelect().isEmpty()) {
                try {
                    client = grid.asSingleSelect().getValue();
                    binder.setBean(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            setCaption("Добавление клинта");
            fio.focus();
        }

        ok.addClickListener(clickEvent -> {
            if (binder.validate().isOk()) {

                        Client clientToWrite = new Client();
                        clientToWrite.setFIO(fio.getValue());
                        clientToWrite.setBankID(bankComboBox.getValue());
                        clientToWrite.setPhoneNumber(phoneNumber.getValue());
                        clientToWrite.setEmail(email.getValue());
                        clientToWrite.setPassport(passport.getValue());
                        ClientService cs = new ClientService(Config.getInstance());
                        if (edit) {
                            cs.updateOne(client);
                        } else {
                            cs.createOne(clientToWrite);
                        }
                        List<Client> clients = cs.getAll();
                        grid.setItems(clients);
                    close();
                }
        });
        cancel.addClickListener(clickEvent -> close());
    }

}
