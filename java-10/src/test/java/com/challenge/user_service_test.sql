delete
from submission;
delete
from candidate;
delete
from acceleration;
delete
from challenge;
delete
from company;
delete
from users;

insert into users (id, full_name, email, nickname, password, created_at)
values (6001, 'Bruno', 'bruno@gmail.com', 'nick_bruno', '123456', now());

insert into users (id, full_name, email, nickname, password, created_at)
values (6002, 'Fernando', 'fernando@email.com', 'nick_fernando', '123456', now());

insert into users (id, full_name, email, nickname, password, created_at)
values (6003, 'Marcela', 'marcela@email.com', 'nick_marcela', '123456', now());

insert into users (id, full_name, email, nickname, password, created_at)
values (6004, 'Geovanna', 'geovanna@email.com', 'nick_geovanna', '123456', now());

insert into users (id, full_name, email, nickname, password, created_at)
values (6005, 'Alisson', 'alisson@email.com', 'nick_alisson', '123456', now());

insert into company (id, created_at, name, slug)
VALUES (5001, now(), 'Blue Company', 'Blue Slug');

insert into company (id, created_at, name, slug)
VALUES (5002, now(), 'Green Company', 'Green Slug');

insert into challenge (id, created_at, name, slug)
values (1001, now(), 'Challenge A', 'The A Slug');

insert into challenge (id, created_at, name, slug)
values (1002, now(), 'Challenge B', 'The B Slug');

insert into challenge (id, created_at, name, slug)
values (1003, now(), 'Challenge C', 'The C Slug');

insert into acceleration (id, created_at, name, slug, challenge_id)
values (2001, now(), 'Metallica Acceleration', 'Black Album', 1001);

insert into acceleration (id, created_at, name, slug, challenge_id)
values (2002, now(), 'Slipknot Acceleration', 'IOWA', 1003);

insert into candidate (created_at, status, user_id, company_id, acceleration_id)
values (now(), 1, 6001, 5001, 2001);

insert into candidate (created_at, status, user_id, company_id, acceleration_id)
values (now(), 1, 6002, 5001, 2001);

insert into candidate (created_at, status, user_id, company_id, acceleration_id)
values (now(), 1, 6003, 5001, 2001);

insert into candidate (created_at, status, user_id, company_id, acceleration_id)
values (now(), 1, 6004, 5002, 2002);

insert into candidate (created_at, status, user_id, company_id, acceleration_id)
values (now(), 1, 6005, 5002, 2002);

insert into candidate (created_at, status, user_id, company_id, acceleration_id)
values (now(), 1, 6003, 5002, 2002);

insert into submission (created_at, score, user_id, challenge_id)
values (now(), 99, 6001, 1003);

insert into submission (created_at, score, user_id, challenge_id)
values (now(), 67, 6002, 1003);

insert into submission (created_at, score, user_id, challenge_id)
values (now(), 98, 6003, 1003);

insert into submission (created_at, score, user_id, challenge_id)
values (now(), 67, 6004, 1002);