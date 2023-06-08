package fiuba.tdd.tp;

import fiuba.tdd.tp.carta.EfectoComposite;
import fiuba.tdd.tp.carta.MetodoCarta;
import fiuba.tdd.tp.carta.Metodos.Atacar;
import fiuba.tdd.tp.carta.Metodos.TomarCarta;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.zona.ZonaCombate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MetodoCartaTests {


    @Test
    public void atacarYTomarCarta(){
        MetodoCarta atacar = new Atacar(10);
        MetodoCarta tomarCarta = new TomarCarta(1, Tipo.Criatura);

        EfectoComposite efecto = new EfectoComposite();
        efecto.setEfectos(atacar);
        efecto.setEfectos(tomarCarta);
        assertTrue(efecto.esAplicableA(new EtapaDeAtaque(),new ZonaCombate()));
        efecto.ejecutar();
    }

}
