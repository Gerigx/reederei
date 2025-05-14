-- Schiffe einfügen
INSERT INTO Ships (id, name, state) VALUES (1, 'MS Titanic II', 'BOOKED');
INSERT INTO Ships (id, name, state) VALUES (2, 'MS Unsinkbar', 'BOOKED');
INSERT INTO Ships (id, name, state) VALUES (3, 'MS Ninja', 'BOOKED');
INSERT INTO Ships (id, name, state) VALUES (4, 'MS Black Pearl', 'BOOKED');
INSERT INTO Ships (id, name, state) VALUES (5, 'MS Flying Dutchman', 'BOOKED');
INSERT INTO Ships (id, name, state) VALUES (6, 'MS Enterprise', 'BOOKED');
INSERT INTO Ships (id, name, state) VALUES (7, 'MS Millennium Falcon', 'FREE');
INSERT INTO Ships (id, name, state) VALUES (8, 'MS Nautilus', 'FREE');
INSERT INTO Ships (id, name, state) VALUES (9, 'MS Poseidon', 'FREE');
INSERT INTO Ships (id, name, state) VALUES (10, 'MS Queen Anne', 'FREE');
INSERT INTO Ships (id, name, state) VALUES (11, 'MS Bismarck', 'FREE');

-- Sequence für die ID der Schiffe setzen (wichtig für auto-increment)
ALTER SEQUENCE ships_seq RESTART WITH 12;

-- Aufträge einfügen mit Schiffszuweisungen
-- Beachte: Das Date-Feld wird automatisch durch @CreationTimestamp gefüllt
INSERT INTO Orders (id, description, ship) VALUES (1, 'Transport von Luxusgütern nach Rotterdam', '/ship/1');
INSERT INTO Orders (id, description, ship) VALUES (2, 'Containertransport nach Hamburg', '/ship/2');
INSERT INTO Orders (id, description, ship) VALUES (3, 'Kreuzfahrt in der Karibik', '/ship/3');
INSERT INTO Orders (id, description, ship) VALUES (4, 'Geheime Fracht nach Singapur', '/ship/4');
INSERT INTO Orders (id, description, ship) VALUES (5, 'Windkraftanlagen nach Dänemark', '/ship/5');
INSERT INTO Orders (id, description, ship) VALUES (6, 'Autotransport nach Japan', '/ship/6');

-- Sequence für die ID der Aufträge setzen
ALTER SEQUENCE orders_seq RESTART WITH 7;