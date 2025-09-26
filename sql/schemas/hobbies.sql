
-- create database called hobby_match
CREATE DATABASE hobby_match; 

-- create location table
-- multiple people can be tied to one location
CREATE TABLE location(
    id SERIAL PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);

-- create person table
-- one person can have multiple interests
-- one person is tied to one and only one location
CREATE TABLE person(
    id SERIAL PRIMARY KEY, 
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL, 
    age INT, -- age can be optional
    location_id INT REFERENCES location(id) -- foreign key to location
);

-- create interest table
-- one person can have multiple interests
CREATE TABLE interest(
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL UNIQUE -- do not allow duplicate interests
);

-- create junction table person_interest
-- join person with interest in one to many relationship
CREATE TABLE person_interest(
    person_id INT REFERENCES person(id) ON DELETE CASCADE,
    interest_id INT REFERENCES interest(id) ON DELETE CASCADE,
    PRIMARY KEY (person_id, interest_id) -- composite key to ensure uniqueness
);

-- insert data into location table
-- location ids are auto-generated
INSERT INTO location(city, state, country) VALUES
('Nashville', 'Tennessee', 'United States'),
('Memphis', 'Tennessee', 'United States'),
('Phoenix', 'Arizona', 'United States'),
('Denver', 'Colorado', 'United States');

-- insert data into person table 
-- person ids are auto-generated 
INSERT INTO person(firstName, lastName, age, location_id) VALUES
('Chickie', 'Ourtic', 21, 1),
('Hilton', 'O''Hanley', 37, 1), 
('Barbe', 'Purver', 50, 3),
('Reeta', 'Sammons', 34, 2), 
('Abbott', 'Fisbburne', 49, 1), 
('Winnie', 'Whines', 19, 4),
('Samantha', 'Leese', 35, 2),
('Edouard', 'Lorimer', 29, 1),
('Mattheus', 'Shaplin', 27, 3),
('Donnell', 'Corney', 25, 3) ,
('Wallis', 'Kauschke', 28, 3),
('Melva', 'Lanham', 20, 2),
('Amelina', 'McNirlan', 22, 4),
('Courtney', 'Holley', 22, 1),
('Sigismond', 'Vala', 21, 4),
('Jacquelynn', 'Halfacre', 24, 2), 
('Alanna', 'Spino', 25, 3),
('Isa', 'Slight', 32, 1),
('Kakalina', 'Renne', 26, 3);

-- insert data into interest table 
-- interest ids are auto-generated 
INSERT INTO interest(title) VALUES
('Programming'),
('Gaming'),
('Computers'),
('Music'),
('Movies'),
('Cooking'),
('Sports');

-- insert data into person_interest table 
-- this is a junction table between person and interest
INSERT INTO person_interest(person_id, interest_id) VALUES
(1,	1),
(1,	2),
(1,	6),
(2,	1),
(2,	7),
(2,	4),
(3,	1),
(3,	3),
(3,	4),
(4,	1),
(4,	2),
(4,	7),
(5,	6),
(5,	3),
(5,	4),
(6,	2),
(6,	7),
(7,	1),
(7,	3),
(8,	2),
(8,	4),
(9,	5),
(9,	6),
(10, 7),
(10, 5),
(11, 1),
(11, 2),
(11, 5),
(12, 1),
(12, 4),
(12, 5),
(13, 2),
(13, 3),
(13, 7),
(14, 2),
(14, 4),
(14, 6),
(15, 1),
(15, 5),
(15, 7),
(16, 2),
(16, 3),
(16, 4),
(17, 1),
(17, 3),
(17, 5),
(17, 7),
(18, 2),
(18, 4),
(18, 6),
(19, 1),
(19, 2),
(19, 3),
(19, 4),
(19, 5),
(19, 6),
(19, 7);

-- update the age of the following users (Add 1 to their age)
UPDATE person
SET age = age + 1
WHERE (firstName = 'Chickie' AND lastName = 'Ourtic')
   OR (firstName = 'Winnie' AND lastName = 'Whines')
   OR (firstName = 'Edouard' AND lastName = 'Lorimer')
   OR (firstName = 'Courtney' AND lastName = 'Holley')
   OR (firstName = 'Melva' AND lastName = 'Lanham')
   OR (firstName = 'Isa' AND lastName = 'Slight')
   OR (firstName = 'Abbott' AND lastName = 'Fisbburne')
   OR (firstName = 'Reeta' AND lastName = 'Sammons');

-- remove all data for the following people in person table 
DELETE FROM person
WHERE (firstName = 'Hilton' AND lastName = 'O''Hanley')
   OR (firstName = 'Alanna' AND lastName = 'Spino');

-- info about people using the app
-- 1: get all the names (first and last name) of the people
SELECT firstName, lastName FROM person;

-- 2: find all the people who live in Nashville, TN
SELECT person.firstName, person.lastName, location.city, location.state 
FROM person
JOIN location 
ON location.id = person.location_id
WHERE location.city = 'Nashville' AND location.state = 'Tennessee';

-- 3: figure out how many people live in each of our four cities
SELECT location.city, COUNT(person.id) AS count
FROM person
JOIN location 
ON person.location_id = location.id
GROUP BY location.city, location.state, location.country; -- prevent duplication of cities

-- 4: determine how many people are interested in each of the 7 interests
SELECT interest.title, COUNT(person.id) AS count
FROM person
JOIN person_interest 
ON person.id = person_interest.person_id
JOIN interest 
ON person_interest.interest_id = interest.id
GROUP BY interest.title;

-- 5: finds the names (first and last) of all the people who live in Nashville, TN and are interested in programming 
-- return firstName, lastName, city, state, & interest title
SELECT person.firstName, person.lastName, location.city, location.state, interest.title AS interest
FROM person
JOIN location 
ON person.location_id = location.id
JOIN person_interest 
ON person.id = person_interest.person_id
JOIN interest ON interest.id = person_interest.interest_id
WHERE location.city = 'Nashville'
  AND location.state = 'Tennessee'
  AND LOWER(interest.title) = 'programming'; -- case-sensitivity

-- 6: determine how many people there are in each of the following age ranges: 20-30, 30-40, 40-50 
-- return range & count
SELECT
CASE
    WHEN age BETWEEN 20 AND 30 THEN '20-30'
    WHEN age BETWEEN 31 AND 40 THEN '31-40'
    WHEN age BETWEEN 41 AND 50 THEN '41-50'
    ELSE 'Other'
END 
AS range, -- alias for age groups
COUNT(*) AS count -- alias for count per age group
FROM person
GROUP BY range
ORDER BY range;




















