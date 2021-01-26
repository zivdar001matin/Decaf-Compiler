package codegen.ast;

import codegen.symboltable.DSCP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimpleNode implements Node{

    private List<Node> children = new ArrayList<>();
    private Node parent;
    private final NodeType nodeType;
    private DSCP dscp;

    public SimpleNode(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public NodeType getNodeType() {
        return this.nodeType;
    }

    @Override
    public void setDSCP(DSCP dscp) {
        this.dscp = dscp;
    }

    @Override
    public DSCP getDSCP() {
        return this.dscp;
    }

    @Override
    public void addChild(Node node) {
        children.add(node);
    }

    @Override
    public void addChild(int index, Node node) {
        children.add(index, node);
    }

    @Override
    public void addChildren(List<Node> nodes) {
        children.addAll(nodes);
    }

    @Override
    public void addChild(Node... nodes) {
        Collections.addAll(children, nodes);
    }

    @Override
    public void setChildren(Node... nodes) {
        children = Arrays.asList(nodes);
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }

    @Override
    public Node getChild(int index) {
        return children.get(index);
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public Node getParent() {
        return parent;
    }
}
