INSERT INTO USER(USER_ID, USER_NAME, IS_SELLER) VALUES (1, 'João', TRUE);
INSERT INTO USER(USER_ID, USER_NAME, IS_SELLER) VALUES (2, 'Pedro', FALSE);
INSERT INTO USER(USER_ID, USER_NAME, IS_SELLER) VALUES (3, 'Mateus', FALSE);
INSERT INTO USER(USER_ID, USER_NAME, IS_SELLER) VALUES (4, 'Paula', FALSE);
INSERT INTO USER(USER_ID, USER_NAME, IS_SELLER) VALUES (5, 'Maria', FALSE);
INSERT INTO USER(USER_ID, USER_NAME, IS_SELLER) VALUES (6, 'Joana', TRUE);
INSERT INTO USER(USER_ID, USER_NAME, IS_SELLER) VALUES (7, 'Bruna', TRUE);

INSERT INTO SELLER(USER_ID) VALUES (1);
INSERT INTO SELLER(USER_ID) VALUES (6);
INSERT INTO SELLER(USER_ID) VALUES (7);

INSERT INTO USER_SELLER(USER_ID, SELLER_ID) VALUES (3, 1);
INSERT INTO USER_SELLER(USER_ID, SELLER_ID) VALUES (4, 1);
INSERT INTO USER_SELLER(USER_ID, SELLER_ID) VALUES (5, 1);

INSERT INTO USER_SELLER(USER_ID, SELLER_ID) VALUES (2, 7);
INSERT INTO USER_SELLER(USER_ID, SELLER_ID) VALUES (3, 7);

INSERT INTO USER_SELLER(USER_ID, SELLER_ID) VALUES (3, 6);


INSERT INTO PRODUCT(PRODUCT_ID, BRAND, COLOR, NOTES, PRODUCT_NAME, TYPE) VALUES (1, 'Epson EcoTank', 'Black', 'Epson EcoTank L3150 110V/220V', 'Printer Epson EcoTank', 'Computing');


