package com.mstfahlal.examate_tcf.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import com.mstfahlal.examate_tcf.R
import com.mstfahlal.examate_tcf.domain.model.connectios.ConnectItemModel
import com.mstfahlal.examate_tcf.domain.model.connectios.UserModel
import com.mstfahlal.examate_tcf.domain.model.progress.ProgressStepItemData
import com.mstfahlal.examate_tcf.domain.model.questions.OralQuestionsModel
import com.mstfahlal.examate_tcf.domain.model.questions.WritingQuestionsModel

val listItem = listOf(
    ProgressStepItemData(1, "Unite 1: what is Examate ?", false, 1f, false),
    ProgressStepItemData(2, "Unite 2: what is TCF ?", true, 0f, true),
    ProgressStepItemData(3, "Unite 3:What is Writing Tasks ?", true, 0f, true),
    ProgressStepItemData(4, "Unite 4:What is Oral Task ?", true, 0f, true),
    ProgressStepItemData(5, "Unite 5:Listening Comprehension", true, 0f, true),
    ProgressStepItemData(6, "Unite 6:Reading Comprehension", true, 0f, true),
)
val connectionsList = listOf(
    ConnectItemModel(
        userName = "Mostafa Helal",
        image = "https://upload.wikimedia.org/wikipedia/commons/4/41/Sunflower_from_Silesia2.jpg",
        target = "B2",
        userInfo = listOf(
            UserModel("Cairo", Icons.Outlined.LocationOn),
            UserModel("Male", Icons.Outlined.Person),
            UserModel("26", Icons.Outlined.DateRange),
            UserModel("14/03/2024", Icons.Outlined.DateRange)
        ),
        languages = listOf("Arabic", "English")
    ),
    ConnectItemModel(
        userName = "Ahmed Helal",
        image = "https://upload.wikimedia.org/wikipedia/commons/a/a5/Red_Kitten_01.jpg",
        target = "B2",
        userInfo = listOf(
            UserModel("Cairo", Icons.Outlined.LocationOn),
            UserModel("Male", Icons.Outlined.Person),
            UserModel("26", Icons.Outlined.DateRange),
            UserModel("14/03/2024", Icons.Outlined.DateRange)
        ),
        languages = listOf("Arabic", "French")
    ),
    ConnectItemModel(
        userName = "lama Helal",
        image = "https://i0.wp.com/preetpalk.wordpress.com/wp-content/uploads/2018/08/mini1.jpg?w=2500&h=&ssl=1",
        target = "C1",
        userInfo = listOf(
            UserModel("Cairo", Icons.Outlined.LocationOn),
            UserModel("Female", Icons.Outlined.Person),
            UserModel("24", Icons.Outlined.DateRange),
            UserModel("05/05/2019", Icons.Outlined.DateRange)
        ),
        languages = listOf("Arabic", "English", "Spanish")
    ),
    ConnectItemModel(
        userName = "saad mahmoud",
        image = "https://upload.wikimedia.org/wikipedia/commons/a/a5/Red_Kitten_01.jpg",
        target = "B1",
        userInfo = listOf(
            UserModel("New York", Icons.Outlined.LocationOn),
            UserModel("Male", Icons.Outlined.Person),
            UserModel("35", Icons.Outlined.DateRange),
            UserModel("01/01/2020", Icons.Outlined.DateRange)
        ),
        languages = listOf("English", "Spanish")
    ),
    ConnectItemModel(
        userName = "Lyan Mahmoud",
        image = "https://images.pexels.com/photos/6836442/pexels-photo-6836442.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        target = "C2",
        userInfo = listOf(
            UserModel("Florida", Icons.Outlined.LocationOn),
            UserModel("Female", Icons.Outlined.Person),
            UserModel("29", Icons.Outlined.DateRange),
            UserModel("02/02/2021", Icons.Outlined.DateRange)
        ),
        languages = listOf("English", "French")
    )
)

val questionsList = listOf(
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Events", "Task 2"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Technology", "Task 3"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Culture", "Task 3"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Technology", "Task 3"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Technology", "Task 3"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Technology", "Task 3"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Technology", "Task 3"),
        date = "13 May 2023"
    ),
)

val writingsList = listOf(
    WritingQuestionsModel(
        type = "Voyage",
        answersCount = 10,
        questionsCount = 10,
        progress = 100f,
        icon = R.drawable.ic_travel
    ),
    WritingQuestionsModel(
        type = "Immigration",
        answersCount = 6,
        questionsCount = 10,
        progress = 50f,
        icon = R.drawable.ic_travel
    ),
    WritingQuestionsModel(
        type = "Technologie",
        answersCount = 4,
        questionsCount = 10,
        progress = 60f,
        icon = R.drawable.ic_tech
    ),
    WritingQuestionsModel(
        type = "Art et Culture",
        answersCount = 4,
        questionsCount = 10,
        progress = 70f,
        icon = R.drawable.ic_art
    ),
    WritingQuestionsModel(
        type = "Environm-ent ",
        answersCount = 5,
        questionsCount = 10,
        progress = 40f,
        icon = R.drawable.ic_enviroment
    ),
    WritingQuestionsModel(
        type = "Travel",
        answersCount = 3,
        questionsCount = 10,
        progress = 20f,
        icon = R.drawable.ic_travel
    )
)