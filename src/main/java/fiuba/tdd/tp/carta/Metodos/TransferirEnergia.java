package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaReserva;

public class TransferirEnergia extends MetodoCarta {

    public TransferirEnergia(ArrayList<Integer> costoDeUso) {
        tipo = Tipo.Criatura;
        costo = costoDeUso;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        return etapa instanceof EtapaPrincipal && (zona instanceof ZonaCombate || zona instanceof ZonaReserva);
    }

    @Override
   public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {
        
        if (energia != null) {
            contrincante.disminuirEnergia(energia, 1);
            enJuego.aumentarEnergia(energia, 1);
        }
    }
    
}
