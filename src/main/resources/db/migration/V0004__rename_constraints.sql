alter table person drop constraint passport_number_unique_constarint;
alter table person drop constraint email_unique_constarint;

alter  table  person add constraint passport_number_unique_constraint unique (passport_number);
alter  table  person add constraint email_unique_constraint unique (email);