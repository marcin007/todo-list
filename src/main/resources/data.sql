INSERT INTO users(first_name, last_name, user_name, password, avatar_url, is_expired, is_enabled, is_locked,
                  is_credentials_expired, has_expired)
values ('Maryla', 'Rodowicz', 'm', '$2a$10$ffNADwF.yPvjUdXkjB4S7eAl5f1/EZXFDeSttqPYIF5cay3ReNSWi', 'www.asd.pl', false, true, false, false, false);

INSERT INTO users(first_name, last_name, user_name, password, avatar_url, is_expired, is_enabled, is_locked,
                  is_credentials_expired, has_expired)
values ('Zenek', 'Martyniuk', 'z', '$2a$10$ffNADwF.yPvjUdXkjB4S7eAl5f1/EZXFDeSttqPYIF5cay3ReNSWi', 'www.haha.pl', false, true, false, false, false);
INSERT INTO users(first_name, last_name, user_name, password, avatar_url, is_expired, is_enabled, is_locked,
                  is_credentials_expired, has_expired)
values ('DistabledName', 'DistabledLastName', 'disabled', '$2a$10$ffNADwF.yPvjUdXkjB4S7eAl5f1/EZXFDeSttqPYIF5cay3ReNSWi', 'www.dis.pl', true, false, true, true, true);


INSERT INTO roles(name, has_expired)
values ('ROLE_ADMIN', false),
       ('ROLE_USER', false);

INSERT INTO user_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO tasks_boards(user_id, name, colour, has_expired)
VALUES (1, 'TEKSTY MARYLI', '#45246e', false);
INSERT INTO tasks_boards(user_id, name, colour, has_expired)
VALUES (1, 'TEKSTY MARYLI TYMCZASOWE', '#236534', false);
INSERT INTO tasks_boards(user_id, name, colour, has_expired)
VALUES (2, 'TRASA ZENKA', '#ab3454', false);
INSERT INTO tasks_boards(user_id, name, colour, has_expired)
VALUES (1, 'WSPOLNY NOTES', '#543463', false);

INSERT INTO board_users(user_id, tasks_board_id)
VALUES (1, 1);
INSERT INTO board_users(user_id, tasks_board_id)
VALUES (1, 2);
INSERT INTO board_users(user_id, tasks_board_id)
VALUES (2, 3);
INSERT INTO board_users(user_id, tasks_board_id)
VALUES (1, 4);
INSERT INTO board_users(user_id, tasks_board_id)
VALUES (2, 4);

INSERT INTO priorities(name, has_expired)
values ('REGULAR', false),
       ('STAR', false);

INSERT INTO tasks(tasks_board_id, priority_id, name, description, status,owner_id, has_expired)
VALUES (3, 1, 'Zostanie krolem DiscoPolo', 'Podlasie pierwsze.', 'DONE', 2, false);
INSERT INTO tasks(tasks_board_id, priority_id, name, description, status,owner_id, has_expired)
VALUES (3, 2, 'Nagrac plyte zenka', '1000 sztuk', 'TODO', 2, false);
INSERT INTO tasks(tasks_board_id, priority_id, name, description, status,owner_id, has_expired)
VALUES (1, 1, 'Nagrac teledysk z Maryla', 'w lesie', 'DONE',1, false);
INSERT INTO tasks(tasks_board_id, priority_id, name, description, status,owner_id, has_expired)
VALUES (2, 2, 'Wpolne zadanie', 'cos razem', 'DONE',1, false);

INSERT INTO task_users(user_id, task_id)
VALUES (2, 1),
       (2, 2),
       (1, 3),
       (1, 4);

