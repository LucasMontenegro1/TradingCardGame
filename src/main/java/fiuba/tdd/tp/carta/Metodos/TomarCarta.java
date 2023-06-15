package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.turno.Etapa;
import fiuba.tdd.tp.turno.EtapaInicial;
import fiuba.tdd.tp.turno.EtapaPrincipal;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaMano;

public class TomarCarta extends MetodoCarta {

    private int cantidad;

    public TomarCarta(int cantidad, Tipo  tipoCarta, ArrayList<Integer> costoDeUso){
        tipo = tipoCarta;
        costo = costoDeUso;
        this.cantidad = cantidad;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos) {
        if (this.tipo == Tipo.Criatura){
            return tipo.etapa.getClass() == etapa.getClass() && zona instanceof ZonaCombate;
        }
        return tipo == Tipo.Artefacto && (etapa instanceof EtapaPrincipal || etapa instanceof EtapaInicial) && !(zona instanceof ZonaMano);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) throws MovimientoInvalido {
        
        for (int i = 0; i < this.cantidad; i++) {
            enJuego.cartasEnZona(null).get(0).cambiarZona();
        }
    }

}
