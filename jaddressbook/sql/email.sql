create table email (
        ID int  unsigned  not null primary key auto_increment,
        owner int unsigned not null references person(ID),
        email varchar(50),
        comments varchar(200),
        first int unsigned
        );
