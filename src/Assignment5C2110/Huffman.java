package Assignment5C2110;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Huffman {
    private BinaryTree<Pair> huffmanTree = null;
    private final String[] encodings = new String[26];

    public Huffman(Pair[] huffmanCodes) {
        double prob = 0;
        LinkedList<BinaryTree<Pair>> nodes = new LinkedList<>();
        for (Pair p : huffmanCodes) {
            BinaryTree<Pair> node = new BinaryTree<>();
            nodes.add(node);
            node.makeRoot(p);
            prob += p.getProb();
        }
        buildTree(nodes);
    }

    public BinaryTree<Pair> getHuffmanTree() {
        return huffmanTree;
    }

    public String[] getEncodings() {
        return encodings;
    }

    private void buildTree(final LinkedList<BinaryTree<Pair>> list) {
        if (list.size() > 1) {
            list.sort(Comparator.comparing(node -> node.getData().getProb()));
            BinaryTree<Pair> parent = new BinaryTree<>();
            BinaryTree<Pair> childRight = list.pop();
            BinaryTree<Pair> childLeft = list.pop();
            parent.makeRoot(new Pair(
                    '\0', childRight.getData().add(childLeft.getData())));
            parent.setLeft(childLeft);
            parent.setRight(childRight);
            list.addFirst(parent);
            buildTree(list);

        } else {
            BinaryTree<Pair> parent = list.peek();
            if (parent != null) {
                if (parent.getData().getValue() != '\0') {
                    BinaryTree<Pair> newParent = new BinaryTree<>();
                    newParent.makeRoot(new Pair('\0', parent.getData().getProb()));
                    newParent.attachLeft(parent);
                    parent = newParent;
                }
            }
            huffmanTree = parent;
            assert huffmanTree != null;
            generateEncodings(huffmanTree, new StringBuilder());
        }
    }

    private void generateEncodings(BinaryTree<Pair> currentLevel, StringBuilder prefix) {
        if (currentLevel.isLeaf())
            encodings[currentLevel.getData().getValue() - 'A'] = prefix.toString();
        else {
            generateEncodings(currentLevel.getLeft(), prefix.append("0"));
            prefix.deleteCharAt(prefix.length() - 1);
            generateEncodings(currentLevel.getRight(), prefix.append("1"));
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public String encode(String text) {
        String[] word = text.split("");
        if (Arrays.stream(word)
                .anyMatch(chr -> Character.isLowerCase(chr.charAt(0)) && !Character.isWhitespace(chr.charAt(0)))) {
            throw new IllegalArgumentException("Cannot pass text with which contains not all Caps or white space");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : word) {
            if (!Character.isWhitespace(s.charAt(0)))
                stringBuilder.append(encodings[s.charAt(0) - 'A']);

            else stringBuilder.append("\t");
        }
        return stringBuilder.toString();
    }

    public String decode(String encode) {
        StringBuilder message = new StringBuilder();
        BinaryTree<Pair> currLevel = huffmanTree;
        for (int decodePtr = 0; decodePtr != encode.length()+1 ; decodePtr++) {
            if (currLevel.isLeaf()) {
                message.append(currLevel.getData().getValue());
                currLevel = huffmanTree;
                if (decodePtr == encode.length()) break;
                decodePtr--;
            } else if (Character.isWhitespace(encode.charAt(decodePtr))) {
                message.append(" ");
            } else if (encode.charAt(decodePtr) == '0') {
                currLevel = currLevel.getLeft();
            } else currLevel = currLevel.getRight();
        }
        return message.toString();
    }
}
