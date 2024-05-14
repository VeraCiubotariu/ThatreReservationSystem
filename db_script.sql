create table administrators (
	id int generated always as identity,
	primary key(id),
	username varchar(20),
	password varchar(20),
	first_name varchar(20),
	last_name varchar(20)
)

create table shows (
	id int generated always as identity,
	primary key(id),
	date timestamp,
	name varchar(50),
	actors varchar(500),
	genre varchar(20),
	description varchar(500),
	duration int,
	director varchar(50),
	poster oid,
	admin_id int,
	constraint fk_admin foreign key(admin_id) references administrators(id)
)

create table seats (
	id int generated always as identity,
	primary key(id),
	row varchar(1) not null,
	number int not null,
	unique(row, number),
	price float,
	state varchar(20)
)

create table tickets (
	id int generated always as identity,
	primary key(id),
	sale_date timestamp,
	client_first_name varchar(20),
	client_last_name varchar(20),
	client_email varchar(100),
	client_phone_number varchar(10),
	show_id int,
	constraint fk_show foreign key(show_id) references shows(id) 
)

create table tickets_seats (
	ticket_id int,
	seat_id int,
	constraint fk_ticket foreign key(ticket_id) references tickets(id),
	constraint fk_seat foreign key(seat_id) references seats(id),
	primary key(ticket_id, seat_id)
)

