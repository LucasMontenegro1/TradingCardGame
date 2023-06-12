package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaMano;

public class TomarCarta extends MetodoCarta {

    private int cantidad;

    public TomarCarta(int cantidad, Tipo  tipoCarta){
        this.cantidad = cantidad;
        tipo = tipoCarta;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        if (this.tipo == Tipo.Criatura){
            return tipo.etapa.getClass() == etapa.getClass() && zona instanceof ZonaCombate;
        }
        return tipo == Tipo.Artefacto && (etapa instanceof EtapaPrincipal || etapa instanceof EtapaInicial) && !(zona instanceof ZonaMano);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, 
                            String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia) {
        
        for (int i = 0; i < this.cantidad; i++){
            ArrayList<Carta> cartas = enJuego.cartasEnZona(null);
            
            Random random = new Random();
            Carta cartaTomada = cartas.get(random.nextInt(cartas.size()));
            cartaTomada.zona = new ZonaMano();
        }
    }

}
