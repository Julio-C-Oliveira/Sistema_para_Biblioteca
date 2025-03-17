package org.example.library;

public interface Verifiable {
    public default int checkAvailability() { return 0; }
}