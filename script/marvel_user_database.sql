create table Comic (
	idComic INT,
	num_lecture INT,
	etat_lecture VARCHAR(45),
	etat_aime VARCHAR(45),
	etat_achete VARCHAR(45),
	primary key (idComic)
);

create table note (
	idNote INT,
	primary key (idNote)
);

alter table note add idComic INT;


create table commentaire (
	idCommentaire INT,
	idComic INT,
	primary key (idCommentaire)
);


create table bookmark (
	idBookmark INT,
	idComic INT,
	num_page INT,
	num_Ligne INT,
	primary key (idBookmark)
);

create table Perso (
	idPerso INT,
	primary key (idPerso)
);

alter table Perso add name VARCHAR(45); 

insert into Perso values (3,'Iron-Man');
insert into Perso values (1,'Spider-Man');
insert into Perso values (2,'Hulk');

alter table Comic drop column etat_aime;
alter table Comic drop column etat_buy;



alter table Comic add etat_buy boolean;



