public class Kort {
    private Färg färg;
    private Nummer nummer;

    public Kort(Färg färg, Nummer nummer){
        this.nummer = nummer;
        this.färg = färg;
    }

    public String toString (){
        return this.färg.toString() + " " + this.nummer.toString();
    }
    public Nummer getNummer (){
        return this.nummer;
    }
}

