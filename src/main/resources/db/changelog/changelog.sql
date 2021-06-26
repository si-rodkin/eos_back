--liquibase formatted sql

--changeset rodkinsi:feature/user_table_creation
--Комментарий: Добавление таблицы пользователей
create table employee
(
    id               bigserial primary key not null,
    username         varchar(128)          not null unique,
    password         varchar(256)          not null,
    first_name       varchar(128)          not null,
    last_name        varchar(128)          not null,
    patr_name        varchar(128),
    personnel_number bigint                not null unique,
    position         varchar(128),
    phone            text                  not null unique,
    timezone         varchar(64),
    is_lead          boolean               not null,
    lead_id          bigint references employee
);
comment
on table employee is 'Таблица пользователей (сотрудников) системы';
comment
on column employee.personnel_number is 'Табельный номер сотрудника';
comment
on column employee.position is 'Должность сотрудника';
comment
on column employee.is_lead is 'Является ли сотрудник руководителем';
comment
on column employee.lead_id is 'Идентификатор руководителя сотрудника';
--rollback drop table employee;

--changeset rodkinsi:feature/secured_facility_table_creation
--Комментарий: Добавление таблицы охраняемых объектов
create table secured_facility
(
    id   bigserial primary key not null,
    name varchar(64)           not null unique,
    itn  varchar(12)           not null unique
);
comment
    on table secured_facility is 'Таблица охраняемых (режимных) объектов';
comment
    on column secured_facility.name is 'Название охраняемого (режимного) объекта';
comment
    on column secured_facility.itn is 'Индивидуальный номер налогоплательщика';
--rollback drop table secured_facility;

--changeset rodkinsi:feature/route_creation
--Комментарий: Добавление таблицы маршрутов охраны
create table route
(
    id                  bigserial primary key not null,
    name                varchar(128) not null unique,
    secured_facility_id bigint references secured_facility
);
comment
    on table route is 'Таблица маршрутов в рамках охраняемого (режимного) объекта';
comment
    on column route.name is 'Название маршрута охраняемого (режимного) объекта';
comment
    on column route.secured_facility_id is 'Идентификатор объекта на котором расположен маршрут';
--rollback drop table route;

--changeset rodkinsi:feature/marker_reader_table_creation
--Комментарий: Добавление таблицы считывателей меток
create table marker_reader
(
    id    bigserial primary key not null,
    name  varchar(128)          not null unique,
    imei  varchar(17)           not null unique,
    phone text                  not null unique
);
comment
on table marker_reader is 'Таблица считывателей меток';
comment
on column marker_reader.name is 'Название считывающего устройства';
comment
on column marker_reader.imei is 'IMEI сим-карты установленной в считывающее устройство';
comment
on column marker_reader.phone is 'Номер сим-карты установленной в считывающее устройство';
--rollback drop table marker_reader;

--changeset rodkinsi:feature/rout_bypass
create table route_bypass
(
    id                  bigserial primary key not null,
    name                varchar(128) not null unique,
    start_bypass_time   time,
    end_bypass_time   time,
    day                 integer,
    notify              boolean,
    route_id            bigint references route,
    marker_reader_id    bigint references marker_reader
);
comment
    on table route_bypass is 'Таблица обхода выбранных маршрутов';
comment
    on column route_bypass.name is 'Название обхода выбранного маршрута';
comment
    on column route_bypass.start_bypass_time is 'Время начала обхода выбранного маршрута';
comment
    on column route_bypass.end_bypass_time is 'Время начала обхода выбранного маршрута';
comment
    on column route_bypass.notify is 'Оповещение';
comment
    on column route.id is 'Идентификатор объекта на котором расположен маршрут';
--rollback drop table route_bypass;

--changeset rodkinsi:feature/marker_table_creation
--Комментарий: Добавление таблицы маркеров
create table marker
(
    id       bigserial primary key not null,
    name     varchar(64) not null unique,
    rfid     varchar(10) unique,
    route_id bigint references route
);
--rollback drop table marker;

--changeset rodkinsi:feature/check_point_table_creation
--Комментарий: Добавление таблицы контрольных точек
create table check_point
(
    id                  bigserial primary key not null,
    name                varchar(64) not null unique,
    read_time           time not null,
    allowance_time      integer,
    late_time           integer,
    route_bypass_id     bigint references route_bypass,
    marker_id           bigint references marker
);
--rollback drop table check_point;

--Комментарий: Добаление таблицы маршрута охраны и считывающих устройств
create table route_marker_reader
(
    id               bigserial primary key not null,
    route_id         bigint references route,
    marker_reader_id bigint references marker_reader
);
comment
    on table route_marker_reader is 'Таблица связи cчитывателя маркеров и маршрута охраняемого объекта';
--rollback drop table route_marker_reader;

--changeset rodkinsi:statistic
--Комментарий: Добавление таблицы статистики
create table statistics
(
    id               bigserial primary key not null,
    date             timestamp,
    marker_id        bigint references marker,
    marker_reader_id bigint references marker_reader,
    check_point_id   bigint references check_point
);
--rollback drop table statistics;

