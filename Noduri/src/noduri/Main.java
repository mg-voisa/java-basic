
package noduri;


public class Main {
    public static void main(String[] argv){
        FirdeExecutie fir1=new FirdeExecutie("Nod 1");//crearea unui fir de executie
        FirdeExecutie fir2=new FirdeExecutie("Nod 2");
        FirdeExecutie fir3=new FirdeExecutie("Nod 3");
        fir1.setPriority(Thread.MIN_PRIORITY);//setarea prioritatii
        fir2.setPriority(Thread.MAX_PRIORITY);
        fir3.setPriority(7);
        fir1.start();//rularea firului de executie
        fir2.start();
        fir3.start();
        System.out.println("Revenim la main");
        
        String[] hosts = {"228.5.6.7", "228.5.6.8", "228.5.6.9", "228.5.6.10", "228.5.6.11", "228.5.6.12"};
        Nod[] noduri = new Nod[6];
        for(int i=0;i<hosts.length;i++){
           noduri[i] = new Nod(i, hosts[i]);
        }
        for(int i=0;i<hosts.length;i++){
            noduri[i] = new Nod(i,hosts[i]);
        }
    }
}
