parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

program
    : BEGIN newclass* func* stat END EOF
    ;

func
    : type IDENT OPEN_BRACKETS paramList? CLOSE_BRACKETS IS stat END
    ;

paramList
    : param (COMMA param)*
    ;

param
    : type IDENT
    ;

stat
    : SKIP_
	| type IDENT ASSIGNMENT assignRhs
	| assignLhs ASSIGNMENT assignRhs
	| READ assignLhs
	| FREE expr
	| EXIT expr
	| RETURN expr
	| PRINT expr
	| PRINTLN expr
    | IF expr THEN stat ELSE stat FI
    | WHILE expr DO stat DONE
    | FOR OPEN_BRACKETS stat COMMA expr
      COMMA stat CLOSE_BRACKETS stat DONE
    | BREAK
    | CONTINUE
    | DO stat UNTIL expr
    | BEGIN stat END
    | CALL IDENT OPEN_BRACKETS argList? CLOSE_BRACKETS
    | (IDENT | SELF) (classOp)+
    | stat SEMI_COLON stat
    ;

assignLhs
    : (IDENT | SELF) (classOp)*
	| arrayElem
	| pairElem
	;

assignRhs
    : expr
	| arrayLiter
	| NEW_PAIR OPEN_BRACKETS expr COMMA expr CLOSE_BRACKETS
	| pairElem
	| CALL IDENT OPEN_BRACKETS argList? CLOSE_BRACKETS
	| (IDENT | SELF) (classOp)+
	| NEW IDENT OPEN_BRACKETS argList? CLOSE_BRACKETS
	;

classOp
    : DOT IDENT OPEN_BRACKETS argList? CLOSE_BRACKETS
    | DOT IDENT
    ;

argList
    : expr (COMMA expr)* 
    ;

pairElem
    : FST expr
	| SND expr 
	;

type
    : baseType
	| arrayType
	| pairType
	| IDENT //class
	;

baseType
    : INT
	| BOOL
	| CHAR
	| STRING
	| VOID
	;

arrayType
    : baseArrayType OPEN_SQUARE_BRACKETS CLOSE_SQUARE_BRACKETS
    | arrayType OPEN_SQUARE_BRACKETS CLOSE_SQUARE_BRACKETS
    ;

baseArrayType
    : baseType
    | pairType 
    ;

pairType
    : PAIR OPEN_BRACKETS pairElemType COMMA pairElemType CLOSE_BRACKETS
    ;

pairElemType
    : baseType
	| arrayType
	| PAIR 
	;

expr
    : strLiter
    | boolLiter
    | charLiter
    | intLiter
    | pairLiter
    | expr (MULTIPLY|DIVIDE|MODULO) expr
    | expr (ADD|MINUS) expr
    | expr (BITWISEOR|BITWISEAND|BITWISEXOR|SHIFTLEFT|SHIFTRIGHT) expr
    | expr (GREATER_THAN|GREATER_OR_EQUAL|LESS_THAN|LESS_OR_EQUAL) expr
    | expr (NOT_EQUALS | EQUALS) expr
    | expr AND expr
    | expr OR expr
    | unaryOper expr
    | IDENT
    | arrayElem
    | OPEN_BRACKETS expr CLOSE_BRACKETS
    ;

unaryOper
    : NOT
	| MINUS
	| LENGTH
	| ORDINAL
	| CHR
	| COMPLEMENT
	;

arrayElem
    : IDENT (OPEN_SQUARE_BRACKETS expr CLOSE_SQUARE_BRACKETS)+
    ;

intLiter
    : (ADD | MINUS)? INTEGER
    ;

boolLiter
    : TRUE
	| FALSE
	;

charLiter
    : CHAR_LITER
    ;

strLiter
    : STR_LITER
    ;

arrayLiter
    : OPEN_SQUARE_BRACKETS (expr (COMMA expr)*)? CLOSE_SQUARE_BRACKETS
    ;

pairLiter
    : NULL
    ;

attribute : (PUBLIC)? type IDENT COMMA;

constructor : IDENT OPEN_BRACKETS paramList? CLOSE_BRACKETS OPEN_CURLY_BRACKET stat CLOSE_CURLY_BRACKET ;

newclass : CLASS IDENT OPEN_CURLY_BRACKET attribute* constructor* method* CLOSE_CURLY_BRACKET ;

method : PUBLIC? func ;
