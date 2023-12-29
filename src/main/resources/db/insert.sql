insert into users (id, username, email, password) values
(101, 'emmanuel', 'cagobek958@monutri.com', 'password'),
(102, 'ebukizy1', 'ebukizy1@gmail.com', 'ebukizy4u@');

insert into task (id, task_description, user_task_id, created_at, due_date, completed_at) values
(1, 'learn python in 1 weeks', 101, '2023-12-26 13:56:09.028444', '2024-01-05 13:56:09.021443', null),
(2, 'learn java in 2 weeks', 101, '2023-12-26 13:56:09.028444', '2024-01-05 13:56:09.021443',null),
(3, 'learn javascript in 3 weeks', 102, '2023-12-26 13:56:09.028444', '2024-01-05 13:56:09.021443', null),
(4, 'learn Goland in 4 weeks', 102, '2023-12-26 13:56:09.028444', '2024-01-05 13:56:09.021443',null);
