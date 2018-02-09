package glupak.m.MySqlCore;

import glupak.m.VictorinaCore.Question;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class MySqlExecute {

    private final String SQL="SELECT question,answer FROM question WHERE id=?";
    private final String SQL_COUNT_RECORD="SELECT COUNT(*) FROM question";
    private Connection connection;

    public MySqlExecute(Connection connection){
        this.connection=connection;
    }

    public Question getQuestion(){
        int countQuestion=getCountQuestion();
        Random random=new Random();
        Question question=null;//=new Question();
        int indexQuestion=random.nextInt(countQuestion);
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setInt(1,indexQuestion);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            question=new Question(resultSet.getString("question"),resultSet.getString("answer"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return question;
    }

    public int getCountQuestion(){
        int returnValue=0;
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(SQL_COUNT_RECORD);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            returnValue=resultSet.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return returnValue;
    }



}
