CREATE TABLE public."user" (
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NOT NULL,
	surname varchar NOT NULL,
	phone varchar NOT NULL
);
