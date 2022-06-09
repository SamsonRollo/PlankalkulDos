package lib;

public class Parser {
    private Lexer lexer;
    private Token token;

    public Parser(Lexer lexer){
        this.lexer = lexer;
        this.token = lexer.getNextToken();
    }

    public AST parse(){
        return new AST(ASTType.NOOP); //change this
    }

    public Token advance(TokenType type){ //change method name
        // if(token.getType()!=type){
        //     //raise an error
        //     System.out.println("unexpected token");
        //     System.exit(1);
        // }
        this.token = lexer.getNextToken();
        return this.token;
    }

    public AST parseCompound(){
        AST ast = new AST(ASTType.COMPOUND);

        while(token!=null)
            ast.addChild(parseExpression());

        return ast;
    } 

    public AST parseParenContent(){
        advance(TokenType.TOKEN_LPAREN);
        AST parenRootAST = new AST(ASTType.COMPOUND);

        parenRootAST.addChild(parseExpression());

        while(token.getType()==TokenType.TOKEN_COMMA){
            advance(TokenType.TOKEN_COMMA);
            parenRootAST.addChild(parseExpression());
        }

        advance(TokenType.TOKEN_RPAREN);

        return parenRootAST;
    }

    public AST parseID(){
        String name = token.getValue();
        advance(TokenType.TOKEN_BLOCK_ID);
        
        if(token.getType()==TokenType.TOKEN_EQUALS){
            advance(TokenType.TOKEN_EQUALS);
            AST assAST = new AST(ASTType.ASSIGNMENT, name); 
            assAST.setValue(parseExpression());
            return assAST;
        }
        else
            return new AST(ASTType.VARIABLE, name);
    }

    public AST parseExpression(){
        if(token.getType()==TokenType.TOKEN_BLOCK_ID)
            return parseID();
        else if(token.getType()==TokenType.TOKEN_LPAREN)
            return parseParenContent();

        //raise an error
        System.out.println("unexpected token");
        System.exit(1);

        return null;
    }

    public Parser getParser(){
        return this;
    }
}
