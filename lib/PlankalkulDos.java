package lib;

public class PlankalkulDos {
    private String fileContent;
    
    public PlankalkulDos(String fileContent){
        this.fileContent = fileContent;
    }

    public void compile(){
        Lexer lexer = new Lexer(fileContent);
        Parser parser = new Parser(lexer);
        AST astRoot = parser.parse();

        Token token = null;

        do{
            token = lexer.getNextToken();
            if(token != null)
                System.out.println("val: "+token.getValue()+", type: "+token.getType().name());
        }while(token != null);
    }
}
