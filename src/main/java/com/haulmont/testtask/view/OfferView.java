package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Offer;
import com.haulmont.testtask.entity.Schedule;
import com.haulmont.testtask.serivce.ClientService;
import com.haulmont.testtask.serivce.CreditService;
import com.haulmont.testtask.serivce.OfferService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.List;

public class OfferView extends VerticalLayout implements View {


    public static final String NAME = "offers";

    CreditService creditService = new CreditService(Config.getInstance());
    ClientService clientService = new ClientService(Config.getInstance());
    OfferService offerService = new OfferService(Config.getInstance());

    private Grid<Offer> offerGrid = new Grid<>(Offer.class);
    private Grid<Schedule> scheduleGrid = new Grid<>(Schedule.class);

    private Button addBtn;
    private Button editBtn;
    private Button deleteBtn;
    private Button scheduleBtn;


    public OfferView() {
        buildView();
        setupListeners();
    }

    private void buildView() {

        Label label = new Label("Кредитные предложения");
        label.setWidth("100%");

        offerGrid.removeAllColumns();
        offerGrid.addColumn(offer -> clientService.findById(offer.getClientID()).getFIO()).setCaption("Ф.И.О Клиента");
        offerGrid.addColumn(offer -> creditService.findById(offer.getCreditID()).getName()).setCaption("Название кредита");
        offerGrid.addColumn(Offer::getCreditAmount).setCaption("Сумма кредита");
        offerGrid.addColumn(Offer::getDate).setCaption("Дата взятие кредита");
        offerGrid.addColumn(Offer::getCreditMonthValue).setCaption("Срок кредита (в месяцах)");
        offerGrid.setSizeFull();

        HorizontalLayout btnLayout = new HorizontalLayout();
        addBtn = new Button("ADD");
        editBtn = new Button("EDIT");
        deleteBtn = new Button("DELETE");
        scheduleBtn = new Button("Посмотреть график платежей");
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);


        btnLayout.addComponents(addBtn, editBtn, deleteBtn ,scheduleBtn);
        setMargin(true);
        setSpacing(true);
        setSizeFull();
        addComponents(label,offerGrid, btnLayout);
        setExpandRatio(offerGrid, 1f);

    }

    private void setupListeners() {
        try {
            offerGrid.addSelectionListener(selectionEvent -> {
                if (!offerGrid.asSingleSelect().isEmpty()) {
                    editBtn.setEnabled(true);
                    deleteBtn.setEnabled(true);
                } else {
                    editBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                }
            });

            addBtn.addClickListener(clickEvent ->
                    getUI().addWindow(new OfferWindow(offerGrid, false)));

            editBtn.addClickListener(clickEvent ->
                    getUI().addWindow(new OfferWindow(offerGrid, true)));

            scheduleBtn.addClickListener(clickEvent -> {
                if (!offerGrid.asSingleSelect().isEmpty()) {
                    getUI().addWindow(new ScheduleWindow(offerGrid.asSingleSelect().getValue()));
                }
            });


            deleteBtn.addClickListener(clickEvent -> {
                if (!offerGrid.asSingleSelect().isEmpty()) {
                    offerService.deleteOne(offerGrid.asSingleSelect().getValue());
                    updateGrid();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }




    }

    private void updateGrid() {
        List<Offer> offers = offerService.getAll();
        offerGrid.setItems(offers);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        updateGrid();
    }

}
