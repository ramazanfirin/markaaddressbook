create table web (
        ID int  unsigned  not null primary key auto_increment,
        owner int unsigned not null references person(ID),
        web varchar(200),
        comments varchar(200),
        first int unsigned
        );
