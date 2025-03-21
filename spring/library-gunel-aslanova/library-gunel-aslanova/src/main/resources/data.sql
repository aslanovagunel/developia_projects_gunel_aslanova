insert into authority_list
( authority,librarian) values
('ROLE_ADD_BOOK',1),
('ROLE_DELETE_BOOK',1),
('ROLE_GET_BOOK',1),
('ROLE_UPDATE_BOOK',1),
('ROLE_SEARCH_BOOK',1);

insert into users
(username,password,enabled,user_id,user_type) values
('u1','{noop}1',1,1,'librarian'),
('u2','{noop}1',1,2,'librarian');

insert into authorities(username,authority) 
select 'u1',authority from authority_list where librarian=1;

insert into authorities(username,authority) 
select 'u2',authority from authority_list where librarian=1;


insert into librarians
(name,surname,phone,birthday) values
('gunel','aslanova','12345','2012-09-07');

insert into books
(name,description,price,author,color,page_count,quantity,weight,publish_date,librarian_code) values
('Java 21','James yazdi 21','60','Emin, Yusif','red',300,150,3,'2020-10-10',1),
('Css3','James yazdi 21','40','Emin, Yusif','red',233,150,3,'2020-10-10',1),
('python','James yazdi 21','70','Emin, Yusif','red',233,150,3,'2020-10-10',1),
('java','James yazdi 21','68','Emin, Yusif','red',233,150,3,'2020-10-10',1),
('Css3','James yazdi 21','45','Emin, Yusif','red',233,150,3,'2020-10-10',1),
('Html 5','James yazdi 21','60','Emin, Yusif','red',676,150,3,'2020-10-10',2);






