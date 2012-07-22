create table addressbook (
        ID int  unsigned  not null primary key auto_increment,
	owner int unsigned not null references addressbook(ID),
        name varchar(200),
        comments varchar(200)
	);
