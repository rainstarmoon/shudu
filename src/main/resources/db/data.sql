DELETE FROM form_header;

INSERT INTO form_header (sort_No, desc) VALUES (1, '报账');
INSERT INTO form_header (sort_No, desc) VALUES (2, '喝水');
INSERT INTO form_header (sort_No, desc) VALUES (3, '运动');
INSERT INTO form_header (sort_No, desc) VALUES (4, '不吃零食');
INSERT INTO form_header (sort_No, desc) VALUES (5, '十点睡觉');
INSERT INTO form_header (sort_No, desc) VALUES (6, '读书');

DELETE FROM form_body;

INSERT INTO form_body (clock_date, form_header_id, clock) VALUES (20230510, 1, true);
INSERT INTO form_body (clock_date, form_header_id, clock) VALUES (20230510, 2, false);