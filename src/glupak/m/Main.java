package glupak.m;

import glupak.m.MySqlCore.MySqlConnect;
import glupak.m.VictorinaCore.Question;
import glupak.m.VictorinaCore.Victorina;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class Main extends TelegramLongPollingBot {


    private static Victorina victorina;
    private Question question;
    private static boolean isQuestion;
    private static boolean isAnswer;

    public static void main(String[] args) {

        victorina = new Victorina();
        victorina.init();
        isQuestion = true;
        isAnswer = false;
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Main());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "VictorinBot";
    }

    @Override
    public String getBotToken() {
        return "478720146:AAG__J_ykz_iIC3Yv5s1bJ5XVKyj992S6lY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (isAnswer) {
            question = victorina.getQuestion();
            isAnswer = false;
        }
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help"))
                sendMsg(message, "Привет, я робот");
            else {
                if (isQuestion) {
                    sendMsg(message, question.question);
                    isQuestion = false;
                }
                if (message.getText().equalsIgnoreCase(question.answer)){
                    sendMsg(message,"Правильный ответ!");
                    sendMsg(message,victorina.getQuestion().question);
                    isAnswer = true;
                }

            }
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}