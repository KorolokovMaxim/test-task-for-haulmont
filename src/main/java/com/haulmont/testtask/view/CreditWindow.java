package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Bank;
import com.haulmont.testtask.entity.Credit;
import com.haulmont.testtask.serivce.BankService;
import com.haulmont.testtask.serivce.CreditService;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreditWindow extends Window {


    private Grid<Credit> grid;
    private boolean edit;
    private Button ok;
    private Button cancel;
    private TextField name;
    private TextField bankId;
    private TextField limit;
    private TextField interestRate;
    private ComboBox<String> banksComboBox;


    BankService bs = new BankService(Config.getInstance());
    private Credit credit;
    private Binder<Credit> binder = new Binder<>(Credit.class);

    public CreditWindow(Grid<Credit> grid, boolean edit) {
        this.grid = grid;
        this.edit = edit;
        buildWindow();
        fillBanksComboBox();
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

        name = new TextField("Название кредита");
        name.setMaxLength(50);
        name.setWidth("100%");
        name.setRequiredIndicatorVisible(true);
        binder.forField(name)
                .withValidator(s -> s != null && !s.isEmpty(), "Введите название кредита")
                .asRequired()
                .bind(Credit::getName, Credit::setName);

        banksComboBox = new ComboBox<String>("Банк кредитор");
        banksComboBox.setTextInputAllowed(false);
        banksComboBox.setPlaceholder("Выберите банк кредитор");
        banksComboBox.setWidth("100%");
        binder.forField(banksComboBox)
                .withValidator(s -> s != null && !s.isEmpty(), "Выберите банк которому принадлежит кредит")
                .asRequired()
                .bind(Credit::getBankID, Credit::setBankID);

        limit = new TextField("Лимит по кредиту");
        limit.setMaxLength(8);
        limit.setWidth("100%");
        limit.setRequiredIndicatorVisible(true);
        binder.forField(limit)
                .withValidator(Objects::nonNull, "Укажите лимит по кредиту")
                .withValidator(new RegexpValidator("Только цифры" , "[0-9]+"))
                .withValidator(s -> !s.equals("0"), "Не может быть 0")
                .asRequired()
                .bind(Credit::getLimit, Credit::setLimit);

        interestRate = new TextField("Процентная ставка");
        interestRate.setMaxLength(4);
        interestRate.setWidth("100%");
        interestRate.setRequiredIndicatorVisible(true);
        binder.forField(interestRate)
                .withValidator(Objects::nonNull, "Укажите процентную ставку")
                .withValidator(s -> !s.equals("0"), "Не может быть 0")
                .asRequired()
                .bind(Credit::getInterestRate, Credit::setInterestRate);

        formLayout.addComponents(name, banksComboBox, limit, interestRate);

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

    private void fillBanksComboBox() {
        try {
            List<String> banksId = new ArrayList<>();
            for (Bank bank : bs.getAll()) {
                banksId.add(bank.getId());
            }
            banksComboBox.setItems(banksId);
            banksComboBox.setItemCaptionGenerator(s -> bs.findById(s).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setupListeners() {
        if (edit) {
            setCaption("Редактирование кредита");
            if (!grid.asSingleSelect().isEmpty()) {
                try {
                    credit = grid.asSingleSelect().getValue();
                    binder.setBean(credit);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            setCaption("Добавление клинта");
            name.focus();
        }

        ok.addClickListener(clickEvent -> {
            if (binder.validate().isOk()) {

                Credit creditToWrite = new Credit();
                creditToWrite.setName(name.getValue());
                creditToWrite.setBankID(banksComboBox.getValue());
                creditToWrite.setLimit(limit.getValue());
                creditToWrite.setInterestRate(interestRate.getValue());
                CreditService creditService = new CreditService(Config.getInstance());
                if (edit) {
                    creditService.updateOne(credit);
                } else {
                    creditService.createOne(creditToWrite);
                }
                List<Credit> credits = creditService.getAll();
                grid.setItems(credits);
                close();
            }
        });
        cancel.addClickListener(clickEvent -> close());
    }
}
