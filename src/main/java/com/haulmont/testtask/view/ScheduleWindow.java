package com.haulmont.testtask.view;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.entity.Offer;
import com.haulmont.testtask.entity.Schedule;
import com.haulmont.testtask.serivce.CreditService;
import com.vaadin.ui.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class ScheduleWindow extends Window {

    private Button cancel;
    private Offer offer;
    static CreditService cs = new CreditService(Config.getInstance());


    public ScheduleWindow(Offer offer) {

        this.offer = offer;
        buildWindow();
    }


    private void buildWindow() {

        setStyleName("modal-window");
        setWidth("860px");
        setHeight("100%");
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

        String resultPaymentString = String.format("%.2f", resultPayment);
        String paymentBodyString = String.format("%.2f", paymentBody);
        String percentString = String.format("%.2f", percent);


        List<Schedule> schedules = new ArrayList<>();


        for (int i = 1; i < index + 1; i++) {
            Schedule schedule = new Schedule();
            schedule.setDatePayment(datePayment(offer.getDate(), i));
            schedule.setAmountPayment(resultPaymentString);
            schedule.setAmountPaymentBody(paymentBodyString);
            schedule.setAmountPaymentPercent(percentString);
            schedules.add(schedule);

        }

        Grid<Schedule> scheduleGrid = new Grid<>(Schedule.class);

        scheduleGrid.removeAllColumns();
        scheduleGrid.addColumn(Schedule::getDatePayment).setCaption("Дата выплаты");
        scheduleGrid.addColumn(Schedule::getAmountPayment).setCaption("Сумма выплаты");
        scheduleGrid.addColumn(Schedule::getAmountPaymentBody).setCaption("Тело платежа");
        scheduleGrid.addColumn(Schedule::getAmountPaymentPercent).setCaption("Процент платежа");
        scheduleGrid.setItems(schedules);
        scheduleGrid.setSizeFull();

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

    private String datePayment(Date date, int i) {
        Calendar cal = GregorianCalendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date currentMonth = date;
        cal.setTime(currentMonth);

        // current month
        String currentMonthAsSting = df.format(cal.getTime());

        // Add next month
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + i);
        String nextMonthAsString = df.format(cal.getTime());
        return nextMonthAsString;
    }


}
