/* Generated by: JavaCC 21 Parser Generator. Do not edit. JmmGrammarConstants.java */
package pt.up.fe.comp;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import static java.nio.charset.StandardCharsets.*;
/**
 * Token literal values and constants.
 */
public interface JmmGrammarConstants {
    public enum TokenType {
        EOF, _TOKEN_1, _TOKEN_2, _TOKEN_3, _TOKEN_4, _TOKEN_5, _TOKEN_6, LESS, PLUS, MINUS, TIMES, DIVIDE, OPEN_PAREN, CLOSE_PAREN, INTEGER, IMPORT, DOT, NEXTLINE, COMMA, CLASS, EXTENDS, OPEN_CHAV, CLOSE_CHAV, INT, BOOL, STRING, VOID, PUBLIC, RETORNO, STATIC, MAIN, OPEN_RET, CLOSE_RET, EQUAL, IF, WHILE, ELSE, AND, TRUE, FALSE, COMPRIMENTO, THIS, NEW, EXCL, IDENTIFIER, INVALID
    }
    /**
   * Lexical States
   */
    public enum LexicalState {
        DEFAULT, 
    }
    static String displayChar(int ch) {
        if (ch== '\'') return"\'\\'\'";
        if (ch== '\\') return"\'\\\\\'";
        if (ch== '\t') return"\'\\t\'";
        if (ch== '\r') return"\'\\r\'";
        if (ch== '\n') return"\'\\n\'";
        if (ch== '\f') return"\'\\f\'";
        if (ch== ' ') return"\' \'";
        if (ch<128&&!Character.isWhitespace(ch)&&!Character.isISOControl(ch)) return"\'"+(char) ch+"\'";
        if (ch<10) return""+ch;
        return"0x"+Integer.toHexString(ch);
    }

    static String addEscapes(String str) {
        StringBuilder retval= new StringBuilder();
        for (int ch : str.codePoints().toArray()) {
            switch(ch) {
                case'\b':
                retval.append("\\b");
                continue;
                case'\t':
                retval.append("\\t");
                continue;
                case'\n':
                retval.append("\\n");
                continue;
                case'\f':
                retval.append("\\f");
                continue;
                case'\r':
                retval.append("\\r");
                continue;
                case'\"':
                retval.append("\\\"");
                continue;
                case'\'':
                retval.append("\\\'");
                continue;
                case'\\':
                retval.append("\\\\");
                continue;
                default:
                if (Character.isISOControl(ch)) {
                    String s= "0000"+java.lang.Integer.toString(ch, 16);
                    retval.append("\\u"+s.substring(s.length()-4, s.length()));
                }
                else  {
                    retval.appendCodePoint(ch);
                }
                continue;
            }
        }
        return retval.toString();
    }

    // Annoying kludge really...
    static public String readToEnd(Reader reader) {
        try {
            return readFully(reader);
        }
        catch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    static final int BUF_SIZE= 0x10000;
    static public String readFully(Reader reader) throws IOException {
        char[] block= new char[BUF_SIZE];
        int charsRead= reader.read(block);
        if (charsRead<0) {
            throw new IOException("No input");
        }
        else if (charsRead<BUF_SIZE) {
            char[] result= new char[charsRead];
            System.arraycopy(block, 0, result, 0, charsRead);
            reader.close();
            return new String(block, 0, charsRead);
        }
        StringBuilder buf= new StringBuilder();
        buf.append(block);
        do {
            charsRead= reader.read(block);
            if (charsRead> 0) {
                buf.append(block, 0, charsRead);
            }
        }
        while (charsRead== BUF_SIZE);
        reader.close();
        return buf.toString();
    }

    /**
    * @param bytes the raw byte array 
    * @param charset The encoding to use to decode the bytes. If this is null, we check for the
    * initial byte order mark (used by Microsoft a lot seemingly)
    * See: https://docs.microsoft.com/es-es/globalization/encoding/byte-order-markc
    * @return A String taking into account the encoding passed in or in the byte order mark (if it was present). 
    * And if no encoding was passed in and no byte-order mark was present, we assume the raw input
    * is in UTF-8.
    */
    static public String stringFromBytes(byte[] bytes, Charset charset) {
        int arrayLength= bytes.length;
        if (charset== null) {
            int firstByte= arrayLength> 0?Byte.toUnsignedInt(bytes[0]):
            1;
            int secondByte= arrayLength> 1?Byte.toUnsignedInt(bytes[1]):
            1;
            int thirdByte= arrayLength> 2?Byte.toUnsignedInt(bytes[2]):
            1;
            int fourthByte= arrayLength> 3?Byte.toUnsignedInt(bytes[3]):
            1;
            if (firstByte== 0xEF&&secondByte== 0xBB&&thirdByte== 0xBF) {
                return new String(bytes, 3, bytes.length-3, Charset.forName("UTF-8"));
            }
            if (firstByte== 0&&secondByte== 0&&thirdByte== 0xFE&&fourthByte== 0xFF) {
                return new String(bytes, 4, bytes.length-4, Charset.forName("UTF-32BE"));
            }
            if (firstByte== 0xFF&&secondByte== 0xFE&&thirdByte== 0&&fourthByte== 0) {
                return new String(bytes, 4, bytes.length-4, Charset.forName("UTF-32LE"));
            }
            if (firstByte== 0xFE&&secondByte== 0xFF) {
                return new String(bytes, 2, bytes.length-2, Charset.forName("UTF-16BE"));
            }
            if (firstByte== 0xFF&&secondByte== 0xFE) {
                return new String(bytes, 2, bytes.length-2, Charset.forName("UTF-16LE"));
            }
            charset= UTF_8;
        }
        return new String(bytes, charset);
    }

    static public String stringFromBytes(byte[] bytes) {
        return stringFromBytes(bytes, null);
    }

}
