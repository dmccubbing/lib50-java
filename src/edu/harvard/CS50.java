/**
 * CS50 Library for Java
 * https://github.com/cs50/java
 *
 * Port of CS50 Library to Java from C.
 *
 * Copyright (c) 2016,
 * David J. Malan <malan@harvard.edu>
 * Kareem Zidane <kzidane@cs50harvard.edu>
 * All rights reserved.
 *
 * BSD 3-Clause License
 * http://www.opensource.org/licenses/BSD-3-Clause
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 * * Neither the name of CS50 nor the names of its contributors may be used
 *   to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.harvard;

import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class CS50
{
    // disable buffering on stderr and stdout, but only if class is initialized (i.e., actually used), per
    // http://docs.oracle.com/javase/specs/jls/se7/html/jls-12.html#jls-12.4.1
    static
    {
        System.setErr(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.err), 1)));
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out), 1)));
    }

    /**
     * Prevents instantiation of class (since all methods are static).
     */
    private CS50()
    {}

    /**
     * Prints an error message, formatted like {@link java.io.PrintStream#printf},
     * to standard error, prefixing it with program's name as well as the file and
     * line number from which method was called.
     */
    public static void eprintf(String format, Object... args)
    {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.err.print(stackTrace[2].getFileName() + ":" + stackTrace[2].getLineNumber() + ": ");
        System.err.printf(format, args);
    }

    /**
     * Reads a line of text from standard input and returns the equivalent
     * {@code char}; if text does not represent a {@code char}, user is prompted
     * to retry. If line can't be read, returns {@link Character#MAX_VALUE}.
     *
     * @return char
     */
    public static char getChar()
    {
        // try to get a char from user
        while (true)
        {
            // get a line of text, returning {@link Character#MAX_VALUE} on error
            String s = CS50.getString();
            if (s == null)
            {
                return Character.MAX_VALUE;
            }

            // return a char if only a char was provided
            if (s.length() == 1)
            {
                return s.charAt(0);
            }
            else
            {
                System.out.print("Retry: ");
            }
        }
    }

    /**
     * Reads a line of text from standard input and returns the equivalent
     * {@code double} as precisely as possible; if text does not represent a
     * {@code double} or if value would cause underflow or overflow, user is
     * prompted to retry. If line can't be read, returns {@link Double#MAX_VALUE}.
     *
     * @return double
     */
    public static double getDouble()
    {
        // try to get a double from user
        while (true)
        {
            // get a line of text, returning {@link Double#MAX_VALUE} on error
            String s = CS50.getString();
            if (s == null)
            {
                return Double.MAX_VALUE;
            }

            // return a {@code double} if only a {@code double} was provided
            try
            {
                if (s.matches("(\\+|-)?\\d*(\\.\\d*)?"))
                {
                    double d = Double.parseDouble(s);
                    if (!Double.isInfinite(d) && d < Double.MAX_VALUE)
                    {
                        return d;
                    }
                }
                throw new NumberFormatException();
            }
            catch (Exception e)
            {
                System.out.print("Retry: ");
            }
        }
    }

    /**
     * Reads a line of text from standard input and returns the equivalent
     * {@code float} as precisely as possible; if text does not represent a
     * {@code float} or if value would cause underflow or overflow, user is
     * prompted to retry. If line can't be read, returns {@link Float#MAX_VALUE}.
     *
     * @return float
     */
    public static float getFloat()
    {
        // try to get a float from user
        while (true)
        {
            // get a line of text, returning {@link Float#MAX_VALUE} on error
            String s = CS50.getString();
            if (s == null)
            {
                return Float.MAX_VALUE;
            }

            // return a {@code float} if only a {@code float} was provided
            try
            {
                if (s.matches("(\\+|-)?\\d*(\\.\\d*)?"))
                {
                    float f = Float.parseFloat(s);
                    if (!Float.isInfinite(f) && f < Float.MAX_VALUE)
                    {
                        return f;
                    }
                }
                throw new NumberFormatException();
            }
            catch (Exception e)
            {
                System.out.print("Retry: ");
            }
        }
    }

    /**
     * Reads a line of text from standard input and returns it as an
     * {@code int} in [-2<sup>31</sup>, 2<sup>31</sup> - 1) if possible;
     * if text does not represent such an {@code int} or if value would
     * cause underflow or overflow, user is prompted to retry. If line
     * can't be read, returns {@link Integer#MAX_VALUE}.
     *
     * @return int
     */
    public static int getInt()
    {
        // try to get an int from user
        while (true)
        {
            // get a line of text, returning {@link Integer.MAX_VALUE} on error
            String s = CS50.getString();
            if (s == null)
            {
                return Integer.MAX_VALUE;
            }

            // return an {@code int} if only an {@code int} was provided
            try
            {
                if (s.matches("(\\+|-)?\\d+"))
                {
                    return Integer.parseInt(s);
                }
                throw new NumberFormatException();
            }
            catch (Exception e)
            {
                System.out.print("Retry: ");
            }
        }
    }

    /**
     * Reads a line of text from standard input and returns an equivalent
     * {@code long} in [-2<sup>63</sup>, 2<sup>63</sup> - 1) if possible;
     * if text does not represent such a {@code long} or if value would
     * cause underflow or overflow, user is prompted to retry. If line
     * can't be read, returns {@link Long#MAX_VALUE}.
     *
     * @return long
     */
    public static long getLong()
    {
        // try to get a long from user
        while (true)
        {
            // get a line of text, returning {@link Long.MAX_VALUE} on error
            String s = CS50.getString();
            if (s == null)
            {
                return Long.MAX_VALUE;
            }

            // return a {@code long} if only a {@code long} was provided
            try
            {
                if (s.matches("(\\+|-)?\\d+"))
                {
                    return Long.parseLong(s);
                }
                throw new NumberFormatException();
            }
            catch (Exception e)
            {
                System.out.print("Retry: ");
            }
        }
    }

    /**
     * Reads a line of text from standard input and returns it as a
     * {@link String}, sans trailing line ending. Supports CR ({@code \r}),
     * LF ({@code \n}), and CRLF ({@code \r\n}) as line endings. Returns ""
     * if user inputs only CR, LF, or CRLF. Returns "" upon no input
     * whatsoever (i.e., EOF). Returns {@code null} upon error.
     *
     * @return String
     */
    public static String getString()
    {
        // try to get a String from user, returning null on error
        Scanner s = new Scanner(System.in).useDelimiter("\\n|\\r|\\r\\n");
        try
        {
            return s.nextLine();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
