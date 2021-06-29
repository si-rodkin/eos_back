insert into secured_facility(id, name, itn) values
(1, 'name1', 'itn1'),
(2, 'name2', 'itn2');
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

insert into route_bypass(id, name, start_bypass_time, end_bypass_time, day, notify, route_id, marker_reader_id) values
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