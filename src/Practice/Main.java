package Practice;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static final Map<Symbol, Object> GLOBAL_REFERENCING_ENVIRONMENT = new HashMap<>(Map.ofEntries(
            Map.entry(SchemeCore.symbol("quote"), Procedure.QUOTE),
            Map.entry(SchemeCore.symbol("define"), Procedure.DEFINE),
            Map.entry(SchemeCore.symbol("car"), Procedure.CAR),
            Map.entry(SchemeCore.symbol("cdr"), Procedure.CDR),
            Map.entry(SchemeCore.symbol("length"), Procedure.LENGTH),
            Map.entry(SchemeCore.symbol("cons"), Procedure.CONS),
            Map.entry(SchemeCore.symbol("+"), Procedure.ADD),
            Map.entry(SchemeCore.symbol("-"), Procedure.SUBTRACT),
            Map.entry(SchemeCore.symbol("*"), Procedure.MULTIPLY),
            Map.entry(SchemeCore.symbol("quotient"), Procedure.QUOTIENT),
            Map.entry(SchemeCore.symbol("remainder"), Procedure.REMAINDER),
            Map.entry(SchemeCore.symbol("list"), Procedure.LIST)
    ));

    public static void main(String[] args) {
        String input;

        input = "(+ 1 2)";
        input = "(+ * 1 4 2)";
        input = "(cons 1 2)";
        input = "'(cons 1 2)";
        input = "quote(cons 1 2)";
        input = "(+ (remainder (* 2 2) (quotient 4 2)) (- -1 1))";
        input = "list"; // TODO: FIX
        input = "list 1 (list 1 2 (+ 1 3)) (+ 1 3)";
        input = "length list 1 (list 1 2 (+ 1 3)) (+ 1 3)";
        input = "(define poem 3)";
        input = "(define poem (+ 1 2))";
        input = "(define poem length list 1 (list 1 2 (+ 1 3)) (+ 1 3))";
        input = "'()"; // Failed
        input = "(length '(0 1))"; // Failed
//        input = "(cons 0 '())";
//        input = "(car '(1 2))";
//        input = "(cdr '(1 2))";
//        input = "(define x 1) (+ x x)";
//        input = "(define plus +) (plus 1 1)";
        input = "length ()";


        System.out.println("Input: " + input);
        var tokenize = Tokenizer.tokenize(input);
        System.out.println("Tokens: " + tokenize);
        Object parsed = Parser.parse(tokenize);
        System.out.println("AST: " + parsed);
        Object eval = SchemeCore.eval(parsed, GLOBAL_REFERENCING_ENVIRONMENT);
        System.out.println("Result: " + eval);
        eval = SchemeCore.eval(new Symbol("poem"), GLOBAL_REFERENCING_ENVIRONMENT);
        System.out.println("poem_Result: " + eval);


//        Scanner scanner = new Scanner(System.in);
//        System.out.print("> ");
//        input = scanner.nextLine();
//        while (!input.equals("exit")) {
//            Object ast = Parser.parse(Tokenizer.tokenize(input));
//
//            System.out.println("Input: " + input);
//            var tokenize = Tokenizer.tokenize(input);
//            System.out.println("Tokens: " + tokenize);
//            Object parsed = Parser.parse(tokenize);
//            System.out.println("AST: " + parsed);
//            System.out.println();
//
//            Object result = SchemeCore.eval(ast, GLOBAL_REFERENCING_ENVIRONMENT);
//            System.out.println(result);
//            System.out.print("> ");
//            input = scanner.nextLine();
//        }

    }
}
