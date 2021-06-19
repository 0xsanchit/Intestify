package com.sanchit.intestify;

public class Questions {
    private String CorrectAnswer,Option1,Option2,Option3,Option4,Question;

    public Questions() {
    }

    public Questions(String correctAnswer, String option1, String option2, String option3, String option4, String question) {
        CorrectAnswer = correctAnswer;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
        Question = question;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public String getOption4() {
        return Option4;
    }

    public void setOption4(String option4) {
        Option4 = option4;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
