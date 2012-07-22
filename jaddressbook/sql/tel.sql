create table tel (
	ID int  unsigned  not null primary key auto_increment,
	owner int unsigned not null references person(ID),
	tel varchar(50),
	comments varchar(200),
	first int unsigned
	);
