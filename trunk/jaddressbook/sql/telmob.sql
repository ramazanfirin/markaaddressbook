create table telmob (
        ID int  unsigned  not null primary key auto_increment,
        owner int unsigned not null references person(ID),
        telmob varchar(50),
        comments varchar(200),
        first int unsigned
        );
