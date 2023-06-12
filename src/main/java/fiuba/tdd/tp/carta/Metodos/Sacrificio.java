package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaMano;

public class Sacrificio extends MetodoCarta {

    final Integer CANTIDAD  = 3;

    public Sacrificio(ArrayList<Integer> costoDeUso) {
        tipo = Tipo.Accion;
        costo = costoDeUso;
    }
  
    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        return etapa instanceof EtapaPrincipal && zona instanceof ZonaMano;
    }
    
    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, 
                            String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia) {
        
        modificarEnergia(enJuego.energia, Energia.Fuego, cartaActivada.costoDeInvocacion.get(0));
        modificarEnergia(enJuego.energia, Energia.Planta, cartaActivada.costoDeInvocacion.get(1));
        modificarEnergia(enJuego.energia, Energia.Agua, cartaActivada.costoDeInvocacion.get(2));
    }

    private void modificarEnergia(HashMap<Energia, Integer> energias,Energia energia, Integer costo){
        Integer cantidadEnergia = energias.get(energia) + CANTIDAD * costo;

        energias.put(energia, cantidadEnergia);
    }
}
