/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firedeexecutie;

public class Fir2
{ 
    public static void main(String args[])
    {
        FirdeExecutie fir1=new FirdeExecutie("Fir 1");
        FirdeExecutie fir2=new FirdeExecutie("Fir 2");
        FirdeExecutie fir3=new FirdeExecutie("Fir 3");
        fir1.setPriority(Thread.MIN_PRIORITY);
        fir2.setPriority(Thread.MAX_PRIORITY);
        fir3.setPriority(7);
        fir1.start();
        fir2.start();
        fir3.start();
        System.out.println("Revenim la main");
    }
}
class FirdeExecutie extends Thread
{
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
