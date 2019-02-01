/* EsiParser.java */
/* Generated By:JavaCC: Do not edit this line. EsiParser.java */
import java.io.PrintStream ;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.*;

class EsiParser implements EsiParserConstants {
    public static void main(String[] args) throws ParseException, TokenMgrError {
        //Reader reader = new StringReader(args[0]);
        EsiParser parser = new EsiParser(System.in);
        parser.Start(System.out);
    }
    String output;
    HashMap<String, Composant> composants = new HashMap<String, Composant>();
    AttributFactory attFactory = new AttributFactory();

  final public void Start(PrintStream printStream) throws ParseException {
    Interface();
    Inits();
    Actions();
    jj_consume_token(FIN);
System.out.println("Compilation ended with success.");
  }

  final public String ReadVal(Token t) throws ParseException {
{if ("" != null) return String.valueOf(t.image);}
    throw new Error("Missing return statement in function");
  }

  final public void Interface() throws ParseException {Composant comp = null;
    jj_consume_token(DEBUT);
    jj_consume_token(INTERFACE);
    label_1:
    while (true) {
      comp = Comp();
      jj_consume_token(PROP);
      Prop(comp);
      jj_consume_token(EVET);
      Evet(comp);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMP:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
    }
  }

  final public void Inits() throws ParseException {Token t;
    String comp, prop, val;
    jj_consume_token(INIT);
    label_2:
    while (true) {
      t = jj_consume_token(ID);
comp=ReadVal(t);
      jj_consume_token(DOUBLEDOUBLEDOT);
      t = jj_consume_token(ID);
prop=ReadVal(t);
      jj_consume_token(ASSIGN);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ID:{
        jj_consume_token(ID);
        break;
        }
      case NUMBER:{
        jj_consume_token(NUMBER);
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(SEMICOLON);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ID:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
    }
  }

  final public void Actions() throws ParseException {
    jj_consume_token(ACT);
    label_3:
    while (true) {
      Expression();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OPENTAG:
      case ACTION:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
    }
  }

  final public Composant Comp() throws ParseException {Token t;
    String id, id2, com;
    Composant comp = null;
    jj_consume_token(COMP);
    t = jj_consume_token(ID);
id = ReadVal(t);
    jj_consume_token(DOUBLEDOT);
    t = jj_consume_token(COMPONENT);
com = ReadVal(t);
        //composant name unique
        if (!composants.containsKey(id)) {
            comp = new Composant(id, com);
            composants.put(id, comp);
        }
        else {
            System.out.println("Error: Le nom du composant doit etre unique.");
            System.exit(0);
        }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case OPENPAR:{
      jj_consume_token(OPENPAR);
      t = jj_consume_token(ID);
      jj_consume_token(CLOSEPAR);
id2 = ReadVal(t);
            //Checks if propriatary composant exists and is different than created composant
            if (composants.containsKey(id2) && id2!=comp.getName()) {
            Composant origComp = composants.get(id2);
            origComp.addComposant(id, comp);
            }
            else{
                System.out.println("Error: Composant proprietaire indefini.");
                System.exit(0);
            }
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
{if ("" != null) return comp;}
    throw new Error("Missing return statement in function");
  }

  final public void Prop(Composant comp) throws ParseException {Token t;
    int N = 20;
    String id[] = new String[N];
    String type[] = new String[N];
    int i = 0, j = 0;
    label_4:
    while (true) {
      /*ASSEMBLE ATT NAMES*/
                  t = jj_consume_token(ID);
id[i++]=ReadVal(t);
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[5] = jj_gen;
          break label_5;
        }
        jj_consume_token(COMMA);
        t = jj_consume_token(ID);
id[i++]=ReadVal(t);
      }
      jj_consume_token(DOUBLEDOT);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ID:{
        /*CASE OF : 1 KNOWN TYPE*/
                    t = jj_consume_token(ID);
type[0]=ReadVal(t);
                Attribut att = null;
                for(int c1=0 ; c1<i ; c1++){
                    att = attFactory.createAttribut(id[c1],type[0]);
                    if(att != null){
                        comp.addAttribut(id[c1], att);
                    }
                    else{
                        System.out.println("Error: Type attribut indefini.");
                        System.exit(0);
                    }
                }
        break;
        }
      case OPENTAG:{
        jj_consume_token(OPENTAG);
        t = jj_consume_token(ID);
type[j++] = ReadVal(t);
        label_6:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case COMMA:{
            ;
            break;
            }
          default:
            jj_la1[6] = jj_gen;
            break label_6;
          }
          jj_consume_token(COMMA);
          t = jj_consume_token(ID);
type[j++] = ReadVal(t);
        }
        jj_consume_token(CLOSETAG);
Attribut att = null;
                for(int c1=0 ; c1<i ; c1++){
                    att = attFactory.createAttribut(id[c1],"list");
                    if(att != null){
                        List attl = (List)att;
                        attl.setValues(new HashSet<String>(Arrays.asList(type)));
                        comp.addAttribut(id[c1], attl);
                    }
                    else{
                        System.out.println("Error: Type attribut indefini.");
                        System.exit(0);
                    }
                }
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(SEMICOLON);
i = 0; j = 0;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ID:{
        ;
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        break label_4;
      }
    }
  }

  final public void Evet(Composant comp) throws ParseException {Token t;
    String act, att1, op, att2, att, aff;
    Attribut attr = null, attr1 = null;
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ACTION:
      case ID:{
        ;
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        break label_7;
      }
      if (jj_2_1(2)) {
        t = jj_consume_token(ACTION);
act=ReadVal(t);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case OPENPAR:{
          jj_consume_token(OPENPAR);
          jj_consume_token(ID);
          jj_consume_token(CLOSEPAR);
          break;
          }
        default:
          jj_la1[10] = jj_gen;
          ;
        }
        jj_consume_token(OPENTAG);
        jj_consume_token(IF);
        t = jj_consume_token(ID);
att1=ReadVal(t);
            attr1 = getAttribut(comp, att1);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case EQUAL:{
          t = jj_consume_token(EQUAL);
          break;
          }
        case NOTEQUAL:{
          t = jj_consume_token(NOTEQUAL);
          break;
          }
        default:
          jj_la1[11] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
op=ReadVal(t);
        t = jj_consume_token(ID);
att2=ReadVal(t);
        jj_consume_token(THEN);
        t = jj_consume_token(ID);
att=ReadVal(t);
            attr = getAttribut(comp, att);
        jj_consume_token(ASSIGN);
        t = jj_consume_token(ID);
aff=ReadVal(t);
        label_8:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case PLUS:
          case OPERATION:{
            ;
            break;
            }
          default:
            jj_la1[12] = jj_gen;
            break label_8;
          }
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case PLUS:{
            jj_consume_token(PLUS);
            break;
            }
          case OPERATION:{
            jj_consume_token(OPERATION);
            break;
            }
          default:
            jj_la1[13] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case ID:{
            jj_consume_token(ID);
            break;
            }
          case NUMBER:{
            jj_consume_token(NUMBER);
            break;
            }
          default:
            jj_la1[14] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
        jj_consume_token(CLOSETAG);
Condition cond = new Condition(attr1, op, att2);
            Action action = new Action(act, cond, attr, aff);
            comp.addAction(act, action);
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case ID:{
          t = jj_consume_token(ID);
att=ReadVal(t);
          jj_consume_token(ASSIGN);
          t = jj_consume_token(ID);
aff=ReadVal(t); Assign(comp,att,aff);
          break;
          }
        default:
          jj_la1[15] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

  final public Attribut getAttribut(Composant comp, String att) throws ParseException {
if (comp.attExists(att)) {if ("" != null) return comp.getAttribut(att);}
        else {
            System.out.println("Erreur: Attribut indefini.");
            System.exit(0);
            {if ("" != null) return null;}
        }
    throw new Error("Missing return statement in function");
  }

  final public void Assign(Composant comp, String att, String aff) throws ParseException {Attribut attr = getAttribut(comp, att);
attr.setValue(aff);
  }

  final public void Expression() throws ParseException {Token t;
    String act, comp, next;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ACTION:{
      jj_consume_token(ACTION);
      jj_consume_token(OPENPAR);
      jj_consume_token(ID);
      jj_consume_token(CLOSEPAR);
      break;
      }
    case OPENTAG:{
      jj_consume_token(OPENTAG);
      Expression();
      jj_consume_token(CLOSETAG);
      break;
      }
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_9:
    while (true) {
      if (jj_2_2(2)) {
        ;
      } else {
        break label_9;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SEMICOLON:{
        jj_consume_token(SEMICOLON);
        break;
        }
      case NEXTACTION:{
        jj_consume_token(NEXTACTION);
        break;
        }
      case PLUS:{
        jj_consume_token(PLUS);
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      Expression();
    }
  }

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_3R_11()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    return false;
  }

  private boolean jj_3R_13()
 {
    if (jj_scan_token(OPENTAG)) return true;
    return false;
  }

  private boolean jj_3_2()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(7)) {
    jj_scanpos = xsp;
    if (jj_scan_token(27)) {
    jj_scanpos = xsp;
    if (jj_scan_token(25)) return true;
    }
    }
    if (jj_3R_11()) return true;
    return false;
  }

  private boolean jj_3R_12()
 {
    if (jj_scan_token(ACTION)) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_scan_token(ACTION)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_10()) jj_scanpos = xsp;
    if (jj_scan_token(OPENTAG)) return true;
    return false;
  }

  private boolean jj_3R_10()
 {
    if (jj_scan_token(OPENPAR)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public EsiParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[18];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x10000000,0x40,0x0,0x4002000,0x8000,0x20000,0x20000,0x2000,0x0,0x4000000,0x8000,0x600,0x22000000,0x22000000,0x40,0x0,0x4002000,0xa000080,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x2,0x2,0x0,0x0,0x0,0x0,0x2,0x2,0x2,0x0,0x0,0x0,0x0,0x2,0x2,0x0,0x0,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[2];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public EsiParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public EsiParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new EsiParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public EsiParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new EsiParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public EsiParser(EsiParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(EsiParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[34];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 18; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 34; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 2; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
