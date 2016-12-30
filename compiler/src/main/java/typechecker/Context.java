package typechecker;

/**
 * Created by tobi on 30.12.16.
 * globally declared stores are Global (also, when they are locally imported)
 * all other stores (parameters, return stores, local stores) are Local
 */
public enum Context {
    LOCAL, GLOBAL
}
