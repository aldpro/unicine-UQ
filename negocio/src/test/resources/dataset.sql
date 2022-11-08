insert into administrador values (1003496468,"henrya.barraganp@uqvirtual.edu.co","Henry","unicine123");

insert into administrador_teatro values (1119000000,"jhona.belloc@uqvirtual.edu.co","Jhon","xxunicinexx");
insert into administrador_teatro values (1228000000,"mariaf.camachog@uqvirtual.edu.co","Maria","unicine12345");
insert into administrador_teatro values (1337000000,"cristians.barreram@uqvirtual.edu.co ","Cristian","unicine");
insert into administrador_teatro values (1446000000,"henrya.barraganp@uqvirtual.edu.co","Alejandro","unicine1320");
insert into administrador_teatro values (1557000000,"rodolfo@email.com","Rodolfo","1Av3yEzA2Ts");
insert into administrador_teatro values (1657000800,"jose@email.com","Jose","1EzA2Ts");

insert into ciudad values (1,"Armenia");
insert into ciudad values (2,"Pereira");
insert into ciudad values (3,"Cali");
insert into ciudad values (4,"Bogota");
insert into ciudad values (5,"Choco");

insert into distribucion_silla values (1, 10, 8, 80, "C://esquema[floresta-bosa].html");
insert into distribucion_silla values (2, 12, 10, 120, "C://esquma[atlantis].jpg");
insert into distribucion_silla values (3, 8, 8, 64, "C://esquema[multiplaza].jpg");
insert into distribucion_silla values (4, 9, 8, 72, "C://esquema[colonia].png");
insert into distribucion_silla values (5, 11, 10, 120, "C://esquema[imperial].jpg");

insert into teatro values (1, "Carrera 14 # 4-6 Norte", "3185469257", 1119000000, 5);
insert into teatro values (2, "Calle 3 # 1 A 24 Sur", "3185749321", 1228000000, 4);
insert into teatro values (3, "Calle 16 4-2 Centro", "3124720846", 1337000000, 4);
insert into teatro values (4, "Carrera 7 # B 12-13 Sur", "3001247585", 1446000000, 3);
insert into teatro values (5, "Carrera 9 # 4-7 Oeste", "3186347896", 1557000000, 2);
insert into teatro values (6, "Calle 4 # 4-2 Sur", "3178532410", 1657000800, 1);

insert into sala values (1, "Atlantis", "XD", 2, 5);
insert into sala values (2, "FLoresta", "NORMAL", 1, 5);
insert into sala values (3, "Gran Plaza Bosa", "IMAX", 1, 4);
insert into sala values (4, "Altavista", "IMAX", 1, 6);
insert into sala values (5, "Multiplaza", "XD", 3, 3);
insert into sala values (6, "Parque Colonia", "NORMAL", 4, 2);
insert into sala values (7, "Plaza Imperial", "DX4", 5, 1);
insert into sala values (8, "Colon", "IMAX", 1, 6);

insert into horario values (1, "2022-12-20 23:15:32", "2022-12-20 21:30:32");
insert into horario values (2, "2022-12-15 20:31:05", "2022-12-15 18:50:45");
insert into horario values (3, "2022-12-16 00:07:20", "2022-12-16 22:12:02");
insert into horario values (4, "2022-12-17 19:00:23", "2022-12-17 17:30:06");
insert into horario values (5, "2022-12-22 20:30:18", "2022-12-22 18:40:28");
insert into horario values (6, "2022-12-17 23:11:02", "2022-12-17 21:40:12");

insert into pelicula values (1, "PREVENTA", "Pinocho", 4.2,"Tom Hanks, Cynthia Erivo, Luke Evans", "En un pueblo italiano, el títere de madera Pinocho cobra vida gracias al Hada Azul. Pinocho se esfuerza por comportarse como un niño de carne y hueso, pero su vida da un vuelco al abandonar a su padre para unirse a un circo.", "https://www.youtube.com/watch?v=TITv1TNi5mI");
insert into pelicula values (2, "CARTELERA", "Dragon Ball: Super Hero", 3.5, "	Masako Nozawa, Toshio Furukawa, Ryō Horikawa, Yūko Minaguchi", "La malvada organización Red Ribbon Army se reforma con nuevos y más poderosos androides, Gamma {1} y Gamma {2} para buscar venganza.", "https://www.youtube.com/watch?v=lXLPVQ-WrU4");
insert into pelicula values (3, "CARTELERA", "Smile", 4.0, "Sosie Bacon, Jessie T Usher", "Después de ser testigo de un extraño y traumático accidente que involucró a una paciente, la Dr. Rose Cotter (Sosie Bacon) empieza a experimentar sucesos aterradores que no puede explicarse. A medida que el terror comienza a apoderarse de su vida, Rose debe enfrentarse a su pasado para sobrevivir y escapar de su horrible nueva realidad.", "https://www.youtube.com/watch?v=yhKiQGJop_8");
insert into pelicula values (4, "CARTELERA", "Minions", 4.5, "Sandra Bullock, Jon Hamm, Michael Keaton", "En los años 70, Gru crece siendo un gran admirador de <<Los salvajes seis>>, un supergrupo de villanos. Para demostrarles que puede ser malvado, Gru idea un plan con la esperanza de formar parte de la banda. Por suerte, cuenta con la ayuda de sus fieles seguidores, los Minions, siempre dispuestos a sembrar el caos.", "https://www.youtube.com/watch?v=W27moupirnI");
insert into pelicula values (5, "PREVENTA", "Encanto", 4.1, "Stephanie Beatriz, María Cecilia Botero, John Leguizamo", "En lo alto de las montañas de Colombia hay un lugar encantado llamado Encanto. Aquí, en una casa mágica, vive la extraordinaria familia Madrigal donde todos tienen habilidades fantásticas.", "https://www.youtube.com/watch?v=SAH_W9q_brE");

insert into pelicula_imagenes values(1, "https://res.cloudinary.com/dwu4xtiun/image/upload/v1667875113/unicine/peliculas/pinocho_wehtj2.jpg","unicine/peliculas/pinocho_wehtj2");
insert into pelicula_imagenes values(2, "https://res.cloudinary.com/dwu4xtiun/image/upload/v1667775208/unicine/peliculas/Dragon_Ball_Super_Super_Hero_kgaa1r.jpg","unicine/peliculas/Dragon_Ball_Super_Super_Hero_kgaa1r");
insert into pelicula_imagenes values(3, "https://res.cloudinary.com/dwu4xtiun/image/upload/v1667775212/unicine/peliculas/Smile_dl13uz.jpg","unicine/peliculas/Smile_dl13uz");
insert into pelicula_imagenes values(4, "https://res.cloudinary.com/dwu4xtiun/image/upload/v1667775203/unicine/peliculas/Minions_gqwkoe.jpg","unicine/peliculas/Minions_gqwkoe");
insert into pelicula_imagenes values(5, "https://res.cloudinary.com/dwu4xtiun/image/upload/v1667775197/unicine/peliculas/Encanto_fhr4vu.jpg","unicine/peliculas/Encanto_fhr4vu");

insert into pelicula_generos_pelicula values (1, "AVENTURAS");
insert into pelicula_generos_pelicula values (1, "FANTASIA");
insert into pelicula_generos_pelicula values (1, "ANIMADA");
insert into pelicula_generos_pelicula values (2, "ACCION");
insert into pelicula_generos_pelicula values (2, "AVENTURAS");
insert into pelicula_generos_pelicula values (2, "ANIMADA");
insert into pelicula_generos_pelicula values (3, "TERROR");
insert into pelicula_generos_pelicula values (3, "SUSPENSO");
insert into pelicula_generos_pelicula values (4, "ANIMADA");
insert into pelicula_generos_pelicula values (4, "COMEDIA");
insert into pelicula_generos_pelicula values (4, "AVENTURAS");
insert into pelicula_generos_pelicula values (5, "ANIMADA");
insert into pelicula_generos_pelicula values (5, "MUSICAL");
insert into pelicula_generos_pelicula values (5, "AVENTURAS");

insert into funcion values (1, 7000, 1, 1, 6);
insert into funcion values (2, 6500, 2, 2, 5);
insert into funcion values (3, 6800, 3, 3, 4);
insert into funcion values (4, 6800, 4, 4, 3);
insert into funcion values (5, 7100, 5, 5, 2);
insert into funcion values (6, 6800, 6, 4, 1);

insert into cliente values (1009000011,"pepe@hotmail.com","Pepe","CjT30mNQ1dV",1,"https://pepe.jpg");
insert into cliente values (1008000022,"juan@outlook.com","Juan","HrF52cVT7So",0,"http://juan.jpg");
insert into cliente values (1007000033,"luis@yahoo.com","Luis","kUL17Okt1SM",0,"http://luis.png");
insert into cliente values (1006000044,"maria@gmail.com","Maria","Dss57UgC3QK",1,"http://maria.jpg");
insert into cliente values (1005000055,"luisa@google.com","Luisa","MqG82VtF2Ob",0,"https://luis.png");

insert into cliente_telefonos values (1009000011, "3146832477");
insert into cliente_telefonos values (1009000011, "3008245984");
insert into cliente_telefonos values (1008000022, "3176857415");
insert into cliente_telefonos values (1007000033, "3126845287");
insert into cliente_telefonos values (1006000044, "3139847645");
insert into cliente_telefonos values (1005000055, "3101036478");

insert into cupon values (1, "Primer registro", "Cupon del 15% de descuento por registrarse por primera vez en nuestra plataforma", 0.15, "2022-12-25 20:00:00");
insert into cupon values (2, "Primera compra", "Cupon del 10% de descuento por realizar una primera compra por medio de nuestra plataforma", 0.1, "2022-12-19 15:45:00");

insert into confiteria values (1, "Combo para niños", 15000);
insert into confiteria values (2, "Combo para dos", 49900);
insert into confiteria values (3, "Crispeta + Dos Gaseosas", 29800);
insert into confiteria values (4, "Gaseosa + Perro caliente + Crispeta + KitKat", 19900);
insert into confiteria values (5, "Nevado de arequipe", 6000);

insert into confiteria_imagenes_confi values (1,"https://res.cloudinary.com/dwu4xtiun/image/upload/v1667927564/unicine/confiteria/combo_ni%C3%B1os_ydpbay.jpg", "unicine/confiteria/combo_ni%C3%B1os_ydpbay");
insert into confiteria_imagenes_confi values (2,"https://res.cloudinary.com/dwu4xtiun/image/upload/v1667927565/unicine/confiteria/combo_para_dos_r5rvxp.jpg", "unicine/confiteria/combo_para_dos_r5rvxp");
insert into confiteria_imagenes_confi values (3,"https://res.cloudinary.com/dwu4xtiun/image/upload/v1667927564/unicine/confiteria/crispeta_2gaseosas_vnrpli.jpg", "unicine/confiteria/crispeta_2gaseosas_vnrpli");
insert into confiteria_imagenes_confi values (4,"https://res.cloudinary.com/dwu4xtiun/image/upload/v1667927565/unicine/confiteria/combo_para_dos_r5rvxp.jpg", "unicine/confiteria/combo_para_dos_r5rvxp");
insert into confiteria_imagenes_confi values (5,"https://res.cloudinary.com/dwu4xtiun/image/upload/v1667927565/unicine/confiteria/nevado_arequipe_afpfeo.jpg", "unicine/confiteria/nevado_arequipe_afpfeo");

insert into cupon_cliente values (1, 1, 1005000055, 1);
insert into cupon_cliente values (2, 0, 1006000044, 2);
insert into cupon_cliente values (3, 1, 1006000044, 1);
insert into cupon_cliente values (4, 1, 1007000033, 2);
insert into cupon_cliente values (5, 0, 1008000022, 1);

insert into compra values (1, "2022-12-20 18:32:25", "NEQUI", 17000, 1008000022, 1, 6);
insert into compra values (2, "2022-12-15 14:47:41", "VISA", 59800, 1007000033, 2, 5);
insert into compra values (3, "2022-12-16 19:12:04", "NEQUI", 24000, 1006000044, 3, 4);
insert into compra values (4, "2022-12-17 15:32:07", "MASTERCARD", 54800, 1005000055, 4, 3);
insert into compra values (5, "2022-12-16 20:30:12", "DAVIPLATA", 72000, 1008000022, 5, 2);

insert into compra_confiteria values (1, 6000, 2, 1, 5);
insert into compra_confiteria values (2, 15000, 1, 1, 4);
insert into compra_confiteria values (3, 29900, 2, 2, 2);
insert into compra_confiteria values (4, 24000, 1, 3, 1);
insert into compra_confiteria values (5, 54800, 1, 4, 3);
insert into compra_confiteria values (6, 24000, 3, 5, 1);

insert into entrada values (1, 5, 2, 17000, 1);
insert into entrada values (2, 4, 4, 59800, 2);
insert into entrada values (3, 3, 5, 24000, 3);
insert into entrada values (4, 4, 2, 54800, 4);
insert into entrada values (5, 5, 2, 54800, 4);
insert into entrada values (6, 5, 3, 72000, 5);