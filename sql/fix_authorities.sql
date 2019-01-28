DROP TABLE PCM_AUTHORITIES;

CREATE TABLE PCM_AUTHORITIES (
		ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 5),
		USERNAME VARCHAR(100),
		AUTHORITY VARCHAR(30),
		USER_ID INTEGER,
		PRIMARY KEY (ID)
	)