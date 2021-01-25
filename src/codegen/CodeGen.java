package codegen;

import ast.*;
import ast.literal.Literal;
import symboltable.DSCP;
import symboltable.SymbolTable;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGen {

    private static final SymbolTable spaghettiStack = new SymbolTable();

    public static String dataSeg = ".data\n";
    public static String textSeg = ".text\n";

    public static void cgen(Node node) throws Exception {
        switch (node.getNodeType()) {
            case METHOD_DECLARATION:
                cgenMethodDeclaration(node);
                break;
            case BLOCK:
                cgenBlock(node);
                break;
            case VARIABLE_DECLARATION:
                cgenVariableDecl(node);
                break;
            case ASSIGN:
                cgenAssign(node);
                break;
            case EXPRESSION_STATEMENT:
                cgenExpressionStatement(node);
                break;
            case LITERAL:
                cgenLiteral((Literal) node);
                break;
            case ADDITION:
                cgenAdditon(node);
                break;
            case SUBTRACTION:
                cgenSubtraction(node);
                break;
            case MULTIPLICATION:
                cgenMultiplication(node);
                break;
            case DIVISION:
                cgenDivision(node);
                break;
            default:
                cgenAllChildren(node);
                break;
        }
    }

    private static void cgenMethodDeclaration(Node node) throws Exception {
        //type
        PrimitiveNode returnNode = (PrimitiveNode) node.getChild(0);
        String returnSig = returnNode.getType().getSignature();
        //identifier
        IdentifierNode identifierNode = (IdentifierNode) node.getChild(1);
        String methodName = identifierNode.getValue();  //TODO add to vTable
        //arguments
        cgen(node.getChild(2));
        //body
        cgen(node.getChild(3));
    }

    private static void cgenLiteral(Literal node) throws Exception {
        ((ExpressionNode) node.getParent()).setIsIdentifier();
        DSCP dscp = new DSCP(node.getType(), null);
        dscp.setValue(String.valueOf(node));
        node.setDSCP(dscp);
    }

    private static void cgenExpressionStatement(Node node) throws Exception {
        cgen(node.getChild(0));
        node.setDSCP(node.getChild(0).getDSCP());
    }

    private static void cgenAssign(Node node) throws Exception {
        IdentifierNode identifierNode = (IdentifierNode) node.getChild(0);
        ExpressionNode expressionNode = (ExpressionNode) node.getChild(1);

        cgen(expressionNode);
        String value = expressionNode.getDSCP().getValue();

        DSCP identifierDSCP = spaghettiStack.getDSCP(identifierNode.toString());

        identifierDSCP.setValue(value);

        //continue code generating
        cgen(node.getChild(2));
    }

    private static void cgenVariableDecl(Node node) throws Exception {
        Type typePrimitive = ((PrimitiveNode) node.getChild(0)).getType();
        IdentifierNode identifierNode = (IdentifierNode) node.getChild(1);
        DSCP dscp = new DSCP(typePrimitive, identifierNode);
//        dscp.setConstant(); //TODO
        spaghettiStack.addEntry(identifierNode.getValue(), dscp);
    }

    private static void cgenBlock(Node node) throws Exception {
        spaghettiStack.enterScope(String.valueOf(node.getParent().getChild(1)));
        cgenAllChildren(node);
        spaghettiStack.leaveScope();
    }

    private static void cgenAllChildren(Node node) throws Exception {
        for (Node child : node.getChildren()) {
            cgen(child);
        }
    }

    private static void cgenAdditon(Node node) throws Exception {
        ExpressionNode leftChild = (ExpressionNode) node.getChild(0);
        ExpressionNode rightChild = (ExpressionNode) node.getChild(1);
        cgen(leftChild);
        cgen(rightChild);
        Type type = widen(leftChild, rightChild);

        DSCP dscp = new DSCP(type, null);
        String value = null;
        if (type.equals(PrimitiveType.INT))
            value = String.valueOf(Integer.parseInt(leftChild.getResultName()) + Integer.parseInt(rightChild.getResultName()));
        else if (type.equals(PrimitiveType.DOUBLE))
            value = String.valueOf(Double.parseDouble(leftChild.getResultName()) + Double.parseDouble(rightChild.getResultName()));
        dscp.setValue(value);
        node.setDSCP(dscp);

        ExpressionNode parent = (ExpressionNode) node.getParent();
        parent.setIsIdentifier();
    }

    private static void cgenSubtraction(Node node) throws Exception {
        ExpressionNode leftChild = (ExpressionNode) node.getChild(0);
        ExpressionNode rightChild = (ExpressionNode) node.getChild(1);
        cgen(leftChild);
        cgen(rightChild);
        Type type = widen(leftChild, rightChild);

        DSCP dscp = new DSCP(type, null);
        String value = null;
        if (type.equals(PrimitiveType.INT))
            value = String.valueOf(Integer.parseInt(leftChild.getResultName()) - Integer.parseInt(rightChild.getResultName()));
        else if (type.equals(PrimitiveType.DOUBLE))
            value = String.valueOf(Double.parseDouble(leftChild.getResultName()) - Double.parseDouble(rightChild.getResultName()));
        dscp.setValue(value);
        node.setDSCP(dscp);

        ExpressionNode parent = (ExpressionNode) node.getParent();
        parent.setIsIdentifier();
    }

    private static void cgenMultiplication(Node node) throws Exception {
        ExpressionNode leftChild = (ExpressionNode) node.getChild(0);
        ExpressionNode rightChild = (ExpressionNode) node.getChild(1);
        cgen(leftChild);
        cgen(rightChild);
        Type type = widen(leftChild, rightChild);

        DSCP dscp = new DSCP(type, null);
        String value = null;
        if (type.equals(PrimitiveType.INT))
            value = String.valueOf(Integer.parseInt(leftChild.getResultName()) * Integer.parseInt(rightChild.getResultName()));
        else if (type.equals(PrimitiveType.DOUBLE))
            value = String.valueOf(Double.parseDouble(leftChild.getResultName()) * Double.parseDouble(rightChild.getResultName()));
        dscp.setValue(value);
        node.setDSCP(dscp);

        ExpressionNode parent = (ExpressionNode) node.getParent();
        parent.setIsIdentifier();
    }

    private static void cgenDivision(Node node) throws Exception {
        ExpressionNode leftChild = (ExpressionNode) node.getChild(0);
        ExpressionNode rightChild = (ExpressionNode) node.getChild(1);
        cgen(leftChild);
        cgen(rightChild);
        Type type = widen(leftChild, rightChild);

        DSCP dscp = new DSCP(type, null);
        String value = null;
        if (type.equals(PrimitiveType.INT))
            value = String.valueOf(Integer.parseInt(leftChild.getResultName()) / Integer.parseInt(rightChild.getResultName()));
        else if (type.equals(PrimitiveType.DOUBLE))
            value = String.valueOf(Double.parseDouble(leftChild.getResultName()) / Double.parseDouble(rightChild.getResultName()));
        dscp.setValue(value);
        node.setDSCP(dscp);

        ExpressionNode parent = (ExpressionNode) node.getParent();
        parent.setIsIdentifier();
    }

    private static Type widen(ExpressionNode leftChild, ExpressionNode rightChild) throws Exception {
        if(leftChild.getDSCP().getType().equals(rightChild.getDSCP().getType())) {
            Type type = leftChild.getType();
            if (type.equals(PrimitiveType.INT) || type.equals(PrimitiveType.DOUBLE))
                return type;
            throw new Exception("can't do operation on " + type);
        }
        throw new Exception("can't do operation on " + leftChild.getType() + " and " + rightChild.getType());
    }

    public static void compile() throws IOException {
        FileWriter out = new FileWriter("out.asm");
        out.write(dataSeg + textSeg);
        out.close();
    }

}
