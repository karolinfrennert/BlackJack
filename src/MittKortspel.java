import javax.sound.midi.Soundbank;

import java.io.*;
import java.util.Scanner;

public class MittKortspel {

    //Kollar om filen existerar, och om den gör det så läser den informationen i filen
    public static void checkFile (String namn, int timesPlayed) throws IOException {
        String content = "";
        File fileExists = new File("TimesPlayed.txt");

        if (fileExists.exists()) {
            {
                FileReader file = new FileReader("TimesPlayed.txt");
                BufferedReader reader = new BufferedReader(file);
                String line = reader.readLine();
                while (line != null) {
                    content += line + "\n";
                    line = reader.readLine();
                }
            }
            content += (namn + ": " + timesPlayed);
            writeFile(content);
        } else {
            content = "Times played\n";
            content += (namn + ": " + timesPlayed);
            writeFile(content);
        }
    }

    public static void writeFile (String content) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("TimesPlayed.txt")));
        while (true) {
            String s = input.readLine();
            break;
        }
        output.println(content);
        output.close();
    }

    public static void main(String [] args) throws IOException {
        int timesPlayed = 0;

        Scanner userInput = new Scanner(System.in);
        boolean keepPlaying = true;

        Scanner nameInput = new Scanner(System.in);
        System.out.println("Välkommen till 21! Vad heter du?");
        String namn = nameInput.nextLine();

        while (keepPlaying) {
            timesPlayed++;
            BlackJack(namn);
            System.out.println("Vill du fortsätta (1) eller avsluta spelet (2)?");
            int response =userInput.nextInt();
            while (response != 1 && response !=2 ) {
                System.out.println("Skriv in ett värdigt nummer");
                System.out.println("Vill du fortsätta (1) eller avsluta (2)?");
                response = userInput.nextInt();

            }



            if (response == 2) {
                System.out.println("Hejdå!");
                keepPlaying = false;
            }
            System.out.println("Du spelade " + timesPlayed + (timesPlayed > 1 ? " omgångar" : " omgång"));
            checkFile(namn, timesPlayed);
        }



    }


    public static void BlackJack (String namn) throws IOException {
        // Skapar leken som vi spelar med
        TemporärKortlek spelLek = new TemporärKortlek();
        spelLek.skapaKortlek();
        spelLek.shuffle();
        // SKapar korten som spelaren använder
        Kortlek spelarKort = new Kortlek();
        //Skapar dealerns kortlek
        Kortlek dealerKort = new Kortlek();
       // Scanner userInput = new Scanner(System.in);
        Scanner userInput = new Scanner(System.in);


        boolean avsluta = false;
        //Spelaren får sitt kort
        spelarKort.dra(spelLek);
        //Dealern får sitt kort
        dealerKort.dra(spelLek);



        while (true) {
            System.out.println( namn +"´s hand: " + spelarKort.toString() );
            System.out.println("Värde: " + spelarKort.kortVärde());
            System.out.println("Dealerns hand: " + dealerKort.toString());
            System.out.println("Värde " + dealerKort.kortVärde());


            //Vad vill man göra med handen
            System.out.println("Vill du fortsätta (1) eller avsluta (2)?");
            int response = userInput.nextInt();
            while (response != 1 && response !=2) {
                System.out.println("Skriv in ett värdigt nummer");
                System.out.println("Vill du fortsätta (1) eller avsluta (2)?");
                response = userInput.nextInt();
            }


            if(response == 1) {
                spelarKort.dra(spelLek);
                System.out.println(" Du drog en " + spelarKort.getKort(spelarKort.deckSize() - 1).toString());
                //OM man får mer än 21 så förlorar man
                if (spelarKort.kortVärde() > 21) {
                    System.out.println("Du fick över 21. Du förlorar");
                    avsluta = true;
                    break;
                }
                //Om delerns kort har värdet som är mindre än 17 eller 17 så ska dealern dra kort
                if (dealerKort.kortVärde()<=17 ){
                    System.out.println(" Dealerns kort är " + dealerKort.getKort(dealerKort.deckSize() - 1).toString());
                }



                if ((dealerKort.kortVärde() <= 17) && avsluta == false) {
                    dealerKort.dra(spelLek);
                    System.out.println("Dealern drar " + dealerKort.getKort(dealerKort.deckSize() - 1).toString());
                    avsluta = true;}

                else if ((dealerKort.kortVärde() <= 17) && avsluta == true) {
                    dealerKort.dra(spelLek);
                    System.out.println("Dealern drar " + dealerKort.getKort(dealerKort.deckSize()-1).toString());
                    //Om delern får mer än 21 så förlorar den
                    if(dealerKort.kortVärde() > 21){
                        System.out.println("Dealern fick över 21, dealern förlorar");
                        break;
                    }

                }
            }

            //Om spelaren får 21 så vinner den
            if((spelarKort.kortVärde() == 21)) {
                System.out.println("Du fick 21! Grattis du vinner!");
                break;
            }
//Om man vill avsluta spelet så måste man fortfarande kolla värderna på de olika korten
            if (response ==2 ) {
                boolean keepGoing = true;
                while (keepGoing){
                    //Om man vill avsluta och dealern fortfarande har under 17 så drar man kort till delern har 17 eller mer
                    if (dealerKort.kortVärde() < spelarKort.kortVärde() && dealerKort.kortVärde() <= 17 ) {
                        dealerKort.dra(spelLek);
                        System.out.println("Dealern drog " + dealerKort.getKort(dealerKort.deckSize() - 1).toString());
                        keepGoing = false;
                    }

                    if (dealerKort.kortVärde()> spelarKort.kortVärde()&& dealerKort.kortVärde() <= 21 || dealerKort.kortVärde() == spelarKort.kortVärde()) {
                        System.out.println("Du förlorar");
                        keepGoing = false;
                    }

                    if (dealerKort.kortVärde () < spelarKort.kortVärde () && dealerKort.kortVärde() <= 21 || dealerKort.kortVärde() > 21){
                        System.out.println("Grattis du vinner");
                        keepGoing = false;
                    }
                }

                System.out.println("Dealerns värde är " + dealerKort.kortVärde () +  namn + " värde " + spelarKort.kortVärde ());

                break;
            }



            System.out.println("Dealerns kort har värdet " + dealerKort.kortVärde());
            if((dealerKort.kortVärde() > 21) && avsluta == false){
                System.out.println("Dealern förlorar. Du vinner");
                avsluta = true;
                break;
            }

            if((spelarKort.kortVärde()== dealerKort.kortVärde()) && avsluta == false){
                System.out.println("Samma nummer. Du förlorar, dealern vinner");
                avsluta = true;
                break;
            }

            if ((spelarKort.kortVärde() > dealerKort.kortVärde()) && avsluta == false){
                System.out.println("Du vinner eftersom du hade " + spelarKort.kortVärde());
                avsluta = true;
                break; }

            if (dealerKort.kortVärde() == 21){
                System.out.println("Dealern vinner");
            }
        }


    }}


















