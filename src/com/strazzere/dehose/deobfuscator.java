package com.strazzere.dehose;

/**
 * Deobfuscation for HoseDex2Jar
 * 
 * Reversed from the "com.code.code.df.DF" class.
 * 
 * I've attempted to retain the, "interesting" class names that have been used inside the obfuscated class so it's
 * easier for people to follow along when reversing
 * 
 * @author Tim Strazzere <diff@lookout.com>
 */
public class deobfuscator {

    /**
     * The main syrup method - so slog through all those sugar coated bytes
     * 
     * @param buffer1
     *            byte[] to demangle/deobfuscate/blah
     * @return a byte[] that contains the zipped original dex
     */
    public static byte[] syrup(byte[] buffer1) {
        // get zero
        int integer = thepoursomerealgridedpepper(1, buffer1);

        // some magical math stuff
        integer = thensomesomefishoil(buffer1, integer, false, integer, (byte) 0);

        // some more simple math
        integer = cuzitsgoodforyourmindandyoursoul(buffer1, integer, buffer1, (byte) 1);

        // Get a buffers of the proper size
        byte[] buffer2 = ss(buffer1, integer);
        byte[] buffer3 = ww(buffer1, integer, buffer2);

        // Fill and demangle
        is(buffer1, integer, buffer2, buffer3);
        fj(buffer1, integer, buffer2, buffer3);

        // Finish the work
        return andenjoy(buffer3, buffer1, (byte) 0);
    }

    /**
     * Odd function - doesn't look like it does anything
     * 
     * Just ends up returning buff1 without any changes
     * 
     * @param buff1
     *            byte[] to be returned
     * @param buff2
     *            ignored
     * @param extra_byte
     *            ignored
     * @return buff1 (no changes)
     */
    private static byte[] andenjoy(byte[] buff1, byte[] buff2, byte extra_byte) {
        // Maybe this is meant to mangle some decompiler
        for (int i = 0; i < 5; i++) {
            extra_byte = buff2[i];
        }

        // Just return buff1 without doing anything to it
        return buff1;
    }

    /**
     * Simple maths; absolute(integer % buffer.length)
     * 
     * @param buffer
     * @param integer
     * @param buffer2
     *            ignored
     * @param extra_byte
     *            ignored
     * @return absolute(integer % buffer.length)
     */
    private static int cuzitsgoodforyourmindandyoursoul(byte[] buffer, int integer, byte[] buffer2, byte extra_byte) {
        return Math.abs(integer % buffer.length);
    }

    /**
     * Do some magic maths
     * 
     * @param buffer
     *            byte[] to perform some math magic on
     * @param integer_one
     *            integer to increment that magic math on
     * @param bool
     *            a bool switch used to determine if extra_byte should have integer_two added or subtracted (ignored)
     * @param integer_two
     *            int added/subtracted to extra_byte (ignored)
     * @param extra_byte
     *            (ignored)
     * @return the result from magical math
     */
    private static int thensomesomefishoil(byte[] buffer, int integer_one, boolean bool, int integer_two,
                    byte extra_byte) {
        // This is not even used, but in the code
        if (bool) {
            integer_two = extra_byte - integer_two;
        } else {
            integer_two = extra_byte + integer_two;
        }

        // Magic maths!
        for (byte byte_from_buffer : buffer) {
            integer_one += (byte_from_buffer * 1300237) % 164477;
        }
        return integer_one;
    }

    /**
     * Just return zero
     * 
     * @param integer
     *            (ignored)
     * @param buffer
     *            (ignored)
     * @return always returns 0
     */
    private static int thepoursomerealgridedpepper(int integer, byte[] buffer) {
        // not used at all
        integer -= buffer[0];

        return 0;
    }

    /**
     * Return a byte array of size buffer[integer]
     * 
     * @param buffer
     *            byte[] to look at
     * @param integer
     *            position in the byte[] to look at
     * @return a byte array of size buffer[integer]
     */
    private static byte[] ss(byte[] buffer, int integer) {
        return new byte[bubba(buffer[integer])];
    }

    /**
     * Create a new byte[] array of length buffer1.length - integer
     * 
     * @param buffer1
     *            byte[] only used for it's length
     * @param integer
     *            used to subtract against the length of the above byte[]
     * @param buffer2
     *            this byte[] gets demangled here
     * @return new byte[] of length buffer1.length - integer
     */
    private static byte[] ww(byte[] buffer1, int integer, byte[] buffer2) {
        // Do magic to buffer2
        for (int i = integer; i < (integer + bubba(buffer1[integer])); i++) {
            buffer2[(i - integer)] = buffer1[(i % buffer1.length)];
        }

        return new byte[buffer1.length - bubba(buffer1[integer])];
    }

    /**
     * Nerf those bytes :D
     * 
     * @param buffer1
     * @param integer
     * @param buffer2
     * @param buffer3
     */
    private static void is(byte[] buffer1, int integer, byte[] buffer2, byte[] buffer3) {
        int upper_bound = integer + bubba(buffer1[integer]) + buffer3.length;
        for (int i = integer + bubba(buffer2[0]); i < upper_bound; i++) {
            buffer3[(i - (integer + bubba(buffer1[integer])))] = buffer1[(i % buffer1.length)];
        }
    }

    /**
     * Nerf the bytes more!
     * 
     * @param buffer1
     * @param integer
     * @param buffer2
     * @param buffer3
     */
    private static void fj(byte[] buffer1, int integer, byte[] buffer2, byte[] buffer3) {
        for (int i = 0; i < buffer3.length; i++) {
            buffer3[i] = (byte) (buffer3[i] ^ buffer2[(i % bubba(buffer1[integer]))]);
        }
    }

    /**
     * Ensure byte is unsigned and not signed
     * 
     * Off Topic: Bubba (gump?) and pancakes? really?
     * 
     * @param the_byte
     *            a byte
     * @return a unsigned byte
     */
    public static int bubba(byte the_byte) {
        return the_byte & 0xFF;
    }
}
