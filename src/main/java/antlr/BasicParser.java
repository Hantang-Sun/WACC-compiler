// Generated from ./BasicParser.g4 by ANTLR 4.7
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicParser extends Parser {
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
	public static final int
		RULE_program = 0, RULE_func = 1, RULE_paramList = 2, RULE_param = 3, RULE_stat = 4, 
		RULE_assignLhs = 5, RULE_assignRhs = 6, RULE_classOp = 7, RULE_argList = 8, 
		RULE_pairElem = 9, RULE_type = 10, RULE_baseType = 11, RULE_arrayType = 12, 
		RULE_baseArrayType = 13, RULE_pairType = 14, RULE_pairElemType = 15, RULE_expr = 16, 
		RULE_unaryOper = 17, RULE_arrayElem = 18, RULE_intLiter = 19, RULE_boolLiter = 20, 
		RULE_charLiter = 21, RULE_strLiter = 22, RULE_arrayLiter = 23, RULE_pairLiter = 24, 
		RULE_attribute = 25, RULE_constructor = 26, RULE_newclass = 27, RULE_method = 28;
	public static final String[] ruleNames = {
		"program", "func", "paramList", "param", "stat", "assignLhs", "assignRhs", 
		"classOp", "argList", "pairElem", "type", "baseType", "arrayType", "baseArrayType", 
		"pairType", "pairElemType", "expr", "unaryOper", "arrayElem", "intLiter", 
		"boolLiter", "charLiter", "strLiter", "arrayLiter", "pairLiter", "attribute", 
		"constructor", "newclass", "method"
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

	@Override
	public String getGrammarFileName() { return "BasicParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BasicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TerminalNode EOF() { return getToken(BasicParser.EOF, 0); }
		public List<NewclassContext> newclass() {
			return getRuleContexts(NewclassContext.class);
		}
		public NewclassContext newclass(int i) {
			return getRuleContext(NewclassContext.class,i);
		}
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(BEGIN);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(59);
				newclass();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(68);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(65);
					func();
					}
					} 
				}
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(71);
			stat(0);
			setState(72);
			match(END);
			setState(73);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public TerminalNode IS() { return getToken(BasicParser.IS, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			type();
			setState(76);
			match(IDENT);
			setState(77);
			match(OPEN_BRACKETS);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & ((1L << (VOID - 32)) | (1L << (INT - 32)) | (1L << (BOOL - 32)) | (1L << (CHAR - 32)) | (1L << (STRING - 32)) | (1L << (PAIR - 32)) | (1L << (IDENT - 32)))) != 0)) {
				{
				setState(78);
				paramList();
				}
			}

			setState(81);
			match(CLOSE_BRACKETS);
			setState(82);
			match(IS);
			setState(83);
			stat(0);
			setState(84);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			param();
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(87);
				match(COMMA);
				setState(88);
				param();
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			type();
			setState(95);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public TerminalNode SKIP_() { return getToken(BasicParser.SKIP_, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(BasicParser.ASSIGNMENT, 0); }
		public AssignRhsContext assignRhs() {
			return getRuleContext(AssignRhsContext.class,0);
		}
		public AssignLhsContext assignLhs() {
			return getRuleContext(AssignLhsContext.class,0);
		}
		public TerminalNode READ() { return getToken(BasicParser.READ, 0); }
		public TerminalNode FREE() { return getToken(BasicParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EXIT() { return getToken(BasicParser.EXIT, 0); }
		public TerminalNode RETURN() { return getToken(BasicParser.RETURN, 0); }
		public TerminalNode PRINT() { return getToken(BasicParser.PRINT, 0); }
		public TerminalNode PRINTLN() { return getToken(BasicParser.PRINTLN, 0); }
		public TerminalNode IF() { return getToken(BasicParser.IF, 0); }
		public TerminalNode THEN() { return getToken(BasicParser.THEN, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(BasicParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(BasicParser.FI, 0); }
		public TerminalNode WHILE() { return getToken(BasicParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(BasicParser.DO, 0); }
		public TerminalNode DONE() { return getToken(BasicParser.DONE, 0); }
		public TerminalNode FOR() { return getToken(BasicParser.FOR, 0); }
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public TerminalNode BREAK() { return getToken(BasicParser.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(BasicParser.CONTINUE, 0); }
		public TerminalNode UNTIL() { return getToken(BasicParser.UNTIL, 0); }
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TerminalNode CALL() { return getToken(BasicParser.CALL, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode SELF() { return getToken(BasicParser.SELF, 0); }
		public List<ClassOpContext> classOp() {
			return getRuleContexts(ClassOpContext.class);
		}
		public ClassOpContext classOp(int i) {
			return getRuleContext(ClassOpContext.class,i);
		}
		public TerminalNode SEMI_COLON() { return getToken(BasicParser.SEMI_COLON, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_stat, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(98);
				match(SKIP_);
				}
				break;
			case 2:
				{
				setState(99);
				type();
				setState(100);
				match(IDENT);
				setState(101);
				match(ASSIGNMENT);
				setState(102);
				assignRhs();
				}
				break;
			case 3:
				{
				setState(104);
				assignLhs();
				setState(105);
				match(ASSIGNMENT);
				setState(106);
				assignRhs();
				}
				break;
			case 4:
				{
				setState(108);
				match(READ);
				setState(109);
				assignLhs();
				}
				break;
			case 5:
				{
				setState(110);
				match(FREE);
				setState(111);
				expr(0);
				}
				break;
			case 6:
				{
				setState(112);
				match(EXIT);
				setState(113);
				expr(0);
				}
				break;
			case 7:
				{
				setState(114);
				match(RETURN);
				setState(115);
				expr(0);
				}
				break;
			case 8:
				{
				setState(116);
				match(PRINT);
				setState(117);
				expr(0);
				}
				break;
			case 9:
				{
				setState(118);
				match(PRINTLN);
				setState(119);
				expr(0);
				}
				break;
			case 10:
				{
				setState(120);
				match(IF);
				setState(121);
				expr(0);
				setState(122);
				match(THEN);
				setState(123);
				stat(0);
				setState(124);
				match(ELSE);
				setState(125);
				stat(0);
				setState(126);
				match(FI);
				}
				break;
			case 11:
				{
				setState(128);
				match(WHILE);
				setState(129);
				expr(0);
				setState(130);
				match(DO);
				setState(131);
				stat(0);
				setState(132);
				match(DONE);
				}
				break;
			case 12:
				{
				setState(134);
				match(FOR);
				setState(135);
				match(OPEN_BRACKETS);
				setState(136);
				stat(0);
				setState(137);
				match(COMMA);
				setState(138);
				expr(0);
				setState(139);
				match(COMMA);
				setState(140);
				stat(0);
				setState(141);
				match(CLOSE_BRACKETS);
				setState(142);
				stat(0);
				setState(143);
				match(DONE);
				}
				break;
			case 13:
				{
				setState(145);
				match(BREAK);
				}
				break;
			case 14:
				{
				setState(146);
				match(CONTINUE);
				}
				break;
			case 15:
				{
				setState(147);
				match(DO);
				setState(148);
				stat(0);
				setState(149);
				match(UNTIL);
				setState(150);
				expr(0);
				}
				break;
			case 16:
				{
				setState(152);
				match(BEGIN);
				setState(153);
				stat(0);
				setState(154);
				match(END);
				}
				break;
			case 17:
				{
				setState(156);
				match(CALL);
				setState(157);
				match(IDENT);
				setState(158);
				match(OPEN_BRACKETS);
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_BRACKETS) | (1L << NOT) | (1L << MINUS) | (1L << LENGTH) | (1L << ORDINAL) | (1L << CHR) | (1L << COMPLEMENT) | (1L << ADD))) != 0) || ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (NULL - 71)) | (1L << (TRUE - 71)) | (1L << (FALSE - 71)) | (1L << (IDENT - 71)) | (1L << (INTEGER - 71)) | (1L << (CHAR_LITER - 71)) | (1L << (STR_LITER - 71)))) != 0)) {
					{
					setState(159);
					argList();
					}
				}

				setState(162);
				match(CLOSE_BRACKETS);
				}
				break;
			case 18:
				{
				setState(163);
				_la = _input.LA(1);
				if ( !(_la==SELF || _la==IDENT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(165); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(164);
						classOp();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(167); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(176);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(171);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(172);
					match(SEMI_COLON);
					setState(173);
					stat(2);
					}
					} 
				}
				setState(178);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignLhsContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode SELF() { return getToken(BasicParser.SELF, 0); }
		public List<ClassOpContext> classOp() {
			return getRuleContexts(ClassOpContext.class);
		}
		public ClassOpContext classOp(int i) {
			return getRuleContext(ClassOpContext.class,i);
		}
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignLhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLhs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignLhs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLhsContext assignLhs() throws RecognitionException {
		AssignLhsContext _localctx = new AssignLhsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignLhs);
		int _la;
		try {
			int _alt;
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				_la = _input.LA(1);
				if ( !(_la==SELF || _la==IDENT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(183);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(180);
						classOp();
						}
						} 
					}
					setState(185);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(187);
				pairElem();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignRhsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayLiterContext arrayLiter() {
			return getRuleContext(ArrayLiterContext.class,0);
		}
		public TerminalNode NEW_PAIR() { return getToken(BasicParser.NEW_PAIR, 0); }
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public TerminalNode CALL() { return getToken(BasicParser.CALL, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode SELF() { return getToken(BasicParser.SELF, 0); }
		public List<ClassOpContext> classOp() {
			return getRuleContexts(ClassOpContext.class);
		}
		public ClassOpContext classOp(int i) {
			return getRuleContext(ClassOpContext.class,i);
		}
		public TerminalNode NEW() { return getToken(BasicParser.NEW, 0); }
		public AssignRhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRhs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignRhs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRhsContext assignRhs() throws RecognitionException {
		AssignRhsContext _localctx = new AssignRhsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignRhs);
		int _la;
		try {
			int _alt;
			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				arrayLiter();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(192);
				match(NEW_PAIR);
				setState(193);
				match(OPEN_BRACKETS);
				setState(194);
				expr(0);
				setState(195);
				match(COMMA);
				setState(196);
				expr(0);
				setState(197);
				match(CLOSE_BRACKETS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(199);
				pairElem();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(200);
				match(CALL);
				setState(201);
				match(IDENT);
				setState(202);
				match(OPEN_BRACKETS);
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_BRACKETS) | (1L << NOT) | (1L << MINUS) | (1L << LENGTH) | (1L << ORDINAL) | (1L << CHR) | (1L << COMPLEMENT) | (1L << ADD))) != 0) || ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (NULL - 71)) | (1L << (TRUE - 71)) | (1L << (FALSE - 71)) | (1L << (IDENT - 71)) | (1L << (INTEGER - 71)) | (1L << (CHAR_LITER - 71)) | (1L << (STR_LITER - 71)))) != 0)) {
					{
					setState(203);
					argList();
					}
				}

				setState(206);
				match(CLOSE_BRACKETS);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(207);
				_la = _input.LA(1);
				if ( !(_la==SELF || _la==IDENT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(209); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(208);
						classOp();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(211); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(213);
				match(NEW);
				setState(214);
				match(IDENT);
				setState(215);
				match(OPEN_BRACKETS);
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_BRACKETS) | (1L << NOT) | (1L << MINUS) | (1L << LENGTH) | (1L << ORDINAL) | (1L << CHR) | (1L << COMPLEMENT) | (1L << ADD))) != 0) || ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (NULL - 71)) | (1L << (TRUE - 71)) | (1L << (FALSE - 71)) | (1L << (IDENT - 71)) | (1L << (INTEGER - 71)) | (1L << (CHAR_LITER - 71)) | (1L << (STR_LITER - 71)))) != 0)) {
					{
					setState(216);
					argList();
					}
				}

				setState(219);
				match(CLOSE_BRACKETS);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassOpContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(BasicParser.DOT, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public ClassOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitClassOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassOpContext classOp() throws RecognitionException {
		ClassOpContext _localctx = new ClassOpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_classOp);
		int _la;
		try {
			setState(231);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				match(DOT);
				setState(223);
				match(IDENT);
				setState(224);
				match(OPEN_BRACKETS);
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_BRACKETS) | (1L << NOT) | (1L << MINUS) | (1L << LENGTH) | (1L << ORDINAL) | (1L << CHR) | (1L << COMPLEMENT) | (1L << ADD))) != 0) || ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (NULL - 71)) | (1L << (TRUE - 71)) | (1L << (FALSE - 71)) | (1L << (IDENT - 71)) | (1L << (INTEGER - 71)) | (1L << (CHAR_LITER - 71)) | (1L << (STR_LITER - 71)))) != 0)) {
					{
					setState(225);
					argList();
					}
				}

				setState(228);
				match(CLOSE_BRACKETS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(229);
				match(DOT);
				setState(230);
				match(IDENT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			expr(0);
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(234);
				match(COMMA);
				setState(235);
				expr(0);
				}
				}
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemContext extends ParserRuleContext {
		public TerminalNode FST() { return getToken(BasicParser.FST, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SND() { return getToken(BasicParser.SND, 0); }
		public PairElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemContext pairElem() throws RecognitionException {
		PairElemContext _localctx = new PairElemContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pairElem);
		try {
			setState(245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FST:
				enterOuterAlt(_localctx, 1);
				{
				setState(241);
				match(FST);
				setState(242);
				expr(0);
				}
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				match(SND);
				setState(244);
				expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_type);
		try {
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
				baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(248);
				arrayType(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(249);
				pairType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(250);
				match(IDENT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(BasicParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(BasicParser.BOOL, 0); }
		public TerminalNode CHAR() { return getToken(BasicParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(BasicParser.STRING, 0); }
		public TerminalNode VOID() { return getToken(BasicParser.VOID, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public BaseArrayTypeContext baseArrayType() {
			return getRuleContext(BaseArrayTypeContext.class,0);
		}
		public TerminalNode OPEN_SQUARE_BRACKETS() { return getToken(BasicParser.OPEN_SQUARE_BRACKETS, 0); }
		public TerminalNode CLOSE_SQUARE_BRACKETS() { return getToken(BasicParser.CLOSE_SQUARE_BRACKETS, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		return arrayType(0);
	}

	private ArrayTypeContext arrayType(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, _parentState);
		ArrayTypeContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_arrayType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(256);
			baseArrayType();
			setState(257);
			match(OPEN_SQUARE_BRACKETS);
			setState(258);
			match(CLOSE_SQUARE_BRACKETS);
			}
			_ctx.stop = _input.LT(-1);
			setState(265);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayTypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_arrayType);
					setState(260);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(261);
					match(OPEN_SQUARE_BRACKETS);
					setState(262);
					match(CLOSE_SQUARE_BRACKETS);
					}
					} 
				}
				setState(267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BaseArrayTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public BaseArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseArrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBaseArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseArrayTypeContext baseArrayType() throws RecognitionException {
		BaseArrayTypeContext _localctx = new BaseArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_baseArrayType);
		try {
			setState(270);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				baseType();
				}
				break;
			case PAIR:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairTypeContext extends ParserRuleContext {
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public List<PairElemTypeContext> pairElemType() {
			return getRuleContexts(PairElemTypeContext.class);
		}
		public PairElemTypeContext pairElemType(int i) {
			return getRuleContext(PairElemTypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public PairTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairTypeContext pairType() throws RecognitionException {
		PairTypeContext _localctx = new PairTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(PAIR);
			setState(273);
			match(OPEN_BRACKETS);
			setState(274);
			pairElemType();
			setState(275);
			match(COMMA);
			setState(276);
			pairElemType();
			setState(277);
			match(CLOSE_BRACKETS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElemType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElemType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
		PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_pairElemType);
		try {
			setState(282);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(279);
				baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(280);
				arrayType(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(281);
				match(PAIR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public StrLiterContext strLiter() {
			return getRuleContext(StrLiterContext.class,0);
		}
		public BoolLiterContext boolLiter() {
			return getRuleContext(BoolLiterContext.class,0);
		}
		public CharLiterContext charLiter() {
			return getRuleContext(CharLiterContext.class,0);
		}
		public IntLiterContext intLiter() {
			return getRuleContext(IntLiterContext.class,0);
		}
		public PairLiterContext pairLiter() {
			return getRuleContext(PairLiterContext.class,0);
		}
		public UnaryOperContext unaryOper() {
			return getRuleContext(UnaryOperContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public TerminalNode MULTIPLY() { return getToken(BasicParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(BasicParser.DIVIDE, 0); }
		public TerminalNode MODULO() { return getToken(BasicParser.MODULO, 0); }
		public TerminalNode ADD() { return getToken(BasicParser.ADD, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode BITWISEOR() { return getToken(BasicParser.BITWISEOR, 0); }
		public TerminalNode BITWISEAND() { return getToken(BasicParser.BITWISEAND, 0); }
		public TerminalNode BITWISEXOR() { return getToken(BasicParser.BITWISEXOR, 0); }
		public TerminalNode SHIFTLEFT() { return getToken(BasicParser.SHIFTLEFT, 0); }
		public TerminalNode SHIFTRIGHT() { return getToken(BasicParser.SHIFTRIGHT, 0); }
		public TerminalNode GREATER_THAN() { return getToken(BasicParser.GREATER_THAN, 0); }
		public TerminalNode GREATER_OR_EQUAL() { return getToken(BasicParser.GREATER_OR_EQUAL, 0); }
		public TerminalNode LESS_THAN() { return getToken(BasicParser.LESS_THAN, 0); }
		public TerminalNode LESS_OR_EQUAL() { return getToken(BasicParser.LESS_OR_EQUAL, 0); }
		public TerminalNode NOT_EQUALS() { return getToken(BasicParser.NOT_EQUALS, 0); }
		public TerminalNode EQUALS() { return getToken(BasicParser.EQUALS, 0); }
		public TerminalNode AND() { return getToken(BasicParser.AND, 0); }
		public TerminalNode OR() { return getToken(BasicParser.OR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(285);
				strLiter();
				}
				break;
			case 2:
				{
				setState(286);
				boolLiter();
				}
				break;
			case 3:
				{
				setState(287);
				charLiter();
				}
				break;
			case 4:
				{
				setState(288);
				intLiter();
				}
				break;
			case 5:
				{
				setState(289);
				pairLiter();
				}
				break;
			case 6:
				{
				setState(290);
				unaryOper();
				setState(291);
				expr(4);
				}
				break;
			case 7:
				{
				setState(293);
				match(IDENT);
				}
				break;
			case 8:
				{
				setState(294);
				arrayElem();
				}
				break;
			case 9:
				{
				setState(295);
				match(OPEN_BRACKETS);
				setState(296);
				expr(0);
				setState(297);
				match(CLOSE_BRACKETS);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(324);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(322);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(301);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(302);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MODULO))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(303);
						expr(12);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(304);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(305);
						_la = _input.LA(1);
						if ( !(_la==MINUS || _la==ADD) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(306);
						expr(11);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(307);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(308);
						_la = _input.LA(1);
						if ( !(((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (BITWISEOR - 65)) | (1L << (BITWISEAND - 65)) | (1L << (BITWISEXOR - 65)) | (1L << (SHIFTLEFT - 65)) | (1L << (SHIFTRIGHT - 65)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(309);
						expr(10);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(310);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(311);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER_THAN) | (1L << GREATER_OR_EQUAL) | (1L << LESS_THAN) | (1L << LESS_OR_EQUAL))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(312);
						expr(9);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(313);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(314);
						_la = _input.LA(1);
						if ( !(_la==EQUALS || _la==NOT_EQUALS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(315);
						expr(8);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(316);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(317);
						match(AND);
						setState(318);
						expr(7);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(319);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(320);
						match(OR);
						setState(321);
						expr(6);
						}
						break;
					}
					} 
				}
				setState(326);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryOperContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(BasicParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode LENGTH() { return getToken(BasicParser.LENGTH, 0); }
		public TerminalNode ORDINAL() { return getToken(BasicParser.ORDINAL, 0); }
		public TerminalNode CHR() { return getToken(BasicParser.CHR, 0); }
		public TerminalNode COMPLEMENT() { return getToken(BasicParser.COMPLEMENT, 0); }
		public UnaryOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnaryOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperContext unaryOper() throws RecognitionException {
		UnaryOperContext _localctx = new UnaryOperContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_unaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << LENGTH) | (1L << ORDINAL) | (1L << CHR) | (1L << COMPLEMENT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayElemContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public List<TerminalNode> OPEN_SQUARE_BRACKETS() { return getTokens(BasicParser.OPEN_SQUARE_BRACKETS); }
		public TerminalNode OPEN_SQUARE_BRACKETS(int i) {
			return getToken(BasicParser.OPEN_SQUARE_BRACKETS, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CLOSE_SQUARE_BRACKETS() { return getTokens(BasicParser.CLOSE_SQUARE_BRACKETS); }
		public TerminalNode CLOSE_SQUARE_BRACKETS(int i) {
			return getToken(BasicParser.CLOSE_SQUARE_BRACKETS, i);
		}
		public ArrayElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElemContext arrayElem() throws RecognitionException {
		ArrayElemContext _localctx = new ArrayElemContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			match(IDENT);
			setState(334); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(330);
					match(OPEN_SQUARE_BRACKETS);
					setState(331);
					expr(0);
					setState(332);
					match(CLOSE_SQUARE_BRACKETS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(336); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntLiterContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(BasicParser.INTEGER, 0); }
		public TerminalNode ADD() { return getToken(BasicParser.ADD, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public IntLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIntLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntLiterContext intLiter() throws RecognitionException {
		IntLiterContext _localctx = new IntLiterContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_intLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS || _la==ADD) {
				{
				setState(338);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==ADD) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(341);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolLiterContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(BasicParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(BasicParser.FALSE, 0); }
		public BoolLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBoolLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiterContext boolLiter() throws RecognitionException {
		BoolLiterContext _localctx = new BoolLiterContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_boolLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharLiterContext extends ParserRuleContext {
		public TerminalNode CHAR_LITER() { return getToken(BasicParser.CHAR_LITER, 0); }
		public CharLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitCharLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharLiterContext charLiter() throws RecognitionException {
		CharLiterContext _localctx = new CharLiterContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_charLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			match(CHAR_LITER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrLiterContext extends ParserRuleContext {
		public TerminalNode STR_LITER() { return getToken(BasicParser.STR_LITER, 0); }
		public StrLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStrLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrLiterContext strLiter() throws RecognitionException {
		StrLiterContext _localctx = new StrLiterContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_strLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			match(STR_LITER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiterContext extends ParserRuleContext {
		public TerminalNode OPEN_SQUARE_BRACKETS() { return getToken(BasicParser.OPEN_SQUARE_BRACKETS, 0); }
		public TerminalNode CLOSE_SQUARE_BRACKETS() { return getToken(BasicParser.CLOSE_SQUARE_BRACKETS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ArrayLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiterContext arrayLiter() throws RecognitionException {
		ArrayLiterContext _localctx = new ArrayLiterContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			match(OPEN_SQUARE_BRACKETS);
			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_BRACKETS) | (1L << NOT) | (1L << MINUS) | (1L << LENGTH) | (1L << ORDINAL) | (1L << CHR) | (1L << COMPLEMENT) | (1L << ADD))) != 0) || ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (NULL - 71)) | (1L << (TRUE - 71)) | (1L << (FALSE - 71)) | (1L << (IDENT - 71)) | (1L << (INTEGER - 71)) | (1L << (CHAR_LITER - 71)) | (1L << (STR_LITER - 71)))) != 0)) {
				{
				setState(350);
				expr(0);
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(351);
					match(COMMA);
					setState(352);
					expr(0);
					}
					}
					setState(357);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(360);
			match(CLOSE_SQUARE_BRACKETS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairLiterContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(BasicParser.NULL, 0); }
		public PairLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairLiterContext pairLiter() throws RecognitionException {
		PairLiterContext _localctx = new PairLiterContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_pairLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(NULL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode PUBLIC() { return getToken(BasicParser.PUBLIC, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUBLIC) {
				{
				setState(364);
				match(PUBLIC);
				}
			}

			setState(367);
			type();
			setState(368);
			match(IDENT);
			setState(369);
			match(COMMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public TerminalNode OPEN_CURLY_BRACKET() { return getToken(BasicParser.OPEN_CURLY_BRACKET, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode CLOSE_CURLY_BRACKET() { return getToken(BasicParser.CLOSE_CURLY_BRACKET, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public ConstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitConstructor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorContext constructor() throws RecognitionException {
		ConstructorContext _localctx = new ConstructorContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constructor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			match(IDENT);
			setState(372);
			match(OPEN_BRACKETS);
			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & ((1L << (VOID - 32)) | (1L << (INT - 32)) | (1L << (BOOL - 32)) | (1L << (CHAR - 32)) | (1L << (STRING - 32)) | (1L << (PAIR - 32)) | (1L << (IDENT - 32)))) != 0)) {
				{
				setState(373);
				paramList();
				}
			}

			setState(376);
			match(CLOSE_BRACKETS);
			setState(377);
			match(OPEN_CURLY_BRACKET);
			setState(378);
			stat(0);
			setState(379);
			match(CLOSE_CURLY_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewclassContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(BasicParser.CLASS, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_CURLY_BRACKET() { return getToken(BasicParser.OPEN_CURLY_BRACKET, 0); }
		public TerminalNode CLOSE_CURLY_BRACKET() { return getToken(BasicParser.CLOSE_CURLY_BRACKET, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<ConstructorContext> constructor() {
			return getRuleContexts(ConstructorContext.class);
		}
		public ConstructorContext constructor(int i) {
			return getRuleContext(ConstructorContext.class,i);
		}
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public NewclassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newclass; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitNewclass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewclassContext newclass() throws RecognitionException {
		NewclassContext _localctx = new NewclassContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_newclass);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			match(CLASS);
			setState(382);
			match(IDENT);
			setState(383);
			match(OPEN_CURLY_BRACKET);
			setState(387);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(384);
					attribute();
					}
					} 
				}
				setState(389);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			setState(393);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(390);
					constructor();
					}
					} 
				}
				setState(395);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			setState(399);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & ((1L << (VOID - 32)) | (1L << (INT - 32)) | (1L << (BOOL - 32)) | (1L << (CHAR - 32)) | (1L << (STRING - 32)) | (1L << (PAIR - 32)) | (1L << (PUBLIC - 32)) | (1L << (IDENT - 32)))) != 0)) {
				{
				{
				setState(396);
				method();
				}
				}
				setState(401);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(402);
			match(CLOSE_CURLY_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodContext extends ParserRuleContext {
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(BasicParser.PUBLIC, 0); }
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_method);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUBLIC) {
				{
				setState(404);
				match(PUBLIC);
				}
			}

			setState(407);
			func();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return stat_sempred((StatContext)_localctx, predIndex);
		case 12:
			return arrayType_sempred((ArrayTypeContext)_localctx, predIndex);
		case 16:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean arrayType_sempred(ArrayTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 6);
		case 8:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3O\u019c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\7\2?\n\2\f"+
		"\2\16\2B\13\2\3\2\7\2E\n\2\f\2\16\2H\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\5\3R\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4\\\n\4\f\4\16\4_\13\4"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00a3\n\6\3"+
		"\6\3\6\3\6\6\6\u00a8\n\6\r\6\16\6\u00a9\5\6\u00ac\n\6\3\6\3\6\3\6\7\6"+
		"\u00b1\n\6\f\6\16\6\u00b4\13\6\3\7\3\7\7\7\u00b8\n\7\f\7\16\7\u00bb\13"+
		"\7\3\7\3\7\5\7\u00bf\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\5\b\u00cf\n\b\3\b\3\b\3\b\6\b\u00d4\n\b\r\b\16\b\u00d5\3\b"+
		"\3\b\3\b\3\b\5\b\u00dc\n\b\3\b\5\b\u00df\n\b\3\t\3\t\3\t\3\t\5\t\u00e5"+
		"\n\t\3\t\3\t\3\t\5\t\u00ea\n\t\3\n\3\n\3\n\7\n\u00ef\n\n\f\n\16\n\u00f2"+
		"\13\n\3\13\3\13\3\13\3\13\5\13\u00f8\n\13\3\f\3\f\3\f\3\f\5\f\u00fe\n"+
		"\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u010a\n\16\f\16"+
		"\16\16\u010d\13\16\3\17\3\17\5\17\u0111\n\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\5\21\u011d\n\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u012e\n\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\7\22\u0145\n\22\f\22\16\22\u0148\13\22\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\6\24\u0151\n\24\r\24\16\24\u0152\3\25\5"+
		"\25\u0156\n\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\7\31\u0164\n\31\f\31\16\31\u0167\13\31\5\31\u0169\n\31\3\31\3\31"+
		"\3\32\3\32\3\33\5\33\u0170\n\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\5\34"+
		"\u0179\n\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\7\35\u0184\n"+
		"\35\f\35\16\35\u0187\13\35\3\35\7\35\u018a\n\35\f\35\16\35\u018d\13\35"+
		"\3\35\7\35\u0190\n\35\f\35\16\35\u0193\13\35\3\35\3\35\3\36\5\36\u0198"+
		"\n\36\3\36\3\36\3\36\2\5\n\32\"\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:\2\13\4\2..LL\3\2\"&\3\2\679\4\2\62\62::\3\2"+
		"CG\3\2;>\3\2?@\3\2\61\66\3\2JK\2\u01c6\2<\3\2\2\2\4M\3\2\2\2\6X\3\2\2"+
		"\2\b`\3\2\2\2\n\u00ab\3\2\2\2\f\u00be\3\2\2\2\16\u00de\3\2\2\2\20\u00e9"+
		"\3\2\2\2\22\u00eb\3\2\2\2\24\u00f7\3\2\2\2\26\u00fd\3\2\2\2\30\u00ff\3"+
		"\2\2\2\32\u0101\3\2\2\2\34\u0110\3\2\2\2\36\u0112\3\2\2\2 \u011c\3\2\2"+
		"\2\"\u012d\3\2\2\2$\u0149\3\2\2\2&\u014b\3\2\2\2(\u0155\3\2\2\2*\u0159"+
		"\3\2\2\2,\u015b\3\2\2\2.\u015d\3\2\2\2\60\u015f\3\2\2\2\62\u016c\3\2\2"+
		"\2\64\u016f\3\2\2\2\66\u0175\3\2\2\28\u017f\3\2\2\2:\u0197\3\2\2\2<@\7"+
		"\4\2\2=?\58\35\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AF\3\2\2\2B@\3"+
		"\2\2\2CE\5\4\3\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3"+
		"\2\2\2IJ\5\n\6\2JK\7\5\2\2KL\7\2\2\3L\3\3\2\2\2MN\5\26\f\2NO\7L\2\2OQ"+
		"\7\6\2\2PR\5\6\4\2QP\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST\7\7\2\2TU\7\b\2\2U"+
		"V\5\n\6\2VW\7\5\2\2W\5\3\2\2\2X]\5\b\5\2YZ\7\t\2\2Z\\\5\b\5\2[Y\3\2\2"+
		"\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^\7\3\2\2\2_]\3\2\2\2`a\5\26\f\2ab\7"+
		"L\2\2b\t\3\2\2\2cd\b\6\1\2d\u00ac\7\n\2\2ef\5\26\f\2fg\7L\2\2gh\7\13\2"+
		"\2hi\5\16\b\2i\u00ac\3\2\2\2jk\5\f\7\2kl\7\13\2\2lm\5\16\b\2m\u00ac\3"+
		"\2\2\2no\7\f\2\2o\u00ac\5\f\7\2pq\7\r\2\2q\u00ac\5\"\22\2rs\7\17\2\2s"+
		"\u00ac\5\"\22\2tu\7\16\2\2u\u00ac\5\"\22\2vw\7\20\2\2w\u00ac\5\"\22\2"+
		"xy\7\21\2\2y\u00ac\5\"\22\2z{\7\22\2\2{|\5\"\22\2|}\7\23\2\2}~\5\n\6\2"+
		"~\177\7\24\2\2\177\u0080\5\n\6\2\u0080\u0081\7\25\2\2\u0081\u00ac\3\2"+
		"\2\2\u0082\u0083\7\26\2\2\u0083\u0084\5\"\22\2\u0084\u0085\7\27\2\2\u0085"+
		"\u0086\5\n\6\2\u0086\u0087\7\30\2\2\u0087\u00ac\3\2\2\2\u0088\u0089\7"+
		"\31\2\2\u0089\u008a\7\6\2\2\u008a\u008b\5\n\6\2\u008b\u008c\7\t\2\2\u008c"+
		"\u008d\5\"\22\2\u008d\u008e\7\t\2\2\u008e\u008f\5\n\6\2\u008f\u0090\7"+
		"\7\2\2\u0090\u0091\5\n\6\2\u0091\u0092\7\30\2\2\u0092\u00ac\3\2\2\2\u0093"+
		"\u00ac\7\32\2\2\u0094\u00ac\7\33\2\2\u0095\u0096\7\27\2\2\u0096\u0097"+
		"\5\n\6\2\u0097\u0098\7\34\2\2\u0098\u0099\5\"\22\2\u0099\u00ac\3\2\2\2"+
		"\u009a\u009b\7\4\2\2\u009b\u009c\5\n\6\2\u009c\u009d\7\5\2\2\u009d\u00ac"+
		"\3\2\2\2\u009e\u009f\7\37\2\2\u009f\u00a0\7L\2\2\u00a0\u00a2\7\6\2\2\u00a1"+
		"\u00a3\5\22\n\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3"+
		"\2\2\2\u00a4\u00ac\7\7\2\2\u00a5\u00a7\t\2\2\2\u00a6\u00a8\5\20\t\2\u00a7"+
		"\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2"+
		"\2\2\u00aa\u00ac\3\2\2\2\u00abc\3\2\2\2\u00abe\3\2\2\2\u00abj\3\2\2\2"+
		"\u00abn\3\2\2\2\u00abp\3\2\2\2\u00abr\3\2\2\2\u00abt\3\2\2\2\u00abv\3"+
		"\2\2\2\u00abx\3\2\2\2\u00abz\3\2\2\2\u00ab\u0082\3\2\2\2\u00ab\u0088\3"+
		"\2\2\2\u00ab\u0093\3\2\2\2\u00ab\u0094\3\2\2\2\u00ab\u0095\3\2\2\2\u00ab"+
		"\u009a\3\2\2\2\u00ab\u009e\3\2\2\2\u00ab\u00a5\3\2\2\2\u00ac\u00b2\3\2"+
		"\2\2\u00ad\u00ae\f\3\2\2\u00ae\u00af\7\35\2\2\u00af\u00b1\5\n\6\4\u00b0"+
		"\u00ad\3\2\2\2\u00b1\u00b4\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2"+
		"\2\2\u00b3\13\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b9\t\2\2\2\u00b6\u00b8"+
		"\5\20\t\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2"+
		"\u00b9\u00ba\3\2\2\2\u00ba\u00bf\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bf"+
		"\5&\24\2\u00bd\u00bf\5\24\13\2\u00be\u00b5\3\2\2\2\u00be\u00bc\3\2\2\2"+
		"\u00be\u00bd\3\2\2\2\u00bf\r\3\2\2\2\u00c0\u00df\5\"\22\2\u00c1\u00df"+
		"\5\60\31\2\u00c2\u00c3\7\36\2\2\u00c3\u00c4\7\6\2\2\u00c4\u00c5\5\"\22"+
		"\2\u00c5\u00c6\7\t\2\2\u00c6\u00c7\5\"\22\2\u00c7\u00c8\7\7\2\2\u00c8"+
		"\u00df\3\2\2\2\u00c9\u00df\5\24\13\2\u00ca\u00cb\7\37\2\2\u00cb\u00cc"+
		"\7L\2\2\u00cc\u00ce\7\6\2\2\u00cd\u00cf\5\22\n\2\u00ce\u00cd\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00df\7\7\2\2\u00d1\u00d3\t\2"+
		"\2\2\u00d2\u00d4\5\20\t\2\u00d3\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00df\3\2\2\2\u00d7\u00d8\7\60"+
		"\2\2\u00d8\u00d9\7L\2\2\u00d9\u00db\7\6\2\2\u00da\u00dc\5\22\n\2\u00db"+
		"\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\7\7"+
		"\2\2\u00de\u00c0\3\2\2\2\u00de\u00c1\3\2\2\2\u00de\u00c2\3\2\2\2\u00de"+
		"\u00c9\3\2\2\2\u00de\u00ca\3\2\2\2\u00de\u00d1\3\2\2\2\u00de\u00d7\3\2"+
		"\2\2\u00df\17\3\2\2\2\u00e0\u00e1\7/\2\2\u00e1\u00e2\7L\2\2\u00e2\u00e4"+
		"\7\6\2\2\u00e3\u00e5\5\22\n\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2"+
		"\u00e5\u00e6\3\2\2\2\u00e6\u00ea\7\7\2\2\u00e7\u00e8\7/\2\2\u00e8\u00ea"+
		"\7L\2\2\u00e9\u00e0\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\21\3\2\2\2\u00eb"+
		"\u00f0\5\"\22\2\u00ec\u00ed\7\t\2\2\u00ed\u00ef\5\"\22\2\u00ee\u00ec\3"+
		"\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\23\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f4\7 \2\2\u00f4\u00f8\5\"\22"+
		"\2\u00f5\u00f6\7!\2\2\u00f6\u00f8\5\"\22\2\u00f7\u00f3\3\2\2\2\u00f7\u00f5"+
		"\3\2\2\2\u00f8\25\3\2\2\2\u00f9\u00fe\5\30\r\2\u00fa\u00fe\5\32\16\2\u00fb"+
		"\u00fe\5\36\20\2\u00fc\u00fe\7L\2\2\u00fd\u00f9\3\2\2\2\u00fd\u00fa\3"+
		"\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fc\3\2\2\2\u00fe\27\3\2\2\2\u00ff"+
		"\u0100\t\3\2\2\u0100\31\3\2\2\2\u0101\u0102\b\16\1\2\u0102\u0103\5\34"+
		"\17\2\u0103\u0104\7\'\2\2\u0104\u0105\7(\2\2\u0105\u010b\3\2\2\2\u0106"+
		"\u0107\f\3\2\2\u0107\u0108\7\'\2\2\u0108\u010a\7(\2\2\u0109\u0106\3\2"+
		"\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c"+
		"\33\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u0111\5\30\r\2\u010f\u0111\5\36"+
		"\20\2\u0110\u010e\3\2\2\2\u0110\u010f\3\2\2\2\u0111\35\3\2\2\2\u0112\u0113"+
		"\7)\2\2\u0113\u0114\7\6\2\2\u0114\u0115\5 \21\2\u0115\u0116\7\t\2\2\u0116"+
		"\u0117\5 \21\2\u0117\u0118\7\7\2\2\u0118\37\3\2\2\2\u0119\u011d\5\30\r"+
		"\2\u011a\u011d\5\32\16\2\u011b\u011d\7)\2\2\u011c\u0119\3\2\2\2\u011c"+
		"\u011a\3\2\2\2\u011c\u011b\3\2\2\2\u011d!\3\2\2\2\u011e\u011f\b\22\1\2"+
		"\u011f\u012e\5.\30\2\u0120\u012e\5*\26\2\u0121\u012e\5,\27\2\u0122\u012e"+
		"\5(\25\2\u0123\u012e\5\62\32\2\u0124\u0125\5$\23\2\u0125\u0126\5\"\22"+
		"\6\u0126\u012e\3\2\2\2\u0127\u012e\7L\2\2\u0128\u012e\5&\24\2\u0129\u012a"+
		"\7\6\2\2\u012a\u012b\5\"\22\2\u012b\u012c\7\7\2\2\u012c\u012e\3\2\2\2"+
		"\u012d\u011e\3\2\2\2\u012d\u0120\3\2\2\2\u012d\u0121\3\2\2\2\u012d\u0122"+
		"\3\2\2\2\u012d\u0123\3\2\2\2\u012d\u0124\3\2\2\2\u012d\u0127\3\2\2\2\u012d"+
		"\u0128\3\2\2\2\u012d\u0129\3\2\2\2\u012e\u0146\3\2\2\2\u012f\u0130\f\r"+
		"\2\2\u0130\u0131\t\4\2\2\u0131\u0145\5\"\22\16\u0132\u0133\f\f\2\2\u0133"+
		"\u0134\t\5\2\2\u0134\u0145\5\"\22\r\u0135\u0136\f\13\2\2\u0136\u0137\t"+
		"\6\2\2\u0137\u0145\5\"\22\f\u0138\u0139\f\n\2\2\u0139\u013a\t\7\2\2\u013a"+
		"\u0145\5\"\22\13\u013b\u013c\f\t\2\2\u013c\u013d\t\b\2\2\u013d\u0145\5"+
		"\"\22\n\u013e\u013f\f\b\2\2\u013f\u0140\7A\2\2\u0140\u0145\5\"\22\t\u0141"+
		"\u0142\f\7\2\2\u0142\u0143\7B\2\2\u0143\u0145\5\"\22\b\u0144\u012f\3\2"+
		"\2\2\u0144\u0132\3\2\2\2\u0144\u0135\3\2\2\2\u0144\u0138\3\2\2\2\u0144"+
		"\u013b\3\2\2\2\u0144\u013e\3\2\2\2\u0144\u0141\3\2\2\2\u0145\u0148\3\2"+
		"\2\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147#\3\2\2\2\u0148\u0146"+
		"\3\2\2\2\u0149\u014a\t\t\2\2\u014a%\3\2\2\2\u014b\u0150\7L\2\2\u014c\u014d"+
		"\7\'\2\2\u014d\u014e\5\"\22\2\u014e\u014f\7(\2\2\u014f\u0151\3\2\2\2\u0150"+
		"\u014c\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2"+
		"\2\2\u0153\'\3\2\2\2\u0154\u0156\t\5\2\2\u0155\u0154\3\2\2\2\u0155\u0156"+
		"\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0158\7M\2\2\u0158)\3\2\2\2\u0159\u015a"+
		"\t\n\2\2\u015a+\3\2\2\2\u015b\u015c\7N\2\2\u015c-\3\2\2\2\u015d\u015e"+
		"\7O\2\2\u015e/\3\2\2\2\u015f\u0168\7\'\2\2\u0160\u0165\5\"\22\2\u0161"+
		"\u0162\7\t\2\2\u0162\u0164\5\"\22\2\u0163\u0161\3\2\2\2\u0164\u0167\3"+
		"\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0169\3\2\2\2\u0167"+
		"\u0165\3\2\2\2\u0168\u0160\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016a\3\2"+
		"\2\2\u016a\u016b\7(\2\2\u016b\61\3\2\2\2\u016c\u016d\7I\2\2\u016d\63\3"+
		"\2\2\2\u016e\u0170\7-\2\2\u016f\u016e\3\2\2\2\u016f\u0170\3\2\2\2\u0170"+
		"\u0171\3\2\2\2\u0171\u0172\5\26\f\2\u0172\u0173\7L\2\2\u0173\u0174\7\t"+
		"\2\2\u0174\65\3\2\2\2\u0175\u0176\7L\2\2\u0176\u0178\7\6\2\2\u0177\u0179"+
		"\5\6\4\2\u0178\u0177\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017a\3\2\2\2\u017a"+
		"\u017b\7\7\2\2\u017b\u017c\7+\2\2\u017c\u017d\5\n\6\2\u017d\u017e\7,\2"+
		"\2\u017e\67\3\2\2\2\u017f\u0180\7*\2\2\u0180\u0181\7L\2\2\u0181\u0185"+
		"\7+\2\2\u0182\u0184\5\64\33\2\u0183\u0182\3\2\2\2\u0184\u0187\3\2\2\2"+
		"\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u018b\3\2\2\2\u0187\u0185"+
		"\3\2\2\2\u0188\u018a\5\66\34\2\u0189\u0188\3\2\2\2\u018a\u018d\3\2\2\2"+
		"\u018b\u0189\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u0191\3\2\2\2\u018d\u018b"+
		"\3\2\2\2\u018e\u0190\5:\36\2\u018f\u018e\3\2\2\2\u0190\u0193\3\2\2\2\u0191"+
		"\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0194\3\2\2\2\u0193\u0191\3\2"+
		"\2\2\u0194\u0195\7,\2\2\u01959\3\2\2\2\u0196\u0198\7-\2\2\u0197\u0196"+
		"\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019a\5\4\3\2\u019a"+
		";\3\2\2\2%@FQ]\u00a2\u00a9\u00ab\u00b2\u00b9\u00be\u00ce\u00d5\u00db\u00de"+
		"\u00e4\u00e9\u00f0\u00f7\u00fd\u010b\u0110\u011c\u012d\u0144\u0146\u0152"+
		"\u0155\u0165\u0168\u016f\u0178\u0185\u018b\u0191\u0197";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}