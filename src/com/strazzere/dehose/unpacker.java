package com.strazzere.dehose;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Unpacker for the first release of the HoseDex2Jar, which uses the inflated header to hide the original dex file.
 * 
 * The dex file is wrapped in a jar (just zipped) and then encrypted. Instead of caring what the crypto is, let's just
 * use the tool this packer is meant to thwart, to make our lives easier. As an included library, I've attached the
 * dex2jar translated crypto dex file used by the packer. I quickly looked at the munging it's doing, but there's no
 * real reason to care what it does - since we can just use it :)
 * 
 * 
 * @author Tim Strazzere <diff@lookout.com>
 */
public class unpacker {

    /**
     * You know, the main stuff, read in the dex file, figure out the packed section, decrypt the buffer and output jar.
     * 
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("[!] Dehoser - unpacker for HoseDex2Jar packer");
            System.out.println("[-] Tim Strazzere, diff@lookout.com");

            byte[] file = readFile(args[0]);
            System.out.println(" [+] Read [ " + file.length + " ] bytes from [ " + args[0] + " ]");

            byte[] packed_section = getPackedSection(file);
            System.out.println(" [+] Packed section appears to have a size of [ " + file.length + " ] bytes");

            byte[] buff = decryptSection(packed_section);
            System.out.println(" [+] Decrypted [ " + buff.length + " ] bytes");

            writeFile(args[0] + ".decrypted", buff);
            System.out.println(" [+] Output decrypted content!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Figure out the size of the packed/encrypted jar and extract it.
     * 
     * @param buffer
     * @return the byte[] of interest
     * @throws Exception
     */
    private static byte[] getPackedSection(byte[] buffer) throws Exception {
        int section_size = getPackedSectionLength(buffer);
        if (section_size == -1) {
            throw new Exception("File does not appear to be packed!");
        }
        byte[] packed_section = new byte[section_size];

        // Copy out the encrypted/packed section
        System.arraycopy(buffer, 0x70, packed_section, 0, section_size);

        return packed_section;
    }

    /**
     * Read the header section, and figure out the packed length
     * 
     * @return Length of the packed header section or -1 if no packed section
     */
    private static int getPackedSectionLength(byte[] buffer) {
        // Read the header size
        int header_size = 0;
        for (int i = 0; i < 4; i++) {
            header_size += (buffer[0x24 + i] & 0xFF) << (i * 8);
        }
        if (header_size != 112) {
            int end_byte = buffer[header_size - 1] & 0xFF;
            // The packed section is the size of the header,
            // minus the size of the default header (112, 0x70),
            // minus the padding at the end (represented by the value
            // of the last byte of the header)
            return header_size - 112 - end_byte;
        }

        // The header appeared normal, not packed
        return -1;
    }

    /**
     * The deobfuscator actually reversed to our own code
     * 
     * @param buffer
     *            byte[] to decrypt
     * @return decrypted byte[]
     */
    private static byte[] decryptSection(byte[] buffer) {
        return deobfuscator.syrup(buffer);
    }

    /**
     * Leverage the crypto functions against itself ^_^
     * 
     * @param buffer
     *            byte[] to decrypt
     * @return decrypted byte[]
     */
    private static byte[] decryptSectionViaExternal(byte[] buffer) {
        return com.code.code.df.DF.syrup(buffer);
    }

    /**
     * Read the file
     * 
     * @param path
     * @return
     * @throws IOException
     */
    private static byte[] readFile(String path) throws IOException {
        RandomAccessFile f = new RandomAccessFile(path, "r");
        byte[] b = new byte[(int) f.length()];
        f.read(b);
        f.close();
        return b;
    }

    /**
     * Write the file
     * 
     * @param path
     * @param buffer
     * @throws IOException
     */
    private static void writeFile(String path, byte[] buffer) throws IOException {
        RandomAccessFile f = new RandomAccessFile(path, "rw");
        f.write(buffer);
        f.close();
    }

}
