insert into employee(id, username, password, first_name, last_name, patr_name,
                     personnel_number, "position", phone, timezone,
                     is_lead, lead_id, email)
values (1, 'admin', '$2a$10$sQsn4soAkhPh3ACoDC3GUO4GVw3heYlVepo63/dV9RK5OTvWCf6a2', 'admin', 'admin', null,
        0, 'Админ', '+79008007060', 'Europe/Moscow',
        true, null, 'admin@localhost'),
       (2, 'username1-1', '$2a$10$sQsn4soAkhPh3ACoDC3GUO4GVw3heYlVepo63/dV9RK5OTvWCf6a2', 'fname1-1', 'lname1-1', 'pname1-1',
        111111, 'position1-1', 'phone1-1', 'tz1-1',
        true, 1, 'email1-1'),
       (3, 'username1-2', '$2a$10$sQsn4soAkhPh3ACoDC3GUO4GVw3heYlVepo63/dV9RK5OTvWCf6a2', 'fname1-2', 'lname1-2', 'pname1-2',
        222222, 'position1-2', 'phone1-2', 'tz1-2',
        'false', 1, 'email1-2'),
       (4, 'username2-1', '$2a$10$sQsn4soAkhPh3ACoDC3GUO4GVw3heYlVepo63/dV9RK5OTvWCf6a2', 'fname2-1', 'lname2-1', 'pname2-1',
        333333, 'position2-1', 'phone2-1', 'tz2-1',
        'false', 2, 'email2-1');


insert into secured_facility(id, name, itn, owner_id) values
(1, 'nameAdmin', 'itn1', 1),
(2, 'name2-1', 'itn2', 4),
(3, 'name1-2', 'itn3', 3);
select setval('secured_facility_id_seq', (select max(id) from secured_facility));

insert into route(id, name, secured_facility_id) values
(1, 'name1', 1),
(2, 'name2', 2);
select setval('route_id_seq', (select max(id) from route));

insert into marker_reader(id, name, imei, phone) values
(1, 'name1', 'imei1', 'phone1'),
(2, 'name2', 'imei2', 'phone2');
select setval('marker_reader_id_seq', (select max(id) from marker_reader));

insert into route_marker_reader(id, route_id, marker_reader_id) values
(1, 1, 1),
(2, 2, 2);
select setval('route_marker_reader_id_seq', (select max(id) from route_marker_reader));

insert into route_bypass(id, name, bypass_time, bypass_end_time, day, notify, route_id, marker_reader_id) values
(1, 'name1', '11:11', '22:22', 1, false, 1, 1),
(2, 'name2', '10:10', '11:11', 2, true, 2, 2);
select setval('route_bypass_id_seq', (select max(id) from route_bypass));

insert into marker(id, name, rfid, route_id) values
(1, 'name1', 'rfid1', 2),
(2, 'name2', 'rfid2', 1);
select setval('marker_id_seq', (select max(id) from marker));

insert into check_point(id, name, read_time, allowance_time, late_time, route_bypass_id, marker_id) values
(1, 'name1', '11:11', 11, 11, 1, 1),
(2, 'name2', '22:22', 22, 22, 2, 2);
select setval('check_point_id_seq', (select max(id) from check_point));

insert into statistics(id, date, marker_id, marker_reader_id, check_point_id) values
(1, CURRENT_TIMESTAMP, 1, 1, 2),
(2, CURRENT_TIMESTAMP, 2, 2, 1),
(3, CURRENT_TIMESTAMP, null, 1, 1),
(4, CURRENT_TIMESTAMP, 2, 2, 2);
select setval('statistics_id_seq', (select max(id) from statistics));

insert into subordinate_leader(id, leader_id, subordinate_id) values
(1, 1, 2),
(2, 2, 4),
(3, 1, 3);
