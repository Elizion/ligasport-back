INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN');

SELECT 
	c.id AS customer_id,
	l.id AS league_id,
	s.id AS schedule_id,
	j.id AS journey_id,
	j.NAME AS journey_name,
	g.teama,
	g.teamb,
	g.HOUR,
	t1.NAME,
	t2.name
FROM
   customers c
INNER JOIN leagues l 
    ON c.id = l.customer_id
INNER JOIN schedules s
    ON s.league_id = l.id
INNER JOIN journeys j
    ON j.schedule_id = s.id
INNER JOIN games g
    ON g.journey_id = j.id
INNER JOIN teams t1
    ON g.teama = t1.sequence
INNER JOIN teams t2 
    ON g.teamb = t2.sequence
WHERE c.id = 1
AND s.id = 1
AND g.schedule_id = 1
ORDER BY g.id ASC;

SELECT 
	c.id AS customer_id,
	l.id AS league_id,
	s.id AS schedule_id,
	j.NAME AS journey
FROM
    customers c
INNER JOIN leagues l 
    ON c.id = l.customer_id
INNER JOIN schedules s
    ON s.league_id = l.id
INNER JOIN journeys j
    ON j.schedule_id = s.id
INNER JOIN games g
    ON g.journey_id = j.id
WHERE c.id = 1
AND s.id = 1
AND g.schedule_id = 1;




SELECT
	g1.id,
	g1.teama,
	g1.teamb,
	g1.hour,
	g1.schedule_id,
	g1.journey_id,
	t2.sequence AS sequenceA,
	t3.sequence AS sequenceB,
	t2.NAME AS teamA,
	t3.NAME AS teamB
FROM
    games g1
INNER JOIN teams t2 
    ON g1.teama = t2.sequence
INNER JOIN teams t3 
    ON g1.teamb = t3.sequence   
WHERE g1.schedule_id = 1
AND g1.state = TRUE
AND t2.league_id = 1
AND t3.league_id = 1
ORDER BY g1.id ASC;




SELECT 
	c.id AS customer_id,
	l.id AS league_id,
	s.id AS schedule_id,
	j.id AS journey_id,
	j.NAME AS journey_name,
	g.teama,
	g.teamb,
	g.HOUR,
	t1.NAME,
	t2.name
FROM
   customers c
INNER JOIN leagues l 
    ON c.id = l.customer_id
INNER JOIN schedules s
    ON s.league_id = l.id
INNER JOIN journeys j
    ON j.schedule_id = s.id
INNER JOIN games g
    ON g.journey_id = j.id
INNER JOIN teams t1
    ON g.teama = t1.sequence
INNER JOIN teams t2 
    ON g.teamb = t2.sequence
WHERE c.id = 1
AND s.id = 1
AND g.schedule_id = 1
ORDER BY g.id ASC;