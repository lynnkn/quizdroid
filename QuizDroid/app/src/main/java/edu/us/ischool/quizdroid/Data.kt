package edu.us.ischool.quizdroid

class Data : TopicRepository {
    private val topicList: MutableList<Topic> = mutableListOf()

    // getter methods
    override fun getTopics() : List<Topic> {
        return topicList
    }

    override fun addTopic(topic: Topic) {
        topicList.add(topic)
    }
}

fun initializeData() : List<Topic> {
    val topicData = Data()

    // adding hard-coded data


    return topicData.getTopics()
}

//class Data {
//    // topic labels
//    private var topicLabels: Array<String> = arrayOf(
//        "Math", "Physics", "Marvel Super Heroes", "Pokemon"
//    )
//
//    // topic images
//    private var topicImages: Array<Int> = arrayOf(
//        R.drawable.math_img, R.drawable.physics_img, R.drawable.marvel_img, R.drawable.pokemon_img
//    )
//
//    // topic descriptions
//    private var topicOverviews: Array<String> = arrayOf(
//        "This topic will test you on some basic arithmetical math problem. Don't worry about "
//            + "seeing any calculus here! \n \n There will be a total of 3 questions in this topic.",
//        "This topic will be some physics related trivia and facts. You won't need any mathematical "
//            + "formulas to solve these questions. \n \n There will be a total of 3 questions in this topic.",
//        "This topic will test you on your knowledge of Marvel Super Heroes and the Marvel Cinematic "
//            + "Universe. \n \n There will be a total of 3 questions in this topic.",
//        "This topic will test your knowledge of Pokemon trivia. Do you have what it takes to become the next "
//            + "Pokemon master? \n \n There will be a total of 3 questions in this topic."
//    )
//
//    // topic questions
//    // format: question, choice 1, choice 2, choice 3, choice 4, answer
//    private var mathQuestions: Array<Array<String>> = arrayOf(
//        arrayOf("What does 4 + 4 equal?", "4", "16", "8", "44", "8"),
//        arrayOf("What does 5 / 0 equal?", "5", "0", "50", "undefined", "undefined"),
//        arrayOf("What does 2^5 equal?", "32", "10", "7", "64", "32")
//    )
//    private var physicsQuestions: Array<Array<String>> = arrayOf(
//        arrayOf("What is the acceleration of gravity on earth?", "9.8 m/s", "9.8 m/s^2", "9.6 m/s^2", "8.9 m/s^2", "9.8 m/s^2"),
//        arrayOf("How many of Newton's Laws of Motion are there?", "5", "1", "3", "2", "3"),
//        arrayOf("Does light behave as a particle or a wave?", "Particle", "Wave", "Neither", "Both", "Both")
//    )
//    private var marvelQuestions: Array<Array<String>> = arrayOf(
//        arrayOf("Which of these characters is not a Marvel superhero?", "Spider-Man", "Scarlet Witch", "Shang Chi", "Starfire", "Starfire"),
//        arrayOf("Who dies in Avengers: Endgame?", "Iron Man", "Captain America", "Gamora", "Spider-Man", "Iron Man"),
//        arrayOf("Which of these characters is not part of the Avengers?", "Thor", "Black Widow", "Gwen Stacy", "Hulk", "Gwen Stacy")
//    )
//    private var pokemonQuestions: Array<Array<String>> = arrayOf(
//        arrayOf("How many original Pokemon are there?", "151", "150", "149", "200", "151"),
//        arrayOf("Who is Pokemon's mascot?", "Eevee", "Jigglypuff", "Bidoof", "Pikachu", "Pikachu"),
//        arrayOf("Which of these Pokemon is not a fire type?", "Charizard", "Samurott", "Rapidash", "Ninetales", "Samurott")
//    )
//
//    // total question list
//    private var totalQuestions: Array<Array<Array<String>>> = arrayOf(
//        mathQuestions, physicsQuestions, marvelQuestions, pokemonQuestions
//    )
//
//    // getter methods
//    fun getLabels() : Array<String> {
//        return topicLabels
//    }
//
//    fun getImage(index: Int) : Int {
//        return topicImages[index]
//    }
//
//    fun getOverview(index: Int) : String {
//        return topicOverviews[index]
//    }
//
//    fun getQuestions(index: Int): Array<Array<String>> {
//        return totalQuestions[index]
//    }
//}