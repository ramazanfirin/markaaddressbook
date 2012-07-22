create table addressbook (
        ID int  unsigned  not null primary key auto_increment,
	owner int unsigned not null references addressbook(ID),
        name varchar(200),
        comments varchar(200)
	);
create table address (
	ID int  unsigned  not null primary key auto_increment,
	owner int unsigned not null references person(ID),
	address varchar(200),
	comments varchar(200),
	first int unsigned
	);
create table email (
        ID int  unsigned  not null primary key auto_increment,
        owner int unsigned not null references person(ID),
        email varchar(50),
        comments varchar(200),
        first int unsigned
        );
create table fax (
        ID int  unsigned  not null primary key auto_increment,
        owner int unsigned not null references person(ID),
        fax varchar(50),
        comments varchar(200),
        first int unsigned
        );
create table foto (
        ID int  unsigned  not null primary key auto_increment,
        owner int unsigned not null references person(ID),
        foto longblob ,
        comments varchar(200)
        );
create table person (
        ID int  unsigned  not null primary key auto_increment,
	addressbook int unsigned not null references addressbook(ID),
	surname varchar(200),
	name varchar(200),
	nickname varchar(200),
	comments longtext
	);
create table telmob (
        ID int  unsigned  not null primary key auto_increment,
        owner int unsigned not null references person(ID),
        telmob varchar(50),
        comments varchar(200),
        first int unsigned
        );
create table tel (
	ID int  unsigned  not null primary key auto_increment,
	owner int unsigned not null references person(ID),
	tel varchar(50),
	comments varchar(200),
	first int unsigned
	);
create table web (
        ID int  unsigned  not null primary key auto_increment,
        owner int unsigned not null references person(ID),
        web varchar(200),
        comments varchar(200),
        first int unsigned
        );
