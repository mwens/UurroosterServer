/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Jente
 * Created: 21-nov-2017
 */
drop table URS_StudentRelatie;
drop table URS_Student;
drop table URS_Klas;
drop table URS_Gebruiker;

create table URS_Gebruiker(
        userId      int,
        naam    varchar(32)     not null unique,
        ww      varchar(32)     not null,
        /* security groep (niet klasgroep!) */
        groep   varchar(32)     not null,
        primary key(userId)
        );

create table URS_Klas(
        klasId  int,
        naam    varchar(32)     not null unique,
        /* 0 = OPEN, 1 = GESLOTEN */
        status  int             not null,
        primary key(klasId)
        );

create table URS_Student(
        userId  int             not null,
        klasId  int,
        /* 0 = Select, 1 = Bevestig + overzicht, 2 = overzicht */
        status  int             not null,
        foreign key(userId) references URS_Gebruiker,
        foreign key(klasId) references URS_Klas,
        primary key(userId)
        );
        
create table URS_StudentRelatie(
        student int             not null,
        collega int             not null,
        /* 0 = enkel voor test, 1 = WEL samen, 2 = NIET samen */
        relatie int             not null,
        foreign key(student) references URS_Gebruiker,
        foreign key(collega) references URS_Gebruiker,
        primary key(student, collega)
        );
