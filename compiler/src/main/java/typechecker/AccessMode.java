package typechecker;

/**
 * Created by tobi on 30.12.16.
 * access mode of reference parameters is Indirect
 * all other stores (global stores, copy parameters, return stores, local stores) have Direct access mode
 */
public enum AccessMode {
    DIRECT, INDIRECT
}
