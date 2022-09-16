
  CREATE TABLE customer_roles(
  	 customer_id int NOT NULL,
      roles varchar(50) not null,
          FOREIGN KEY (customer_id) REFERENCES customer(id)

  );