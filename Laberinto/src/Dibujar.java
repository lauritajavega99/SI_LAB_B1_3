/*************************************************************************
 Código para generar y dibujar un laberinto, utilizable (o no) como punto 
 de partida de la práctica 2 de APD (2013-14).
 Código prestado del Dpto. de Informática de la Universidad de Princeton.
 El programa requiere la biblioteca StdDraw: 
 http://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html
 *************************************************************************/
import java.util.*;

public class Dibujar {
    private int N; // dimensión del laberinto
    private int M;
    private boolean[][] norte;     // existe pared hacia el norte de la casilla i,j
    private boolean[][] este;
    private boolean[][] sur;
    private boolean[][] oeste;
  
    public Dibujar(int N ,int M) {
        this.N = N;
        this.M=M;
        StdDraw.setXscale(0, N+2);
        StdDraw.setYscale(0, M+2);
        inicializar();
        
    }

    public Dibujar() {
		super();
	}

	private void inicializar() {
       // inicializar todas las paredes como presentes
       // nótese que cada pared se almacena 2 veces
        norte = new boolean[N+2][M+2];
        este  = new boolean[N+2][M+2];
        sur   = new boolean[N+2][M+2];
        oeste = new boolean[N+2][M+2];
        for (int x = 0; x < N+2; x++)
            for (int y = 0; y < M+2; y++)
                norte[x][y] = este[x][y] = sur[x][y] = oeste[x][y] = true;
    }
 
    public void borrar(int x, int y, char d){
	 
	 	if ((d=='N') && (y<M)){
			
			norte[x][y] = sur[x][y+1] = false;
		}
		else if ((d=='O')&& (x>1)){
			
			oeste[x][y] = este[x-1][y] = false;
      }
		else if ((d=='S')&& (y>1)){
			
			sur[x][y] = norte[x][y-1] = false;
		}
		else if ((d=='E')&& (x<N)){
			
			este[x][y] = oeste[x+1][y] = false;		
		}
	 }	
	 
	 // dibuja el laberinto
    public void dibujar() {
        StdDraw.setPenColor(StdDraw.RED);
      //  StdDraw.filledCircle(N + 0.5, N + 0.5, 0.375);
       // StdDraw.filledCircle(1.5, 1.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= M; y++) {
                if (sur[x][y])   StdDraw.line(x, y, x + 1, y);
                if (norte[x][y]) StdDraw.line(x, y + 1, x + 1, y + 1);
                if (oeste[x][y]) StdDraw.line(x, y, x, y + 1);
                if (este[x][y])  StdDraw.line(x + 1, y, x + 1, y + 1);
            }
        }
        StdDraw.show(0);
    }
	 
  public  void dibujar(Laberinto lab, int M , int N) {
       // M ==FILAS N==COLUMNAS
        Dibujar miLaberinto = new Dibujar(N,M);
        int fd=1;
        int flab=M-1;
        StdDraw.show(0);
        for (int i=0;i<M;i++) {
        	for(int j=0;j<N;j++) {
        		
        		if(lab.getCeldas()[flab][j].getVecinoN()==true) {
        			miLaberinto.borrar((j+1),fd,'N');
        		}
        		if(lab.getCeldas()[flab][j].getVecinoE()==true) {
        			miLaberinto.borrar((j+1),fd,'E');
      		}
        		if(lab.getCeldas()[flab][j].getVecinoS()==true) {
        			miLaberinto.borrar((j+1),fd,'S');
      		}
        		if(lab.getCeldas()[flab][j].getVecinoO()==true) {
      			  	miLaberinto.borrar((j+1),fd,'O');
      		}
        	}
        
        	flab=flab-1;
        	fd++;
        }
      
		
        miLaberinto.dibujar();
    }
}
