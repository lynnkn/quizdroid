package edu.us.ischool.quizdroid

class QuizData : TopicRepository {
    private val topicList: MutableList<Topic> = mutableListOf()

    init {
        // adding hard-coded data
        val mathQuestions = listOf(
            Quiz("What does 4 + 4 equal?", listOf("4", "16", "8", "44"), 2),
            Quiz("What does 5 / 0 equal?", listOf("5", "0", "50", "undefined"), 3),
            Quiz("What does 2^5 equal?", listOf("32", "10", "7", "64"), 0)
        )
        val math = Topic(
            "Math",
            "This topic will test you on some basic arithmetical math problems. Don't worry about "
                    + "seeing any calculus here!",
            R.drawable.math_img,
            mathQuestions
        )
        val physicsQuestions = listOf(
            Quiz("What is the acceleration of gravity on earth?", listOf("9.8 m/s", "9.8 m/s^2", "9.6 m/s^2", "8.9 m/s^2"), 1),
            Quiz("How many of Newton's Laws of Motion are there?", listOf("5", "1", "3", "2"), 2),
            Quiz("Does light behave as a particle or a wave?", listOf("Particle", "Wave", "Neither", "Both"), 3)
        )
        val physics = Topic(
            "Physics",
            "This topic will be some physics related trivia and facts. You won't need any mathematical "
                    + "formulas to solve these questions.",
            R.drawable.physics_img,
            physicsQuestions
        )
        val marvelQuestions = listOf(
            Quiz("Which of these characters is not a Marvel superhero?", listOf("Spider-Man", "Scarlet Witch", "Shang Chi", "Starfire"), 3),
            Quiz("Who dies in Avengers: Endgame?", listOf("Iron Man", "Captain America", "Gamora", "Spider-Man"), 0),
            Quiz("Which of these characters is not part of the Avengers?", listOf("Thor", "Black Widow", "Gwen Stacy", "Hulk"), 2)
        )
        val marvel = Topic(
            "Marvel Super Heroes",
            "This topic will test you on your knowledge of Marvel Super Heroes and the Marvel Cinematic "
                    + "Universe.",
            R.drawable.marvel_img,
            marvelQuestions
        )
        val pokemonQuestions = listOf(
            Quiz("How many original Pokemon are there?", listOf("151", "150", "149", "200"), 0),
            Quiz("Who is Pokemon's mascot?", listOf("Eevee", "Jigglypuff", "Bidoof", "Pikachu"), 3),
            Quiz("Which of these Pokemon is not a fire type?", listOf("Charizard", "Samurott", "Rapidash", "Ninetales"), 1)
        )
        val pokemon = Topic(
            "Pokemon",
            "This topic will test your knowledge of Pokemon trivia. Do you have what it takes to become the next "
                    + "Pokemon master?",
            R.drawable.pokemon_img,
            pokemonQuestions
        )

        // adding topics to repository
        addTopic(math)
        addTopic(physics)
        addTopic(marvel)
        addTopic(pokemon)
    }

    // getter methods
    override fun getAllTopics() : List<Topic> {
        return topicList
    }

    override fun getTopic(index: Int) : Topic {
        return topicList[index]
    }

    override fun addTopic(topic: Topic) {
        topicList.add(topic)
    }

    override fun updateTopic(index: Int, update: Topic) {
        topicList[index] = update
    }

    override fun removeTopic(index: Int) {
        topicList.removeAt(index)
    }
}

