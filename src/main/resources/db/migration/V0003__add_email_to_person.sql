alter  table  if exists person add email varchar(150);
alter  table  person add constraint email_unique_constarint unique (email);