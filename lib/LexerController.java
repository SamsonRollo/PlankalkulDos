package lib;

public class LexerController {
    private Lexer lexer;

    public LexerController(Lexer lexer){
        this.lexer = lexer;
    }

    public void setLexer(Lexer lexer){
        this.lexer = lexer;
    }

    public Lexer getLexer(){
        return this.lexer;
    }

    public void moveCurrentChar(Lexer lexer){
        lexer.moveCurrentChar();
    }

    public Token parseID(Lexer lexer){
        return lexer.parseID();
    }
}
