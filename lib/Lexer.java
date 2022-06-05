package lib;

public class Lexer {
    private String unit;
    private char currentChar;
    private int currentIndex;
    private boolean prevBlockIDParsed = false;
    private boolean prevDTParsed = false;
    private boolean inBlock = false;

    public Lexer(String unit){
        this.unit = unit;
        this.currentIndex = 0;
        this.currentChar = unit.charAt(0);

        //raise an error if first char is not a letter
    }

    public void moveCurrentChar(){
        if(currentIndex<unit.length()-1)
            setCurrentChar(unit.charAt(++currentIndex));
        else{
            currentIndex++;
            currentChar = '\0';
        }
    }

    public Token getNextToken(){
        while(currentIndex<unit.length()){
            if(checkWhitespace(currentChar))
                skipWhitespace();
                
            if((!isPrevDTParsed() || !isPrevBlockIDParsed()) 
                && Character.isLetter(currentChar)
                && !isInBlock()){
                setInBlock(true);
                return parseID();
            }
            if(isPrevBlockIDParsed() && Character.isLetter(currentChar)){
                setprevBlockIDParsed(false);
                setPrevDTParsed(true);
                return parseDataType();
            }
            if(isPrevDTParsed() && Character.isLetter(currentChar)){
                setPrevDTParsed(false);
                return parseName();
            }

            if(isInBlock() && Character.isLetter(currentChar))
                return parseToken("Return", TokenType.TOKEN_RETURN);
            if(isInBlock() && Character.isDigit(currentChar))
                return parseNumber();
            if(getCurrentChar()=='(')
                return parseToken("(", TokenType.TOKEN_LPAREN);
            if(getCurrentChar()==')')
                return parseToken(")", TokenType.TOKEN_RPAREN);
            if(getCurrentChar()==':')
                return parseToken(":", TokenType.TOKEN_COLON);
            if(getCurrentChar()=='{')
                return parseToken("{", TokenType.TOKEN_LBRACE);
            if(getCurrentChar()=='}')
                return parseToken("}", TokenType.TOKEN_RBRACE);
            if(getCurrentChar()=='[')
                return parseToken("[", TokenType.TOKEN_LBRACK);
            if(getCurrentChar()==']')
                return parseToken("]", TokenType.TOKEN_RBRACK);
            if(getCurrentChar()==';')
                return parseToken(";", TokenType.TOKEN_SEMI_COLON);
            if(getCurrentChar()==',')
                return parseToken(",", TokenType.TOKEN_COMMA);
            if(getCurrentChar()=='=')
                return parseToken("=", TokenType.TOKEN_EQUALS);
            
            //raise an error unexpected token
            System.out.println("Unexpected token at index: "+currentIndex+", char: "+currentChar);
            System.exit(1);
        }

        return null; //null token
    }

    public void skipWhitespace(){
        while(checkWhitespace(currentChar))
            moveCurrentChar();
    }

    public Token parseToken(String value, TokenType type){
        if(type==TokenType.TOKEN_RETURN)
            for(int i=0; i<value.length(); i++)
                moveCurrentChar();
        else
            moveCurrentChar();
        return new Token(value, type);
    }

    public Token parseNumber(){ //make it with float later
        Token token = new Token();
        String value = "";

        while(Character.isDigit(currentChar)){
            value+= currentChar;
            moveCurrentChar();
        }

        try{ //for integer
            Integer.parseInt(value);
        }catch(Exception e){
            //raise an error
            System.out.println("Integer is not okay");
            System.exit(1);
        }

        token.setValue(value);
        token.setType(TokenType.TOKEN_INTEGER);

        return token;
    }

    public Token parseID(){
        Token token = new Token();
        String value = "";

        while(Character.isLetter(getCurrentChar())){
            value+=getCurrentChar();
            moveCurrentChar();
        }

        token.setValue(value);
        token.setType(TokenType.TOKEN_BLOCK_ID);
        setprevBlockIDParsed(true);

        return token;
    }

    public Token parseDataType(){
        Token token = new Token();
        String value = "";

        while(Character.isLetter(getCurrentChar())){
            value+=getCurrentChar();
            moveCurrentChar();
        }

        if(!isValidDataType(value)){
            //raise and exception invalid data type
            System.out.println("Invalid data type");
            System.exit(1);
        }

        token.setValue(value);
        token.setType(TokenType.TOKEN_DATA_TYPE);

        return token;
    }

    public Token parseName(){
        Token token = new Token();
        String value = "";

        if(Character.isDigit(currentChar)){
            //raise and error invalid name
            System.out.println("Invalid name");
            System.exit(1);
        }

        while(Character.isLetterOrDigit(currentChar)){
            value += currentChar;
            moveCurrentChar();
        }

        token.setValue(value);
        token.setType(TokenType.TOKEN_NAME);

        return token;
    }

    private boolean isValidDataType(String value){
        for(DataType dt : DataType.values())
            if(dt.name().equals(value))
                return true;
        return false;
    }

    private boolean checkWhitespace(char c){
        if(currentChar=='\n' ||
        currentChar=='\t' ||
        currentChar=='\r' ||
        currentChar==' ')
            return true;
        return false;
    }

    public void setCurrentChar(char currentChar){
        this.currentChar = currentChar;
    }

    public char getCurrentChar(){
        return this.currentChar;
    }

    public void setCurrentIndex(int currentIndex){
        this.currentIndex = currentIndex;
    }

    public int getCurrentIndex(){
        return this.currentIndex;
    }

    public void setprevBlockIDParsed(boolean stat){
        this.prevBlockIDParsed = stat;
    }

    public boolean isPrevBlockIDParsed(){
        return this.prevBlockIDParsed;
    }

    public void setPrevDTParsed(boolean stat){
        this.prevDTParsed = stat;
    }

    public boolean isPrevDTParsed(){
        return this.prevDTParsed;
    }

    public void setInBlock(boolean stat){
        this.inBlock = stat;
    }

    public boolean isInBlock(){
        return this.inBlock;
    }

    public Lexer getLexer(){
        return this;
    }
}
