package JSONManipulation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import  java.io.FileWriter;
import java.io.IOException;

import java.util.*;


public class QuizProgram {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);

        System.out.println("\nPress '1' to Add Quiz\nPress '2' to Start Quiz\n");

        int switchOption = input.nextInt();
        String enter;
        enter = input.nextLine();
        char ch;

        switch (switchOption){
            case 1:{

                do{
                    JSONParser jsonParser = new JSONParser();

                    Object obj = jsonParser.parse(new FileReader("./src/main/resources/QuesList.json"));

                    JSONArray jsonArray = (JSONArray) obj;

                    JSONObject QuesObject = new JSONObject();

                    System.out.println("\nPlease add a ques here:");
                    QuesObject.put("Question", input.nextLine());

                    System.out.println("\n~~ Insert options ~~\n");
                    for(int i=1; i<5; i++){
                        System.out.println("Option " + i + ": ");
                        QuesObject.put("Option " + i, input.nextLine());
                    }
                    System.out.println("\nPlease insert the correct answer: ");
                    QuesObject.put("answer", input.nextLine());

                    jsonArray.add(QuesObject);

                    FileWriter fileWriter = new FileWriter ("./src/main/resources/QuesList.json");
                    fileWriter.write(jsonArray.toString());
                    fileWriter.flush();

                    System.out.println("\nQuiz saved at the database. Do you want to add more? (y/n)");

                    ch = input.next().charAt(0);
                    enter = input.nextLine();
                }while(ch != 'n');
                break;
            }

            case 2:{

                JSONParser jsonParser = new JSONParser();
                Object obj = jsonParser.parse(new FileReader("./src/main/resources/QuesList.json"));

                JSONArray jArr = (JSONArray) obj;

                int size = jArr.size();
                int score = 0, i;

                ArrayList numbers = new ArrayList();
                for(i=0; i<size; i++){
                    numbers.add(i);
                }
                Collections.shuffle(numbers);

                System.out.println("\nYou'll be asked 5 Questions!\n");
                for(i=1; i<6; i++){

                    JSONArray jsonArray = (JSONArray) obj;
                    JSONObject QuesObject = new JSONObject();
                    JSONObject Qob = (JSONObject) jsonArray.get((Integer) numbers.get(i));

                    String ques = (String) Qob.get("Question");
                    String op1 = (String) Qob.get("Option 1");
                    String op2 = (String) Qob.get("Option 2");
                    String op3 = (String) Qob.get("Option 3");
                    String op4 = (String) Qob.get("Option 4");
                    String ans = (String) Qob.get("answer");

                    System.out.println("Question No. " + i + ": " + ques);
                    System.out.println("\nOption 1: " + op1);
                    System.out.println("Option 2: " + op2);
                    System.out.println("Option 3: " + op3);
                    System.out.println("Option 4: " + op4);

                    System.out.println("\nChoose option: ");
                    String userAnswer = "";

                    userAnswer = input.nextLine();

                    if(ans.equals(userAnswer)){
                        System.out.println("\nCorrect!\n");
                        score++;
                    }
                    else{
                        System.out.println("\nIncorrect!\n");
                    }
                }
                System.out.println("\nYour score is " + score + " out of 5.");

                break;
            }
        }
    }
}
