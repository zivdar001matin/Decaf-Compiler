package codegen.ast;

import codegen.symboltable.DSCP;

import java.util.List;

public interface Node {

    /**
     * @return type of the node.
     */
    NodeType getNodeType();

    /**
     * Sets the symbol info.
     */
    void setDSCP(DSCP dscp);

    /**
     * Gets the symbol info.
     */
    DSCP getDSCP();

//    /**
//     * Accepts a simple visitor.
//     */
//    void accept(SimpleVisitor visitor) throws Exception;

    /**
     * Adds a node to the end of the list of children.
     */
    void addChild(Node node);

    /**
     * Adds a node to the list of children at the specified location.
     */
    void addChild(int index, Node node);

    /**
     * Adds a list of nodes to the end of the list of children.
     */
    void addChildren(List<Node> nodes);

    /**
     * Adds a list of nodes to the end of the list of children.
     */
    void addChild(Node... nodes);

    /**
     * @return the list of children.
     */
    List<Node> getChildren();

    /**
     * Sets the list of children.
     */
    void setChildren(Node... nodes);

    /**
     * @return the child at the specified location.
     */
    Node getChild(int index);

    /**
     * @return the parent node.
     */
    Node getParent();

    /**
     * Set the node parent.
     */
    void setParent(Node parent);

}
