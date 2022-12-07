INSERT INTO `sda-project-auction-site-db`.`users` (`id`, `account_name`, `address`, `city`, `created_date`, `email`, `password`) VALUES ('1', 'Jan Kowalski', 'ul. Jasna 4/5', 'Warszawa', '2022-10-06', 'kowal@wp.pl', 'test1');
INSERT INTO `sda-project-auction-site-db`.`users` (`id`, `account_name`, `address`, `city`, `created_date`, `email`, `password`) VALUES ('2', 'Anna Robak', 'ul. Zielona 2/3', 'Kraków', '2022-11-04', 'robak@wp.pl', 'test1');

INSERT INTO `sda-project-auction-site-db`.`categories` (`id`, `description`, `name`) VALUES ('1', 'Kategoria1', 'Komputery');
INSERT INTO `sda-project-auction-site-db`.`categories` (`id`, `description`, `name`) VALUES ('2', 'Kategoria2', 'Srzęt RTV');
INSERT INTO `sda-project-auction-site-db`.`categories` (`id`, `description`, `name`) VALUES ('3', 'Kategoria3', 'Sprzęt AGD');
INSERT INTO `sda-project-auction-site-db`.`categories` (`id`, `description`, `name`, `parent_category`) VALUES ('4', 'Kategoria4', 'Laptopy', '1');
INSERT INTO `sda-project-auction-site-db`.`categories` (`id`, `description`, `name`, `parent_category`) VALUES ('5', 'Kategoria5', 'Monitory', '1');
INSERT INTO `sda-project-auction-site-db`.`categories` (`id`, `description`, `name`, `parent_category`) VALUES ('6', 'Kategoria6', 'Lodówki', '3');

INSERT INTO `sda-project-auction-site-db`.`auctions` (`id`, `buy_now_price`, `category`, `date_of_issue`, `description`, `end_date`, `localization`, `min_price`, `numbers_of_visitors`, `promoted`, `title`, `user_id`) VALUES ('1', '100.00', '1', '2022-10-06', 'Aukcja1', '2023-04-04', 'Warszawa', '1.00', '0', 0, 'Aukcja 1', '1');
INSERT INTO `sda-project-auction-site-db`.`auctions` (`id`, `buy_now_price`, `category`, `date_of_issue`, `description`, `end_date`, `localization`, `min_price`, `numbers_of_visitors`, `promoted`, `title`, `user_id`) VALUES ('2', '100.00', '1', '2022-10-06', 'Aukcja2', '2023-04-04', 'Warszawa', '10.00', '0', 0, 'Aukcja 2', '1');
INSERT INTO `sda-project-auction-site-db`.`auctions` (`id`, `buy_now_price`, `category`, `date_of_issue`, `description`, `end_date`, `localization`, `min_price`, `numbers_of_visitors`, `promoted`, `title`, `user_id`) VALUES ('3', '100.00', '2', '2022-10-06', 'Aukcja3', '2023-04-04', 'Kraków', '12.00', '0', 0, 'Aukcja 3', '2');
INSERT INTO `sda-project-auction-site-db`.`auctions` (`id`, `buy_now_price`, `category`, `date_of_issue`, `description`, `end_date`, `localization`, `min_price`, `numbers_of_visitors`, `promoted`, `title`, `user_id`) VALUES ('4', '100.00', '2', '2022-10-06', 'Aukcja4', '2022-10-24', 'Łódz', '12.00', '0', 0, 'Aukcja 4', '2');
INSERT INTO `sda-project-auction-site-db`.`auctions` (`id`, `buy_now_price`, `category`, `date_of_issue`, `description`, `end_date`, `localization`, `min_price`, `numbers_of_visitors`, `promoted`, `title`, `user_id`) VALUES ('5', '100.00', '2', '2022-10-05', 'Aukcja5', '2022-11-25', 'Warszawa', '12.00', '0', 0, 'Aukcja 5', '2');
INSERT INTO `sda-project-auction-site-db`.`auctions` (`id`, `buy_now_price`, `category`, `date_of_issue`, `description`, `end_date`, `localization`, `min_price`, `numbers_of_visitors`, `promoted`, `title`, `user_id`) VALUES ('6', '100.00', '2', '2022-10-07', 'Aukcja6', '2022-11-24', 'Gdańsk', '14.00', '0', 0, 'Aukcja 6', '2');

INSERT INTO `sda-project-auction-site-db`.`biddings` (`id`, `amount`, `auction_id`, `user_id`) VALUES ('3', '101', '1', '2');
INSERT INTO `sda-project-auction-site-db`.`biddings` (`id`, `amount`, `auction_id`, `user_id`) VALUES ('4', '100', '2', '2');

INSERT INTO `sda-project-auction-site-db`.`observed_auctions` (`id`, `auction_id`, `user_id`) VALUES ('1', '1', '2');
INSERT INTO `sda-project-auction-site-db`.`observed_auctions` (`id`, `auction_id`, `user_id`) VALUES ('2', '1', '2');
