insert into ezprofmeeting.studente(idStudente, nomeStudente, cognomeStudente, matricola, emailStudente, telefonoStudente, numAssenza) 
values (1, 'Salvatore', 'Santelia', '0512104001', 's.santelia1@studenti.unisa.it', '3334455678', 0);
insert into ezprofmeeting.studente(idStudente, nomeStudente, cognomeStudente, matricola, emailStudente, telefonoStudente, numAssenza) 
values (2, 'Rocco', 'Aliberti', '0512104627', 'r.aliberti18@studenti.unisa.it', '3334455678', 1);

insert into ezprofmeeting.professore(idProfessore, nomeProfessore, cognomeProfessore, emailProfessore, telefonoProfessore, ufficioProfessore) 
values (1, 'Filomena', 'Ferrucci', 'fferrucci@unisa.it', '0819876543', 'stecca F');
insert into ezprofmeeting.professore(idProfessore, nomeProfessore, cognomeProfessore, emailProfessore, telefonoProfessore, ufficioProfessore) 
values (2, 'Carmine', 'Gravino', 'cgravino@unisa.it', '0819876543', 'stecca F');

insert into ezprofmeeting.ricevimento(idRicevimento, orarioInizio, orarioFine, luogo, data, idProfessore) 
values (1, '12:00:00', '12:30:00', 'stecca F', '19-02-16', 1);
insert into ezprofmeeting.ricevimento(idRicevimento, orarioInizio, orarioFine, luogo, data, idProfessore) 
values (2, '12:00:00', '12:30:00', 'stecca F', '19-02-16', 2);

insert into ezprofmeeting.prenotazione(idPrenotazione, listaStudenti, motivazione, orario, idRicevimento, idStudente) 
values (1, 'Salvatore Santelia', 'Sono stupido', '11:59:59', 1, 1);
insert into ezprofmeeting.prenotazione(idPrenotazione, listaStudenti, motivazione, orario, idRicevimento, idStudente) 
values (2, 'Rocco Aliberti - Luca Postiglione', 'Sono stupido', '11:59:59', 2, 2);