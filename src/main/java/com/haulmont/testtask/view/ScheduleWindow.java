package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Offer;
import com.haulmont.testtask.entity.Schedule;
import com.haulmont.testtask.serivce.CreditService;
import com.vaadin.ui.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleWindow extends Window {

    private Grid<Schedule> scheduleGrid = new Grid<>(Schedule.class);
    private Button cancel;
    private Offer offer;
    static CreditService cs = new CreditService(Config.getInstance());


    public ScheduleWindow(Offer offer) {

        this.offer = offer;
        buildWindow();
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

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.setMargin(false);
        horizontalLayout.setSpacing(true);


        Double getCreditPercent = Double.parseDouble(cs.findById(offer.getCreditID()).getInterestRate());
        int index = Integer.parseInt(offer.getCreditMonthValue());
        Double creditSUm = Double.parseDouble(offer.getCreditAmount());
        Double paymentBody = creditSUm / index;
        Double percent = paymentBody * (getCreditPercent / 100);
        Double resultPayment = paymentBody + percent;

        List<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule();

        for (int i = index, month = 0; i > 0; i--, month++) {

            schedule.setDatePayment(LocalDate.now().plusMonths(month));
            schedule.setAmountPayment(resultPayment);
            schedule.setAmountPaymentBody(paymentBody);
            schedule.setAmountPaymentPercent(percent);
            schedules.add(schedule);


        }



        scheduleGrid.addColumn(Schedule::getDatePayment).setCaption("Дата платежа");
        scheduleGrid.addColumn(Schedule::getAmountPayment).setCaption("Сумма платежа");
        scheduleGrid.addColumn(Schedule::getAmountPaymentBody).setCaption("Тело платежа");
        scheduleGrid.addColumn(Schedule::getAmountPaymentPercent).setCaption("Процент платежа");

        scheduleGrid.setItems(schedule);
        HorizontalLayout btnLayout = new HorizontalLayout();
        btnLayout.setSpacing(true);
        cancel = new Button("CANCEL");
        cancel.addClickListener(clickEvent -> close());
        btnLayout.addComponent(cancel);

        horizontalLayout.addComponents(scheduleGrid, btnLayout);

        layout.addComponents(horizontalLayout, btnLayout);
        layout.setExpandRatio(horizontalLayout, 1f);
        layout.setComponentAlignment(btnLayout, Alignment.BOTTOM_CENTER);
        setContent(layout);


    }
}
