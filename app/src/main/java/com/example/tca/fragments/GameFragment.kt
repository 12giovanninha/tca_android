package com.example.tca.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.tca.R
import com.example.tca.UserRoomController
import com.example.tca.GameRoomController
import com.example.tca.adapters.AnswerAdapter
import com.example.tca.dao.GameDAO
import com.example.tca.models.game.CurrentProblem
import com.example.tca.models.game.NextProblem
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment(), View.OnClickListener {

    private val dao: GameDAO = GameDAO()
    private lateinit var answerAdapter: AnswerAdapter
    var nav: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_game, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nav = Navigation.findNavController(view)

        btnFinishGame.setOnClickListener(this)
        btnSendAnswer.setOnClickListener(this)
        btnNextProblem.setOnClickListener(this)

        initialize()
    }

    private fun initialize() {
        progressBarGame.visibility = View.VISIBLE
        visibility()

        startGame()
    }

    private fun startGame() {
        val user = UserRoomController.getUser(requireActivity())
        val game = GameRoomController.getGame(requireActivity())
        dao.findOrCreate(game.difficulty, game.outCategory?.id, user.token,  { dataAPI ->
            if (dataAPI.data?.game?.creation == "new_game") { nextProblem() }
            else if (dataAPI.data?.game?.creation == "existing_game") { currentProblem() }
        },{
            progressBarGame.visibility = View.GONE
            Toast.makeText(requireActivity(), "Error on Servidor", Toast.LENGTH_SHORT)
        })
    }

    private fun currentProblem(){
        val user = UserRoomController.getUser(requireActivity())
        dao.findCurrentProblem(user.token, { problemAPI ->
            setCurrentProblem(problemAPI)
        }, {
            nextProblem()
        })
    }

    private fun nextProblem() {
        btnNextProblem.visibility = View.GONE
        progressBarAnswer.visibility = View.GONE
        val user = UserRoomController.getUser(requireActivity())
        dao.findNextProblem(user.token, { problemAPI ->
            setNextProblem(problemAPI)
        }, {
            progressBarGame.visibility = View.GONE
            Toast.makeText(requireActivity(), "Error on Servidor", Toast.LENGTH_SHORT)
        })
    }

    private fun setNextProblem(problem: NextProblem) {
        val response = problem.data?.problem?.question!!.toString()
        tvProblem.text = response

        answerAdapter = AnswerAdapter(problem.data?.problem?.answers!!)
        listAnswers.adapter = answerAdapter
        listAnswers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setVisibleAllItems(View.VISIBLE)
        progressBarGame.visibility = View.GONE
        showButtons(true)
    }

    private fun setCurrentProblem(problem: CurrentProblem) {
        tvProblem.text = problem.data?.problem?.question!!.toString()

        answerAdapter = AnswerAdapter(problem.data?.problem?.answers!!)
        listAnswers.adapter = answerAdapter
        listAnswers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setVisibleAllItems(View.VISIBLE)
        progressBarGame.visibility = View.GONE
        showButtons(true)
    }

    private fun setVisibleAllItems(visible: Int) {
        btnSendAnswer.visibility = visible
        btnFinishGame.visibility = visible
        tvProblem.visibility = visible
        listAnswers.visibility = visible
    }

    private fun showButtons(enable: Boolean) {
        btnSendAnswer.isEnabled = enable
        btnFinishGame.isEnabled = enable
    }

    private fun hideButtons(visible: Int) {
        btnSendAnswer.visibility = visible
        btnFinishGame.visibility = visible
    }

    private fun finishGame() {
        val user = UserRoomController.getUser(requireActivity())
        dao.finishGame(user.token, { gameAPI ->
            nav!!.navigate(R.id.action_gameFragment_to_finishGameFragment)
        },{
            Toast.makeText(requireActivity(), "Error on Servidor", Toast.LENGTH_SHORT)
        })
    }

    private fun sendAnswer() {
        val user = UserRoomController.getUser(requireActivity())
        val selectAnswer = answerAdapter.getSelectedItem() ?: return

        showButtons(false)
        hideButtons(View.GONE)

        progressBarAnswer.visibility = View.VISIBLE

        dao.sendAnswer(selectAnswer!!.order, user.token, { res ->
            if (res.data.answer.isCorrect()) {
                answerAdapter.setCorrect(selectAnswer)
                GameRoomController.addAssert(requireActivity())
            } else {
                answerAdapter.setError(selectAnswer)
                answerAdapter.setCorrect(res.data.answer.correctAnswer)
                GameRoomController.addError(requireActivity())
            }
            GameRoomController.setScore(requireActivity(), res.data.answer.score)
            btnNextProblem.visibility = View.VISIBLE
            progressBarAnswer.visibility = View.GONE
        },{
            Toast.makeText(requireActivity(), "Error on Servidor", Toast.LENGTH_SHORT)
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSendAnswer -> {
                sendAnswer()
            }
            R.id.btnFinishGame -> {
                finishGame()
            }
            R.id.btnNextProblem -> {
                nextProblem()
            }
            else -> {
            }
        }
    }

    private fun visibility(){
        setVisibleAllItems(View.GONE)
        showButtons(false)
        btnNextProblem.visibility = View.GONE
        progressBarAnswer.visibility = View.GONE
    }

}
