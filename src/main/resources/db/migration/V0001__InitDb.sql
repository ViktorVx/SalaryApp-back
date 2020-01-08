DROP TABLE if exists employee cascade;
drop table if exists person cascade;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;
create table employee (id int8 not null, dismissal_date timestamp, employment_date timestamp, person_id int8, primary key (id));
create table person (id int8 not null, birth_date timestamp, first_name varchar(255), gender int4, last_name varchar(255), middle_name varchar(255), primary key (id));
alter table if exists employee add constraint FKfm68kmqett1iydj8xgfb6two8 foreign key (person_id) references person;