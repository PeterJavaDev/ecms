INSERT INTO sys_user (id, username, password) VALUES (1, 'john','john123');
INSERT INTO sys_user (id, username, password) VALUES (2, 'mark','mark123');
INSERT INTO sys_user (id, username, password) VALUES (3, 'tim','tim123');

INSERT INTO user_role (id, name) VALUES (1, 'operator');
INSERT INTO user_role (id, name) VALUES (2, 'supervisor');

INSERT INTO sys_permission (id, name) VALUES (1, 'list_calls');
INSERT INTO sys_permission (id, name) VALUES (2, 'create_calls');
INSERT INTO sys_permission (id, name) VALUES (3, 'delete_calls');

INSERT INTO sys_user_role (id, sys_user_id, user_role_id) VALUES (1, 1, 1);
INSERT INTO sys_user_role (id, sys_user_id, user_role_id) VALUES (2, 2, 1);
INSERT INTO sys_user_role (id, sys_user_id, user_role_id) VALUES (3, 3, 2);

INSERT INTO user_role_permission (id, user_role_id, sys_permission_id) VALUES (1, 1, 1);
INSERT INTO user_role_permission (id, user_role_id, sys_permission_id) VALUES (2, 1, 2);
INSERT INTO user_role_permission (id, user_role_id, sys_permission_id) VALUES (3, 1, 3);
INSERT INTO user_role_permission (id, user_role_id, sys_permission_id) VALUES (4, 2, 1);

INSERT INTO incident_type (id, name) VALUES (1, 'Natural');
INSERT INTO incident_type (id, name) VALUES (2, 'Medical');
INSERT INTO incident_type (id, name) VALUES (3, 'Criminal');
INSERT INTO incident_type (id, name) VALUES (4, 'Traffic');

INSERT INTO call_status_type (id, name) VALUES (1, 'Created');
INSERT INTO call_status_type (id, name) VALUES (2, 'Processing');
INSERT INTO call_status_type (id, name) VALUES (3, 'Closed');

INSERT INTO call_record (title, message_text, incident_type_id, call_status_type_id) VALUES ('Fire', 'Fire! Fire! Please assist.', 1, 3);
INSERT INTO call_record (title, message_text, incident_type_id, call_status_type_id) VALUES ('Flooding', 'Flooding! Flooding! Please assist.',1, 2);
INSERT INTO call_record (title, message_text, incident_type_id, call_status_type_id) VALUES ('Heart attack', 'Heart attack! Heart attack! Please assist.',2, 2);
INSERT INTO call_record (title, message_text, incident_type_id, call_status_type_id) VALUES ('Earthquake', 'Earthquake! Earthquake! Please assist.', 1, 3);
INSERT INTO call_record (title, message_text, incident_type_id, call_status_type_id) VALUES ('Car Crash', 'Car Crash! Car Crash! Please assist.', 4, 1);
