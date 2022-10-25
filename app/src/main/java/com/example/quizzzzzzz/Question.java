package com.example.quizzzzzzz;

public class Question {
    private int questionId;
    public boolean trueAnswer;

    public Question(int questionId, boolean trueAnswer) {
        this.questionId = questionId;
        this.trueAnswer = trueAnswer;
    }
    public boolean isTrueAnswer(){
            if(trueAnswer == true){
                return true;
            }
            if(trueAnswer == false){
                return false;
            }
        return false;
    }
    public int getQuestionId(){
        return questionId;
    }

}