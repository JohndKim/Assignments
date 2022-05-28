package model.logic;

import commands.IPParser;
import errors.Errors;
import errors.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static commands.IPParser.PERIOD;
import static commands.IPParser.SPACE;

/**
 * A class representing an IP address
 *
 * @author kayak
 * @version 1.0
 */
public class IP implements Comparable<IP> {
    private final String networkIP;

    /**
     * Initializes an IP
     *
     * @param pointNotation IP string
     * @throws ParseException wrong format
     */
    public IP(final String pointNotation) throws ParseException {
        if (!pointNotation.matches(IPParser.REGEX_POINT_NOTATION)) throw new ParseException(Errors.INVALID_IP);
        this.networkIP = pointNotation;
    }

    @Override
    public String toString() {
        return networkIP;
    }

    @Override
    public int compareTo(IP o) {
        if (networkIP.equals(o.toString())) return 0;

        List<String> digits = new ArrayList<>(Arrays.asList(networkIP.split(PERIOD)));
        List<String> newDigits = new ArrayList<>(Arrays.asList(o.toString().split(PERIOD)));

        for (int i = 0; i < digits.size(); i++) {
            if (Integer.parseInt(digits.get(i)) < Integer.parseInt(newDigits.get(i))) return 1;
            if (Integer.parseInt(digits.get(i)) > Integer.parseInt(newDigits.get(i))) return -1;
        }

        return toString().compareTo(o.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IP ip = (IP) o;
        return Objects.equals(networkIP, ip.networkIP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(networkIP);
    }
}
