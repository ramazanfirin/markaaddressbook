create table person (
        ID int  unsigned  not null primary key auto_increment,
	addressbook int unsigned not null references addressbook(ID),
	surname varchar(200),
	name varchar(200),
	nickname varchar(200),
	comments longtext
	);
