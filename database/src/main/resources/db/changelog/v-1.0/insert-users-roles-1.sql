INSERT INTO `user_roles`(`user_id`, `role_id`)
SELECT (SELECT id FROM `users` WHERE `login` = 'admin1'), (SELECT id FROM `roles` WHERE `title` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `login` = 'user1'), (SELECT id FROM `roles` WHERE `title` = 'ROLE_GUEST')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `login` = 'user2'), (SELECT id FROM `roles` WHERE `title` = 'ROLE_GUEST')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `login` = 'user3'), (SELECT id FROM `roles` WHERE `title` = 'ROLE_GUEST')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `login` = 'user4'), (SELECT id FROM `roles` WHERE `title` = 'ROLE_GUEST')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `login` = 'manager'), (SELECT id FROM `roles` WHERE `title` = 'ROLE_ADMIN');
GO