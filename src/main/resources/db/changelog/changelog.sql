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

--changeset rodkinsi:feature/marker_reader_table_creation
--Комментарий: Дабвление таблицы считывателей меток
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

--changeset rodkinsi:feature/secure_facility_table_creation
--Комментарий: Добавление таблицы охраняемых объектов
create table secure_facility
(
    id   bigserial primary key not null,
    name varchar(64)           not null unique,
    itn  varchar(12)           not null unique
);
comment
on table secure_facility is 'Таблица охраняемых (режимных) объектов';
comment
on column secure_facility.name is 'Название охраняемого (режимного) объекта';
comment
on column secure_facility.itn is 'Индивидуальный номер налогоплательщика';
--rollback drop table secure_facility;

--changeset rodkinsi:feature/secure_facility_route_creation
--Комментарий: Добавление таблицы маршрутов охраны
create table secure_facility_route
(
    id        bigserial primary key not null,
    name      varchar(128)          not null unique,
    object_id bigint references secure_facility
);
comment
on table secure_facility_route is 'Таблица маршрутов в рамках охраняемого (режимного) объекта';
comment
on column secure_facility_route.name is 'Название маршрута охраняемого (режимного) объекта';
comment
on column secure_facility_route.object_id is 'Идентификатор объекта на котором расположен маршрут';
--Комментарий: Доваление таблицы линковки устройств и маршрутов охраны
create table marker_reader_to_secure_facility
(
    id        bigserial primary key not null,
    device_id bigint references marker_reader,
    route_id  bigint references secure_facility_route
);
comment
on table marker_reader_to_secure_facility is 'Таблица связи маршрута охраняемого объекта и назначенного на этот маршрут считывателя маркеров';
--rollback drop table marker_reader_to_secure_facility;
--rollback drop table secure_facility_route;

--changeset rodkinsi:feature/marker_table_creation
--Комментарий: Добавление таблицы маркеров
create table marker
(
    id       bigserial primary key not null,
    name     varchar(64),
    rfid     varchar(10) unique,
    route_id bigint references secure_facility_route
);
--rollback drop table marker;

--changeset rodkinsi:feature/check_point_table_creation
--Комментарий: Добавление таблицы контрольных точек
create table check_point
(
    id             bigserial primary key not null,
    name           varchar(64),
    days           integer,
    start_time     timestamp             not null,
    end_time       timestamp,
    time_allowance integer,
    late_time      integer,
    marker_id      bigint references marker,
    device_id      bigint references marker_reader
);
--rollback drop table check_point;

-- --changeset rodkinsi:statistics
-- --Комментарий: Добавление таблицы статистики
-- create table statistics
-- (
--     id        bigserial primary key not null,
--     device_id bigint references devices,
--     marker_id bigint references markers,
--     date      timestamp
-- --         TODO:    round = models.ForeignKey(Round, on_delete=models.CASCADE)
-- );
-- --rollback drop table statistics;
