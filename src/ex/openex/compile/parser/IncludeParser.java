package ex.openex.compile.parser;

import ex.openex.astvm.code.ByteCode;
import ex.openex.astvm.code.struct.NulByteCode;
import ex.openex.compile.Compiler;
import ex.openex.compile.LexicalAnalysis;
import ex.openex.exception.CompileException;

import java.util.ArrayList;

public class IncludeParser implements BaseParser{
    ArrayList<LexicalAnalysis.Token> tds;

    public IncludeParser(ArrayList<LexicalAnalysis.Token> tds){
        this.tds = tds;
    }

    @Override
    public ByteCode eval(Parser parser, Compiler compiler)throws CompileException {
        if(tds.size()>2)throw new CompileException("include语句存在多余的符号",tds.get(tds.size()-2),parser.filename);
        LexicalAnalysis.Token l = tds.get(0);
        if(l.getType()==LexicalAnalysis.STRING) compiler.getLibnames().add(l.getData());
        else throw new CompileException("未知的库名",l, parser.filename);
        return new NulByteCode();
    }
}
