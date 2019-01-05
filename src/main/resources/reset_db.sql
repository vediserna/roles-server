CREATE TABLE Users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username VARCHAR(45) not null,
  name VARCHAR(45) not null
);

CREATE TABLE Teams (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(45) not null,
  lead INTEGER not null ,
  FOREIGN KEY(lead) REFERENCES Users(id)
);


CREATE TABLE Memberships (
  userId INTEGER not null,
  teamId INTEGER not null,
  role VARCHAR(45) not null,
  CONSTRAINT PK_Memberships PRIMARY KEY (userId, teamId),
  FOREIGN KEY(userId) REFERENCES Users(id),
  FOREIGN KEY(teamId) REFERENCES Teams(id)
);

CREATE TABLE Roles (
  teamId INTEGER not null,
  role VARCHAR(45) not null,
  CONSTRAINT PK_Role PRIMARY KEY (teamId, role),
  FOREIGN KEY(teamId) REFERENCES Teams(id)
);

INSERT INTO Users VALUES (30, 'superghost', 'Emma Lisbon');
INSERT INTO Users VALUES (29, 'evilman', 'Ava Ulaanbaatar');
INSERT INTO Users VALUES (28, 'nastydog', 'Michael Episkopi');
INSERT INTO Users VALUES (27, 'bigman', 'Sophia Ulaanbaatar');
INSERT INTO Users VALUES (26, 'nastyhamster', 'Daniel Vaduz');
INSERT INTO Users VALUES (25, 'evilhamster', 'Michael Yaren');
INSERT INTO Users VALUES (24, 'tallcat', 'Daniel Zagreb');
INSERT INTO Users VALUES (23, 'talldoctor', 'Alexander Ulaanbaatar');
INSERT INTO Users VALUES (22, 'strangepanda', 'Mason Cario');
INSERT INTO Users VALUES (21, 'niceghost', 'Mason Ouagadougou');
INSERT INTO Users VALUES (20, 'tinygirl', 'James Zagreb');
INSERT INTO Users VALUES (19, 'stupidboy', 'Olivia Islamabad');
INSERT INTO Users VALUES (18, 'loudpilot', 'Ava Baku');
INSERT INTO Users VALUES (17, 'niceman', 'Madison Baku');
INSERT INTO Users VALUES (16, 'bigghost', 'Mason Saipan');
INSERT INTO Users VALUES (15, 'goodpanda', 'Charlotte Amsterdan');
INSERT INTO Users VALUES (14, 'stupidfish', 'Ethan Saipan');
INSERT INTO Users VALUES (13, 'weirddog', 'James Islamabad');
INSERT INTO Users VALUES (12, 'bigcat', 'Emily Wellington');
INSERT INTO Users VALUES (11, 'nastypilot', 'Daniel Cario');
INSERT INTO Users VALUES (10, 'quiethamster', 'Noah Wellington');
INSERT INTO Users VALUES (9, 'quietboy', 'Charlotte Lisbon');
INSERT INTO Users VALUES (8, 'stupidpilot', 'Liam Gustavia');
INSERT INTO Users VALUES (7, 'bigdoctor', 'Abigail Juba');
INSERT INTO Users VALUES (6, 'evilsanta', 'Emily Nassau');
INSERT INTO Users VALUES (5, 'strangepilot', 'Isabella Rome');
INSERT INTO Users VALUES (4, 'loudhamster', 'Mason Juba');
INSERT INTO Users VALUES (3, 'nastyghost', 'Emily Kabul');
INSERT INTO Users VALUES (2, 'strangeboy', 'Ethan Ulaanbaatar');
INSERT INTO Users VALUES (1, 'superdog', 'Ava Nassau');

INSERT INTO Teams VALUES (1, 'Awesome Dancers', 30);
INSERT INTO Teams VALUES (2, 'Awesome Tricksters', 24);
INSERT INTO Teams VALUES (3, 'Invincible Jugglers', 16);
INSERT INTO Teams VALUES (4, 'Unbelivable Astronauts', 9);
INSERT INTO Teams VALUES (5, 'Unbelivable Heroes', 3);

INSERT INTO Memberships VALUES (30, 1, 'Lead');
INSERT INTO Memberships VALUES (24, 2, 'Lead');
INSERT INTO Memberships VALUES (16, 3, 'Lead');
INSERT INTO Memberships VALUES (9, 4, 'Lead');
INSERT INTO Memberships VALUES (3, 5, 'Lead');
INSERT INTO Memberships VALUES (25, 1, 'Developer');
INSERT INTO Memberships VALUES (26, 1, 'Developer');
INSERT INTO Memberships VALUES (27, 1, 'Developer');
INSERT INTO Memberships VALUES (28, 1, 'Developer');
INSERT INTO Memberships VALUES (29, 1, 'Developer');
INSERT INTO Memberships VALUES (17, 2, 'Developer');
INSERT INTO Memberships VALUES (18, 2, 'Developer');
INSERT INTO Memberships VALUES (19, 2, 'Developer');
INSERT INTO Memberships VALUES (20, 2, 'Developer');
INSERT INTO Memberships VALUES (21, 2, 'Developer');
INSERT INTO Memberships VALUES (22, 2, 'Developer');
INSERT INTO Memberships VALUES (23, 2, 'Developer');
INSERT INTO Memberships VALUES (10, 3, 'Developer');
INSERT INTO Memberships VALUES (11, 3, 'Developer');
INSERT INTO Memberships VALUES (12, 3, 'Developer');
INSERT INTO Memberships VALUES (13, 3, 'Developer');
INSERT INTO Memberships VALUES (14, 3, 'Developer');
INSERT INTO Memberships VALUES (15, 3, 'Developer');
INSERT INTO Memberships VALUES (4, 4, 'Developer');
INSERT INTO Memberships VALUES (5, 4, 'Developer');
INSERT INTO Memberships VALUES (6, 4, 'Developer');
INSERT INTO Memberships VALUES (7, 4, 'Developer');
INSERT INTO Memberships VALUES (8, 4, 'Developer');
INSERT INTO Memberships VALUES (1, 5, 'Developer');
INSERT INTO Memberships VALUES (2, 5, 'Developer');
INSERT INTO Memberships VALUES (7, 5, 'Developer');
INSERT INTO Memberships VALUES (17, 5, 'Developer');
INSERT INTO Memberships VALUES (19, 5, 'Developer');
INSERT INTO Memberships VALUES (28, 5, 'Developer');

INSERT INTO Roles VALUES (1, "Developer");
INSERT INTO Roles VALUES (1, "Product Owner");
INSERT INTO Roles VALUES (1, "Tester");
INSERT INTO Roles VALUES (1, "Lead");
INSERT INTO Roles VALUES (2, "Developer");
INSERT INTO Roles VALUES (2, "Product Owner");
INSERT INTO Roles VALUES (2, "Tester");
INSERT INTO Roles VALUES (2, "Lead");
INSERT INTO Roles VALUES (3, "Developer");
INSERT INTO Roles VALUES (3, "Product Owner");
INSERT INTO Roles VALUES (3, "Tester");
INSERT INTO Roles VALUES (3, "Lead");
INSERT INTO Roles VALUES (4, "Developer");
INSERT INTO Roles VALUES (4, "Product Owner");
INSERT INTO Roles VALUES (4, "Tester");
INSERT INTO Roles VALUES (4, "Lead");
INSERT INTO Roles VALUES (5, "Developer");
INSERT INTO Roles VALUES (5, "Product Owner");
INSERT INTO Roles VALUES (5, "Tester");
INSERT INTO Roles VALUES (5, "Lead");