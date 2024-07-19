INSERT INTO organization (name, fizaddres, legaladdres, director_id)
VALUES ('OOO Моя Проблема', 'г. Москва, Улица Ленина, дом 232', 'г. Москва, Улица Пушкина, дом 444',
        NULL),
       ('OOO Быстрое', 'г. Москва, ул. Амиурское', 'г. Уфа, ул. Красноармейская', NULL),
       ('OOO Быстро и метко', 'г. Уфа, ул. Достоевского ', 'г. Уфа, ул. Карла Маркса', NULL);

INSERT INTO subdivision (name, contactdetails, director_id, organization_id)
VALUES ('Реклама', '85005653535', NULL, 1),
       ('Директор', '82045253566', NULL, 1),
       ('Друг', '88005553537', NULL, 1),
       ('Сис Админ', '88005553538', NULL, 2),
       ('Анастасия', '88005553539', NULL, 2),
       ('Пиццерия', '82005553540', NULL, 2),
       ('Руководство', '88005553541', NULL, 3),
       ('Военкомат', '83519201359', NULL, 3),
       ('Иван', '88405253543', NULL, 3);

INSERT INTO employee (surname, name, patronymic, post, subdivision_id)
VALUES ('Аникина', 'Маргарита', 'Романовна', 'Директор', 1),
       ('Бондарев', 'Марк', 'Сергеевич', 'Ряспрямитель подушек', 2),
       ('Виноградов', 'Платон', 'Глебович', 'Полировщик клюшек', 3),
       ('Волков', 'Максим', 'Михайлович', 'Танкист', 4),
       ('Жуков', 'Тимофей', 'Глебович', 'Испытатель мебели', 5),
       ('Марк', 'Роберт', 'Уолберг', 'Таксист', 6),
       ('Райан', 'Родни', 'Рейнольдс', 'Директор', 7),
       ('Киа́ну', 'Чарльз', 'Ривз', 'Гид по туалетам', 8),
       ('Скарлетт', 'Ингрид', 'Йо́ханссон', 'Муравьиный селекционер', 9),
       ('Уильям', 'Брэдли', 'Питт', 'Актер малого театра', 3);

INSERT INTO task (subjectorder, author_id, deadline, singofcontrol, singofexecution, "text", status)
VALUES ('Не баг а фича', 3, '2023-08-18 00:00:00', false, false, 'Добавить фичу в проект', 'PREPARING'),
       ('Делаем', 1, '2023-08-18 00:00:00', false, false, 'Делаем что-то', 'EXECUTION'),
       ('Протестить код', 3, '2023-08-18 00:00:00', false, true, 'Нужны тесты', 'CONTROL'),
       ('Принятие', 5, '2023-08-18 00:00:00', true, true, 'Я в этой жизни преисполнился', 'ACCEPTANCE'),
       ('Написать код', 3, '2023-08-18 00:00:00', false, false, 'Нужно написать в gpt', 'REFINEMENT'),
       ('Сломать проект', 9, '2023-08-18 00:00:00', true, true, 'Я все исправлю', 'ARCHIVE');

INSERT INTO employee_task (employee_id, task_id)
VALUES (3, 1),
       (10, 1),
       (3, 2),
       (10, 3),
       (4, 4),
       (6, 4),
       (1, 5),
       (3, 5),
       (10, 5),
       (8, 6);

UPDATE organization
SET director_id=1 WHERE id = 1;
UPDATE organization
SET director_id=4 WHERE id = 2;
UPDATE organization
SET director_id=7 WHERE id = 3;

UPDATE subdivision
SET director_id=1 WHERE id = 1;
UPDATE subdivision
SET director_id=2 WHERE id = 2;
UPDATE subdivision
SET director_id=3 WHERE id = 3;
UPDATE subdivision
SET director_id=4 WHERE id = 4;
UPDATE subdivision
SET director_id=5 WHERE id = 5;
UPDATE subdivision
SET director_id=6 WHERE id = 6;
UPDATE subdivision
SET director_id=7 WHERE id = 7;
UPDATE subdivision
SET director_id=8 WHERE id = 8;
UPDATE subdivision
SET director_id=9 WHERE id = 9;