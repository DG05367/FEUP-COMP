/* Generated by: JavaCC 21 Parser Generator. Token.java */
package pt.up.fe.comp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
public class Token implements JmmGrammarConstants, Node {
    private TokenType type;
    private JmmGrammarLexer tokenSource;
    private int beginOffset, endOffset;
    private boolean unparsed;
    private Node parent;
    private String image;
    public void setImage(String image) {
        this.image= image;
    }

    private Token prependedToken, appendedToken;
    private boolean inserted;
    public boolean isInserted() {
        return inserted;
    }

    public void preInsert(Token prependedToken) {
        if (prependedToken== this.prependedToken) return;
        prependedToken.appendedToken= this;
        Token existingPreviousToken= this.previousCachedToken();
        if (existingPreviousToken!=null) {
            existingPreviousToken.appendedToken= prependedToken;
            prependedToken.prependedToken= existingPreviousToken;
        }
        prependedToken.inserted= true;
        prependedToken.beginOffset= prependedToken.endOffset= this.beginOffset;
        this.prependedToken= prependedToken;
    }

    void unsetAppendedToken() {
        this.appendedToken= null;
    }

    /**
     * @param type the #TokenType of the token being constructed
     * @param image the String content of the token
     * @param tokenSource the object that vended this token.
     */
    public Token(TokenType type, String image, JmmGrammarLexer tokenSource) {
        this.type= type;
        this.image= image;
        this.tokenSource= tokenSource;
    }

    public static Token newToken(TokenType type, String image, JmmGrammarLexer tokenSource) {
        Token result= newToken(type, tokenSource, 0, 0);
        result.setImage(image);
        return result;
    }

    /**
     * It would be extremely rare that an application
     * programmer would use this method. It needs to
     * be public because it is part of the pt.up.fe.comp.Node interface.
     */
    public void setBeginOffset(int beginOffset) {
        this.beginOffset= beginOffset;
    }

    /**
     * It would be extremely rare that an application
     * programmer would use this method. It needs to
     * be public because it is part of the pt.up.fe.comp.Node interface.
     */
    public void setEndOffset(int endOffset) {
        this.endOffset= endOffset;
    }

    /**
     * @return the JmmGrammarLexer object that handles 
     * location info for the tokens. 
     */
    public JmmGrammarLexer getTokenSource() {
        JmmGrammarLexer flm= this.tokenSource;
        // If this is null and we have chained tokens,
        // we try to get it from there! (Why not?)
        if (flm== null) {
            if (prependedToken!=null) {
                flm= prependedToken.getTokenSource();
            }
            if (flm== null&&appendedToken!=null) {
                flm= appendedToken.getTokenSource();
            }
        }
        return flm;
    }

    /**
     * It should be exceedingly rare that an application
     * programmer needs to use this method.
     */
    public void setTokenSource(JmmGrammarLexer tokenSource) {
        this.tokenSource= tokenSource;
    }

    /**
     * Return the TokenType of this Token object
     */
    public TokenType getType() {
        return type;
    }

    protected void setType(TokenType type) {
        this.type= type;
    }

    /**
     * @return whether this Token represent actual input or was it inserted somehow?
     */
    public boolean isVirtual() {
        return type== TokenType.EOF;
    }

    /**
     * @return Did we skip this token in parsing?
     */
    public boolean isSkipped() {
        return false;
    }

    public int getBeginOffset() {
        return beginOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }

    /**
     * @return the string image of the token.
     */
    public String getImage() {
        return image!=null?image:
        getSource();
    }

    /**
     * @return the next _cached_ regular (i.e. parsed) token
     * or null
     */
    public final Token getNext() {
        return getNextParsedToken();
    }

    /**
     * @return the previous regular (i.e. parsed) token
     * or null
     */
    public final Token getPrevious() {
        Token result= previousCachedToken();
        while (result!=null&&result.isUnparsed()) {
            result= result.previousCachedToken();
        }
        return result;
    }

    /**
     * @return the next regular (i.e. parsed) token
     */
    private Token getNextParsedToken() {
        Token result= nextCachedToken();
        while (result!=null&&result.isUnparsed()) {
            result= result.nextCachedToken();
        }
        return result;
    }

    /**
     * @return the next token of any sort (parsed or unparsed or invalid)
     */
    public Token nextCachedToken() {
        if (appendedToken!=null) return appendedToken;
        JmmGrammarLexer tokenSource= getTokenSource();
        return tokenSource!=null?tokenSource.nextCachedToken(getEndOffset()):
        null;
    }

    public Token previousCachedToken() {
        if (prependedToken!=null) return prependedToken;
        if (getTokenSource()== null) return null;
        return getTokenSource().previousCachedToken(getBeginOffset());
    }

    Token getPreviousToken() {
        return previousCachedToken();
    }

    public Token replaceType(TokenType type) {
        Token result= newToken(type, getTokenSource(), getBeginOffset(), getEndOffset());
        result.prependedToken= this.prependedToken;
        result.appendedToken= this.appendedToken;
        result.inserted= this.inserted;
        if (result.appendedToken!=null) {
            result.appendedToken.prependedToken= result;
        }
        if (result.prependedToken!=null) {
            result.prependedToken.appendedToken= result;
        }
        if (!result.inserted) {
            getTokenSource().cacheToken(result);
        }
        return result;
    }

    public String getSource() {
        if (type== TokenType.EOF) return"";
        JmmGrammarLexer flm= getTokenSource();
        return flm== null?null:
        flm.getText(getBeginOffset(), getEndOffset());
    }

    protected Token() {
    }

    public Token(TokenType type, JmmGrammarLexer tokenSource, int beginOffset, int endOffset) {
        this.type= type;
        this.tokenSource= tokenSource;
        this.beginOffset= beginOffset;
        this.endOffset= endOffset;
    }

    public boolean isUnparsed() {
        return unparsed;
    }

    public void setUnparsed(boolean unparsed) {
        this.unparsed= unparsed;
    }

    public void clearChildren() {
    }

    public String getNormalizedText() {
        if (getType()== TokenType.EOF) {
            return"EOF";
        }
        return getImage();
    }

    public String toString() {
        return getNormalizedText();
    }

    /**
     * @return An iterator of the tokens preceding this one.
     */
    public Iterator<Token> precedingTokens() {
        return new Iterator<Token> () {
            Token currentPoint= Token.this;
            public boolean hasNext() {
                return currentPoint.previousCachedToken()!=null;
            }

            public Token next() {
                Token previous= currentPoint.previousCachedToken();
                if (previous== null) throw new java.util.NoSuchElementException("No previous token!");
                return currentPoint= previous;
            }

        }
        ;
    }

    /**
     * @return a list of the unparsed tokens preceding this one in the order they appear in the input
     */
    public List<Token> precedingUnparsedTokens() {
        List<Token> result= new ArrayList<> ();
        Token t= this.previousCachedToken();
        while (t!=null&&t.isUnparsed()) {
            result.add(t);
            t= t.previousCachedToken();
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * @return An iterator of the (cached) tokens that follow this one.
     */
    public Iterator<Token> followingTokens() {
        return new java.util.Iterator<Token> () {
            Token currentPoint= Token.this;
            public boolean hasNext() {
                return currentPoint.nextCachedToken()!=null;
            }

            public Token next() {
                Token next= currentPoint.nextCachedToken();
                if (next== null) throw new java.util.NoSuchElementException("No next token!");
                return currentPoint= next;
            }

        }
        ;
    }

    /**
     * Copy the location info from a Node
     */
    public void copyLocationInfo(Node from) {
        Node.super.copyLocationInfo(from);
        if (from instanceof Token) {
            Token otherTok= (Token) from;
            appendedToken= otherTok.appendedToken;
            prependedToken= otherTok.prependedToken;
        }
        setTokenSource(from.getTokenSource());
    }

    public void copyLocationInfo(Node start, Node end) {
        Node.super.copyLocationInfo(start, end);
        if (start instanceof Token) {
            prependedToken= ((Token) start).prependedToken;
        }
        if (end instanceof Token) {
            Token endToken= (Token) end;
            appendedToken= endToken.appendedToken;
        }
    }

    public static Token newToken(TokenType type, JmmGrammarLexer tokenSource, int beginOffset, int endOffset) {
        switch(type) {
            case LESS:
            return new LESS(TokenType.LESS, tokenSource, beginOffset, endOffset);
            case PLUS:
            return new PLUS(TokenType.PLUS, tokenSource, beginOffset, endOffset);
            case MINUS:
            return new MINUS(TokenType.MINUS, tokenSource, beginOffset, endOffset);
            case TIMES:
            return new TIMES(TokenType.TIMES, tokenSource, beginOffset, endOffset);
            case DIVIDE:
            return new DIVIDE(TokenType.DIVIDE, tokenSource, beginOffset, endOffset);
            case OPEN_PAREN:
            return new OPEN_PAREN(TokenType.OPEN_PAREN, tokenSource, beginOffset, endOffset);
            case CLOSE_PAREN:
            return new CLOSE_PAREN(TokenType.CLOSE_PAREN, tokenSource, beginOffset, endOffset);
            case INTEGER:
            return new INTEGER(TokenType.INTEGER, tokenSource, beginOffset, endOffset);
            case IMPORT:
            return new IMPORT(TokenType.IMPORT, tokenSource, beginOffset, endOffset);
            case DOT:
            return new DOT(TokenType.DOT, tokenSource, beginOffset, endOffset);
            case NEXTLINE:
            return new NEXTLINE(TokenType.NEXTLINE, tokenSource, beginOffset, endOffset);
            case COMMA:
            return new COMMA(TokenType.COMMA, tokenSource, beginOffset, endOffset);
            case CLASS:
            return new CLASS(TokenType.CLASS, tokenSource, beginOffset, endOffset);
            case EXTENDS:
            return new EXTENDS(TokenType.EXTENDS, tokenSource, beginOffset, endOffset);
            case OPEN_CHAV:
            return new OPEN_CHAV(TokenType.OPEN_CHAV, tokenSource, beginOffset, endOffset);
            case CLOSE_CHAV:
            return new CLOSE_CHAV(TokenType.CLOSE_CHAV, tokenSource, beginOffset, endOffset);
            case INT:
            return new INT(TokenType.INT, tokenSource, beginOffset, endOffset);
            case BOOL:
            return new BOOL(TokenType.BOOL, tokenSource, beginOffset, endOffset);
            case STRING:
            return new STRING(TokenType.STRING, tokenSource, beginOffset, endOffset);
            case VOID:
            return new VOID(TokenType.VOID, tokenSource, beginOffset, endOffset);
            case PUBLIC:
            return new PUBLIC(TokenType.PUBLIC, tokenSource, beginOffset, endOffset);
            case RETORNO:
            return new RETORNO(TokenType.RETORNO, tokenSource, beginOffset, endOffset);
            case STATIC:
            return new STATIC(TokenType.STATIC, tokenSource, beginOffset, endOffset);
            case MAIN:
            return new MAIN(TokenType.MAIN, tokenSource, beginOffset, endOffset);
            case OPEN_RET:
            return new OPEN_RET(TokenType.OPEN_RET, tokenSource, beginOffset, endOffset);
            case CLOSE_RET:
            return new CLOSE_RET(TokenType.CLOSE_RET, tokenSource, beginOffset, endOffset);
            case EQUAL:
            return new EQUAL(TokenType.EQUAL, tokenSource, beginOffset, endOffset);
            case IF:
            return new IF(TokenType.IF, tokenSource, beginOffset, endOffset);
            case WHILE:
            return new WHILE(TokenType.WHILE, tokenSource, beginOffset, endOffset);
            case ELSE:
            return new ELSE(TokenType.ELSE, tokenSource, beginOffset, endOffset);
            case AND:
            return new AND(TokenType.AND, tokenSource, beginOffset, endOffset);
            case TRUE:
            return new TRUE(TokenType.TRUE, tokenSource, beginOffset, endOffset);
            case FALSE:
            return new FALSE(TokenType.FALSE, tokenSource, beginOffset, endOffset);
            case COMPRIMENTO:
            return new COMPRIMENTO(TokenType.COMPRIMENTO, tokenSource, beginOffset, endOffset);
            case THIS:
            return new THIS(TokenType.THIS, tokenSource, beginOffset, endOffset);
            case NEW:
            return new NEW(TokenType.NEW, tokenSource, beginOffset, endOffset);
            case EXCL:
            return new EXCL(TokenType.EXCL, tokenSource, beginOffset, endOffset);
            case IDENTIFIER:
            return new IDENTIFIER(TokenType.IDENTIFIER, tokenSource, beginOffset, endOffset);
            case INVALID:
            return new InvalidToken(tokenSource, beginOffset, endOffset);
            default:
            return new Token(type, tokenSource, beginOffset, endOffset);
        }
    }

    public String getLocation() {
        return getInputSource()+":"+getBeginLine()+":"+getBeginColumn();
    }

    public void setChild(int i, Node n) {
        throw new UnsupportedOperationException();
    }

    public void addChild(Node n) {
        throw new UnsupportedOperationException();
    }

    public void addChild(int i, Node n) {
        throw new UnsupportedOperationException();
    }

    public Node removeChild(int i) {
        throw new UnsupportedOperationException();
    }

    public final int indexOf(Node n) {
        return-1;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent= parent;
    }

    public final int getChildCount() {
        return 0;
    }

    public final Node getChild(int i) {
        return null;
    }

    public final List<Node> children() {
        return java.util.Collections.emptyList();
    }

}