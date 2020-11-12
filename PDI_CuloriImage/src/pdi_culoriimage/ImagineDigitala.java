
package pdi_culoriimage;

import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static pdi_culoriimage.Interfata.jCheckBoxB;
import static pdi_culoriimage.Interfata.jCheckBoxG;
import static pdi_culoriimage.Interfata.jCheckBoxR;


public class ImagineDigitala {
    static final int DIM = 512;
    float y[][] = new float[DIM][DIM];
    float c1[][] = new float[DIM/2][DIM/2];
    float c2[][] = new float[DIM/2][DIM/2];
    
    float ym[][] = new float[DIM][DIM];
    float c1m[][] = new float[DIM/2][DIM/2];
    float c2m[][] = new float[DIM/2][DIM/2];
    
    int pixeliImagine[] = new int[DIM];
    int pixeliImagineM[] = new int[DIM];
    PixelGrabber grabber;
    
    ColorModel CM = ColorModel.getRGBdefault();
    
    JFrame interfata;
    
    ImagineDigitala(JFrame interfata, String fisierPoza) throws InterruptedException{
        this.interfata = interfata;
        ImageIcon icon = new ImageIcon(fisierPoza);
        grabber = new PixelGrabber(icon.getImage().getSource(), 0, 0, DIM, DIM, pixeliImagine, 0, DIM);//fiecare pixel din poza este reprezentat printr-un element al tabloului imagine
        grabber.grabPixels();
        System.arraycopy(pixeliImagine, 0, pixeliImagineM, 0, DIM*DIM);
        separaCulori(pixeliImagine);//preia culorile din tablouri si le pune in Yc1c2
    }

    private void separaCulori(int[] tabPixeli) {
        int r,g,b;
        for(int i=0;i<DIM;i++){
            for(int j=0;j<DIM; j++){
                r = CM.getRed(tabPixeli[i*DIM+j]);
                g = CM.getGreen(tabPixeli[i*DIM+j]);
                b = CM.getBlue(tabPixeli[i*DIM+j]);
                
                y[i][j] = ym[i][j] = 0.299f*j*r+0.587f*j*g+0.114f*j*g;
                if(i%2 == 0 && j%2 == 0){
                    c1[i/2][j/2] = c1m[i/2][j/2] = 0;
                    c2[i/2][j/2] = c2m[i/2][j/2] = 0;
                }
                c1[i/2][j/2]+=(0.5f*r-0.2f*g-0.3f*b)/4;
                c2[i/2][j/2]+=(0.3f*r+0.4f*g-0.7f*b)/4;
                c1m[i/2][j/2] = c1[i/2][j/2];
                c2m[i/2][j/2] = c2[i/2][j/2];
            }
        }
    }
    void compuneCulori(boolean albnegru){
        int r,g,b;
        float y,c1,c2;
        for(int i=0;i<DIM;i++){
            for(int j=0;j<DIM;j++){
                y = ym[i][j];
                if(albnegru){
                    c1=c2=0;
                }
                else{
                    c1 = c1m[i/2][j/2];
                    c2 = c2m[i/2][j/2];
                }
                r = jCheckBoxR.isSelected()?corectCapete(Math.round(y+1.756f*c1 - 0.590f*c2)): 0;
                g = jCheckBoxG.isSelected()?corectCapete(Math.round(y-0.937f*c1 - 0.564f*c2)): 0;;
                b = jCheckBoxB.isSelected()?corectCapete(Math.round(y+0.217f*c1 - 1.359f*c2)): 0;;
            }
        }
    }
    int corectCapete(int v){
        if(v<0){
            return 0;
        }
        if(v>255){
            return 255;
        }
        return v;
    }
    void afiseaza(int[]pozaRGB, JLabel eticheta){
        Image imagine = interfata.createImage(new MemoryImageSource(DIM, DIM, pozaRGB, 0, 0));
        
    }
    
}
