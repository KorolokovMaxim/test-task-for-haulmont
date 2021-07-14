package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.entity.Credit;
import com.haulmont.testtask.entity.Offer;
import com.haulmont.testtask.serivce.ClientService;
import com.haulmont.testtask.serivce.CreditService;
import com.haulmont.testtask.serivce.OfferService;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OfferWindow extends Window {

    private Grid<Offer> grid;
    private boolean edit;
    private Button ok;
    private Button cancel;
    private ComboBox<String> clientsComboBox;
    private ComboBox<String> creditsComboBox;
    private TextField creditAmount;
    private DateField dateOffer;
    private TextField creditForMonth;
    private TextField paymentBody;


    ClientService clientService = new ClientService(Config.getInstance());
    CreditService creditService = new CreditService(Config.getInstance());

    private Offer offer;
    private Binder<Offer> binder = new Binder<>(Offer.class);

    public OfferWindow(Grid<Offer> grid, boolean edit) {
        this.grid = grid;
        this.edit = edit;
        buildWindow();
        fillClientComboBox();
        fillCreditComboBox();
        setupListeners();

    }

    private void buildWindow() {

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

        clientsComboBox = new ComboBox<String>("Клиент");
        clientsComboBox.setTextInputAllowed(false);
        clientsComboBox.setPlaceholder("Выберете клиента");
        clientsComboBox.setWidth("100%");
        binder.forField(clientsComboBox)
                .withValidator(s -> s != null && !s.isEmpty(), "Выберете клиента")
                .asRequired()
                .bind(Offer::getClientID, Offer::setClientID);


        creditsComboBox = new ComboBox<String>("Кредит");
        creditsComboBox.setTextInputAllowed(false);
        creditsComboBox.setPlaceholder("Выберете кредит");
        creditsComboBox.setWidth("100%");
        binder.forField(creditsComboBox)
                .withValidator(s -> s != null && !s.isEmpty(), "Выберете кредит")
                .asRequired()
                .bind(Offer::getCreditID, Offer::setCreditID);

        creditAmount = new TextField("Cумма кредита");
        creditAmount.setMaxLength(8);
        creditAmount.setWidth("100%");
        creditAmount.setRequiredIndicatorVisible(true);
        binder.forField(creditAmount)
                .withValidator(s -> s != null && !s.isEmpty(), "Сумма кредит")
                .withValidator(new RegexpValidator("Только цифры", "[0-9]+"))
                .withValidator(s -> !s.equals("0"), "Не может быть 0")
                .asRequired()
                .bind(Offer::getCreditAmount, Offer::setCreditAmount);

        dateOffer = new DateField("Дата выдачи");
        dateOffer.setDateFormat("dd.MM.yyyy");
        dateOffer.setPlaceholder("Укажите срок выдачи");
        binder.forField(dateOffer)
                .withConverter(new LocalDateToDateConverter(ZoneId.systemDefault()))
                .withValidator(Objects::nonNull, "Укажите дату выдачи")
                .asRequired()
                .bind(Offer::getDate, Offer::setDate);

        creditForMonth = new TextField("Срок кредита");
        creditForMonth.setMaxLength(8);
        creditForMonth.setWidth("100%");
        creditForMonth.setRequiredIndicatorVisible(true);
        binder.forField(creditForMonth)
                .withValidator(s -> s != null && !s.isEmpty(), "Срок выдачи кредита")
                .withValidator(new RegexpValidator("Только цифры", "[0-9]+"))
                .withValidator(s -> !s.equals("0"), "Не может быть 0")
                .asRequired()
                .bind(Offer::getCreditMonthValue, Offer::setCreditMonthValue);

        paymentBody = new TextField("Сумма оплаты в месяц");
        paymentBody.setMaxLength(8);
        paymentBody.setWidth("100%");
        paymentBody.setRequiredIndicatorVisible(true);
        binder.forField(paymentBody)
                .withValidator(s -> s != null && !s.isEmpty(), "Сумма не может быть пустой")
                .withValidator(new RegexpValidator("Только цифры", "[0-9]+"))
                .withValidator(s -> !s.equals("0"), "Не может быть 0")
                .asRequired()
                .bind(Offer::getPaymentBody, Offer::setPaymentBody);


        formLayout.addComponents(clientsComboBox, creditsComboBox, creditAmount, dateOffer, creditForMonth , paymentBody);

        HorizontalLayout btnLayout = new HorizontalLayout();
        btnLayout.setSpacing(true);

        ok = new Button("OK");
        ok.setWidth("150px");
        cancel = new Button("CANCEL");
        cancel.setWidth("150px");
        btnLayout.addComponents(ok, cancel);

        layout.addComponents(formLayout, btnLayout);
        layout.setExpandRatio(formLayout, 1f);
        layout.setComponentAlignment(btnLayout, Alignment.BOTTOM_CENTER);
        setContent(layout);

    }

    private void fillClientComboBox() {
        try {
            List<String> clientId = new ArrayList<>();
            for (Client client : clientService.getAll()) {
                clientId.add(client.getId());
            }
            clientsComboBox.setItems(clientId);
            clientsComboBox.setItemCaptionGenerator(s -> clientService.findById(s).getFIO());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void fillCreditComboBox() {
        try {
            List<String> creditId = new ArrayList<>();
            for (Credit credit : creditService.getAll()) {
                creditId.add(credit.getId());
            }
            creditsComboBox.setItems(creditId);
            creditsComboBox.setItemCaptionGenerator(s -> creditService.findById(s).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setupListeners() {
        if (edit) {
            setCaption("Редактирование предложения");
            if (!grid.asSingleSelect().isEmpty()) {
                try {
                    offer = grid.asSingleSelect().getValue();
                    binder.setBean(offer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            setCaption("Добавление предложения");
            clientsComboBox.focus();
        }

        ok.addClickListener(clickEvent -> {
            if (binder.validate().isOk()) {

                Offer offerToWrite = new Offer();
                offerToWrite.setClientID(clientsComboBox.getValue());
                offerToWrite.setCreditID(creditsComboBox.getValue());
                offerToWrite.setCreditAmount(creditAmount.getValue());
                offerToWrite.setDate(Date.from(dateOffer.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                offerToWrite.setCreditMonthValue(creditForMonth.getValue());
                offerToWrite.setPaymentBody(paymentBody.getValue());
                OfferService offerService = new OfferService(Config.getInstance());
                if (edit) {
                    if (Double.parseDouble(creditAmount.getValue()) > Double.parseDouble(creditService.findById(creditsComboBox.getValue()).getLimit())
                            || Double.parseDouble(creditAmount.getValue()) < Double.parseDouble(paymentBody.getValue())
                    ) {
                        Notification.show("ОШИБКА",
                                "Cумма выдаваемого кредита больше чем лимит по кредиту" +
                                        "или сумма ежемесячного платяжа больше чем сумма кредита",
                                Notification.Type.WARNING_MESSAGE).setDelayMsec(5000);
                    } else {
                        offerService.updateOne(offer);
                    }

                } else {

                    if (Double.parseDouble(creditAmount.getValue()) > Double.parseDouble(creditService.findById(creditsComboBox.getValue()).getLimit())
                    || Double.parseDouble(creditAmount.getValue()) < Double.parseDouble(paymentBody.getValue())
                    ) {
                        Notification.show("ОШИБКА",
                                "Cумма выдаваемого кредита больше чем лимит по кредиту" +
                                        "или сумма ежемесячного платяжа больше чем сумма кредита",
                                Notification.Type.WARNING_MESSAGE).setDelayMsec(5000);
                    } else {
                        offerService.createOne(offerToWrite);
                    }
                }
                List<Offer> offers = offerService.getAll();
                grid.setItems(offers);
                close();
            }
        });
        cancel.addClickListener(clickEvent -> close());
    }


}
