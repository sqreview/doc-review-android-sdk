package com.signalquest.api;

import java.nio.ByteBuffer;

/**
 * A byte buffer that is memory-stable for C and that handles Android's 8-byte memory alignment.
 */
class StableBuffer {

    @SuppressWarnings("FieldCanBeLocal")
    // holding ref to ensure the anti-GC stability
    private final ByteBuffer stable;
    private final ByteBuffer aligned;

    /**
     * Instantiates a new StableBuffer with a given length.
     */
    StableBuffer(int length) {
        // allocate to keep memory-stable (GC-proof) for C
        stable = ByteBuffer.allocateDirect(length);
        // allocateDirect is 8-byte memory-aligned in Android, but need 0 offset for C in SQTP/NTRIP;
        //   wrapping a memory-aligned array gives us 0 offset
        // see https://android.googlesource.com/platform/libcore/+/android-6.0.1_r21/luni/src/main/java/java/nio/ByteBuffer.java#68
        aligned = ByteBuffer.wrap(stable.array(), 0, length);
    }

    /**
     * Instantiates a new StableBuffer from given data and loads the data into the buffer.
     */
    StableBuffer(byte[] data) {
        this(data.length);
        aligned.put(data);
    }

    /**
     * A byte array that backs the buffer
     */
    public final byte[] array() {
        return aligned.array();
    }

    /**
     * The buffer's capacity
     */
    public long capacity() {
        return aligned.capacity();
    }
}
