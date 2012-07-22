create table fax (
        ID int  unsigned  not null primary key auto_increment,
        owner int unsigned not null references person(ID),
        fax varchar(50),
        comments varchar(200),
        first int unsigned
        );
