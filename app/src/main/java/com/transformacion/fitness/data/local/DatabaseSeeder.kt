package com.transformacion.fitness.data.local

import com.transformacion.fitness.data.local.entity.ExerciseEntity

/**
 * Clase para inicializar a base de datos cos 5 exercicios do prototipo HTML.
 * Estes exercicios son o punto de partida do adestramento básico de 15 minutos.
 */
object DatabaseSeeder {
    
    /**
     * Devolve a lista dos 5 exercicios iniciais do prototipo.
     * Todos están en galego e son seguros para principiantes.
     */
    fun getInitialExercises(): List<ExerciseEntity> {
        return listOf(
            ExerciseEntity(
                id = 1,
                name = "Flexións na parede",
                emoji = "🧱",
                description = "<strong>Como facelo:</strong> Pon as mans na parede ao ancho dos ombreiros. Inclina o corpo e <strong>dobra os cóbados</strong> ata que o nariz case toque a parede. <strong>Costas rectas</strong>, non arquear o lombo. Empurra para volver. (Si, é suave, así empezamos).",
                category = "empuxe",
                difficulty = 1,
                isBodyweight = true
            ),
            ExerciseEntity(
                id = 2,
                name = "Remo con botellas (Costas)",
                emoji = "💧",
                description = "<strong>Como facelo:</strong> Colle dúas botellas de auga (cheas). Inclina lixeiramente o tronco cara adiante, costas rectas. <strong>Leva as botellas cara ao peito</strong> xuntando as escápulas (costas). Baixa lentamente. <strong>Isto é para as túas costas e brazos</strong>.",
                category = "traccion",
                difficulty = 1,
                isBodyweight = false
            ),
            ExerciseEntity(
                id = 3,
                name = "Sentadilla con cadeira (Pernas)",
                emoji = "🪑",
                description = "<strong>Como facelo:</strong> Ponte diante da cadeira. Baixa o cu <strong>ata tocar lixeiramente</strong> o asento (non te sentes) e volve subir. <strong>Xeonllos apuntando cara adiante</strong>, non sobrepases as puntas dos pés. Queime nas pernas = estar a adelgazar.",
                category = "pernas",
                difficulty = 1,
                isBodyweight = true
            ),
            ExerciseEntity(
                id = 4,
                name = "Fondos en cadeira (Tríceps)",
                emoji = "💪",
                description = "<strong>Como facelo:</strong> Pon as mans no bordo da cadeira, costas de cara á cadeira, pernas estiradas. Baixa o corpo dobrando os cóbados <strong>ata un ángulo de 90°</strong> e sube. <strong>Non baixes máis</strong> para coidar os ombreiros. Brazos e costas traballan.",
                category = "empuxe",
                difficulty = 2,
                isBodyweight = true
            ),
            ExerciseEntity(
                id = 5,
                name = "Plancha de xeonllos (Núcleo)",
                emoji = "🔥",
                description = "<strong>Como facelo:</strong> Apóiate no chan cos antebrazos e os <strong>xeonllos</strong> (non puntas dos pés). Costas totalmente rectas, ombreiros sobre cóbados. <strong>Mantén 45 segundos</strong> sen arquear nin elevar o cu. Se che treme, perfecto, estás a gañar forza.",
                category = "core",
                difficulty = 2,
                isBodyweight = true
            )
        )
    }
}
