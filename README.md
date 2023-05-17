# full_stack_project-gestionale_aziendale

Tecnologie Back-end: java, hibernate, jpa, jwt, junit, Spring Boot, PostgreSQL<br>

Tecnologie Front-end: HTML, SCSS, TYPESCRIPT, ANGULAR, ANGULAR MATERIAL<br>

Gestionale per aziende con API Rest strutturata a microservizi (prima sperimentazione) e front-end in Angular (con il tentativo di rendere più generici possibile i componenti come tabelle e form).<br> 

Back-End:<br>
 1) Gestione della registrazione e dell'autenticazione tramite Spring Security e jwt token.<br>
 2) CRUDE e relativo JUnit test per la creazione di User, Employee, Department, Section, Team, Role, Skill (Project e Task ma non implementati).<br>
 3) Controller per User, Employee, Department, Section, Team (Project e Task ma non implementati).<br>
 4) Gestione delle eccezione e messaggio di errore custom.<br>
 5) Utilizzo di WebClient per la gestione delle richieste agli microservizi.<br>
 6) Salvataggio dati su db PostgreSQL creato tramite hibernate e jpa.<br>

Front-End:<br>
 1) Guard per la gestione dei contenuti riservati (possibilità di usare il gestionale solo se autenticati).<br>
 2) Interceptor per la gestione del token di autenticazione per le chiamate http.<br>
 3) Form per la registrazione di nuovi User (accessibile solo ad un utente Admin), e form di autenticazione.<br>
 4) Componente Tabella per visualizzare i dati dei Department ,Section e Team.<br>
 5) Componente Form per la creazione di Department ,Section e Team(da collegare ancora con l’api).<br>
 6) Pagina per visualizzare tutti gli impiegati.
