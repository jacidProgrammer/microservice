INSERT INTO user (id, username, password, enable, name, email) VALUES (0, 'admin', '123', true, 'admin', 'admin@gmail.com');

INSERT INTO role (id, name) VALUES (0, 'ROLE_ADMIN');

INSERT INTO user_roles (user_id, roles_id) values (0, 0);