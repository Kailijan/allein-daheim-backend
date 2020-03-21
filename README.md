# Backend für "[Allein daheim](https://github.com/Kailijan/allein-daheim)"

Die aktuelle Version des Backends besitzt folgende Features:

- Topics speichern
- User speichern

## API-Routen (REST JSON)

### Topics

- `GET /api/topics` - Gibt alle Topics zurück
- `GET /api/topics/{id}` - Gibt das Topic mit der übergebenen id zurück

### Users

- `GET /api/users/{id}` - Gibt den Nutzer mit der übergebenen id zurück
- `POST /api/users/{id}/seen` - Aktualisiert den Last-seen Zeitpunkt des Nutzers auf die aktuelle Systemzeit des Servers

Aktuell wird die Datenbank mit Dummy-Daten befüllt.
Ein produktiver Einsatz ist daher nur bedingt möglich.