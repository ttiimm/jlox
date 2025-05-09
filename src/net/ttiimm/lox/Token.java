package net.ttiimm.lox;

public class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    @Override
    public String toString() {
        var s = STR."\{type} \{lexeme}";
        if (literal != null) {
            s += " " + literal;
        }
        return s;
    }
}
