package glupak.m.VictorinaCore;

public class Question {

    public String question;
    public String answer;
    public String hint;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;

        int length = this.answer.length();
        char[] hintArray;

        hintArray = new char[length];
        for (int i = 0; i < hintArray.length; i++) {
            hintArray[i] = '-';
        }
        hint = new String(hintArray);
    }

    public String GenerateHint() {
        int length = answer.length();
        char[] hintArray = hint.toCharArray();

        boolean isCorrectHint = false;
        while (!isCorrectHint) {
            int possition = (int) (Math.random() * length);
            if (hintArray[possition] == '-') {
                hintArray[possition] = answer.toCharArray()[possition];
                isCorrectHint = true;
            }
        }

        this.hint = new String(hintArray);
        return hint;
    }
}
