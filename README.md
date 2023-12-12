Backend Piattaforma Fedeltà - Spring Boot
Questo è il backend dell'applicazione Piattaforma Fedeltà, sviluppato utilizzando Spring Boot. Questo backend gestisce tutte le funzionalità principali dell'applicazione, incluso il routing delle richieste HTTP e l'interazione con il database MySQL.

Panoramica
Il backend della Piattaforma Fedeltà è responsabile di gestire le seguenti funzionalità principali:

Creazione, configurazione e gestione dei programmi fedeltà aziendali.
Creazione e gestione delle ricompense per i programmi fedeltà.
Registrazione dei clienti nei programmi fedeltà e monitoraggio del loro progresso.
Fornire dati analitici sulle prestazioni del programma.
Tecnologie Utilizzate
Questo backend è stato sviluppato utilizzando diverse tecnologie, tra cui:

Spring Boot: Il framework Spring Boot è stato utilizzato per creare l'applicazione web.

Spring Data JPA: Per l'accesso ai dati e la persistenza dei dati nel database MySQL.

ModelMapper: Per la mappatura dei dati tra oggetti di dominio e DTO (Data Transfer Object).

Configurazione e Avvio
Per avviare il backend della Piattaforma Fedeltà, seguire questi passaggi:

Assicurarsi di avere un database MySQL configurato e funzionante. Le informazioni di connessione al database devono essere configurate nel file application.properties.

Compilare ed eseguire l'applicazione utilizzando Maven o la tua IDE preferita.

Il backend sarà in ascolto all'URL specificato (solitamente http://localhost:8080). Puoi utilizzare questa URL per inviare richieste HTTP all'API.

Utilizzo delle Richieste HTTP
Una volta che il backend è in esecuzione, puoi utilizzare richieste HTTP per interagire con l'applicazione. Ecco alcuni esempi di richieste:

POST /api/cliente/registrazione: Per registrare un cliente.
GET /api/cliente/{clienteId}/dashboardCliente: Per ottenere il dashboard di un cliente.
GET /api/cliente/{clienteId}/{tesseraId}: Per ottenere i dettagli di una tessera specifica per un cliente.
POST /api/cliente/{clienteId}/iscrivi-programma-fedelta: Per iscrivere un cliente a un programma fedeltà.
POST /api/cliente/{clienteId}/effettua-acquisto: Per effettuare un acquisto per un cliente.
GET /api/cliente/{clienteId}/{tesseraId}/{programmaPuntiId}/premiAssociati: Per ottenere premi associati a una tessera e a un programma fedeltà specifici.
POST /api/cliente/{clienteId}/{tesseraId}/{programmaFedeltaId}/riscattaPremio: Per riscattare un premio per un cliente all'interno di un programma fedeltà specifico.
GET /api/cliente/{clienteId}/{tesseraId}/storico-punti: Per ottenere lo storico dei punti per una tessera specifica.
Per le operazioni relative alle aziende, puoi utilizzare le seguenti richieste:

POST /api/azienda/registrazione: Per registrare un'azienda.
GET /api/azienda/{aziendaId}/dashboardAzienda: Per ottenere il dashboard di un'azienda.
POST /api/azienda/{aziendaId}/aggiungi-programma-fedelta: Per aggiungere un programma fedeltà a un'azienda.
POST /api/azienda/{aziendaId}/{programmaPuntiId}/configura: Per configurare un programma punti per un'azienda.
GET /api/azienda/{aziendaId}/programmi-fedelta: Per ottenere programmi fedeltà associati a un'azienda.
GET /api/azienda/{aziendaId}: Per ottenere i dati di un'azienda specifica.
GET /api/azienda/tutte: Per ottenere una lista di tutte le aziende.
DELETE /api/azienda/{aziendaId}: Per eliminare un'azienda.
PUT /api/azienda/{aziendaId}: Per aggiornare i dati di un'azienda.
Per le operazioni di login:

POST /loginAzienda: Per il login di un'azienda.
POST /loginCliente: Per il login di un cliente.
Sentiti libero di personalizzare ulteriormente questo README in base alle tue esigenze specifiche.
