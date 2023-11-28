-- V5__insert_data.sql


-- Заполнение таблицы user
INSERT INTO user (id, email, first_name, last_name, password, profile_photo_url, role)
VALUES
    (1, 'user1@example.com', 'John', 'Doe', 'password1', 'https://cdn.pixabay.com/photo/2017/01/16/19/54/ireland-1985088_1280.jpg', 'user'),
    (2, 'user2@example.com', 'Jane', 'Smith', 'password2', 'https://cdn.pixabay.com/photo/2015/04/10/01/41/fox-715588_1280.jpg', 'user'),
    (3, 'user3@example.com', 'Michael', 'Johnson', 'password3', 'https://cdn.pixabay.com/photo/2017/11/09/21/41/cat-2934720_1280.jpg', 'user'),
    (4, 'user4@example.com', 'Emily', 'Brown', 'password4', 'https://cdn.pixabay.com/photo/2017/07/25/01/22/cat-2536662_1280.jpg', 'user'),
    (5, 'user5@example.com', 'William', 'Jones', 'password5', 'https://cdn.pixabay.com/photo/2017/11/09/21/41/cat-2934720_1280.jpg', 'user'),
    (6, 'user6@example.com', 'Olivia', 'Miller', 'password6', 'https://cdn.pixabay.com/photo/2017/10/25/16/54/african-lion-2888519_1280.jpg', 'user'),
    (7, 'user7@example.com', 'James', 'Davis', 'password7', 'https://cdn.pixabay.com/photo/2017/02/07/16/47/kingfisher-2046453_1280.jpg', 'user'),
    (8, 'user8@example.com', 'Sophia', 'Wilson', 'password8', 'https://cdn.pixabay.com/photo/2012/06/19/10/32/owl-50267_1280.jpg', 'user'),
    (9, 'user9@example.com', 'Benjamin', 'Taylor', 'password9', 'https://cdn.pixabay.com/photo/2017/08/30/12/45/girl-2696947_1280.jpg', 'user'),
    (10, 'user10@example.com', 'Ava', 'Anderson', 'password10', 'https://cdn.pixabay.com/photo/2015/11/16/14/43/cat-1045782_1280.jpg', 'user');
-- Заполнение таблицы property
INSERT INTO property (id, address, date, description, link_photo, price, title, type, user_id, deleted)
VALUES
    (1, '123 Main St', '2023-01-01 10:00:00', 'Beautiful house', 'https://static.realt.by/user/59/9/newb64e48959/a623b6f53f.jpg', 100000, 'House 1', 'Residential', 1, 0),
    (2, '456 Elm St', '2023-02-01 11:00:00', 'Spacious apartment', 'https://static.realt.by/user/59/9/newb64e48959/75e879cb07.jpg', 80000, 'Apartment 1', 'Residential', 1, 0),
    (3, '789 Oak St', '2023-03-01 12:00:00', 'Cozy cottage', 'https://static.realt.by/user/59/9/newb64e48959/388e59d32f.jpg', 60000, 'Cottage 1', 'Residential', 2, 0),
    (4, '567 Pine St', '2023-04-01 13:00:00', 'Modern townhouse', 'https://static.realt.by/user/59/9/newb64e48959/89d07ba5c3.jpg', 120000, 'Townhouse 1', 'Residential', 2, 0),
    (5, '890 Maple St', '2023-05-01 14:00:00', 'Luxurious mansion', 'https://static.realt.by/user/fb/5/newb5534e5fb/c9c2283295.jpg', 500000, 'Mansion 1', 'Residential', 3, 0),
    (6, '234 Cedar St', '2023-06-01 15:00:00', 'Charming bungalow', 'https://static.realt.by/user/fb/5/newb5534e5fb/5cdb097e9a.jpg', 70000, 'Bungalow 1', 'Residential', 3, 0),
    (7, '901 Walnut St', '2023-07-01 16:00:00', 'Cozy apartment', 'https://static.realt.by/user/fb/5/newb5534e5fb/a070e82282.jpg', 60000, 'Apartment 2', 'Residential', 4, 0),
    (8, '345 Oak St', '2023-08-01 17:00:00', 'Stunning penthouse', 'https://static.realt.by/user/fb/5/newb5534e5fb/ecb5d346e6.jpg', 300000, 'Penthouse 1', 'Residential', 4, 0),
    (9, '678 Elm St', '2023-09-01 18:00:00', 'Rustic cabin', 'https://static.realt.by/user/fb/5/newb5534e5fb/74a5faa8f0.jpg', 40000, 'Cabin 1', 'Residential', 5, 0),
    (10, '1234 Pine St', '2023-10-01 19:00:00', 'Modern loft', 'https://static.realt.by/user/fb/5/newb5534e5fb/8c5ef572d7.jpg', 150000, 'Loft 1', 'Residential', 5, 0),
    (11, '5678 Maple St', '2023-11-01 20:00:00', 'Spacious house', 'https://static.realt.by/user/fb/5/newb5534e5fb/cf4cd02003.jpg', 200000, 'House 2', 'Residential', 6, 0),
    (12, '9012 Cedar St', '2023-12-01 21:00:00', 'Quaint cottage', 'https://static.realt.by/user/fb/5/newb5534e5fb/1b42c0f7c3.jpg', 80000, 'Cottage 2', 'Residential', 6, 0),
    (13, '3456 Walnut St', '2024-01-01 22:00:00', 'Elegant mansion', 'https://static.realt.by/user/fb/5/newb5534e5fb/a0ddc1780d.jpg', 600000, 'Mansion 2', 'Residential', 7, 0),
    (14, '7890 Oak St', '2024-02-01 23:00:00', 'Cozy bungalow', 'https://static.realt.by/user/fb/5/newb5534e5fb/0cceba107f.jpg', 90000, 'Bungalow 2', 'Residential', 7, 0),
    (15, '123 Main St', '2024-03-01 00:00:00', 'Modern townhouse', 'https://static.realt.by/user/fb/5/newb5534e5fb/1cd417c994.jpg', 180000, 'Townhouse 2', 'Residential', 8, 0),
    (16, '456 Elm St', '2024-04-01 01:00:00', 'Luxurious apartment', 'https://static.realt.by/user/fb/5/newb5534e5fb/7a0809d763.jpg', 120000, 'Apartment 3', 'Residential', 8, 0),
    (17, '789 Oak St', '2024-05-01 02:00:00', 'Charming cottage', 'https://static.realt.by/user/fb/5/newb5534e5fb/760837e84d.jpg', 70000, 'Cottage 3', 'Residential', 9, 0),
    (18, '567 Pine St', '2024-06-01 03:00:00', 'Stunning penthouse', 'https://static.realt.by/user/fb/5/newb5534e5fb/3b91490b28.jpg', 300000, 'Penthouse 2', 'Residential', 9, 0),
    (19, '890 Maple St', '2024-07-01 04:00:00', 'Spacious villa', 'https://static.realt.by/user/fb/5/newb5534e5fb/f3ee08600a.jpg', 400000, 'Villa 1', 'Residential', 10, 0),
    (20, '234 Cedar St', '2024-08-01 05:00:00', 'Cozy cabin', 'https://static.realt.by/user/fb/5/newb5534e5fb/4c08435273.jpg', 60000, 'Cabin 2', 'Residential', 10, 0);
