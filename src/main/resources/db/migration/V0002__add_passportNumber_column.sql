alter  table  if exists person add passport_number varchar(50);
alter  table  person add constraint passport_number_unique_constarint unique (passport_number);