CREATE TABLE sys_user
(
    id INTEGER PRIMARY KEY,
    username VARCHAR(20),
    password  VARCHAR(20)
);


-- user_role --------------------
CREATE TABLE user_role
(
    id INTEGER PRIMARY KEY,
    name VARCHAR(20)
);


-- sys_permission --------------------
CREATE TABLE sys_permission
(
    id INTEGER PRIMARY KEY,
    name VARCHAR(255)
);


-- sys_user_role --------------------
CREATE TABLE sys_user_role
(
    id INTEGER PRIMARY KEY,
    sys_user_id INTEGER,
    user_role_id INTEGER,
    CONSTRAINT fk_user_role_id FOREIGN KEY (user_role_id) REFERENCES user_role (id) ON DELETE CASCADE,
    CONSTRAINT fk_sys_user_id FOREIGN KEY (sys_user_id) REFERENCES sys_user (id) ON DELETE CASCADE
);


-- user_role_permission --------------------
CREATE TABLE user_role_permission
(
    id INTEGER PRIMARY KEY,
    user_role_id INTEGER,
    sys_permission_id INTEGER,
    CONSTRAINT fk_user_role_id FOREIGN KEY (user_role_id) REFERENCES user_role (id) ON DELETE CASCADE,
    CONSTRAINT fk_sys_permission_id FOREIGN KEY (sys_permission_id) REFERENCES sys_permission (id) ON DELETE CASCADE
);


-- incident_type --------------------
CREATE TABLE incident_type
(
    id INTEGER PRIMARY KEY,
    name VARCHAR(255)
);


-- call_status_type --------------------
CREATE TABLE call_status_type
(
    id INTEGER PRIMARY KEY,
    name VARCHAR(255)
);


-- call_record --------------------
CREATE TABLE call_record
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    message_text TEXT,
    create_time TIMESTAMP DEFAULT now(),
    incident_type_id INTEGER,
    call_status_type_id INTEGER,
    CONSTRAINT fk_incident_type_id FOREIGN KEY (incident_type_id) REFERENCES incident_type (id) ON DELETE CASCADE,
    CONSTRAINT fk_call_status_type_id FOREIGN KEY (call_status_type_id) REFERENCES call_status_type (id) ON DELETE CASCADE
);
