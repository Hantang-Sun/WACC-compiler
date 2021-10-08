// Generated from ./BasicLexer.g4 by ANTLR 4.7
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, BEGIN=2, END=3, OPEN_BRACKETS=4, CLOSE_BRACKETS=5, IS=6, COMMA=7, 
		SKIP_=8, ASSIGNMENT=9, READ=10, FREE=11, RETURN=12, EXIT=13, PRINT=14, 
		PRINTLN=15, IF=16, THEN=17, ELSE=18, FI=19, WHILE=20, DO=21, DONE=22, 
		FOR=23, BREAK=24, CONTINUE=25, UNTIL=26, SEMI_COLON=27, NEW_PAIR=28, CALL=29, 
		FST=30, SND=31, VOID=32, INT=33, BOOL=34, CHAR=35, STRING=36, OPEN_SQUARE_BRACKETS=37, 
		CLOSE_SQUARE_BRACKETS=38, PAIR=39, CLASS=40, OPEN_CURLY_BRACKET=41, CLOSE_CURLY_BRACKET=42, 
		PUBLIC=43, SELF=44, DOT=45, NEW=46, NOT=47, MINUS=48, LENGTH=49, ORDINAL=50, 
		CHR=51, COMPLEMENT=52, MULTIPLY=53, DIVIDE=54, MODULO=55, ADD=56, GREATER_THAN=57, 
		GREATER_OR_EQUAL=58, LESS_THAN=59, LESS_OR_EQUAL=60, EQUALS=61, NOT_EQUALS=62, 
		AND=63, OR=64, BITWISEOR=65, BITWISEAND=66, BITWISEXOR=67, SHIFTLEFT=68, 
		SHIFTRIGHT=69, HELLO=70, NULL=71, TRUE=72, FALSE=73, IDENT=74, INTEGER=75, 
		CHAR_LITER=76, STR_LITER=77;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMENT", "BEGIN", "END", "OPEN_BRACKETS", "CLOSE_BRACKETS", "IS", "COMMA", 
		"SKIP_", "ASSIGNMENT", "READ", "FREE", "RETURN", "EXIT", "PRINT", "PRINTLN", 
		"IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", "FOR", "BREAK", "CONTINUE", 
		"UNTIL", "SEMI_COLON", "NEW_PAIR", "CALL", "FST", "SND", "VOID", "INT", 
		"BOOL", "CHAR", "STRING", "OPEN_SQUARE_BRACKETS", "CLOSE_SQUARE_BRACKETS", 
		"PAIR", "CLASS", "OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "PUBLIC", 
		"SELF", "DOT", "NEW", "NOT", "MINUS", "LENGTH", "ORDINAL", "CHR", "COMPLEMENT", 
		"MULTIPLY", "DIVIDE", "MODULO", "ADD", "GREATER_THAN", "GREATER_OR_EQUAL", 
		"LESS_THAN", "LESS_OR_EQUAL", "EQUALS", "NOT_EQUALS", "AND", "OR", "BITWISEOR", 
		"BITWISEAND", "BITWISEXOR", "SHIFTLEFT", "SHIFTRIGHT", "HELLO", "DIGIT", 
		"NULL", "TRUE", "FALSE", "IDENT_START", "IDENT", "INTEGER", "CHAR_LITER", 
		"STR_LITER", "ESC_CHAR", "CHARACTER_SET"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'begin'", "'end'", "'('", "')'", "'is'", "','", "'skip'", 
		"'='", "'read'", "'free'", "'return'", "'exit'", "'print'", "'println'", 
		"'if'", "'then'", "'else'", "'fi'", "'while'", "'do'", "'done'", "'for'", 
		"'break'", "'continue'", "'until'", "';'", "'newpair'", "'call'", "'fst'", 
		"'snd'", "'void'", "'int'", "'bool'", "'char'", "'string'", "'['", "']'", 
		"'pair'", "'class'", "'{'", "'}'", "'public'", "'self'", "'.'", "'new'", 
		"'!'", "'-'", "'len'", "'ord'", "'chr'", "'~'", "'*'", "'/'", "'%'", "'+'", 
		"'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", "'|'", "'&'", 
		"'^'", "'<<'", "'>>'", null, "'null'", "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMENT", "BEGIN", "END", "OPEN_BRACKETS", "CLOSE_BRACKETS", "IS", 
		"COMMA", "SKIP_", "ASSIGNMENT", "READ", "FREE", "RETURN", "EXIT", "PRINT", 
		"PRINTLN", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", "FOR", "BREAK", 
		"CONTINUE", "UNTIL", "SEMI_COLON", "NEW_PAIR", "CALL", "FST", "SND", "VOID", 
		"INT", "BOOL", "CHAR", "STRING", "OPEN_SQUARE_BRACKETS", "CLOSE_SQUARE_BRACKETS", 
		"PAIR", "CLASS", "OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "PUBLIC", 
		"SELF", "DOT", "NEW", "NOT", "MINUS", "LENGTH", "ORDINAL", "CHR", "COMPLEMENT", 
		"MULTIPLY", "DIVIDE", "MODULO", "ADD", "GREATER_THAN", "GREATER_OR_EQUAL", 
		"LESS_THAN", "LESS_OR_EQUAL", "EQUALS", "NOT_EQUALS", "AND", "OR", "BITWISEOR", 
		"BITWISEAND", "BITWISEXOR", "SHIFTLEFT", "SHIFTRIGHT", "HELLO", "NULL", 
		"TRUE", "FALSE", "IDENT", "INTEGER", "CHAR_LITER", "STR_LITER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public BasicLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BasicLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2O\u01f1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\3\2\3\2\7\2"+
		"\u00a8\n\2\f\2\16\2\u00ab\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 "+
		"\3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3"+
		"%\3%\3%\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3*\3*"+
		"\3+\3+\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3/\3/\3/\3/\3\60\3\60"+
		"\3\61\3\61\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64"+
		"\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3;\3<\3<\3=\3="+
		"\3=\3>\3>\3>\3?\3?\3?\3@\3@\3@\3A\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3E\3F"+
		"\3F\3F\3G\3G\3G\3G\3H\3H\3I\3I\3I\3I\3I\3J\3J\3J\3J\3J\3K\3K\3K\3K\3K"+
		"\3K\3L\3L\3M\3M\3M\7M\u01d3\nM\fM\16M\u01d6\13M\3N\6N\u01d9\nN\rN\16N"+
		"\u01da\3O\3O\3O\3O\3P\3P\7P\u01e3\nP\fP\16P\u01e6\13P\3P\3P\3Q\5Q\u01eb"+
		"\nQ\3R\3R\3R\5R\u01f0\nR\3\u00a9\2S\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.["+
		"/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083"+
		"C\u0085D\u0087E\u0089F\u008bG\u008dH\u008f\2\u0091I\u0093J\u0095K\u0097"+
		"\2\u0099L\u009bM\u009dN\u009fO\u00a1\2\u00a3\2\3\2\6\5\2\13\f\17\17\""+
		"\"\5\2C\\aac|\13\2$$))\62\62^^ddhhppttvv\4\2\"#%\u0080\2\u01f2\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2"+
		"\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2"+
		"c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3"+
		"\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2"+
		"\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3"+
		"\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2"+
		"\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0099\3\2\2\2\2\u009b"+
		"\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\3\u00a5\3\2\2\2\5\u00b0\3\2\2"+
		"\2\7\u00b6\3\2\2\2\t\u00ba\3\2\2\2\13\u00bc\3\2\2\2\r\u00be\3\2\2\2\17"+
		"\u00c1\3\2\2\2\21\u00c3\3\2\2\2\23\u00c8\3\2\2\2\25\u00ca\3\2\2\2\27\u00cf"+
		"\3\2\2\2\31\u00d4\3\2\2\2\33\u00db\3\2\2\2\35\u00e0\3\2\2\2\37\u00e6\3"+
		"\2\2\2!\u00ee\3\2\2\2#\u00f1\3\2\2\2%\u00f6\3\2\2\2\'\u00fb\3\2\2\2)\u00fe"+
		"\3\2\2\2+\u0104\3\2\2\2-\u0107\3\2\2\2/\u010c\3\2\2\2\61\u0110\3\2\2\2"+
		"\63\u0116\3\2\2\2\65\u011f\3\2\2\2\67\u0125\3\2\2\29\u0127\3\2\2\2;\u012f"+
		"\3\2\2\2=\u0134\3\2\2\2?\u0138\3\2\2\2A\u013c\3\2\2\2C\u0141\3\2\2\2E"+
		"\u0145\3\2\2\2G\u014a\3\2\2\2I\u014f\3\2\2\2K\u0156\3\2\2\2M\u0158\3\2"+
		"\2\2O\u015a\3\2\2\2Q\u015f\3\2\2\2S\u0165\3\2\2\2U\u0167\3\2\2\2W\u0169"+
		"\3\2\2\2Y\u0170\3\2\2\2[\u0175\3\2\2\2]\u0177\3\2\2\2_\u017b\3\2\2\2a"+
		"\u017d\3\2\2\2c\u017f\3\2\2\2e\u0183\3\2\2\2g\u0187\3\2\2\2i\u018b\3\2"+
		"\2\2k\u018d\3\2\2\2m\u018f\3\2\2\2o\u0191\3\2\2\2q\u0193\3\2\2\2s\u0195"+
		"\3\2\2\2u\u0197\3\2\2\2w\u019a\3\2\2\2y\u019c\3\2\2\2{\u019f\3\2\2\2}"+
		"\u01a2\3\2\2\2\177\u01a5\3\2\2\2\u0081\u01a8\3\2\2\2\u0083\u01ab\3\2\2"+
		"\2\u0085\u01ad\3\2\2\2\u0087\u01af\3\2\2\2\u0089\u01b1\3\2\2\2\u008b\u01b4"+
		"\3\2\2\2\u008d\u01b7\3\2\2\2\u008f\u01bb\3\2\2\2\u0091\u01bd\3\2\2\2\u0093"+
		"\u01c2\3\2\2\2\u0095\u01c7\3\2\2\2\u0097\u01cd\3\2\2\2\u0099\u01cf\3\2"+
		"\2\2\u009b\u01d8\3\2\2\2\u009d\u01dc\3\2\2\2\u009f\u01e0\3\2\2\2\u00a1"+
		"\u01ea\3\2\2\2\u00a3\u01ef\3\2\2\2\u00a5\u00a9\7%\2\2\u00a6\u00a8\13\2"+
		"\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00aa\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ad\7\f"+
		"\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\b\2\2\2\u00af\4\3\2\2\2\u00b0\u00b1"+
		"\7d\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7i\2\2\u00b3\u00b4\7k\2\2\u00b4"+
		"\u00b5\7p\2\2\u00b5\6\3\2\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7p\2\2\u00b8"+
		"\u00b9\7f\2\2\u00b9\b\3\2\2\2\u00ba\u00bb\7*\2\2\u00bb\n\3\2\2\2\u00bc"+
		"\u00bd\7+\2\2\u00bd\f\3\2\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0\7u\2\2\u00c0"+
		"\16\3\2\2\2\u00c1\u00c2\7.\2\2\u00c2\20\3\2\2\2\u00c3\u00c4\7u\2\2\u00c4"+
		"\u00c5\7m\2\2\u00c5\u00c6\7k\2\2\u00c6\u00c7\7r\2\2\u00c7\22\3\2\2\2\u00c8"+
		"\u00c9\7?\2\2\u00c9\24\3\2\2\2\u00ca\u00cb\7t\2\2\u00cb\u00cc\7g\2\2\u00cc"+
		"\u00cd\7c\2\2\u00cd\u00ce\7f\2\2\u00ce\26\3\2\2\2\u00cf\u00d0\7h\2\2\u00d0"+
		"\u00d1\7t\2\2\u00d1\u00d2\7g\2\2\u00d2\u00d3\7g\2\2\u00d3\30\3\2\2\2\u00d4"+
		"\u00d5\7t\2\2\u00d5\u00d6\7g\2\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7w\2\2"+
		"\u00d8\u00d9\7t\2\2\u00d9\u00da\7p\2\2\u00da\32\3\2\2\2\u00db\u00dc\7"+
		"g\2\2\u00dc\u00dd\7z\2\2\u00dd\u00de\7k\2\2\u00de\u00df\7v\2\2\u00df\34"+
		"\3\2\2\2\u00e0\u00e1\7r\2\2\u00e1\u00e2\7t\2\2\u00e2\u00e3\7k\2\2\u00e3"+
		"\u00e4\7p\2\2\u00e4\u00e5\7v\2\2\u00e5\36\3\2\2\2\u00e6\u00e7\7r\2\2\u00e7"+
		"\u00e8\7t\2\2\u00e8\u00e9\7k\2\2\u00e9\u00ea\7p\2\2\u00ea\u00eb\7v\2\2"+
		"\u00eb\u00ec\7n\2\2\u00ec\u00ed\7p\2\2\u00ed \3\2\2\2\u00ee\u00ef\7k\2"+
		"\2\u00ef\u00f0\7h\2\2\u00f0\"\3\2\2\2\u00f1\u00f2\7v\2\2\u00f2\u00f3\7"+
		"j\2\2\u00f3\u00f4\7g\2\2\u00f4\u00f5\7p\2\2\u00f5$\3\2\2\2\u00f6\u00f7"+
		"\7g\2\2\u00f7\u00f8\7n\2\2\u00f8\u00f9\7u\2\2\u00f9\u00fa\7g\2\2\u00fa"+
		"&\3\2\2\2\u00fb\u00fc\7h\2\2\u00fc\u00fd\7k\2\2\u00fd(\3\2\2\2\u00fe\u00ff"+
		"\7y\2\2\u00ff\u0100\7j\2\2\u0100\u0101\7k\2\2\u0101\u0102\7n\2\2\u0102"+
		"\u0103\7g\2\2\u0103*\3\2\2\2\u0104\u0105\7f\2\2\u0105\u0106\7q\2\2\u0106"+
		",\3\2\2\2\u0107\u0108\7f\2\2\u0108\u0109\7q\2\2\u0109\u010a\7p\2\2\u010a"+
		"\u010b\7g\2\2\u010b.\3\2\2\2\u010c\u010d\7h\2\2\u010d\u010e\7q\2\2\u010e"+
		"\u010f\7t\2\2\u010f\60\3\2\2\2\u0110\u0111\7d\2\2\u0111\u0112\7t\2\2\u0112"+
		"\u0113\7g\2\2\u0113\u0114\7c\2\2\u0114\u0115\7m\2\2\u0115\62\3\2\2\2\u0116"+
		"\u0117\7e\2\2\u0117\u0118\7q\2\2\u0118\u0119\7p\2\2\u0119\u011a\7v\2\2"+
		"\u011a\u011b\7k\2\2\u011b\u011c\7p\2\2\u011c\u011d\7w\2\2\u011d\u011e"+
		"\7g\2\2\u011e\64\3\2\2\2\u011f\u0120\7w\2\2\u0120\u0121\7p\2\2\u0121\u0122"+
		"\7v\2\2\u0122\u0123\7k\2\2\u0123\u0124\7n\2\2\u0124\66\3\2\2\2\u0125\u0126"+
		"\7=\2\2\u01268\3\2\2\2\u0127\u0128\7p\2\2\u0128\u0129\7g\2\2\u0129\u012a"+
		"\7y\2\2\u012a\u012b\7r\2\2\u012b\u012c\7c\2\2\u012c\u012d\7k\2\2\u012d"+
		"\u012e\7t\2\2\u012e:\3\2\2\2\u012f\u0130\7e\2\2\u0130\u0131\7c\2\2\u0131"+
		"\u0132\7n\2\2\u0132\u0133\7n\2\2\u0133<\3\2\2\2\u0134\u0135\7h\2\2\u0135"+
		"\u0136\7u\2\2\u0136\u0137\7v\2\2\u0137>\3\2\2\2\u0138\u0139\7u\2\2\u0139"+
		"\u013a\7p\2\2\u013a\u013b\7f\2\2\u013b@\3\2\2\2\u013c\u013d\7x\2\2\u013d"+
		"\u013e\7q\2\2\u013e\u013f\7k\2\2\u013f\u0140\7f\2\2\u0140B\3\2\2\2\u0141"+
		"\u0142\7k\2\2\u0142\u0143\7p\2\2\u0143\u0144\7v\2\2\u0144D\3\2\2\2\u0145"+
		"\u0146\7d\2\2\u0146\u0147\7q\2\2\u0147\u0148\7q\2\2\u0148\u0149\7n\2\2"+
		"\u0149F\3\2\2\2\u014a\u014b\7e\2\2\u014b\u014c\7j\2\2\u014c\u014d\7c\2"+
		"\2\u014d\u014e\7t\2\2\u014eH\3\2\2\2\u014f\u0150\7u\2\2\u0150\u0151\7"+
		"v\2\2\u0151\u0152\7t\2\2\u0152\u0153\7k\2\2\u0153\u0154\7p\2\2\u0154\u0155"+
		"\7i\2\2\u0155J\3\2\2\2\u0156\u0157\7]\2\2\u0157L\3\2\2\2\u0158\u0159\7"+
		"_\2\2\u0159N\3\2\2\2\u015a\u015b\7r\2\2\u015b\u015c\7c\2\2\u015c\u015d"+
		"\7k\2\2\u015d\u015e\7t\2\2\u015eP\3\2\2\2\u015f\u0160\7e\2\2\u0160\u0161"+
		"\7n\2\2\u0161\u0162\7c\2\2\u0162\u0163\7u\2\2\u0163\u0164\7u\2\2\u0164"+
		"R\3\2\2\2\u0165\u0166\7}\2\2\u0166T\3\2\2\2\u0167\u0168\7\177\2\2\u0168"+
		"V\3\2\2\2\u0169\u016a\7r\2\2\u016a\u016b\7w\2\2\u016b\u016c\7d\2\2\u016c"+
		"\u016d\7n\2\2\u016d\u016e\7k\2\2\u016e\u016f\7e\2\2\u016fX\3\2\2\2\u0170"+
		"\u0171\7u\2\2\u0171\u0172\7g\2\2\u0172\u0173\7n\2\2\u0173\u0174\7h\2\2"+
		"\u0174Z\3\2\2\2\u0175\u0176\7\60\2\2\u0176\\\3\2\2\2\u0177\u0178\7p\2"+
		"\2\u0178\u0179\7g\2\2\u0179\u017a\7y\2\2\u017a^\3\2\2\2\u017b\u017c\7"+
		"#\2\2\u017c`\3\2\2\2\u017d\u017e\7/\2\2\u017eb\3\2\2\2\u017f\u0180\7n"+
		"\2\2\u0180\u0181\7g\2\2\u0181\u0182\7p\2\2\u0182d\3\2\2\2\u0183\u0184"+
		"\7q\2\2\u0184\u0185\7t\2\2\u0185\u0186\7f\2\2\u0186f\3\2\2\2\u0187\u0188"+
		"\7e\2\2\u0188\u0189\7j\2\2\u0189\u018a\7t\2\2\u018ah\3\2\2\2\u018b\u018c"+
		"\7\u0080\2\2\u018cj\3\2\2\2\u018d\u018e\7,\2\2\u018el\3\2\2\2\u018f\u0190"+
		"\7\61\2\2\u0190n\3\2\2\2\u0191\u0192\7\'\2\2\u0192p\3\2\2\2\u0193\u0194"+
		"\7-\2\2\u0194r\3\2\2\2\u0195\u0196\7@\2\2\u0196t\3\2\2\2\u0197\u0198\7"+
		"@\2\2\u0198\u0199\7?\2\2\u0199v\3\2\2\2\u019a\u019b\7>\2\2\u019bx\3\2"+
		"\2\2\u019c\u019d\7>\2\2\u019d\u019e\7?\2\2\u019ez\3\2\2\2\u019f\u01a0"+
		"\7?\2\2\u01a0\u01a1\7?\2\2\u01a1|\3\2\2\2\u01a2\u01a3\7#\2\2\u01a3\u01a4"+
		"\7?\2\2\u01a4~\3\2\2\2\u01a5\u01a6\7(\2\2\u01a6\u01a7\7(\2\2\u01a7\u0080"+
		"\3\2\2\2\u01a8\u01a9\7~\2\2\u01a9\u01aa\7~\2\2\u01aa\u0082\3\2\2\2\u01ab"+
		"\u01ac\7~\2\2\u01ac\u0084\3\2\2\2\u01ad\u01ae\7(\2\2\u01ae\u0086\3\2\2"+
		"\2\u01af\u01b0\7`\2\2\u01b0\u0088\3\2\2\2\u01b1\u01b2\7>\2\2\u01b2\u01b3"+
		"\7>\2\2\u01b3\u008a\3\2\2\2\u01b4\u01b5\7@\2\2\u01b5\u01b6\7@\2\2\u01b6"+
		"\u008c\3\2\2\2\u01b7\u01b8\t\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\bG"+
		"\2\2\u01ba\u008e\3\2\2\2\u01bb\u01bc\4\62;\2\u01bc\u0090\3\2\2\2\u01bd"+
		"\u01be\7p\2\2\u01be\u01bf\7w\2\2\u01bf\u01c0\7n\2\2\u01c0\u01c1\7n\2\2"+
		"\u01c1\u0092\3\2\2\2\u01c2\u01c3\7v\2\2\u01c3\u01c4\7t\2\2\u01c4\u01c5"+
		"\7w\2\2\u01c5\u01c6\7g\2\2\u01c6\u0094\3\2\2\2\u01c7\u01c8\7h\2\2\u01c8"+
		"\u01c9\7c\2\2\u01c9\u01ca\7n\2\2\u01ca\u01cb\7u\2\2\u01cb\u01cc\7g\2\2"+
		"\u01cc\u0096\3\2\2\2\u01cd\u01ce\t\3\2\2\u01ce\u0098\3\2\2\2\u01cf\u01d4"+
		"\5\u0097L\2\u01d0\u01d3\5\u0097L\2\u01d1\u01d3\5\u008fH\2\u01d2\u01d0"+
		"\3\2\2\2\u01d2\u01d1\3\2\2\2\u01d3\u01d6\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d4"+
		"\u01d5\3\2\2\2\u01d5\u009a\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d7\u01d9\5\u008f"+
		"H\2\u01d8\u01d7\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01d8\3\2\2\2\u01da"+
		"\u01db\3\2\2\2\u01db\u009c\3\2\2\2\u01dc\u01dd\7)\2\2\u01dd\u01de\5\u00a3"+
		"R\2\u01de\u01df\7)\2\2\u01df\u009e\3\2\2\2\u01e0\u01e4\7$\2\2\u01e1\u01e3"+
		"\5\u00a3R\2\u01e2\u01e1\3\2\2\2\u01e3\u01e6\3\2\2\2\u01e4\u01e2\3\2\2"+
		"\2\u01e4\u01e5\3\2\2\2\u01e5\u01e7\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e7\u01e8"+
		"\7$\2\2\u01e8\u00a0\3\2\2\2\u01e9\u01eb\t\4\2\2\u01ea\u01e9\3\2\2\2\u01eb"+
		"\u00a2\3\2\2\2\u01ec\u01f0\t\5\2\2\u01ed\u01ee\7^\2\2\u01ee\u01f0\5\u00a1"+
		"Q\2\u01ef\u01ec\3\2\2\2\u01ef\u01ed\3\2\2\2\u01f0\u00a4\3\2\2\2\n\2\u00a9"+
		"\u01d2\u01d4\u01da\u01e4\u01ea\u01ef\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}