package com.muflihunnisa.indoquiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.muflihunnisa.indoquiz.R
import com.muflihunnisa.indoquiz.databinding.FragmentQuizBinding
import com.muflihunnisa.indoquiz.model.Quiz

@Suppress("UNREACHABLE_CODE")
class QuizFragment : Fragment() {
    private val questions: MutableList<Quiz> = mutableListOf(
        Quiz(
            image = "http://kbu-cdn.com/dk/wp-content/uploads/kerak-telor.jpg",
            text = "Where is this food from?",
            answers = listOf("Betawi", "Jogja", "Surabaya", "Solo")
        ),
        Quiz(
            image = "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2019/12/Hasyim-Asyari.jpg",
            text = "What is the name of this hero??",
            answers = listOf("Hasyim Asyari", "Ir. Soekarno", "BJ Habibie", "Ki Hadjar Dewantara")
        ),
        Quiz(
            image = "https://asset-a.grid.id//crop/0x0:0x0/700x0/photo/2019/10/22/1908794332.jpg",
            text = "What the name of this food?",
            answers = listOf("Kue Dongkal", "Klepon", "Kue Putu", "Risol")
        ),
        Quiz(
            image = "https://www.sembodorentcar.co.id/images/tour_package_detail/related/Candi_Borobudur1.jpg",
            text = "Which province is Yogyakarta in??",
            answers = listOf("Jawa Tengah", "Jawa Timur", "Jawa Barat", "Banten")
        ),
        Quiz(
            image = "https://cdn.idntimes.com/content-images/post/20200813/pixlr-20200813005845448-bf77501c890c4b693a180fee9ce50d19_600x400.jpg",
            text = "who is a pioneering figure in women's education from Bandung??",
            answers = listOf(
                "Ratna Dewi Sartika",
                "Fatmawati Soekarno Putri",
                "Iwa Kusuma Sumantri",
                "Cut Nyak Dien"
            )
        ),
        Quiz(
            image = "https://oriflameid.com/wp-content/uploads/2021/07/Bagaimana-Aturan-Permainan-Benteng-Bentengan-dan-Gobak-Sodor.jpg",
            text = "What is the name of this game?",
            answers = listOf("Gobak Sodor", "Enggrang", "Petak Umpet", "Lompat Tali")
        ),
        Quiz(
            image = "https://mmc.tirto.id/image/otf/500x0/2017/05/31/PPKI_ratio-16x9.jpg",
            text = "When was BPUPKI formed?",
            answers = listOf(
                "29 April 1945",
                "15 Mei 1945",
                "23 April 1945",
                "10 Maret 1945"
            )
        ),
        Quiz(
            image = "https://gurune.net/wp-content/uploads/2019/06/PEta2BIndonesia2BPropinsi2BEnglish.png",
            text = "How many provinces are there in Indonesia??",
            answers = listOf("34", "37", "32", "30")
        ),
        Quiz(
            image = "https://www.malukunews.co/cache/general/original/f/w/fwj1agp1cevq2c0j5rnxg.jpeg",
            text = "What is the typical food of the Maluku region?",
            answers = listOf("Woku komo-komo", "kerak telor", "sate kambing", "sate buntel")
        ),
        Quiz(
            image = "https://i0.wp.com/sebandung.com/wp-content/uploads/2015/03/Oray-orayan.jpg",
            text = "what is the game from the west java area?",
            answers = listOf("Oray-orayan", "Lompat tali", "bola bekel", "Patok Lele")
        ),
        Quiz(
            image = "https://img.beritasatu.com/cache/beritasatu/600x350-2/411436861634.jpg",
            text = "What is the highest mountain in indonesia?",
            answers = listOf("Puncak Jaya", "Gunung Kerinci", "Gunung Papandayan", "Gunung Sumantri")
        )
    )
    private var questionIndex = 0
    lateinit var currentQuestions: Quiz
    lateinit var answers: MutableList<String>
    private val numberIndicatorQuestion = Math.min((questions.size + 1) / 2, 4)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val quizBinding = DataBindingUtil.inflate<FragmentQuizBinding>(
            inflater,
            R.layout.fragment_quiz,
            container,
            false
        )
        randomQuestion()
        quizBinding.quiz = this
        quizBinding.btnSubmit.setOnClickListener { view: View ->
            val checkedId = quizBinding.rgQuiz.checkedRadioButtonId
            if (-1 != checkedId) {
                var answerCorrectPosition = 0
                when (checkedId) {
                    R.id.rb_option_2 -> answerCorrectPosition = 1
                    R.id.rb_option_3 -> answerCorrectPosition = 2
                    R.id.rb_option_4 -> answerCorrectPosition = 3
                }
                if (answers[answerCorrectPosition] == currentQuestions.answers[0]) {
                    questionIndex++
                    if (questionIndex < numberIndicatorQuestion) {
                        currentQuestions = questions[questionIndex]
                        setNumberQuestion()
                        quizBinding.invalidateAll()
                    } else {
                        view.findNavController().navigate(R.id.action_quizFragment_to_wonFragment)
                    }
                } else {
                    view.findNavController().navigate(R.id.action_quizFragment_to_loseFragment)
                }
            }
            Glide.with(this@QuizFragment).load(questions[questionIndex].image).centerCrop().into(quizBinding.ivPicture)
        }
        return quizBinding.root
    }

    private fun randomQuestion() {
        questions.shuffle()
        questionIndex = 0
        setNumberQuestion()
    }

    private fun setNumberQuestion() {
        currentQuestions = questions[questionIndex]
        answers = currentQuestions.answers.toMutableList()
        answers.shuffle()

        (activity as AppCompatActivity).supportActionBar?.title = getString(
            R.string.title_quezzz,
            questionIndex + 1,
            numberIndicatorQuestion
        )
    }

}