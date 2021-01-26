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

    static {
        dataSeg += "\tzeroDouble: \t.double \t0.0\n";
        textSeg += "\tldc1\t$f0, zeroDouble\n";
    }

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
            case IDENTIFIER:
                cgenIdentifier(node);
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
            case PRINT_STATEMENT:
                cgenPrint(node);
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
        DSCP dscp = new DSCP(node.getType(), null);
        dscp.setValue(String.valueOf(node));
        node.setDSCP(dscp);
        ((ExpressionNode) node.getParent()).setIsIdentifier();
    }

    private static void cgenIdentifier(Node node) throws Exception {
        IdentifierNode identifierNode = (IdentifierNode) node;
        String entry = identifierNode.getValue();
        DSCP dscp = spaghettiStack.getDSCP(entry);
        node.setDSCP(dscp);
        ((ExpressionNode)node.getParent()).setIsIdentifier();
        // load scopeName_Entry into $v1 and send it up
        textSeg += "\tlw\t$v1, " + spaghettiStack.getEntryScope(entry)+"."+entry + "\n";
    }

    private static void cgenExpressionStatement(Node node) throws Exception {
        cgen(node.getChild(0));
        node.setDSCP(node.getChild(0).getDSCP());

        //don't change $v1
    }

    private static void cgenAssign(Node node) throws Exception {
        IdentifierNode identifierNode = (IdentifierNode) node.getChild(0);
        ExpressionNode expressionNode = (ExpressionNode) node.getChild(1);

        cgen(expressionNode);
        String value = expressionNode.getDSCP().getValue();

        String entry = identifierNode.toString();
        DSCP identifierDSCP = spaghettiStack.getDSCP(entry);

        if (!identifierDSCP.getType().equals(expressionNode.getDSCP().getType()))
            throw new Error("Type of assign doesn't match " + identifierDSCP.getType() + " -> " + expressionNode.getDSCP().getType());

        identifierDSCP.setValue(value);

        textSeg += "\t\t\t\t\t\t\t\t\t\t#Begin assign " + spaghettiStack.getEntryScope(entry).toString()+"."+entry + '\n';
        textSeg += "\tla\t$a0, " + spaghettiStack.getEntryScope(entry).toString()+"."+entry + '\n';
        textSeg += "\tli\t$a1, " + value + '\n';
        textSeg += "\tsw\t$a1  0($a0)" + "\t\t\t\t\t\t# End assign\n";

        //continue code generating
        cgen(node.getChild(2));
    }

    private static void cgenVariableDecl(Node node) throws Exception {
        Type typePrimitive = ((PrimitiveNode) node.getChild(0)).getType();
        IdentifierNode identifierNode = (IdentifierNode) node.getChild(1);

        String data_id = spaghettiStack + "." + identifierNode.getValue() + ':';
        dataSeg += '\t' + data_id + '\t' + typePrimitive.getSignature() + '\t' + typePrimitive.getInitialValue() + '\n';

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

    private static void cgenPrint(Node node) throws Exception {
        cgen(node.getChild(0));
        switch (node.getChild(0).getDSCP().getType().getPrimitive()){
            case INT:
                textSeg += "\tli\t$v0, 1\n";
                textSeg += "\tadd\t$a0, $v1, $zero\n";
                textSeg += "\tsyscall\n";
                break;
            case DOUBLE:
                textSeg += "\tli\t$v0, 3\n";
                textSeg += "\tadd.d\t$f12, $f10, $f0\n";
                textSeg += "\tsyscall\n";
                break;
            default:
                break;
        }
        System.out.println(node.getChild(0).getDSCP().getValue());
        cgen(node.getChild(1));
    }

    private static Type widen(ExpressionNode leftChild, ExpressionNode rightChild) throws Exception {
        if(leftChild.getDSCP().getType().equals(rightChild.getDSCP().getType())) {
            Type type = leftChild.getDSCP().getType();
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

    public static String getDataSeg() {
        return dataSeg;
    }

    public static String getTextSeg() {
        return textSeg;
    }
}
