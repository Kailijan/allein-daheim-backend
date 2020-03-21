INSERT INTO TOPIC (NAME, DESCRIPTION) VALUES ('Smalltalk', 'Führen Sie Smalltalk!');
INSERT INTO TOPIC (NAME, DESCRIPTION) VALUES ('Vorlesen', 'Lesen Sie anderen Menschen etwas vor.');
INSERT INTO TOPIC (NAME, DESCRIPTION) VALUES ('Videospiele', 'Ihr sucht jemanden, den ihr bei CSGO so richtig fertig machen könnt? Oder würdet gerne zusammen eine eigene Welt in Minecraft erschaffen? Hier findet ihr eine/n Gleichgesinnte/n!');
INSERT INTO TOPIC (NAME, DESCRIPTION) VALUES ('Sprachen', 'Unterhalten Sie sich über Sprachen.');
INSERT INTO TOPIC (NAME, DESCRIPTION) VALUES ('Lernen', 'Lernen Sie gemeinsam.');
INSERT INTO TOPIC (NAME, DESCRIPTION) VALUES ('Musik', 'Mozart, Britney Spears oder doch lieber BTS? Tauscht euch aus zu Justin Biebers neuestem Track oder gründet einfach eure eigene Band!');
INSERT INTO TOPIC (NAME, DESCRIPTION) VALUES ('Sport', 'Ob Fußball, Tennis, Ballett oder Workouts, hier kommt jeder Sportsfreund auf seine Kosten!');

INSERT INTO USER (NAME) VALUES ('Hans Günther');
INSERT INTO USER (NAME) VALUES ('Angela-Sophie');
INSERT INTO USER (NAME) VALUES ('Sonja Quatsch');
INSERT INTO USER (NAME) VALUES ('Peter Plapper');
INSERT INTO USER (NAME) VALUES ('Elisabeth Müller');

INSERT INTO USER_WITH_TOPIC (USER_ID, TOPIC_ID) VALUES (
    (SELECT USER_ID FROM USER WHERE NAME = 'Hans Günther'),
    (SELECT TOPIC_ID FROM TOPIC WHERE NAME = 'Musik')
);
