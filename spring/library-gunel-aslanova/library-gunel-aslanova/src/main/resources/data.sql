insert into users
(username,password,enabled,user_id) values
('u1','{noop}1',1,1),
('u2','{noop}1',1,2),
('u3','{noop}1',1,3);

insert into authorities
(username,authority) values
('u1','ROLE_ADD_BOOK'),
('u1','ROLE_UPDATE_BOOK');

insert into librarians
(name) values
('Satici1');


insert into authority_list
( authority,librarian) values
('ROLE_ADD_BOOK',1),
('ROLE_DELETE_BOOK',1),
('ROLE_GET_BOOK',1),
('ROLE_UPDATE_BOOK',1) ;



