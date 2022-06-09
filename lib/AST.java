package lib;

import java.util.ArrayList;

public class AST {
    private ASTType astType;
    private String name;
    private ArrayList<AST> children = null;
    private AST value;

    public AST(ASTType astType){
        init(astType);
    }

    public AST(ASTType astType, String name){
        init(astType);
        this.name = name;
    }

    private void init(ASTType astType){
        this.astType = astType;
        if(astType==ASTType.COMPOUND)
            children = new ArrayList<>();
    }

    public void addChild(AST child){
        if(astType!=ASTType.COMPOUND)
            return;
        children.add(child);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setValue(AST value){
        this.value = value;
    }

    public AST getValue(){
        return this.value;
    }

    public AST getAST(){
        return this;
    }
}
