/* Create Oracle database tables and populate with example data */
/* Drop existing table for users */
DROP TABLE USERS PURGE;

/* Create table for users */
CREATE TABLE USERS
(
    ID              NUMBER generated as identity,
    USERNAME        VARCHAR2(50) not null,
    EMAIL           VARCHAR2(50) not null,
    PASSWORD        VARCHAR2(200) not null,
    -- We prefer using number instead boolean because
    -- in future version there could be more types
    TYPE            NUMBER not null, -- 1 - USER, 2 - ADMIN
    LAST_LOGIN_DATE DATE not null,
    -- We prefer using number instead boolean because
    -- in future version there could be more statuses
    STATUS          NUMBER not null -- 1 - NOT_VALID, 2 - VALID
);

/* Insert users */
/* Default ADMIN */
INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, TYPE, LAST_LOGIN_DATE, STATUS)
VALUES ('a', 'a@a.gr', '$2a$12$k8IkPn7y5M.f6uqc//Xuneq8AiJM4/pLBrYGB2elG.Au2ezwHKZqq', 2, CURRENT_TIMESTAMP, 2);

/* Default USER */
INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, TYPE, LAST_LOGIN_DATE, STATUS)
VALUES ('u', 'u@u.gr', '$2a$12$k8IkPn7y5M.f6uqc//Xuneq8AiJM4/pLBrYGB2elG.Au2ezwHKZqq', 1, CURRENT_TIMESTAMP, 2);

/* Drop existing table for user types */
DROP TABLE USER_TYPE PURGE;

/* Create table for user types */
CREATE TABLE USER_TYPE
(
    ID              NUMBER generated as identity,
    NAME            VARCHAR2(40) not null
);

/* Insert user types */
INSERT INTO USER_TYPE (NAME)
VALUES ('USER');

INSERT INTO USER_TYPE (NAME)
VALUES ('ADMIN');

/* Drop existing table for user statuses */
DROP TABLE USER_STATUS PURGE;

/* Create table for user statuses */
CREATE TABLE USER_STATUS
(
    ID              NUMBER generated as identity,
    NAME            VARCHAR2(40) not null
);

/* Insert user statuses */
INSERT INTO USER_STATUS (NAME)
VALUES ('NOT_VALID');

INSERT INTO USER_STATUS (NAME)
VALUES ('VALID');

/* Drop existing table for authorizations */
DROP TABLE AUTHORIZATION PURGE;

/* Create table for authorizations */
CREATE TABLE AUTHORIZATION
(
    ID                  NUMBER generated as identity,
    -- We prefer using number instead boolean because
    -- in future version there could be more types
    TYPE                NUMBER not null -- 1 - FILE_UPLOAD, 2 - FILE_UPLOAD_HISTORY
);

/* Drop existing table for authorization types */
DROP TABLE AUTHORIZATION_TYPE PURGE ;

/* Create table for authorization types */
CREATE TABLE AUTHORIZATION_TYPE
(
    ID                  NUMBER generated as identity,
    NAME                VARCHAR2(40) not null
);

/* Insert authorization types */
INSERT INTO AUTHORIZATION_TYPE (NAME)
VALUES ('FILE_UPLOAD');

INSERT INTO AUTHORIZATION_TYPE (NAME)
VALUES ('FILE_UPLOAD_HISTORY');

/* Drop existing table for user_authorizations */
DROP TABLE USER_AUTHORIZATIONS PURGE;

/* Create table for user_authorizations */
CREATE TABLE USER_AUTHORIZATIONS
(
    ID_USER             NUMBER not null,
    ID_AUTHORIZATION    NUMBER not null
);

/* Drop existing table for uploaded files */
DROP TABLE UPLOADED_FILES PURGE;

/* Create table for uploaded files */
CREATE TABLE UPLOADED_FILES
(
    ID                  NUMBER generated as identity,
    TYPE                VARCHAR2(10) not null,
    FILE_NUMBER         NUMBER not null,
    AMOUNT              DECIMAL not null,
    CURRENCY            VARCHAR2(3) not null,
    RECIPIENT           VARCHAR2(40) not null,
    ID_RECIPIENT        NUMBER not null,
    ISSUER              VARCHAR2(40) not null,
    ID_ISSUER           NUMBER not null,
    FILE_DATE           DATE not null,
    -- We expect payment term as a position specified on the list on 11th November 2021
    -- https://www.nibusinessinfo.co.uk/content/payment-terms-commonly-used-invoice-payment-terms-and-their-meanings
    PAYMENT_TERM        NUMBER,
    UPLOAD_TIMESTAMP    DATE not null,
    ID_USER             NUMBER not null,
    -- We prefer using number instead boolean because
    -- in future version there could be more statuses
    STATUS              NUMBER not null -- 1 - NOT_VALID, 2 - VALID
);

/* Drop existing table for uploaded files payment term */
DROP TABLE UPLOADED_FILE_PAYMENT_TERM PURGE;

/* Create table for uploaded files payment term */
CREATE TABLE UPLOADED_FILE_PAYMENT_TERM
(
    ID                  NUMBER generated as identity,
    NAME                VARCHAR2(40) not null,
    DETAILS             VARCHAR2(200) not null
);

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('PIA', 'Payment in advance');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('Net 7', 'Payment seven days after invoice date');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('Net 10', 'Payment ten days after invoice date');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('Net 30', 'Payment 30 days after invoice date');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('Net 60', 'Payment 60 days after invoice date');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('Net 90', 'Payment 90 days after invoice date');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('EOM', 'End of month');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('21 MFI', '21st of the month following invoice date');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('1% 10 Net 30', '1% discount if payment received within ten days otherwise payment 30 days after invoice date');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('COD', 'Cash on delivery');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('Cash account', 'Account conducted on a cash basis, no credit');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('Letter of credit', 'A documentary credit confirmed by a bank, often used for export');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('Bill of exchange', 'A promise to pay at a later date, usually supported by a bank');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('CND', 'Cash next delivery');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('CBS', 'Cash before shipment');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('CIA', 'Cash in advance');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('CWO', 'Cash with order');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('1MD', 'Monthly credit payment of a full month''s supply');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('2MD', 'As above plus an extra calendar month');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('Contra', 'Payment from the customer offset against the value of supplies purchased from the customer');

INSERT INTO UPLOADED_FILE_PAYMENT_TERM (NAME, DETAILS)
VALUES ('Stage payment	', 'Payment of agreed amounts at stage');

/* Drop existing table for user statuses */
DROP TABLE UPLOADED_FILE_STATUS PURGE;

/* Create table for user statuses */
CREATE TABLE UPLOADED_FILE_STATUS
(
    ID                  NUMBER generated as identity,
    NAME                VARCHAR2(40) not null
);

/* Insert user statuses */
INSERT INTO UPLOADED_FILE_STATUS (NAME)
VALUES ('NOT_VALID');

INSERT INTO UPLOADED_FILE_STATUS (NAME)
VALUES ('VALID');
