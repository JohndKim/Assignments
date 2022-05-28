package model.logic;

import errors.Errors;
import errors.ParseException;

import java.util.*;

import static commands.IPParser.*;

/**
 * Class representing a network
 *
 * @author kayak
 * @version 1.0
 */
public class Network {
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSED_BRACKET = ")";
    private static final String SPACE = " ";
    private static final String SPACE_REGEX = "[\\s]";
    private List<List<IP>> networkList;
    private final List<List<List<IP>>> listOfNetworks;
    private Height height;
    private HashMap<IP, Boolean> visited;

    /**
     * Initializes a network
     *
     * @param root network's root
     * @param children children of that root
     */
    public Network(final IP root, final List<IP> children) {
        this.listOfNetworks = new ArrayList<>();
        this.networkList = createNetwork(root, children);
        this.height = new Height();
    }

    /**
     * Initializes network through bracket notation
     *
     * @param bracketNotation ips in string format
     * @throws ParseException wrong format
     */
    public Network(final String bracketNotation) throws ParseException {
        if (!bracketNotation.matches(REGEX_BRACKET_NOTATION)) throw new ParseException(Errors.INVALID_FORMAT);

        this.listOfNetworks = new ArrayList<>();
        this.networkList = createNetworkBN(bracketNotation);
        this.height = new Height();
    }

    /**
     * Adds a network to an existing network. If contains no matching IP, add to list of networks
     *
     * @param subnet network to add
     * @return true = added; false = not added
     * @throws ParseException wrong IP
     */
    public boolean add(final Network subnet) throws ParseException {
        // find which tree is the main tree
        List<List<IP>> subNetList = subnet.getNetworkList();

        // key: matched ip; value: number of parents
        HashMap<IP, Integer> ipAndParent = getIpAndParent(subNetList);

        if (ipAndParent == null) return false; // circular dependency

        // case NO matching IP, hence store separately (forest is made up of many trees)
        if (ipAndParent.isEmpty()) listOfNetworks.add(subNetList);

        IP mainRoot = null;

        // get the ip (root) with 1 parents (bigger tree)
        for (Map.Entry<IP, Integer> entry : ipAndParent.entrySet()) {
            if (entry.getValue().equals(1)) {
                mainRoot = entry.getKey();
                break;
            }
            if (mainRoot == null && entry.getValue().equals(0)) mainRoot = entry.getKey();
        }

        if (mainRoot == null) return false; // should be impossible but just in case there are no IPs with 0 parents

        List<List<IP>> main;
        List<List<IP>> sub;

        // determining which network will be the main (the one with the root)
        if (networkList.get(0).get(0).equals(mainRoot)) { // if current main tree is the root
            main = subNetList;
            sub = networkList;
        } else {
            main = networkList;
            sub = subNetList;
        }

        // get main's subTree
        List<List<IP>> mainSubTree = getSubTree(mainRoot, main); // immutable copy
        List<List<IP>> newMainSubTree = new ArrayList<>(mainSubTree); // mutable copy
        List<List<IP>> updatedMainSubTree = updateList(mainSubTree, sub, newMainSubTree); // updated copy

        if (updatedMainSubTree.isEmpty()) return false; // circular dependency

        int startIndex = findForeignIPIndex(mainSubTree.get(0).get(0), main);
        int endIndex = findForeignIPIndex(mainSubTree.get(mainSubTree.size() - 1).get(0), main);

        if (endIndex >= startIndex) main.subList(startIndex, endIndex + 1).clear(); // remove old subTree

        int counter = 0;
        for (int i = 0; i < updatedMainSubTree.size(); i++) { // replace with new subTree
            main.add(startIndex + i, updatedMainSubTree.get(counter));
            counter++;
        }

        networkList = main;
        return true;
    }

    /**
     * Converts network to a list of IPs from smallest to largest
     *
     * @return list in ascending order
     */
    public List<IP> list() {
        List<IP> ips = new ArrayList<>();
        networkList.forEach(i -> ips.add(i.get(0)));
        return sortList(ips);
    }

    /**
     * Connects to nodes together
     *
     * @param ip1 ip 1
     * @param ip2 ip 2
     * @return true = connected; false = not connected
     */
    public boolean connect(final IP ip1, final IP ip2) {
        int index = getIndex(ip1);
        if (index == -1) return false;

        networkList.get(index).add(ip2);
        return true;
    }

    /**
     * Disconnects two ips
     *
     * @param ip1 ip 1
     * @param ip2 ip 2
     * @return true = disconnected; false = not disconnected
     */
    public boolean disconnect(final IP ip1, final IP ip2) {
        if (networkList.get(0).size() == 2) return false; // only one connection left (root and 1 child)
        int index = getIndex(ip1);
        if (index == -1) return false;

        networkList.get(index).remove(ip2);
        if (getParent(ip1, networkList) == null && networkList.get(index).size() == 1) // has no parent or child
            networkList.get(index).remove(ip1);
        if (getParent(ip2, networkList) == null && networkList.get(index).size() == 1)
            networkList.get(index).remove(ip2);

        return true;
    }

    /**
     * Checks if the network contains this IP
     *
     * @param ip ip to check
     * @return true = contains it; false = doesn't
     */
    public boolean contains(final IP ip) {
        return networkList.stream().anyMatch(i -> i.get(0).compareTo(ip) == 0);
    }

    /**
     * Gets height of the network
     *
     * @param root root
     * @return height
     * @throws ParseException wrong ip
     */
    public int getHeight(final IP root) throws ParseException {
        visited = new HashMap<>();
        for (List<IP> ips : networkList) visited.put(ips.get(0), false);
        this.height = new Height(); // reset height
        visit(root); // recursion
        return height.getMaxHeight();
    }

    /**
     * Converts network to a list of levels
     *
     * @param root root
     * @return list of levels
     * @throws ParseException wrong IP
     */
    public List<List<IP>> getLevels(final IP root) throws ParseException {
        List<IP> ips = new ArrayList<>();
        List<List<IP>> levels = new ArrayList<>();
        ips.add(root);
        levels.add(ips);

        return levelsList(ips, levels);
    }

    /**
     * Find the shortest route between the starting IP and ending IP
     *
     * @param start start ip
     * @param end end ip
     * @return a list of all visited ips
     */
    public List<IP> getRoute(final IP start, final IP end) throws ParseException {
        List<IP> parents = new ArrayList<>();
        return reverseList(getParents(end, start, parents));
    }

    /**
     * Converts network to string
     *
     * @param root root
     * @return string
     * @throws ParseException wrong IP
     */
    public String toString(IP root) throws ParseException {
        return build(new StringBuilder(), root, 0, getSubTree(root, getNetworkList())).toString();
    }

    /**
     * Gets network list
     *
     * @return network list
     */
    public List<List<IP>> getNetworkList() {
        return networkList;
    }

    // ###############   P R I V A T E   ###############

    private List<List<IP>> createNetwork(final IP root, final List<IP> children) {
        List<List<IP>> newNetwork = new ArrayList<>(1 + children.size());
        List<IP> rootAndChildren = new LinkedList<>();

        rootAndChildren.add(root);
        rootAndChildren.addAll(children);
        newNetwork.add(rootAndChildren);

        for (IP ip : children) {
            LinkedList<IP> child = new LinkedList<>();
            child.add(ip);
            newNetwork.add(child);
        }

        listOfNetworks.add(newNetwork);
        return newNetwork;
    }

    private List<String> separateIPs(String bracketNotation) {
        String str = bracketNotation.replaceAll(BRACKETS, EMPTY_STRING);
        return new ArrayList<>(Arrays.asList(str.split(SPACE_REGEX)));
    }

    private List<List<IP>> createNetworkBN(String bracketNotation) throws ParseException {
        List<String> iPList = separateIPs(bracketNotation);
        IP root = new IP(iPList.get(0));

        List<IP> children = new LinkedList<>();
        for (int i = 1; i < iPList.size(); i++) children.add(new IP(iPList.get(i)));

        return createNetwork(root, children);
    }

    private List<List<IP>> updateList(List<List<IP>> mainSubTree, List<List<IP>> sub, List<List<IP>> newMainSubTree)
            throws ParseException {
        for (List<IP> subInt : sub) { // loops every sub int with a main int
            IP match = subInt.get(0);

            for (List<IP> mainInt : mainSubTree) { // for each list in the adjacency list
                if (!mainInt.get(0).equals(match)) continue; // if they don't match, skip
                if (!sameParents(match, sub, mainSubTree)) return Collections.emptyList(); // circular dependency

                List<IP> subInts = getList(match, sub);
                List<IP> mainInts = getList(match, mainSubTree);
                List<IP> newMainInts = getList(match, mainSubTree);

                List<IP> newIPs = new ArrayList<>();

                for (IP i : subInts) {
                    boolean canAdd = true;

                    for (IP j : mainInts) {
                        if (i.equals(j)) {
                            canAdd = false; // duplicate
                            break;
                        }
                    }
                    if (canAdd) {
                        newIPs.add(i);
                        newMainInts.add(i); // else add
                    }
                }

                newMainSubTree.set(findForeignIPIndex(newMainInts.get(0), newMainSubTree), newMainInts);

                // adds the new children to the list
                for (int i = 0; i < newIPs.size(); i++) {
                    List<IP> list = new ArrayList<>();
                    list.add(newIPs.get(i));
                    newMainSubTree.add(findForeignIPIndex(newMainInts.get(0), newMainSubTree) + 1 + i, list);
                }
            }
        }

        // BASE CASE
        if (mainSubTree == newMainSubTree) return newMainSubTree;

        // RECURSIVE CASE
        return updateList(newMainSubTree, sub, newMainSubTree);
    }

    private List<List<IP>> getSubTree(final IP root, List<List<IP>> list) throws ParseException {
        List<List<IP>> subTree = new ArrayList<>();
        int rootIndex = findForeignIPIndex(root, list);

        for (int i = rootIndex; i < list.size(); i++) { // for each list
            boolean listAdded = false;
            // adds the root unconditionally
            if (i == rootIndex) {
                subTree.add(list.get(i));
                continue;
            }
            // every node after, we check if they're a child of all previous nodes
            for (int j = rootIndex; j < i; j++) {
                if (list.get(j).contains(list.get(i).get(0))) { // checks if this node is a subtree child
                    subTree.add(list.get(i)); // adds child
                    listAdded = true;
                    break;
                }
            }
            if (!listAdded) break; // once we finish the entire subtree, break
        }

        return subTree;
    }

    private List<IP> getList(IP parent, List<List<IP>> list) {
        List<IP> newList = new ArrayList<>();

        for (List<IP> ips : list) {
            if (ips.get(0).equals(parent)) {
                newList.addAll(ips);
                return newList;
            }
        }

        return Collections.emptyList();
    }

    private IP getParent(IP child, List<List<IP>> tree) {
        for (List<IP> ips : tree) {
            if (!ips.get(0).equals(child) && ips.contains(child)) {
                return ips.get(0);
            }
        }
        return null;
    }

    private boolean sameParents(IP child, List<List<IP>> treeOne, List<List<IP>> treeTwo) {
        IP father = getParent(child, treeOne);
        IP mother = getParent(child, treeTwo);

        if (father == null || mother == null) return true; // compare root and child ip
        return father.equals(mother); // compare child ip
    }

    private List<IP> sortList(List<IP> ips) {
        for (int i = 0; i < ips.size() - 1; i++) {
            for (int j = i + 1; j < ips.size(); j++) {
                if (ips.get(i).compareTo(ips.get(j)) > 0) continue; // case that current num is smaller
                IP temp = ips.get(j); // new minimum ip
                ips.set(j, ips.get(i)); // new min replaces with old min
                ips.set(i, temp);
            }
        }
        return ips;
    }

    private int findIPIndex(final IP ip) throws ParseException {
        return networkList.indexOf(networkList.stream().filter(i -> i.get(0).compareTo(ip) == 0).findFirst().
                orElseThrow(() -> new ParseException(Errors.NONEXISTENT_IP)));
    }

    private int findForeignIPIndex(final IP ip, final List<List<IP>> list) throws ParseException {
        return list.indexOf(list.stream().filter(i -> i.get(0).compareTo(ip) == 0).findFirst().
                orElseThrow(() -> new ParseException(Errors.NONEXISTENT_IP)));
    }

    private int findSpecificHeight(final IP start, final IP end) throws ParseException {
        for (List<IP> list : getLevels(start)) if (list.contains(end)) return getLevels(start).indexOf(list) + 1;
        return -1;
    }

    private void visit(IP ip) throws ParseException {
        int index = findIPIndex(ip); // found the node
        visited.replace(ip, true); // visited this node
        height.increaseTreeHeight(); // travel down node = +1 height

        List<IP> ipList = networkList.get(index); // get this node's children
        for (IP ipp : ipList) if (Boolean.FALSE.equals(visited.get(ipp))) visit(ipp);

        height.decreaseTreeHeight(); // no more unvisited children therefore -1 to travel back a node
        if (height.greaterThanMax()) height.setMaxHeight(height.getTreeHeight());
    }

    private IP getIP(List<List<IP>> list, int index) {
        return list.get(index).get(0);
    }

    private HashMap<IP, Integer> getIpAndParent(List<List<IP>> subNetList) throws ParseException {
        HashMap<IP, Integer> ipAndParent = new HashMap<>();

        // check if same node comes up twice, if so then false
        for (List<IP> subInt : subNetList) { // for each new subnet ips
            IP matchedIP = subInt.get(0);

            for (List<IP> mainInt : networkList) { // for each old ips; convert to stream() later
                if (matchedIP.compareTo(mainInt.get(0)) != 0) continue; // if same ip

                // check num of parents
                List<IP> parents = new ArrayList<>(2);

                // find parent in new ips:
                for (int i = 0; i < findForeignIPIndex(matchedIP, subNetList); i++) {
                    if (subNetList.get(i).contains(matchedIP)) {
                        parents.add(getIP(subNetList, i));
                        break;
                    }
                }

                // find parent in old ips:
                for (int i = 0; i < findIPIndex(matchedIP); i++) { // from root to the current ip
                    if (networkList.get(i).contains(matchedIP)) { // if any list contains the matched ip as a child
                        parents.add(getIP(networkList, i));
                        break;
                    }
                }
                // if matched ip both have a parent AND they aren't the same (circular dependency case)
                if (parents.size() == 2 && parents.get(0).compareTo(parents.get(1)) != 0) return null;

                ipAndParent.put(matchedIP, parents.size());
            }
        }

        return ipAndParent;
    }

    private StringBuilder build(StringBuilder sb, IP root, int currentIndex, List<List<IP>> subList)
            throws ParseException {
        // BASE CASE
        if (currentIndex == subList.size()) return sb; // reaches end of list

        boolean isValid = false;
        for (int i = 0; i <= currentIndex; i++) { // first check that the int is a children of the root
            if (subList.get(i).contains(subList.get(currentIndex).get(0))) {
                isValid = true;
                break;
            }
        }
        if (!isValid) return sb; // current child is not a part of this list

        // if it has children
        if (subList.get(currentIndex).size() > 1) {
            // when nothing is in the list
            if (sb.length() == 0) sb.append(OPEN_BRACKET).append(root).append(CLOSED_BRACKET + SPACE);
            else sb.insert(sb.length() - (getHeight(root)), SPACE + OPEN_BRACKET
                    + subList.get(currentIndex).get(0) + CLOSED_BRACKET);

            return build(sb, root, currentIndex + 1, subList);
        }
        // no children
        if (sb.length() == 0) sb.append(OPEN_BRACKET).append(root).append(CLOSED_BRACKET + SPACE);
        else sb.insert(sb.length() - (findSpecificHeight(root, subList.get(currentIndex).get(0))),
                SPACE + subList.get(currentIndex).get(0));

        return build(sb, root, currentIndex + 1, subList);
    }

    private List<IP> getParents(final IP child, final IP end, List<IP> parents) throws ParseException {
        // BASE CASE
        if (child.equals(end)) return parents;

        // REGRESSIVE CASE
        int index = findIPIndex(child);

        // loops through entire list until the index of the IP to see if it is a child
        for (int i = 0; i < index; i++) {
            if (!networkList.get(i).contains(child)) continue; // didn't find the parent

            if (!parents.contains(child)) parents.add(child);
            parents.add(networkList.get(i).get(0)); // add parent to list
            return getParents(networkList.get(i).get(0), end, parents); // find parent's parent

        }
        return parents;
    }

    private List<IP> reverseList(List<IP> list) {
        for (int i = 0; i < list.size() / 2; i++) {
            IP tempIP = list.get(i);
            list.set(i, list.get(list.size() - i - 1));
            list.set(list.size() - i - 1, tempIP);
        }
        return list;
    }

    private List<List<IP>> levelsList(List<IP> ips, List<List<IP>> levels) throws ParseException {
        // BASE CASE (no more children to add)
        if (ips.isEmpty()) return levels;

        // RECURSIVE CASE
        List<IP> children = new ArrayList<>();

        for (IP ip : ips) { // for each IP in the list, find their child
            int index = findIPIndex(ip); // index of the list of children
            for (int i = 1; i < networkList.get(index).size(); i++) children.add(networkList.get(index).get(i));
        }
        if (!children.isEmpty()) levels.add(children);

        return levelsList(children, levels);
    }

    private int getIndex(IP ip) {
        int index;
        try {
            index = findIPIndex(ip);
        } catch (ParseException e) {
            return -1;
        }
        return index;
    }
}
