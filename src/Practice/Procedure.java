package Practice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.BiFunction;

public interface Procedure {
    Procedure QUOTE = (arguments, referencingEnvironment) -> {
        arguments = (Pair) arguments.ar;

        // TODO: fix
//        assert_arg_length(arguments, 1);

        return arguments;
    };
    Procedure DEFINE = (arguments, referencingEnvironment) -> {
        Object car = SchemeCore.car(arguments);
        if (!(car instanceof Symbol))
            throw new IllegalArgumentException("Cannot bind expr to a non symbol type of :" + car);

        referencingEnvironment.put(
                (Symbol) car,
                SchemeCore.eval_bypass(
                        SchemeCore.getAR_Dr(arguments),
                        referencingEnvironment,
                        true
                )
        );

        return null;
    };
    Procedure CAR = (arguments, referencingEnvironment) -> {
        assert_arg_length(arguments, 1);
        return arguments.ar;
    };
    Procedure CDR = (arguments, referencingEnvironment) -> {
        assert_arg_length(arguments, 1);
        return SchemeCore.getAR_Dr(arguments);
    };
    Procedure LENGTH = (arguments, referencingEnvironment) ->
            BigInteger.valueOf(SchemeCore.length(SchemeCore.eval(SchemeCore.car(arguments), referencingEnvironment)));

    Procedure CONS = (arguments, referencingEnvironment) -> {
        assert_arg_length(arguments, 2);
        return SchemeCore.cons(
                SchemeCore.eval(arguments.ar, referencingEnvironment),
                SchemeCore.eval(SchemeCore.getAR_Dr(arguments), referencingEnvironment)
        );
    };
    Procedure ADD = (arguments, referencingEnvironment) ->
            evalBiIntArgs(arguments, referencingEnvironment, BigInteger::add);
    Procedure SUBTRACT = (arguments, referencingEnvironment) ->
            evalBiIntArgs(arguments, referencingEnvironment, BigInteger::subtract);
    Procedure MULTIPLY = (arguments, referencingEnvironment) ->
            evalBiIntArgs(arguments, referencingEnvironment, BigInteger::multiply);
    Procedure QUOTIENT = (arguments, referencingEnvironment) ->
            evalBiIntArgs(arguments, referencingEnvironment, BigInteger::divide);
    Procedure REMAINDER = (arguments, referencingEnvironment) ->
            evalBiIntArgs(arguments, referencingEnvironment, BigInteger::remainder);
    Procedure LIST = (arguments, referencingEnvironment) -> {
        ArrayList<Object> objects = new ArrayList<>();
        Pair p = arguments;
        while (p != SchemeCore.nil) {
            objects.add(SchemeCore.eval(p.ar, referencingEnvironment));
            p = (Pair) p.dr;
        }
        return SchemeCore.list(objects.toArray());
    };

//    private static String pretty_string(Pair arguments) {
//        ArrayList<String> res = new ArrayList<>();
//        Pair p = arguments;
//        while (p != SchemeCore.nil) {
//            String s;
//            if (p.ar instanceof Pair curr) {
//                s = pretty_string(curr);
//            } else if (p.ar instanceof Symbol sym) {
//                s = sym.getName();
//            } else s = p.ar.toString();
//            res.add(s);
//            p = (Pair) p.dr;
//        }
////        if (res.size() != 1) {
//        return "(" + String.join(" ", res) + ")";
////        }else return String.join(" ", res);
//    }


    private static BigInteger evalBiIntArgs(Pair arguments,
                                            Map<Symbol, Object> referencingEnvironment,
                                            BiFunction<BigInteger, BigInteger, BigInteger> op) {
        assert_arg_length(arguments, 2);

        assert_int_args(arguments.ar, '1');
        assert_int_args(SchemeCore.getAR_Dr(arguments), '2');

        if (SchemeCore.eval(arguments.ar, referencingEnvironment) instanceof BigInteger n1) {
            if (SchemeCore.eval(SchemeCore.getAR_Dr(arguments), referencingEnvironment) instanceof BigInteger n2) {
                return op.apply(n1, n2);
            }
        }

        throw new IllegalArgumentException("Arg passed in did not evaluate to an Integer\nArg1: " + arguments.ar + "\nArg2: " + SchemeCore.getAR_Dr(arguments));
    }

    private static void assert_arg_length(Pair arguments, int len) {
        if (SchemeCore.length(arguments) != len)
            throw new IllegalArgumentException("Arguments passed is meant to be of length " + len);
    }

    private static void assert_int_args(Object args, char arg_num) {
        args = SchemeCore.eval(args, Main.GLOBAL_REFERENCING_ENVIRONMENT);
        if (((args instanceof Symbol) || (args instanceof Boolean))) {
            throw new IllegalArgumentException("Args" + arg_num + " passed in is not an integer: " + args);
        }
    }

    Object apply(Pair arguments, Map<Symbol, Object> referencingEnvironment);
}

