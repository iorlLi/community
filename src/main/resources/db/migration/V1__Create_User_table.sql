create table USER
(
    ID           INT auto_increment,
    TOKEN        VARCHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    NAME         VARCHAR(50),
    ACCOUNT_ID   VARCHAR2(50),
    constraint USER_PK
        primary key (ID)
);