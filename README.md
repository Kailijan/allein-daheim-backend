# Backend für "[Allein daheim](https://github.com/Kailijan/allein-daheim)"

Die aktuelle Version des Backends besitzt folgende Features:

- Topics speichern
- User speichern

## API-Routen (REST JSON)

### Topics

- `GET /api/topics` - Gibt alle Topics zurück
    
    Returns (Beispiel):
    ```json
    [
          {
              "id": 1,
              "name": "Smalltalk",
              "description": "Führen Sie Smalltalk!"
          },
          {
              "id": 2,
              "name": "Vorlesen",
              "description": "Lesen Sie anderen Menschen etwas vor."
          }
    ]
    ```
    
- `GET /api/topics/{id}` - Gibt das Topic mit der übergebenen id zurück

    Returns:
    ```json
    {
        "id": 1,
        "name": "Smalltalk",
        "description": "Führen Sie Smalltalk!"
    }
    ```

### Users

- `GET /api/users/{id}` - Gibt den Nutzer mit der übergebenen id zurück
    
    Returns:
    ```json
    {
        "id": 1,
        "name": "Hans Günther",
        "lastSeen": <null or timestamp in format format dd.MM.yyyy H:m:s>
    }
    ```
    
- `POST /api/users/{id}/seen` - Aktualisiert den Last-seen Zeitpunkt des Nutzers auf die aktuelle Systemzeit des Servers

    Returns:
    ```json
    {
        "id": 1,
        "name": "Hans Günther",
        "lastSeen": "22.03.2020 10:40:31"
    }
    ```

### Chat Requests

- `POST /api/chat-request` - Legt neuen Chat Request an
    
    Body:
    ```json
    {
      "userId": <userId>,
      "topicId": <topicId>
    }
    ```
    
    Returns:
    ```json
    {
        "chatRequestKey": {
            "userId": <userId>,
            "topicId": <topicId>
        },
        "expires": "<expiresTimestamp in format dd.MM.yyyy H:m:s>"
    }
    ```

- `DELETE /api/chat-request?user={userId}` - Löscht alle ChatRequests des übergebenen Nutzers
- `GET /api/chat-request?user={userId}` - Sucht ein Match für den übergebenen Nutzer und gibt es zurück, falls eines gefunden wurde

    Returns nothing if no match was found, else:
    ```json
    {
        "chatRequestKey": {
            "userId": 3,
            "topicId": 1
        },
        "expires": "22.03.2020 23:0:0"
    }
    ```

Aktuell wird die Datenbank mit Dummy-Daten befüllt.
Ein produktiver Einsatz ist daher nur bedingt möglich.