import errors.ParseException;
import model.logic.IP;
import model.logic.Network;

import java.util.List;

/**
 * Represents the application where networks can be created
 *
 * @author kayak
 * @version 1.0
 */
public class Application {

    /**
     * Main method
     *
     * @param args args
     * @throws ParseException error thrown when parsing IP notation
     */
    public static void main(String[] args) throws ParseException {
        IP root = new IP("141.255.1.133");
        List<List<IP>> levels = List.of(List.of(root),
                List.of(new IP("0.146.197.108"), new IP("122.117.67.158")));
        Network network = new Network(root, levels.get(1));

        // (141.255.1.133 0.146.197.108 122.117.67.158)
        System.out.println(network.toString(root));

        // height = 1
        System.out.println(network.getHeight(root));

        // true
        System.out.println((levels.size() - 1) == network.getHeight(root));

        // true
        System.out.println(List.of(List.of(root), levels.get(1)).equals(network.getLevels(root)));

        // true (adds the exact same thing that's already in the main)
        System.out.println(network.add(new Network("(141.255.1.133 122.117.67.158)")));

        // 141: 0, 122
        // 85: 34 231 122

        final Network net = new Network("(85.193.148.81 34.49.145.239 231.189.0.127 122.117.67.158)");

        // false as circular dependency
        System.out.println(network.add(net));

        IP newRoot = new IP("85.193.148.81");

        // nothing as it wasn't added
        // System.out.println(network.toString(newRoot)); // parse error as no root exists

        // 141: 0, 122
        // 85: 34, 231, 141

        // true
        System.out.println(network.add(new Network("(85.193.148.81 34.49.145.239 231.189.0.127 141.255.1.133)")));

        // (85.193.148.81 34.49.145.239 231.189.0.127 (141.255.1.133 122.117.67.158 0.146.197.108))
        System.out.println(network.toString(newRoot));

        // [[85.193.148.81], [34.49.145.239, 231.189.0.127, 141.255.1.133], [122.117.67.158, 0.146.197.108]]
        System.out.println(network.getLevels(newRoot));

        // height = 2 (equal to the levels.size() - 1 = (3 - 1))
        System.out.println(network.getHeight(newRoot));

        // [85.193.148.81, 141.255.1.133, 122.117.67.158]
        System.out.println(network.getRoute(newRoot, new IP("122.117.67.158")));

        // [0.146.197.108, 34.49.145.239, 85.193.148.81, 122.117.67.158, 141.255.1.133, 231.189.0.127]
        System.out.println(network.list());

        // true
        System.out.println(network.contains(new IP("122.117.67.158")));

        // this would be a circular dependency :skull:
        System.out.println(network.connect(newRoot, new IP("0.146.197.108")));
        System.out.println(network.disconnect(newRoot, new IP("0.146.197.108")));

        // same after connecting and disconnecting
        System.out.println(network.toString(newRoot));
    }
}
