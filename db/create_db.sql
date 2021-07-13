create table BANK
(
    ID   uuid         not null primary key,
    NAME varchar(255) not null
);

create table CLIENT
(
    ID           uuid         not null primary key,
    FIO          varchar(255) not null,
    BANK_ID      uuid         null,
    constraint FK_CLIENT_BANK foreign key (BANK_ID) references BANK (ID) on delete set null ,
    PHONE_NUMBER varchar(255) not null,
    EMAIL        varchar(255) not null,
    PASSPORT     varchar(255) not null,
);

create table CREDIT
(
    ID            uuid         not null primary key,
    NAME          varchar(255) not null,
    BANK_ID       uuid         not null ,
    LIMIT         varchar(255)       not null,
    INTEREST_RATE varchar(255)       not null,
    constraint FK_CREDIT_BANK foreign key (BANK_ID) references BANK (ID) on delete cascade
);


create table OFFER
(
    ID            uuid   not null primary key,
    CLIENT_ID     uuid   not null,
    CREDIT_ID     uuid   not null,
    CREDIT_AMOUNT varchar(255) not null,
    constraint FK_OFFER_CREDIT foreign key (CREDIT_ID) references CREDIT (ID) on delete cascade ,
    constraint FK_OFFER_CLIENT foreign key (CLIENT_ID) references CLIENT (ID) on delete cascade
);

create table SCHEDULE
(
    ID                     uuid   not null primary key,
    OFFER_ID               uuid   not null,
    DATE_PAYMENT           DATE   not null,
    AMOUNT_PAYMENT         bigint not null,
    AMOUNT_PAYMENT_BODY    bigint not null,
    AMOUNT_PAYMENT_PERCENT bigint not null,
    constraint FK_SCHEDULE_CLIENT foreign key (OFFER_ID) references OFFER (ID) on delete cascade
);
