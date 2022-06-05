package lib;

public class Token {
    private String value;
    private TokenType type;

    public Token(){
        this.value = null;
        this.type = null;
    }

    public Token(String value, TokenType type){
        this.value = value;
        this.type = type;
    }

    public void setValue(String value){
        this.value = value;
    }

    public void setType(TokenType type){
        this.type = type;
    }

    public String getValue(){
        return this.value;
    }

    public TokenType getType(){
        return this.type;
    }

    public Token getToken(){
        return this;
    }
}
