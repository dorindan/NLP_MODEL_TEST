create table books (
    id          serial primary key,
    name        varchar(255),
    author      varchar(255)
);

create table lines (
    id          serial primary key,
    book_id     int references books(id),
    line        varchar(1000)
);

create table wholeBook (
    id          serial primary key,
    name        varchar(255),
    author      varchar(255),
    book        varchar(10485760)
);

create index idx_book_id
on lines(book_id)
