
package firedeexecutie;

public class Fir1
{
 public static void main(String args[])
 {
 FirdeExecutie1 fir1=new FirdeExecutie1();
 FirdeExecutie1 fir2=new FirdeExecutie1();
 fir1.start();
 fir2.start();
 System.out.println("Revenim la main");
 }
}

class FirdeExecutie1 extends Thread
{
 public void run()
 {
 for(int i=0;i<10;i++)
 {
 System.out.println("Pasul "+i);
 try{
 sleep(500);//oprirea pt. 0,5 secunde a firului de executie
 }
 catch(InterruptedException e) {System.err.println("Eroare");}
 }
 System.out.println("Run s-a terminat");
 }
} 
