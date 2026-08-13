
-- working with medians
-- SQL does not have a function to find median like it does with avg, sum, mode etc
-- to find median use percentile_* function and pass in the value
-- .5 returns median value
CREATE TABLE percentile_test (
    numbers integer
);

INSERT INTO percentile_test (numbers) VALUES
    (1), (2), (3), (4), (5), (6);

-- median value
-- _cont return float value
select percentile_cont(.5)
within group (order by numbers)
from percentile_test;

-- store values in single row as an object
select percentile_cont(Array[.25,.5,.75])
within group (order by pop_est_2019) as quartiles
from us_counties_pop_est_2019;

-- unnest stores values as rows in table
select unnest(
 percentile_cont(Array[.25,.5,.75])
 within group (order by pop_est_2019)
) as quartiles
from us_counties_pop_est_2019;


-- sample table for testing joins
CREATE TABLE district_2020 (
    id integer CONSTRAINT id_key_2020 PRIMARY KEY,
    school_2020 text
);

CREATE TABLE district_2035 (
    id integer CONSTRAINT id_key_2035 PRIMARY KEY,
    school_2035 text
);

INSERT INTO district_2020 VALUES
    (1, 'Oak Street School'),
    (2, 'Roosevelt High School'),
    (5, 'Dover Middle School'),
    (6, 'Webutuck High School');

INSERT INTO district_2035 VALUES
    (1, 'Oak Street School'),
    (2, 'Roosevelt High School'),
    (3, 'Morrison Elementary'),
    (4, 'Chase Magnet Academy'),
    (6, 'Webutuck High School');

select * from district_2020;

select * from district_2035;

-- only return matching records
select district_2020.id, school_2020, school_2035
from district_2020
join district_2035
on district_2035.id = district_2020.id;

-- only return matching records from left table
-- returns null on no match
select district_2020.id, school_2020, school_2035
from district_2020
left join district_2035
on district_2035.id = district_2020.id;

-- only return matching records from right table
-- returns null on no match
select district_2020.id, school_2020, school_2035
from district_2020
right join district_2035
on district_2035.id = district_2020.id;

-- union 
-- join everything into first table
-- removes duplicates
select * from district_2020
union
select * from district_2035
order by id;

-- intersect
-- returns only matches
select * from district_2020
intersect
select * from district_2035
order by id;

-- union 
-- returns everything even duplicates
select * from district_2020
union all
select * from district_2035
order by id;

-- should be a total of 9
select count(*) as total_d2020_d2030 from (
	select * from district_2020
	union all
	select * from district_2035
);

-- except
-- returns things from first table but not in the second
select * from district_2020
except
select * from district_2035;




























