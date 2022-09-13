
  CREATE TABLE customer(
  	 id serial primary key NOT NULL,
      name varchar(255) not null,
      email varchar(255) not null unique
  );