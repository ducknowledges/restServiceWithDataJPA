CREATE TABLE IF NOT EXISTS public.users (
	id 		SERIAL      PRIMARY KEY NOT NULL,
	name 	VARCHAR     NOT NULL,
	age 	INTEGER     NOT NULL
);