# Base de datos seed para exercicios iniciais

# Esta base de datos contén os 5 exercicios do prototipo orixinal:
# 1. Flexións na parede (empuxe, dificultade 1)
# 2. Remo con botellas (tracción, dificultade 2)
# 3. Sentadilla con cadeira (pernas, dificultade 2)
# 4. Fondos en cadeira (empuxe/tríceps, dificultade 2)
# 5. Plancha de xeonllos (core, dificultade 2)

# A base de datos créase manualmente ou mediante un script SQL.
# Para crear o ficheiro .db:
# 1. Usar DB Browser for SQLite ou similar
# 2. Crear táboa 'exercises' co esquema:
#    - id INTEGER PRIMARY KEY
#    - name TEXT NOT NULL
#    - description TEXT NOT NULL
#    - emoji TEXT NOT NULL
#    - category TEXT NOT NULL
#    - difficulty INTEGER NOT NULL
#    - isBodyweight INTEGER NOT NULL
#    - createdAt INTEGER NOT NULL
# 3. Insertar os 5 exercicios
# 4. Gardar como seed_exercises.db no directorio assets/database/

# SQL para crear a táboa e insertar datos:
/*
CREATE TABLE exercises (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    emoji TEXT NOT NULL,
    category TEXT NOT NULL,
    difficulty INTEGER NOT NULL,
    isBodyweight INTEGER NOT NULL DEFAULT 1,
    createdAt INTEGER NOT NULL
);

INSERT INTO exercises VALUES (1, 'Flexións na parede', '<strong>Como facelo:</strong> Pon as mans na parede ao ancho dos ombreiros. Inclina o corpo e <strong>dobra os cóbados</strong> ata que o nariz case toque a parede. <strong>Costas rectas</strong>, non arquear o lombo. Empurra para volver. (Si, é suave, así empezamos).', '🧱', 'empuxe', 1, 1, 1700000000000);
INSERT INTO exercises VALUES (2, 'Remo con botellas (Costas)', '<strong>Como facelo:</strong> Colle dúas botellas de auga (cheas). Inclina lixeiramente o tronco cara adiante, costas rectas. <strong>Leva as botellas cara ao peito</strong> xuntando as escápulas (costas). Baixa lentamente. <strong>Isto é para as túas costas e brazos</strong>.', '💧', 'traccion', 2, 1, 1700000000000);
INSERT INTO exercises VALUES (3, 'Sentadilla con cadeira (Pernas)', '<strong>Como facelo:</strong> Ponte diante da cadeira. Baixa o cu <strong>ata tocar lixeiramente</strong> o asento (non te sentes) e volve subir. <strong>Xeonllos apuntando cara adiante</strong>, non sobrepases as puntas dos pés. Queime nas pernas = estar a adelgazar.', '🪑', 'pernas', 2, 1, 1700000000000);
INSERT INTO exercises VALUES (4, 'Fondos en cadeira (Tríceps)', '<strong>Como facelo:</strong> Pon as mans no bordo da cadeira, costas de cara á cadeira, pernas estiradas. Baixa o corpo dobrando os cóbados <strong>ata un ángulo de 90°</strong> e sube. <strong>Non baixes máis</strong> para coidar os ombreiros. Brazos e costas traballan.', '💪', 'empuxe', 2, 1, 1700000000000);
INSERT INTO exercises VALUES (5, 'Plancha de xeonllos (Núcleo)', '<strong>Como facelo:</strong> Apóiate no chan cos antebrazos e os <strong>xeonllos</strong> (non puntas dos pés). Costas totalmente rectas, ombreiros sobre cóbados. <strong>Mantén 45 segundos</strong> sen arquear nin elevar o cu. Se che treme, perfecto, estás a gañar forza.', '🔥', 'core', 2, 1, 1700000000000);
*/

# NOTA PARA O DESENVOLVEDOR:
# Este ficheiro é un marcador de posición. Debes crear o ficheiro .db real
# usando as instrucións SQL anteriores. Alternativamente, podes modificar
# AppDatabase.kt para eliminar .createFromAsset() e inicializar os datos
# programaticamente na primeira execución.
