CREATE TABLE TOPIC(
  TOPIC_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(16) NOT NULL,
  DESCRIPTION VARCHAR(800)
);

CREATE TABLE USER(
  USER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(64) NOT NULL,
  LAST_SEEN_AT DATETIME
);

CREATE TABLE CHAT_REQUEST(
  USER_ID BIGINT NOT NULL,
  TOPIC_ID BIGINT NOT NULL,
  CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (USER_ID, TOPIC_ID),
  FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
  FOREIGN KEY (TOPIC_ID) REFERENCES TOPIC(TOPIC_ID)
);

CREATE TABLE MATCH_INFO(
  MATCH_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  TOPIC_ID BIGINT NOT NULL,
  CREATED_AT DATETIME NOT NULL,
  FOREIGN KEY (TOPIC_ID) REFERENCES TOPIC(TOPIC_ID)
);

CREATE TABLE MATCH(
  USER_ID BIGINT PRIMARY KEY,
  MATCH_ID BIGINT NOT NULL,
  FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
  FOREIGN KEY (MATCH_ID) REFERENCES MATCH_INFO(MATCH_ID)
);

CREATE TABLE MESSAGE(
  SENDER_USER_ID BIGINT NOT NULL,
  RECEIVER_USER_ID BIGINT NOT NULL,
  TOPIC_ID BIGINT NOT NULL
)
