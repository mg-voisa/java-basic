
package noduri;


class FirdeExecutie extends Thread{
    public FirdeExecutie(String s)
    {
        super(s);
        
    }
    public void run()
    {
        String numeFir=getName();
        for(int i=0;i<5;i++)
        {
            //if(numeFir.compareTo("Fir 3")==0) yield();
            System.out.println(numeFir+ " este la pasul "+i);
            try{
                sleep(500);
            }
            catch(InterruptedException e) {System.err.println("Eroare");}
        }
        System.out.println(numeFir+ " s-a terminat");
    }
}
