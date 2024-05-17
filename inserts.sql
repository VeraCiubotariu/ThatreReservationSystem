select s.id, s.date, s.name, s.actors, s.duration, s.genre, count(*) as sold_tickets from shows s inner join tickets t on s.id = t.show_id
group by (s.id, s.date, s.name, s.actors, s.duration, s.genre)

select * from tickets
insert into tickets(sale_date, client_first_name, client_last_name, client_email, client_phone_number, show_id) 
values
(NOW(), 'Ana', 'Popa', 'anapopa@yahoo.com', '0867123543', 1),
(NOW(), 'Ana', 'Popa', 'anapopa@yahoo.com', '0867123543', 2),
(NOW(), 'Ana', 'Popa', 'anapopa@yahoo.com', '0867123543', 4),
(NOW(), 'Ana', 'Popa', 'anapopa@yahoo.com', '0867123543', 4),
(NOW(), 'Alin', 'Sarca', 'alinutz@gmail.com', '0897653245', 2),
(NOW(), 'Calin', 'Anton', 'bebecalin@outlook.com', '0765432512', 2)

insert into tickets_seats(ticket_id, seat_id) values
(1, 'A1'), (2, 'C6'), (3, 'B8'), (4, 'D3'), (5, 'B2'), (6, 'E1')

select * from seats
insert into seats(id, price, state) values
('A1', 149.99, 'FREE'),('A2', 149.99, 'FREE'),('A3', 149.99, 'FREE'),
('A4', 149.99, 'FREE'),('A5', 149.99, 'FREE'),('A6', 149.99, 'FREE'),
('B1', 112.5, 'FREE'),('B2', 112.5, 'FREE'),('B3', 112.5, 'FREE'),('B4', 112.5, 'FREE'),
('B5', 112.5, 'FREE'),('B6', 112.5, 'FREE'),('B7', 112.5, 'FREE'),('B8', 112.5, 'FREE'),
('C1', 100, 'FREE'),('C2', 100, 'FREE'),('C3', 100, 'FREE'),('C4', 100, 'FREE'),('C5', 100, 'FREE'),
('C6', 100, 'FREE'),('C7', 100, 'FREE'),('C8', 100, 'FREE'),('C9', 100, 'FREE'),('C10', 100, 'FREE'),
('D1', 70, 'FREE'),('D2', 70, 'FREE'),('D3', 70, 'FREE'),('D4', 70, 'FREE'),
('D5', 70, 'FREE'),('D6', 70, 'FREE'),('D7', 70, 'FREE'),('D8', 70, 'FREE'),
('E1', 50, 'FREE'),('E2', 50, 'FREE'),('E3', 50, 'FREE'),('E4', 50, 'FREE'),('E5', 50, 'FREE'),
('E6', 50, 'FREE'),('E7', 50, 'FREE'),('E8', 50, 'FREE'),('E9', 50, 'FREE'),('E10', 50, 'FREE')
