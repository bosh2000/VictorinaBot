package glupak.m.VictorinaCore;

import glupak.m.MySqlCore.MySqlConnect;
import glupak.m.MySqlCore.MySqlExecute;

public class Victorina {

    private MySqlConnect mySqlConnect;
    private MySqlExecute mySqlExecute;

    public void init() {
        mySqlConnect=new MySqlConnect();
        mySqlExecute=new MySqlExecute(mySqlConnect.connection);

    }

    public Question getQuestion(){

        return mySqlExecute.getQuestion();
    }

    public boolean checkAnswer(Question question){
        return true;
    }

    public void Close(){
        mySqlConnect.close();
    }

}
