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

        while(token!=null){
            AST child = parse();
        }

        return ast;
    } 

    public Parser getParser(){
        return this;
    }
}
