-- V1__create_property_seq_table.sql
CREATE TABLE property_seq (
    next_val BIGINT NULL
);

-- V2__create_user_table.sql
CREATE TABLE user (
                      id BIGINT NOT NULL PRIMARY KEY,
                      email VARCHAR(255) NULL,
                      first_name VARCHAR(255) NULL,
                      last_name VARCHAR(255) NULL,
                      password VARCHAR(255) NULL,
                      profile_photo_url VARCHAR(1000) NULL,
                      role VARCHAR(255) NULL
);

-- V3__create_property_table.sql
CREATE TABLE property (
                          id BIGINT NOT NULL PRIMARY KEY,
                          address VARCHAR(255) NULL,
                          date DATETIME(6) NULL,
                          description VARCHAR(255) NULL,
                          link_photo VARCHAR(255) NULL,
                          price BIGINT NOT NULL,
                          title VARCHAR(255) NULL,
                          type VARCHAR(255) NULL,
                          user_id BIGINT NULL,
                          deleted BIT NOT NULL,
                          CONSTRAINT FK51njvck50nuf57ngcfuhnjxye FOREIGN KEY (user_id) REFERENCES user (id)
);

-- V4__create_user_seq_table.sql
CREATE TABLE user_seq (
    next_val BIGINT NULL
);