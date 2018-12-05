CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(50) CHARACTER SET koi8r NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `qposts` int(11) DEFAULT NULL,
  PRIMARY KEY (`login`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE `posts` (
  `postid` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(100) DEFAULT NULL,
  `text` varchar(15000) DEFAULT NULL,
  `user` varchar(45) CHARACTER SET koi8r DEFAULT NULL,
  `tags` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`postid`),
  UNIQUE KEY `postid_UNIQUE` (`postid`),
  KEY `UserPost_idx` (`user`),
  CONSTRAINT `UserPost` FOREIGN KEY (`user`) REFERENCES `users` (`login`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;


CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `text` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `post` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `Postkey_idx` (`post`),
  CONSTRAINT `Postkey` FOREIGN KEY (`post`) REFERENCES `posts` (`postid`) ON DELETE CASCADE ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=koi8u;