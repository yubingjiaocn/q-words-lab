package com.example.qwords.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.qwords.model.Word;
import com.example.qwords.model.GameStatus;
import com.example.qwords.service.WordSelectionService;

import io.micrometer.core.lang.Nullable;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class GameController {

    private WordSelectionService wordBank;
    private String selected;
    private Word word;

    @GetMapping("/game")
    public String index(@RequestParam String user, Model model) {

        wordBank = new WordSelectionService();
        selected = wordBank.getWord();
        word = new Word(selected);

        log.info(user);

        int attempts = getAttempts(model);

        String result = "";

        //Set View Attributes
        model.addAttribute("word", word.getWord());
        model.addAttribute("message", "Make your first guess!");
        model.addAttribute("attempts", attempts);
        model.addAttribute("result", result);
        model.addAttribute("status", GameStatus.INPROGRESS);

        return "game";
    }

    @PostMapping("/game")
    public String makeGuess(String guess, int attempts, Model model) {

        String result = word.getInfo(guess);
        attempts = addAttempt(attempts);
        
        model.addAttribute("result", result);
        model.addAttribute("attempts", attempts);
        model.addAttribute("guess", guess);

        if (word.isCorrect(guess.toCharArray())) {
            model.addAttribute("message", "Congratulations! You guessed correctly");
            model.addAttribute("status", GameStatus.SUCCESS);
        } else {

            if (attempts >= 5) {
                model.addAttribute("message", "Sorry, you've reached the maximum number of attempts.");
                model.addAttribute("status", GameStatus.FAILED);
            } else {
                model.addAttribute("message", "Try again. Your next guess:");
                model.addAttribute("status", GameStatus.INPROGRESS);
            }
        }

        return "game";
    }

    private int getAttempts(Model model) {
        Integer attempts = (Integer) model.getAttribute
        ("attempts");
        return (attempts != null) ? attempts : 0;
    }

    private int addAttempt(@Nullable Integer attempt) {
        return attempt + 1;
    }

}
