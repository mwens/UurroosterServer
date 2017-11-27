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
drop table URS_Groep;
drop table URS_Docent;

create table URS_Docent(
        id      int,
        naam    varchar(32)    not null,
        ww      varchar(32)    not null,
        primary key(id)
        );

create table URS_Groep(
        id      int,
        naam    varchar(32)    not null,
        status  int             not null,
        primary key(id)
        );

create table URS_Student(
        id      int,
        naam    varchar(32)    not null,
        ww      varchar(32)    not null,
        /* 0 = OPEN, 1 = GESLOTEN */
        status  int             not null,
        groep   int,
        primary key(id),
        foreign key(groep) references URS_Groep
        );
        
create table URS_StudentRelatie(
        student int             not null,
        collega int             not null,
        /* 0 = Niet gebruikt voor test situaties, 1 = WEL, 2 = NIET */
        relatie int             not null,
        foreign key(student) references URS_Student,
        foreign key(collega) references URS_Student
        );
