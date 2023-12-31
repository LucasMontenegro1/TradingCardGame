package fiuba.tdd.tp.model.jugador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.model.Excepciones.MazoExistente;
import fiuba.tdd.tp.model.Excepciones.MazoInvalido;
import fiuba.tdd.tp.model.carta.CartasDisponibles;

public class Jugador implements UserDetails {

    private String nombre;
    private String contra;
    private int cantdDinero;
    private HashMap<String, Integer> cartas = new HashMap<>();
    private HashMap<String, Mazo> mazos = new HashMap<>();
    private HashMap<String, ArrayList<String>> intercambios = new HashMap<>();

    public Jugador(String nombreJugador, String contra) {
        this.nombre = nombreJugador;
        this.contra = contra;
        this.cantdDinero = 0;
    }

    public int getCantdDinero() {
        return cantdDinero;
    }

    public HashMap<String, ArrayList<String>> intercambiosAbiertos() {
        return intercambios;
    }

    public Object password() {
        return contra;
    }

    public void cambiarUsuario(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public HashMap<String, Integer> getCartas() {
        return cartas;
    }

    public String nombreJugador() {
        return nombre;
    }

    public void modificarNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public HashMap<String, Mazo> getMazos() {
        return this.mazos;
    }

    public void depositarDinero(int cantidad) {
        this.cantdDinero += cantidad;
    }

    public void extraerDinero(int cantidad) throws DineroInsuficiente {
        if (cantidad > this.cantdDinero) {
            throw new DineroInsuficiente("No puede extraer esa cantidad, saldo insuficiente");
        }
        this.cantdDinero -= cantidad;
    }

    void agregarCarta(String nombreCarta) {
        if (this.cartas.containsKey(nombreCarta)) {
            int cantidad = this.cartas.get(nombreCarta);
            this.cartas.put(nombreCarta, cantidad+1);
        } else {
            this.cartas.put(nombreCarta, 1);
        }
    }

    public void comprarCarta(CartasDisponibles carta) throws DineroInsuficiente {
        int precioCarta = carta.precio;
        String nombreCarta = carta.nombre;

        if (this.cantdDinero < precioCarta) {
            throw new DineroInsuficiente("No puede realizar la compra, saldo insuficiente");
        }

        this.extraerDinero(precioCarta);
        
        agregarCarta(nombreCarta);
    }


    public void eliminarCarta(String nombreCarta) throws CartaNoEncontrada {

        if (!this.cartas.containsKey(nombreCarta)) {
            throw new CartaNoEncontrada("Usted no tiene la carta");
        } 

        int cantidad = this.cartas.get(nombreCarta);
        if (cantidad == 1) {
            this.cartas.remove(nombreCarta);
            this.intercambios.remove(nombreCarta);
        } else {
            this.cartas.put(nombreCarta, cantidad-1);
        }

        for (Mazo mazo : this.mazos.values()) {
            if (mazo.cantCartaEspecifica(nombreCarta) == cantidad) {
                mazo.eliminarCarta(nombreCarta);
            }
        }
    }

    public Mazo getMazo(String nombreMazo) {
        return mazos.get(nombreMazo);
    }

    public void agregarCartasAMazo(String nombreMazo, HashMap<String, Integer> cartas) throws MazoInvalido, CartaNoEncontrada {
        if (!this.mazos.containsKey(nombreMazo)) {
            throw new MazoInvalido("El mazo al cual quiere agregar cartas no existe");
        }
        Mazo mazo = this.mazos.get(nombreMazo);

        for (Entry<String, Integer> carta : cartas.entrySet()) {
            String nombreCarta = carta.getKey();
            Integer cantidadAAgregar = carta.getValue();
            Integer cantidadActual = mazo.cantCartaEspecifica(nombreCarta);

            Integer cantidadCartasJugador = this.cartas.get(nombreCarta);
            if (cantidadActual == null || (cantidadCartasJugador < (cantidadAAgregar+cantidadActual))) {
                throw new CartaNoEncontrada("No tiene la carta " + nombreCarta);
            }

            mazo.agregarCarta(nombreCarta, cantidadAAgregar);
        }
    }

    public void removerCartasDeMazo(String nombreMazo, HashMap<String, Integer> cartas) throws MazoInvalido, CartaNoEncontrada {
        if (!this.mazos.containsKey(nombreMazo)) {
            throw new MazoInvalido("El mazo del cual quiere eliminar cartas no existe");
        }
        Mazo mazo = this.mazos.get(nombreMazo);

        for (Entry<String, Integer> carta : cartas.entrySet()) {
            String nombreCarta = carta.getKey();
            Integer canitdadAEliminar = carta.getValue();

            for (int i = 0; i < canitdadAEliminar; i++) {
                mazo.eliminarCarta(nombreCarta);
            }
        }
    }

    public void agregarMazo(String nombre, Mazo mazo) throws MazoExistente, CartaNoEncontrada {
        if (this.mazos.containsKey(nombre)) {
            throw new MazoExistente("No puede agregar un mazo con ese nombre");
        }

        for (Entry<String, Integer> carta : mazo.cartas.entrySet()) {
            String nombreCarta = carta.getKey();
            Integer cantidadMazo = carta.getValue();

            Integer cantidadActual = this.cartas.get(nombreCarta);
            if (cantidadActual == null || cantidadActual < cantidadMazo) {
                throw new CartaNoEncontrada("No tiene las cartas necesarias para armar el mazo");
            }
        }

        this.mazos.put(nombre, mazo);
    }

    public void eliminarMazo(String nombre) {
        Mazo mazo = this.mazos.get(nombre);
        if (mazo != null) {
            this.mazos.remove(nombre);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("JUGADOR"));
    }

    @Override
    public String getPassword() {
        return this.contra;
    }

    @Override
    public String getUsername() {
        return this.nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
