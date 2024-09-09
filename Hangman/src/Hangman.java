import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Hangman {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String ans = "y";
        while (ans.equalsIgnoreCase("y")){
           play();
           System.out.println("Do you want to play again? Y or N");
           ans = input.nextLine();
        }
    }

    public static void play(){
        File file = new File("src/WordList.txt");
        String word ="";
        System.out.println(file.getAbsolutePath());
        try{
            Scanner scan = new Scanner(file);
            for (int i =0;i<(int)(1525*Math.random());i++)
                word = scan.next();
            int counter = 0;
            String wordReveal="";
            for (int i =0;i<word.length();i++)
                wordReveal+="_";
            System.out.println(body(counter,wordReveal));
            ArrayList<String> lst = new ArrayList<String>();
            while (counter<6){
                Scanner input = new Scanner(System.in);
                System.out.println("Choose Your Letter");
                String letter = input.nextLine();
                while(lst.contains(letter) || letter.length()>1){
                    System.out.println("This letter has already been chosen OR multiple letters were typed. Choose a new letter.");
                    letter = input.nextLine();
                }
                lst.add(letter);
                if (!word.contains(letter)){
                    counter++;
                }else{
                    String temp = word;
                    while(temp.contains(letter)){
                        int num = temp.indexOf(letter);
                        if(num==0){
                            wordReveal = letter + wordReveal.substring(1);
                            temp = "*" +temp.substring(1);

                        }else{
                            wordReveal = wordReveal.substring(0,num) + letter + wordReveal.substring(num+1);
                            temp = temp.substring(0,num) + "*"+ word.substring(num+1);
                        }

                    }
                }
                System.out.println(body(counter,wordReveal));
                if(wordReveal.equals(word)){
                 System.out.println("Congrats!");
                 break;
                }
            }
            if(counter==6)
                System.out.println("Game Over :( The word was "+word);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String body(int num,String outputWord){
        switch(num){
            case 1:
                return "___________\n|         |\n|         0\n|\n|\n|\n|  " +outputWord;
            case 2:
                return "___________\n|         |\n|         0\n|       \\ \n|\n|\n|  "+outputWord;
            case 3:
                return "___________\n|         |\n|         0\n|       \\   / \n|\n|\n|  "+outputWord;
            case 4:
                return "___________\n|         |\n|         0\n|       \\ | / \n|         | \n|\n|  "+outputWord;
            case 5:
                return "___________\n|         |\n|         0\n|       \\ | / \n|         | \n|        /\n|  "+outputWord;
            case 6:
                return "___________\n|         |\n|         0\n|       \\ | / \n|         | \n|        / \\ \n|  "+outputWord;
            default:
                return "___________\n|         |\n|\n|\n|\n|\n|  "+outputWord;

        }
    }
}
