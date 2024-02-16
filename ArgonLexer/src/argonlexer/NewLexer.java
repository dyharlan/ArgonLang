/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package argonlexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
enum State {
    START,
    REJECT,
    N1,
    N2,
    N3,
    NACCEPT1,
    NACCEPT2,
    NACCEPT3,
    NACCEPT4,
    NACCEPT5,
    CFGHJLO,
    EFGHJLO,
    INGHJLO,
    KNGHJLO,
    MNGHJLO,
    P,
    R,
    I,
    N,
    T,
    PCONTENT,
    PQUOT,
    STARTCOMMENT,
    ENDCOMMENT,
    ASTERISK,
    MULTILINECONTENT,
    ENDMULTILINECOMMENT,

}

enum Token {
    ALKANE,
    AND,
    CARBON,
    CATALYZE,
    DECOMPOSE,
    DISTILL,
    FUNNEL,
    FILTER,
    FERMENT,
    IS,
    ISNOT,
    INERT,
    INPUT,
    MOLE32,
    MOLE64,
    UMOLE32,
    UMOLE64,
    NOT,
    OR,
    PRINT,
    PRINTF,
    PRINTLN,
    PRINTERR,
    REACTIVE,
    TITRATE,
    ASSIGN,
    ADD,
    INC,
    ADDASSIGN,
    SUB,
    DEC,
    SUBASSIGN,
    MUL,
    MULASSIGN,
    DIV,
    DIVASSIGN,
    EXP,
    EXPASSIGN,
    GT,
    GTE,
    LT,
    LTE,
    COMMA,
    SEMICOLON,
    OPENPAR,
    CLOSEPAR,
    OPENBR,
    CLOSEBR,
    COMMENT,
    OPENMULTILINECOMMENT,
    CLOSEMULTILINECOMMENT,
    NUMLIT,
    IDENT

}

public class NewLexer {

    private List<Token> tokens = new ArrayList<>();

    public void listTokens() {
        for (Token t : tokens) {
            System.out.println(t);
        }
    }

    public void Analyze(File in) throws IOException, IllegalStateException {
        BufferedReader br = new BufferedReader(new FileReader(in));
        String str;
        //declare a line counter
        int lines = 0;
        State s = State.START;
        //reads from file until the next line is null.
        while ((str = br.readLine()) != null) {

            //increments line counter by 1
            lines++;
            if (s == State.REJECT) {
                throw new IllegalStateException("An illegal token was found.");
            }
            if (s == State.ENDCOMMENT) {
                s = State.START;
            }
            for (int c = 0; c < str.length() && s != State.REJECT && s != State.ENDCOMMENT; c++) {
//                if(s == State.END){
//                    s = State.START;
//                }
                switch (s) {
                    case START: //start state of literals
                        //check if it is a number
                        if (str.charAt(c) == '0') {
                            s = State.NACCEPT2;
                        } else if (str.charAt(c) >= '1' && str.charAt(c) <= '9') {
                            s = State.NACCEPT1;
                        } else if (str.charAt(c) == 'p') {
                            s = State.P;
                        } else if (str.charAt(c) == '_') {
                            s = State.CFGHJLO;
                        } else if (str.charAt(c) == ';' ) {
                            tokens.add(Token.SEMICOLON);
                            s = State.START;
                        }else if (str.charAt(c) == '/' ) {
                            s = State.STARTCOMMENT;
                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.EFGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case CFGHJLO:
                        if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.INGHJLO;
                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.KNGHJLO;
                        } else if (str.charAt(c) == '_') {
                            s = State.MNGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case EFGHJLO:
                        if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.INGHJLO;
                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.KNGHJLO;
                        } else if (str.charAt(c) == '_') {
                            s = State.MNGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case INGHJLO:
                        if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.INGHJLO;
                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.KNGHJLO;
                        } else if (str.charAt(c) == '_') {
                            s = State.MNGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case KNGHJLO:
                        if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.INGHJLO;
                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.KNGHJLO;
                        } else if (str.charAt(c) == '_') {
                            s = State.MNGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case MNGHJLO:
                        if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.INGHJLO;
                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.KNGHJLO;
                        } else if (str.charAt(c) == '_') {
                            s = State.MNGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case NACCEPT1://accepting state for decimal literals
                        if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.NACCEPT1;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case NACCEPT2: //accepting state for just 0
                        switch (str.charAt(c)) {
                            //check for subsequent chars
                            case '0':
                                s = State.NACCEPT1;
                                break;
                            case 'b':
                                s = State.N1;
                                break;
                            case 'c':
                                s = State.N2;
                                break;
                            case 'x':
                                s = State.N3;
                                break;
                            default:
                                s = State.REJECT;
                                break;
                        }
                        break;
                    case N1:
                        if (str.charAt(c) == '0' || str.charAt(c) == '1') {
                            s = State.NACCEPT3;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case NACCEPT3://accepting state for bin literals
                        if (str.charAt(c) == '0' || str.charAt(c) == '1') {
                            s = State.NACCEPT3;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case N2:
                        if (str.charAt(c) >= '0' && str.charAt(c) <= '7') {
                            s = State.NACCEPT4;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case NACCEPT4://accepting state for oct literals
                        if (str.charAt(c) >= '0' && str.charAt(c) <= '7') {
                            s = State.NACCEPT4;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case N3:
                        if ((str.charAt(c) >= '0' && str.charAt(c) <= '9') || (str.charAt(c) >= 'a' && str.charAt(c) <= 'f') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'F')) {
                            s = State.NACCEPT5;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case NACCEPT5://accepting state for hex literals
                        if ((str.charAt(c) >= '0' && str.charAt(c) <= '9') || (str.charAt(c) >= 'a' && str.charAt(c) <= 'f') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'F')) {
                            s = State.NACCEPT5;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case P:
                        if (str.charAt(c) == 'r') {
                            s = State.R;
                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.INGHJLO;
                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.KNGHJLO;
                        } else if (str.charAt(c) == '_') {
                            s = State.MNGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case R:
                        if (str.charAt(c) == 'i') {
                            s = State.I;
                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.INGHJLO;
                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.KNGHJLO;
                        } else if (str.charAt(c) == '_') {
                            s = State.MNGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case I:
                        if (str.charAt(c) == 'n') {
                            s = State.N;
                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.INGHJLO;
                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.KNGHJLO;
                        } else if (str.charAt(c) == '_') {
                            s = State.MNGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case N:
                        if (str.charAt(c) == 't') {
                            s = State.T;
                        } else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.INGHJLO;
                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.KNGHJLO;
                        } else if (str.charAt(c) == '_') {
                            s = State.MNGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case T:
                        if(str.charAt(c) == '('){
                            s = State.PCONTENT;
                            tokens.add(Token.PRINT);
                            tokens.add(Token.OPENPAR);
                        }else if ((str.charAt(c) >= 'a' && str.charAt(c) <= 'z') || (str.charAt(c) >= 'A' && str.charAt(c) <= 'Z')) {
                            s = State.INGHJLO;
                        } else if (str.charAt(c) >= '0' && str.charAt(c) <= '9') {
                            s = State.KNGHJLO;
                        } else if (str.charAt(c) == '_') {
                            s = State.MNGHJLO;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case PCONTENT:
                        if(str.charAt(c) == ')'){
                            s = State.START;
                            tokens.add(Token.CLOSEPAR);
                        }else{
                            s = State.PCONTENT;
                        }
                        break;
                    case STARTCOMMENT:
                        if(str.charAt(c) == '/'){
                            s = State.ENDCOMMENT;
                        }else if(str.charAt(c) == '*'){
                            s = State.MULTILINECONTENT;
                        } else {
                            s = State.REJECT;
                        }
                        break;
                    case MULTILINECONTENT:
                        if(str.charAt(c) == '*'){
                            s = State.ASTERISK;
                        }else{
                            s = State.MULTILINECONTENT;
                        }
                        break;
                    case ASTERISK:
                        if(str.charAt(c) == '/'){
                            s = State.ENDMULTILINECOMMENT;
                        }else{
                            s = State.MULTILINECONTENT;
                        }
                        break;
                }
            }
            switch (s) {
                case NACCEPT1:
                case NACCEPT2:
                case NACCEPT3:
                case NACCEPT4:
                case NACCEPT5:
                    tokens.add(Token.NUMLIT);
                    s = State.START;
                    break;
                case CFGHJLO:
                case EFGHJLO:
                case INGHJLO:
                case KNGHJLO:
                case MNGHJLO:
                    tokens.add(Token.IDENT);
                    s = State.START;
                    break;  
                case ENDCOMMENT:
                    tokens.add(Token.COMMENT);
                    break;
                case MULTILINECONTENT:
                    tokens.add(Token.OPENMULTILINECOMMENT);
                    s = State.START;
                    break;
                case ENDMULTILINECOMMENT:
                    tokens.add(Token.CLOSEMULTILINECOMMENT);
                    s = State.START;
                    break;
            }
            System.out.println("State: "+s);
        }
    }
}
