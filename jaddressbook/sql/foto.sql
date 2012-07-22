create table foto (
        ID int  unsigned  not null primary key auto_increment,
        owner int unsigned not null references person(ID),
        foto longblob ,
        comments varchar(200)
        );
