import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    static ArrayList<String> participantArr = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> participantBallArr = new ArrayList<>();
    static ArrayList<String> participantTeamMembers = new ArrayList<>();

    static int getPositiveInput(String innerText) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(innerText);
                int userInput = Integer.parseInt(scanner.nextLine());
                if (userInput >= 2) {
                    return userInput;
                } else {
                    System.out.println("Noto'g'ri qiymat. Kamida 2 yoki undan yuqori son kiriting.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Noto'g'ri qiymat. Faqat son kiriting.");
            }
        }
    }

    static String getParticipantName(int index, String alertInput) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print((index + 1) + "-" + alertInput + ":\n");
            String participant = scanner.nextLine();
            if (!participant.isEmpty()) {
                return participant;
            }
        }
    }

    static int getBallPlayers(int participantIndex, String inputText) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("\n" + (participantIndex + 1) + " - " + inputText + ": " +
                        participantArr.get(participantIndex) + " uchun ball: ");
                int ball = Integer.parseInt(scanner.nextLine());
                if (ball >= 0) {
                    return ball;
                } else {
                    System.out.println("Noto'g'ri qiymat. Faqat son kiriting.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Noto'g'ri qiymat. Faqat son kiriting.");
            }
        }
    }
    public static int choose_directory(){
        Direction new_direction = new Direction();
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        ArrayList<String> input_battle_level = new ArrayList<>();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Kerakli Musobaqa turini tanlang:\n");
        for (int i = 0; i < new_direction.all_direction.length; i++){
            System.out.println(i + 1 + " - " + new_direction.all_direction[i]);
        }
        System.out.println("-----------------------------------------------------------------------");
        while (true){
            System.out.println("Faqat son kiriting:\n");
            int input_index = scanner.nextInt();
            if(input_index >=1 && input_index <= 5){
                result = input_index;
                break;
            }else{
                continue;
            }
        }
        return result - 1;
    }
    public static void alone(){
        Direction main_direction = new Direction();
        int participantCount = getPositiveInput("\nIshtirokchi sonini kiriting: ");
        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < participantCount; i++) {
            participantArr.add(getParticipantName(i, "ishtirokchi ismini kiriting"));
        }
        int main_num = choose_directory();
        System.out.println("-----------------------------------------------------------------------");

        System.out.println("\nO'yin boshlandi.\nJami ishtirokchilar soni " + participantCount + "ta ular:\n");

        for (int i = 0; i < participantArr.size(); i++) {
            System.out.println((i + 1) + " - ishtirokchi: " + participantArr.get(i));
        }
        System.out.println("Tanlangan yo'nalish: " + main_direction.all_direction[main_num]);
        System.out.println("\n\nYonalish bosqichlar:\n");
        for (int i =0; i <5;i++){
            System.out.println(main_direction.all_lvl[main_num][i]);
        }
        System.out.println("-----------------------------------------------------------------------");
        int gameCounter = 0;
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    "\n" + (gameCounter + 1) + " - bosqich yakunlandi\nishtirokchilarga " + main_direction.all_lvl[main_num][i]
                            + " bo'yicha ball bering\n");
            ArrayList<Integer> limitedArrForBall = new ArrayList<>();

            for (int k = 0; k < participantArr.size(); k++) {
                limitedArrForBall.add(getBallPlayers(k, "Ishtirokchi"));
            }
            gameCounter++;
            participantBallArr.add(limitedArrForBall);
        }
        System.out.println("-----------------------------------------------------------------------");

        int levelCounter = 0;
        System.out.println("\nBarcha bosqichlar yakunlandi\nishtirokchilarning umumiy ballari:\n");
        ArrayList<Integer> getMaxBall = new ArrayList<>();
        for (int i = 0; i < participantArr.size(); i++) {
            ArrayList<Integer> ballArr = new ArrayList<>();
            for (ArrayList<Integer> k : participantBallArr) {
                levelCounter++;
                System.out.println(
                        participantArr.get(i) + " " + levelCounter + " - bosqichda " + k.get(i) + ": ball oldi");
                ballArr.add(k.get(i));
                if (levelCounter == 5) {
                    levelCounter = 0;
                    System.out.println("\n\n");
                    getMaxBall.add(ballArr.stream().mapToInt(Integer::intValue).sum());
                }
            }
        }
        int maxBall = getMaxBall.stream().mapToInt(Integer::intValue).max().orElse(0);
        int maxBallIndex = getMaxBall.indexOf(maxBall);
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("G'olib: " + participantArr.get(maxBallIndex) + "\njami to'plagan bali: " + maxBall);
    }

    static void team() {
        Direction main_direction = new Direction();
        int groupCount = getPositiveInput("Gurux sonini kiriting:\n");
        int groupMember = getPositiveInput("Gurux a'zolari sonini kiriting:\n");
        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < groupCount; i++) {
            participantArr.add(getParticipantName(i, "gurux nomini kiriting"));
        }
        for (int i = 0; i < groupMember * groupCount; i++) {
            participantTeamMembers.add(getParticipantName(i, "ishtirokchining ismi"));
        }
        int main_num = choose_directory();
        System.out.println("Tanlangan yo'nalish: " + main_direction.all_direction[main_num]);
        System.out.println("\n\nYonalish bosqichlar:\n");
        for (int i =0; i <5;i++){
            System.out.println(main_direction.all_lvl[main_num][i]);
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\nO'yin boshlandi.\nJami ishtirokchilar soni " + groupMember * groupCount
                + "ta ular:\n");

        for (int i = 0; i < groupMember * groupMember; i++) {
            System.out.println((i + 1) + " - ishtirokchi: " + participantTeamMembers.get(i));
        }
        System.out.println("-----------------------------------------------------------------------");
        int gameCounter = 0;
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    "\n" + (gameCounter + 1) + " - bosqich yakunlandi\nguruxlarga " + main_direction.all_lvl[main_num][i]
                            + " bo'yicha ball bering\n");
            ArrayList<Integer> limitedArrForBall = new ArrayList<>();

            for (int k = 0; k < participantArr.size(); k++) {
                limitedArrForBall.add(getBallPlayers(k, "gurux"));
            }
            gameCounter++;
            participantBallArr.add(limitedArrForBall);
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\nBarcha bosqichlar yakunlandi\nguruxlarning umumiy ballari:\n");
        int levelCounter = 0;
        ArrayList<Integer> getMaxBall = new ArrayList<>();
        for (int i = 0; i < participantArr.size(); i++) {
            ArrayList<Integer> ballArr = new ArrayList<>();
            for (ArrayList<Integer> k : participantBallArr) {
                levelCounter++;
                System.out.println(
                        participantArr.get(i) + " " + levelCounter + " - bosqichda " + k.get(i) + ": ball oldi");
                ballArr.add(k.get(i));
                if (levelCounter == 5) {
                    levelCounter = 0;
                    System.out.println("\n\n");
                    getMaxBall.add(ballArr.stream().mapToInt(Integer::intValue).sum());
                }
            }
        }
        System.out.println("-----------------------------------------------------------------------");
        int maxBall = getMaxBall.stream().mapToInt(Integer::intValue).max().orElse(0);
        int maxBallIndex = getMaxBall.indexOf(maxBall);
        System.out.println(
                "G'olib: " + participantArr.get(maxBallIndex) + " guruhi\n. Jami to'plagan ballari: " + maxBall
                        + "\n\n");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(
                    "__________________________________________________________________________\n");
            System.out.println("1 - Yakka tartib");
            System.out.println("2 - Jamoavi tartib");
            System.out.println("3 - Dasturni to'xtatish");
            System.out.println(
                    "\n__________________________________________________________________________");
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nFaqat son kiriting: ");
            try {
                int inputIndicator = Integer.parseInt(scanner.nextLine());
                if (inputIndicator > 0 && inputIndicator <= 3) {
                    if (inputIndicator == 1) {
                        alone();
                    } else if (inputIndicator == 2) {
                        team();
                    } else if (inputIndicator == 3) {
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }
}

class Direction{
    String[] all_direction = {"English" , "Matematika" , "ITS" , "Programming" , "WebSte"};
    String[][] all_lvl = {{"Listning" , "speaking" , "Reading" , "Writing" , "Grammer"},{"Sodda masalalar" , "Tenglamalar" , "Murakkab masalalar" , "Matritsalar" , "Triganometriya"},{"Infratuzulmalar" , "Biznes" , "ichki malumotlar" , "Tashqi malumotlar" , "Dasturiy taminit"},{"Java" , "Python" , "C++" , "Rubi" , "Dart"},{"HTML teglar" , "Css" , "SCSS" , "JS" , "React"}};
}