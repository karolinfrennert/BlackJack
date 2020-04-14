import java.util.ArrayList;
import java.util.Random;

public class Kortlek {
    public ArrayList<Kort> kort;

    //konstruktor
    public Kortlek() {
        this.kort = new ArrayList<Kort>();
    }

    //Skapar kortleken
    public void skapaKortlek() {
        // returnerar värderna på våra färger
        for (Färg kortFärg : Färg.values()) {
            for (Nummer kortNummer : Nummer.values()) {
                this.kort.add(new Kort(kortFärg, kortNummer));
            }
        }
    }

    public String toString() {
        String kortLista = "";
        for (Kort aKort : this.kort) {
            kortLista += "\n" + " " + aKort.toString();
        }
        return kortLista;
    }

    public void taBortKort(int i) {
        this.kort.remove(i);
    }

    public Kort getKort(int i) {
        return this.kort.get(i);
    }

    public void addKort(Kort addKort) {
        this.kort.add(addKort);
    }

    //Drapr ett kort från leken
    public void dra(Kortlek comingFrom) {
        this.kort.add(comingFrom.getKort(0));
        comingFrom.taBortKort(0);
    }

    public int deckSize() {
        return this.kort.size();
    }

    //funktionen hämtar värdet på korten
    public int kortVärde() {
        int totaltVärde = 0;
        int ess = 0;
//Totala värdet för alla vanliga korten
        for (Kort aKort : this.kort) {
            switch (aKort.getNummer()) {
                case TVÅ:
                    totaltVärde += 2;
                    break;
                case TRE:
                    totaltVärde += 3;
                    break;
                case FYRA:
                    totaltVärde += 4;
                    break;
                case FEM:
                    totaltVärde += 5;
                    break;
                case SEX:
                    totaltVärde += 6;
                    break;
                case SJU:
                    totaltVärde += 7;
                    break;
                case ÅTTA:
                    totaltVärde += 8;
                    break;
                case NIO:
                    totaltVärde += 9;
                    break;
                case TIO:
                    totaltVärde += 10;
                    break;
                case KNECKT:
                    totaltVärde += 10;
                    break;
                case DAM:
                    totaltVärde += 10;
                    break;
                case KUNG:
                    totaltVärde += 10;
                    break;
                case ESS:
                    ess += 1;
                    break;
            }

        }
        //Bestämmer värdet på ESS om det ska vara 1 eller 14.
        for (int i = 0; i < ess; i++) {
            if (totaltVärde > 7) {
                totaltVärde += 1;
            } else {
                totaltVärde += 14;
            }
        }
        return totaltVärde;
    }


}//Ett arv av klassen kortlek som skapar en temporär kortlek att spela med

class TemporärKortlek extends Kortlek {

            public void shuffle() {
                //Skapar en temporär kortlek
                ArrayList<Kort> tmpKortlek = new ArrayList<Kort>();
                Random random = new Random();
                int randomKortIndex = 0;
                int originalSize = this.kort.size();
                for (int i = 0; i < originalSize; i++) {
                    randomKortIndex = random.nextInt((this.kort.size() - 1 - 0) + 1) + 0;
                    tmpKortlek.add(this.kort.get(randomKortIndex));
                    this.kort.remove(randomKortIndex);
                }

                this.kort = tmpKortlek;
            }
        }







