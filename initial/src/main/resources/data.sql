insert into products (`id`, `name`, `description`) values(1, 'Red Shirt', 'The product description regarding the red shirt');
insert into products (`id`, `name`, `description`) values(2, 'Green pant', 'The product description regarding the green pant');
insert into products (`id`, `name`, `description`) values(3, 'Black shoes', 'The product description regarding the black shoes');

alter table `products` alter column `id` restart with 4;