///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package argonlexer;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 
// * @author dyhar
// */
////enum State {
////    COMMENT,
////    MULTILINECOMMENT,
////    START,
////    REJECT,
////    END,
////    N1,
////    N2,
////    N3,
////    NACCEPT1,
////    NACCEPT2,
////    NACCEPT3,
////    NACCEPT4,
////    NACCEPT5,
////    CFGHJLO,
////    EFGHJLO,
////    INGHJLO,
////    KNGHJLO,
////    MNGHJLO,
////    P,
////    R,
////    I,
////    N,
////    T,
////    POPENPAR,
////    PCLOSEPAR,
////    PCONTENT,
////    PQUOT,
////    PACCEPT,
////
////}
////
////enum Token {
////    ALKANE,
////    AND,
////    CARBON,
////    CATALYZE,
////    DECOMPOSE,
////    DISTILL,
////    FUNNEL,
////    FILTER,
////    FERMENT,
////    IS,
////    ISNOT,
////    INERT,
////    INPUT,
////    MOLE32,
////    MOLE64,
////    UMOLE32,
////    UMOLE64,
////    NOT,
////    OR,
////    PRINT,
////    PRINTF,
////    PRINTLN,
////    PRINTERR,
////    REACTIVE,
////    TITRATE,
////    ASSIGN,
////    ADD,
////    INC,
////    ADDASSIGN,
////    SUB,
////    DEC,
////    SUBASSIGN,
////    MUL,
////    MULASSIGN,
////    DIV,
////    DIVASSIGN,
////    EXP,
////    EXPASSIGN,
////    GT,
////    GTE,
////    LT,
////    LTE,
////    COMMA,
////    SEMICOLON,
////    OPENPAR,
////    CLOSEPAR,
////    OPENBR,
////    CLOSEBR,
////    COMMENT,
////    OPENMULTILINECOMMENT,
////    CLOSEMULTILINECOMMENT,
////    NUMLIT,
////    IDENT
////
////}
//
//public class Lexer {
//
//    private List<Token> tokens = new ArrayList<>();
//
//    public void listTokens() {
//        for (Token t : tokens) {
//            System.out.println(t);
//        }
//    }
//
//    public void Analyze(File in) throws IOException, IllegalStateException {
//        BufferedReader br = new BufferedReader(new FileReader(in));
//        String str;
//        //declare a line counter
//        int lines = 0;
//        State s = State.END;
//        //reads from file until the next line is null.
//        while ((str = br.readLine()) != null) {
//
//            //increments line counter by 1
//            lines++;
//            if (s == State.COMMENT) {
//                s = State.END;
//            }
//            if (s == State.REJECT) {
//                throw new IllegalStateException("An illegal token was found.");
//            }
//
//            String[] strArr = str.split(" ");
//            for (int subStr = 0; subStr < strArr.length && s != State.REJECT && s != State.COMMENT; subStr++) {
//                if (s == State.MULTILINECOMMENT) {
//                    switch (strArr[subStr]) {
//                        case "*/":
//                            tokens.add(Token.CLOSEMULTILINECOMMENT);
//                            s = State.END;
//                            continue;
//                    }
//                } else if (s != State.MULTILINECOMMENT) {
//                    switch (strArr[subStr]) {
//                        case "alkane":
//                            tokens.add(Token.ALKANE);
//                            continue;
//                        case "and":
//                            tokens.add(Token.AND);
//                            continue;
//                        case "carbon":
//                            tokens.add(Token.CARBON);
//                            continue;
//                        case "catalyze":
//                            tokens.add(Token.CATALYZE);
//                            continue;
//                        case "decompose":
//                            tokens.add(Token.DECOMPOSE);
//                            continue;
//                        case "distill":
//                            tokens.add(Token.DISTILL);
//                            continue;
//                        case "funnel":
//                            tokens.add(Token.FUNNEL);
//                            continue;
//                        case "filter":
//                            tokens.add(Token.FILTER);
//                            continue;
//                        case "ferment":
//                            tokens.add(Token.FERMENT);
//                            continue;
//                        case "is":
//                            if (subStr + 1 < strArr.length) {
//                                if (strArr[subStr].equals("is") && strArr[subStr + 1].equals("not")) {
//                                    tokens.add(Token.ISNOT);
//                                    subStr++;
//                                } else if (strArr[subStr].equals("is") && !strArr[subStr + 1].equals("not")) {
//                                    tokens.add(Token.IS);
//                                }
//                            } else {
//                                tokens.add(Token.IS);
//                            }
//                            continue;
//                        case "inert":
//                            tokens.add(Token.INERT);
//                            continue;
//                        case "input":
//                            tokens.add(Token.INPUT);
//                            continue;
//                        case "mole32":
//                            tokens.add(Token.MOLE32);
//                            continue;
//                        case "mole64":
//                            tokens.add(Token.MOLE64);
//                            continue;
//                        case "umole32":
//                            tokens.add(Token.UMOLE32);
//                            continue;
//                        case "umole64":
//                            tokens.add(Token.UMOLE64);
//                            continue;
//                        case "not":
//                            tokens.add(Token.NOT);
//                            continue;
//                        case "or":
//                            tokens.add(Token.OR);
//                            continue;
//
//                        case "reactive":
//                            tokens.add(Token.REACTIVE);
//                            continue;
//                        case "titrate":
//                            tokens.add(Token.TITRATE);
//                            continue;
//                        case "=":
//                            tokens.add(Token.ASSIGN);
//                            continue;
//                        case "+":
//                            tokens.add(Token.ADD);
//                            continue;
//                        case "++":
//                            tokens.add(Token.INC);
//                            continue;
//                        case "+=":
//                            tokens.add(Token.ADDASSIGN);
//                            continue;
//                        case "-":
//                            tokens.add(Token.SUB);
//                            continue;
//                        case "--":
//                            tokens.add(Token.DEC);
//                            continue;
//                        case "-=":
//                            tokens.add(Token.SUBASSIGN);
//                            continue;
//                        case "*":
//                            tokens.add(Token.MUL);
//                            continue;
//                        case "*=":
//                            tokens.add(Token.MULASSIGN);
//                            continue;
//                        case "/":
//                            tokens.add(Token.DIV);
//                            continue;
//                        case "/=":
//                            tokens.add(Token.DIVASSIGN);
//                            continue;
//                        case "^":
//                            tokens.add(Token.EXP);
//                            continue;
//                        case "^=":
//                            tokens.add(Token.EXPASSIGN);
//                            continue;
//                        case ">":
//                            tokens.add(Token.GT);
//                            continue;
//                        case ">=":
//                            tokens.add(Token.GTE);
//                            continue;
//                        case "<":
//                            tokens.add(Token.LT);
//                            continue;
//                        case "<=":
//                            tokens.add(Token.LTE);
//                            continue;
//                        case ",":
//                            tokens.add(Token.COMMA);
//                            continue;
//                        case ";":
//                            tokens.add(Token.SEMICOLON);
//                            continue;
//                        case "(":
//                            tokens.add(Token.OPENPAR);
//                            continue;
//                        case ")":
//                            tokens.add(Token.CLOSEPAR);
//                            continue;
//                        case "{":
//                            tokens.add(Token.OPENBR);
//                            continue;
//                        case "}":
//                            tokens.add(Token.CLOSEBR);
//                            continue;
//                        case "//":
//                            tokens.add(Token.COMMENT);
//                            s = State.COMMENT;
//                            continue;
//                        case "/*":
//                            tokens.add(Token.OPENMULTILINECOMMENT);
//                            s = State.MULTILINECOMMENT;
//                            continue;
//                        default:
//
//                            String tempStr = strArr[subStr];
//                            s = State.START;
//                            for (int c = 0; c < tempStr.length() && s != State.REJECT; c++) {
//                                switch (s) {
//                                    case START: //start state of literals
//                                        //check if it is a number
//                                        if (tempStr.charAt(c) == '0') {
//                                            s = State.NACCEPT2;
//                                        } else if (tempStr.charAt(c) >= '1' && tempStr.charAt(c) <= '9') {
//                                            s = State.NACCEPT1;
//                                        } else if (str.charAt(c) == 'p') {
//                                            s = State.P;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.CFGHJLO;
//                                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.EFGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case CFGHJLO:
//                                        if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.INGHJLO;
//                                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
//                                            s = State.KNGHJLO;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.MNGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case EFGHJLO:
//                                        if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.INGHJLO;
//                                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
//                                            s = State.KNGHJLO;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.MNGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case INGHJLO:
//                                        if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.INGHJLO;
//                                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
//                                            s = State.KNGHJLO;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.MNGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case KNGHJLO:
//                                        if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.INGHJLO;
//                                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
//                                            s = State.KNGHJLO;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.MNGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case MNGHJLO:
//                                        if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.INGHJLO;
//                                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
//                                            s = State.KNGHJLO;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.MNGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case NACCEPT1://accepting state for decimal literals
//                                        if (tempStr.charAt(c) >= '0' && tempStr.charAt(c) <= '9') {
//                                            s = State.NACCEPT1;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case NACCEPT2: //accepting state for just 0
//                                        switch (tempStr.charAt(c)) {
//                                            //check for subsequent chars
//                                            case '0':
//                                                s = State.NACCEPT1;
//                                                break;
//                                            case 'b':
//                                                s = State.N1;
//                                                break;
//                                            case 'c':
//                                                s = State.N2;
//                                                break;
//                                            case 'x':
//                                                s = State.N3;
//                                                break;
//                                            default:
//                                                s = State.REJECT;
//                                                break;
//                                        }
//                                        break;
//                                    case N1:
//                                        if (tempStr.charAt(c) == '0' || tempStr.charAt(c) == '1') {
//                                            s = State.NACCEPT3;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case NACCEPT3://accepting state for bin literals
//                                        if (tempStr.charAt(c) == '0' || tempStr.charAt(c) == '1') {
//                                            s = State.NACCEPT3;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case N2:
//                                        if (tempStr.charAt(c) >= '0' && tempStr.charAt(c) <= '7') {
//                                            s = State.NACCEPT4;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case NACCEPT4://accepting state for oct literals
//                                        if (tempStr.charAt(c) >= '0' && tempStr.charAt(c) <= '7') {
//                                            s = State.NACCEPT4;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case N3:
//                                        if ((tempStr.charAt(c) >= '0' && tempStr.charAt(c) <= '9') || (tempStr.charAt(c) >= 'a' && tempStr.charAt(c) <= 'f') || (tempStr.charAt(c) >= 'A' && tempStr.charAt(c) <= 'F')) {
//                                            s = State.NACCEPT5;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case NACCEPT5://accepting state for hex literals
//                                        if ((tempStr.charAt(c) >= '0' && tempStr.charAt(c) <= '9') || (tempStr.charAt(c) >= 'a' && tempStr.charAt(c) <= 'f') || (tempStr.charAt(c) >= 'A' && tempStr.charAt(c) <= 'F')) {
//                                            s = State.NACCEPT5;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case P:
//                                        if (str.charAt(c) == 'r') {
//                                            s = State.R;
//                                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.INGHJLO;
//                                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
//                                            s = State.KNGHJLO;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.MNGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case R:
//                                        if (str.charAt(c) == 'i') {
//                                            s = State.I;
//                                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.INGHJLO;
//                                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
//                                            s = State.KNGHJLO;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.MNGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case I:
//                                        if (str.charAt(c) == 'n') {
//                                            s = State.N;
//                                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.INGHJLO;
//                                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
//                                            s = State.KNGHJLO;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.MNGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case N:
//                                        if (str.charAt(c) == 't') {
//                                            s = State.T;
//                                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.INGHJLO;
//                                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
//                                            s = State.KNGHJLO;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.MNGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case T:
//                                        if (str.charAt(c) == '(') {
//                                            s = State.POPENPAR;
//                                            tokens.add(Token.PRINT);
//                                            tokens.add(Token.OPENPAR);
//                                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
//                                            s = State.INGHJLO;
//                                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
//                                            s = State.KNGHJLO;
//                                        } else if (str.charAt(c) == '_') {
//                                            s = State.MNGHJLO;
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                    case POPENPAR:
//                                        if (str.charAt(c) == ')') {
//                                            s = State.PCLOSEPAR;
//                                            tokens.add(Token.CLOSEPAR);
//                                        } else {
//                                            s = State.PCONTENT;
//                                        }
//                                        break;
//                                    case PCONTENT:
//                                        if (str.charAt(c) == ')') {
//                                            s = State.PCLOSEPAR;
//                                            tokens.add(Token.CLOSEPAR);
//                                        } else {
//                                            s = State.PCONTENT;
//                                        }
//                                        break;
//                                    case PCLOSEPAR:
//                                        if (str.charAt(c) == ';') {
//                                            s = State.PACCEPT;
//                                            tokens.add(Token.SEMICOLON);
//                                        } else {
//                                            s = State.REJECT;
//                                        }
//                                        break;
//                                }
//                            }
//                            switch (s) {
//                                case NACCEPT1:
//                                case NACCEPT2:
//                                case NACCEPT3:
//                                case NACCEPT4:
//                                case NACCEPT5:
//                                    tokens.add(Token.NUMLIT);
//                                    s = State.END;
//                                    break;
//                                case CFGHJLO:
//                                case EFGHJLO:
//                                case INGHJLO:
//                                case KNGHJLO:
//                                case MNGHJLO:
//                                    tokens.add(Token.IDENT);
//                                    s = State.END;
//                                    break;
//                                case PACCEPT:
//                                    s = State.END;
//                                    break;
//                                default:
//                                    break;
//                            }
//                            continue;
//
//                    }
//                }
//            }
//
//        }
//        //closes the BufferedReader
//        br.close();
//    }
//}
