package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn;
import ch.fhnw.cpib.iml.scanner.Token;

public class Program implements IConcSyn.IProgram {
  // TODO : should those fields be private? should IProgram have getters?
  public final Token<?>       program;
  public final Token<String>  ident;
  public final IConcSyn.IOptGlobalCpsDecl optGlobalCpsDecl;
  public final IConcSyn.IBlockCmd         blockCmd;

  public Program(final Token<?> program,
      final Token<String> ident,
      final IConcSyn.IOptGlobalCpsDecl globalCpsDecl,
      final IConcSyn.IBlockCmd blockCmd) {
    this.program = program;
    this.ident = ident;
    this.optGlobalCpsDecl = globalCpsDecl;
    this.blockCmd = blockCmd;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append("program\n");
    sb.append(indent);
    sb.append(this.ident.getAttribute());
    sb.append("\n");
    this.optGlobalCpsDecl.appendAsString(sb, indent + " ");
    this.blockCmd.appendAsString(sb, indent + " ");
  }

  @Override
  public IAbsSyn.IProgram toAbsSyn() {
    return new ch.fhnw.cpib.iml.parser.abssyntaxtree.Program(this.ident,
        this.optGlobalCpsDecl.toAbsSyn(),
        this.blockCmd.toAbsSyn());
  }
}
