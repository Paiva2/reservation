INSERT INTO TB_ROLES (RL_ID, RL_NAME, RL_CREATED_AT, RL_UPDATED_AT)
VALUES  (DEFAULT, 'USER', NOW(), NOW()),
        (DEFAULT, 'ADMIN', NOW(), NOW());

INSERT INTO TB_GENRES (GR_ID, GR_NAME, GR_CREATED_AT, GR_UPDATED_AT)
VALUES  (DEFAULT, 'action', NOW(), NOW()),
        (DEFAULT, 'romance', NOW(), NOW()),
        (DEFAULT, 'comedy', NOW(), NOW()),
        (DEFAULT, 'horror', NOW(), NOW()),
        (DEFAULT, 'mistery', NOW(), NOW()),
        (DEFAULT, 'fiction', NOW(), NOW()),
        (DEFAULT, 'drama', NOW(), NOW()),
        (DEFAULT, 'fantasy', NOW(), NOW());