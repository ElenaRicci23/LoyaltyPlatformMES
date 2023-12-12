# Backend Piattaforma Fedeltà - Spring Boot

Questo è il backend dell'applicazione Piattaforma Fedeltà, sviluppato utilizzando Spring Boot. 
Questo backend gestisce tutte le funzionalità principali dell'applicazione, 
incluso il routing delle richieste HTTP e l'interazione con il database MySQL.

## Autori

## Autori

- [Avatroni Sara](https://github.com/418TheTeapot)
- [Di Felice Matteo](https://github.com/Matteodife)
- [Ricci Elena](https://github.com/ElenaRicci23)



## Panoramica

Il backend della Piattaforma Fedeltà è responsabile di gestire le seguenti funzionalità principali:

- Creazione, configurazione e gestione dei programmi fedeltà aziendali.
- Creazione e gestione delle ricompense per i programmi fedeltà.
- Registrazione dei clienti nei programmi fedeltà e monitoraggio del loro progresso.
- Fornire dati analitici sulle prestazioni del programma.

## Tecnologie Utilizzate

Questo backend è stato sviluppato utilizzando diverse tecnologie, tra cui:

- Spring Boot: Il framework Spring Boot è stato utilizzato per creare l'applicazione web.
- Spring Data JPA: Per l'accesso ai dati e la persistenza dei dati nel database MySQL.
- ModelMapper: Per la mappatura dei dati tra oggetti di dominio e DTO (Data Transfer Object).

## Configurazione e Avvio

Per avviare il backend della Piattaforma Fedeltà, seguire questi passaggi:

1. Assicurarsi di avere un database MySQL configurato e funzionante. Le informazioni di connessione al database devono essere configurate nel file `application.properties`.

2. Compilare ed eseguire l'applicazione utilizzando Maven o la tua IDE preferita.

3. Il backend sarà in ascolto all'URL specificato (solitamente http://localhost:8080). Puoi utilizzare questa URL per inviare richieste HTTP all'API.

## Utilizzo delle Richieste HTTP

Una volta che il backend è in esecuzione, puoi utilizzare richieste HTTP per interagire con l'applicazione. Ecco alcuni esempi di richieste:

- Esempio di richiesta GET per ottenere dati da un endpoint specifico.
- Esempio di richiesta POST per inviare dati a un endpoint per la registrazione di un cliente.
- Esempio di richiesta PUT per aggiornare dati esistenti.
- Esempio di richiesta DELETE per eliminare dati.

Puoi consultare la documentazione dell'API per ulteriori dettagli sulle richieste supportate e sui parametri necessari.


## API per i Clienti

- **Registrazione Cliente**: `/api/cliente/registrazione`
- **Visualizza Dashboard Cliente**: `/api/cliente/{clienteId}/dashboardCliente`
- **Ottieni Dettagli Tessera Cliente**: `/api/cliente/{clienteId}/{tesseraId}`
- **Iscrizione a Programma Fedeltà**: `/api/cliente/{clienteId}/iscrivi-programma-fedelta`
- **Effettua Acquisto Cliente**: `/api/cliente/{clienteId}/effettua-acquisto`
- **Ottieni Premi Associati**: `/api/cliente/{clienteId}/{tesseraId}/{programmaPuntiId}/premiAssociati`
- **Riscatta Premio**: `/api/cliente/{clienteId}/{tesseraId}/{programmaFedeltaId}/riscattaPremio`
- **Storico dei Punti**: `/api/cliente/{clienteId}/{tesseraId}/storico-punti`
- **Login Cliente**: `/loginCliente`

## API per le Aziende

- **Registrazione Azienda**: `/api/azienda/registrazione`
- **Visualizza Dashboard Azienda**: `/api/azienda/{aziendaId}/dashboardAzienda`
- **Aggiungi Programma Fedeltà**: `/api/azienda/{aziendaId}/aggiungi-programma-fedelta`
- **Configura Programma Punti**: `/api/azienda/{aziendaId}/{programmaPuntiId}/configura`
- **Ottieni Programmi Fedeltà Azienda**: `/api/azienda/{aziendaId}/programmi-fedelta`
- **Ottieni Dati Azienda**: `/api/azienda/{aziendaId}`
- **Ottieni Tutte le Aziende**: `/api/azienda/tutte`
- **Elimina Azienda**: `/api/azienda/{aziendaId}`
- **Aggiorna Azienda**: `/api/azienda/{aziendaId}`

## Login Utenti (Aziende e Clienti)

- **Login Azienda**: `/loginAzienda`
- **Login Cliente**: `/loginCliente`

  MIT License

Copyright (c) [Anno] [Tuo Nome]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
