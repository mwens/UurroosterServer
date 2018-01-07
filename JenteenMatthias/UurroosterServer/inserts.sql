/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  student
 * Created: 21-nov-2017
 */

/*                             userId   | naam     |    ww  |groep (security) */
INSERT into URS_Gebruiker VALUES (0,'Matthias Wens','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (1,'Jente Heremans','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (2,'Jeroen Streulens','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (3,'Wouter Maurien','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (4,'Wouter Raes','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (5,'Pieterjan Peetermans','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (6,'Nele Annaert','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (7,'Jelle Meus','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (8,'Jari Rooman','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (9,'Toon Blommaerts','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (10,'Wouter Symons','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (11,'Nick Todts','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (12,'Kevin Todts','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (13,'Kristof De Ridder','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (14,'Lena Bastenie','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (15,'Bruno Horemans','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (16,'Jan van der Veken','PassW0rd','student');
INSERT into URS_Gebruiker VALUES (17,'admin','admin', 'docent');
INSERT into URS_Gebruiker VALUES (18,'Herman Crauwels','IH8Toledo', 'docent');
INSERT into URS_Gebruiker VALUES (19,'Joost Vennekens','IH8Toledo', 'docent');

/*                         klasId, naam */
INSERT into URS_Klas VALUES (1,'Groep 1');

/*                        userId | klas | status */
INSERT into URS_Student VALUES (0, NULL, 0);
INSERT into URS_Student VALUES (1, NULL, 0);
INSERT into URS_Student VALUES (2, NULL, 0);
INSERT into URS_Student VALUES (3, NULL, 0);
INSERT into URS_Student VALUES (4, NULL,0);
INSERT into URS_Student VALUES (5, NULL, 0);
INSERT into URS_Student VALUES (6, NULL, 0);
INSERT into URS_Student VALUES (7, NULL, 0);
INSERT into URS_Student VALUES (8, NULL, 0);
INSERT into URS_Student VALUES (9, NULL, 0);
INSERT into URS_Student VALUES (10, NULL, 0);
INSERT into URS_Student VALUES (11, NULL, 0);
INSERT into URS_Student VALUES (12, NULL, 0);
INSERT into URS_Student VALUES (13, NULL, 0);
INSERT into URS_Student VALUES (14, NULL, 0);
INSERT into URS_Student VALUES (15, NULL, 0);
INSERT into URS_Student VALUES (16, NULL, 0);

/*                                student | collega | status */
INSERT into URS_StudentRelatie VALUES (0,2,2);
INSERT into URS_StudentRelatie VALUES (0,1,1);
INSERT into URS_StudentRelatie VALUES (0,14,2);
INSERT into URS_StudentRelatie VALUES (0,16,1);
INSERT into URS_StudentRelatie VALUES (0,3,1);
INSERT into URS_StudentRelatie VALUES (1,4,2);
INSERT into URS_StudentRelatie VALUES (1,0,2);
INSERT into URS_StudentRelatie VALUES (1,7,1);

INSERT into URS_Settings VALUES (1,'Melding Admin', NULL)

