INSERT INTO users (id, username, password, enable, name, email, login_attempts) VALUES (0, 'admin', '$2a$10$haGilaSa6rlFTVrK3FPX0OYlON6tNNPUYPU4X0YY.aI8LLul8pGvK', true, 'admin', 'admin@gmail.com', 0);

INSERT INTO role (id, name) VALUES (0, 'ROLE_ADMIN');

INSERT INTO users_roles (user_id, roles_id) values (0, 0);