insert into authority_list
( authority,librarian,student) values
('ROLE_ADD_BOOK',1,0),
('ROLE_DELETE_BOOK',1,0),
('ROLE_GET_BOOK',1,0),
('ROLE_UPDATE_BOOK',1,0),
('ROLE_SEARCH_BOOK',1,0),
('ROLE_ADD_STUDENT',1,0),
('ROLE_SEARCH_FOR_STUDENT_BOOK',0,1),
('ROLE_DELETE_STUDENT',1,0),
('ROLE_UPDATE_STUDENT',1,0),
('ROLE_FIND_STUDENT',1,0),
('ROLE_LEND_BOOK',1,0),
('ROLE_SHOW_LEND_BOOK',1,0),
('ROLE_RETURN_BOOK',1,0),
('ROLE_SHOW_RETURN_BOOK',1,0),
('ROLE_SHOW_BOOK_FOR_STUDENT',0,1),
('ROLE_STUDENT_BORROW_VIEW',0,1),  
('ROLE_SHOW_SUGGESTION',1,0),
('ROLE_SEARCH_BOOK_FALLBACK',0,1),
('ROLE_SEND_EMAIL',1,0),
('ROLE_SEND_EMAILI',1,0),
('ROLE_REQUEST_BOOK',0,1),
('ROLE_LATE_RETURN_BOOK',1,0);


insert into users
(username,password,enabled,user_id,user_type) values
('u1','{noop}1',1,1,'librarian'),
('u2','{noop}1',1,2,'librarian'),
('s1','{noop}1',1,3,'student'),
('s2','{noop}1',1,4,'student'),
('s3','{noop}1',1,5,'student');

insert into authorities(username,authority) 
select 'u1',authority from authority_list where librarian=1;

insert into authorities(username,authority) 
select 'u2',authority from authority_list where librarian=1;

insert into authorities(username,authority) 
select 's1',authority from authority_list where student=1;

insert into authorities(username,authority) 
select 's2',authority from authority_list where student=1;

insert into authorities(username,authority) 
select 's3',authority from authority_list where student=1;


insert into librarians
(name,surname,phone,birthday,email) values
('gunel','aslanova','12345','2012-09-07','asla@gmail.com'),
('gun','aslanova','12345','2012-09-07','asla@gmail.com');


insert into students
(name,surname,phone,birthday,email,librarian_code) values
('gunel1','aslanova1','12345','2012-09-07','aslanovagunel409@gmail.com',1),
('Vusal','Hüseynova','12345','2012-09-07','aslanovagunel409@gmail.com',2),
('Vusal','Hüseynova','12345','2012-09-07','aslanovagunel409@gmail.com',1),
('Vusal','Hüseynova','12345','2012-09-07','nnecefova6@std.beu.edu.az',2);

insert into books
(name,description,price,author,color,page_count,quantity,weight,publish_date,librarian_code,student_code,category_id) values
('Java 21','James yazdi 21','60','Emin, Yusif','red',300,150,3,'2020-10-10',1,0,1),
('Css3','James yazdi 21','40','Emin, Yusif','red',233,150,3,'2020-10-10',2,1,1),
('python','James yazdi 21','70','Emin, Yusif','red',233,150,3,'2020-10-10',2,1,1),
('java','James yazdi 21','68','Emin, Yusif','red',233,150,3,'2020-10-10',0,0,1),
('Css3','James yazdi 21','45','Emin, Yusif','red',233,150,3,'2020-10-10',1,0,1),
('Html 5','James yazdi 21','60','Emin, Yusif','red',676,150,3,'2020-10-10',2,0,1);

insert into translates
(id,language,word,translate) values
('1','tr','edit','Düzenlendi'),
('2','tr','file','Fayl'),

('3','en','edit','Edit'),
('4','en','file','File');

drop table librarians_book_count;

create view librarians_book_count
as
(select l.id,l.name,count(b.name) as count from librarians l inner join books b
on l.id=b.librarian_code group by l.name);

drop table show_lend_books;

create view show_lend_books as
select l.id,s.id as student_code, s.name as student_name, 
       b.id as book_code, b.name as book_name, 
       l.librarian_code,l.count,l.reg_date,l.return_date,l.must_return_date,
from LEND_BOOKS l 
join students s on l.student_code = s.id 
join books b on l.book_code = b.id;






