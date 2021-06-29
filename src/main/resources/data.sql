-- INSERT INTO blog (title, description, img, datetime, author) VALUES ( 'Golf', 'Kun Hole in ones', 'img', '26-04-2021', 'Torben Træben');
-- INSERT INTO blog (title, description, img, datetime, author) VALUES ( 'Golf by Night', 'Kun Hole in ones', 'img', '28-04-2021', 'Jens Træben');
-- INSERT INTO blog (title, description, img, datetime, author) VALUES ( 'Golf by Morning', 'Kun Hole in ones', 'img', '26-04-2021', 'Thor Træben');
--
-- -- INSERT INTO blog_page (id, title, description, img, datetime, author) VALUES (1 , 'Golf', 'Kun Hole in ones', 'img', '26-04-2021', 'Torben Træben');
-- INSERT INTO review (author, description, review_image) VALUES ('Daniel', 'Hvorfor sletter du ikke', 'img');
-- INSERT INTO pages (title, description, img, banner) VALUES ('Styrketræning', 'Tobias bænker 982 kg', 'img', 'banner');
-- INSERT INTO pages (title, description, img, banner) VALUES ('Kondi', 'Inteval', 'img', 'banner');
-- INSERT INTO pages (title, description, img, banner) VALUES ('Massage', 'Ømme Muskler', 'img', 'banner');
-- INSERT INTO pages (title, description, img, banner) VALUES ('Kostplan', 'Get Fit Food', 'img', 'banner');
-- INSERT INTO pages (title, description, img, banner) VALUES ('Swim', 'Jeg har ikke lært at svømme', 'img', 'banner');
-- INSERT INTO pages (title, description, img, banner) VALUES ('TRX', 'Jeg har ikke lært at Løfte, er svag som Daniel', 'img', 'banner');
-- INSERT INTO review (author, description, review_image) VALUES ('Daniel', 'Daniel er super god til reviews', 'img');
-- -- INSERT INTO auth (mail, role) VALUES ('daniel.lorenzen@hotmail.com', 'ADMIN')
--
-- INSERT INTO about (description, img, title) VALUES ('desc', 'img', 'title');
--
INSERT INTO users (enabled, mail, password) VALUES (1, 'asd', '123');
INSERT INTO auth (role, user_id) VALUES ('ROLE_ADMIN', 1)

