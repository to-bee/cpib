/**
 * The scanner is a state machine and all states are in this package.
 * Each state is a specialization of the abstract
 * {@link ch.fhnw.cpib.iml.scanner.state.ScannerState} and returns the
 * {@link ScannerState#next(ch.fhnw.cpib.iml.lib.Chr) next} state that the
 * machine will process.
 */
package ch.fhnw.cpib.iml.scanner.state;