lexer grammar BasicLexer;

//comment
COMMENT: '#' .*? '\n' -> skip;

//program
BEGIN: 'begin' ;
END: 'end' ;

//func
OPEN_BRACKETS: '(' ;
CLOSE_BRACKETS: ')' ;
IS: 'is' ;

//param-list
COMMA: ',' ;

//stat
SKIP_: 'skip';
ASSIGNMENT: '=' ;
READ: 'read' ;
FREE: 'free' ;
RETURN: 'return' ;
EXIT: 'exit' ;
PRINT: 'print' ;
PRINTLN: 'println' ;
IF: 'if' ;
THEN: 'then' ;
ELSE: 'else' ;
FI: 'fi' ;
WHILE: 'while' ;
DO: 'do' ;
DONE: 'done' ;
FOR: 'for' ;
BREAK: 'break' ;
CONTINUE: 'continue' ;
UNTIL: 'until' ;
SEMI_COLON: ';' ;

//assign-lhs

//assign-rhs
NEW_PAIR: 'newpair' ;
CALL: 'call' ;

//arg-list

//pair-elem
FST: 'fst' ;
SND: 'snd' ;

//type

//base-type
VOID: 'void' ;
INT: 'int' ;
BOOL: 'bool' ;
CHAR: 'char' ;
STRING: 'string' ;


//array-type
OPEN_SQUARE_BRACKETS: '[' ;
CLOSE_SQUARE_BRACKETS: ']' ;

//pair-type
PAIR: 'pair' ;

//array-liter

CLASS : 'class';
OPEN_CURLY_BRACKET : '{';
CLOSE_CURLY_BRACKET : '}';
PUBLIC : 'public' ;
SELF : 'self' ;
DOT : '.';
NEW : 'new';

//pair-elem-type

//expr

//unary-oper
NOT: '!' ;
MINUS: '-' ;
LENGTH: 'len' ;
ORDINAL: 'ord' ;
CHR: 'chr' ;
COMPLEMENT: '~';

//binary-oper
MULTIPLY: '*' ;
DIVIDE: '/' ;
MODULO: '%' ;
ADD: '+' ;
GREATER_THAN: '>' ;
GREATER_OR_EQUAL: '>=' ;
LESS_THAN: '<' ;
LESS_OR_EQUAL: '<=' ;
EQUALS: '==' ;
NOT_EQUALS: '!=' ;
AND: '&&' ;
OR: '||' ;
BITWISEOR: '|';
BITWISEAND: '&' ;
BITWISEXOR: '^';
SHIFTLEFT: '<<';
SHIFTRIGHT: '>>';


HELLO: [ \n\t\r] -> skip ;

//digit
fragment DIGIT: '0'..'9' ;

//int-sign

//pair-liter
NULL: 'null' ;

//bool-liter
TRUE: 'true' ;
FALSE: 'false' ;

//ident
fragment IDENT_START: '_' | 'a'..'z' | 'A'..'Z' ;
IDENT: IDENT_START (IDENT_START | DIGIT)* ;

//array-elem

//int-liter
INTEGER: DIGIT+ ;

//char-liter
CHAR_LITER: '\'' CHARACTER_SET '\'' ;

//string-liter
STR_LITER: '"' CHARACTER_SET* '"' ;

//escaped-char
fragment ESC_CHAR: [0btnfr"] | '\'' | '\\' ;

//character
fragment CHARACTER_SET: [ -!#-~] | '\\' ESC_CHAR ;
















																																																																																					





