package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IFunDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptGlobImpList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptLocalCpsDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IParamList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IStoreDecl;
import ch.fhnw.cpib.iml.scanner.Token;

public class FunDecl implements IFunDecl {
  private final Token<?>      fun;
  private final Token<String> ident;
  private final IParamList                paramList;
  private final Token<?>      returns;
  private final IStoreDecl                storeDecl;
  private final IOptGlobImpList           optGlobImpList;
  private final IOptLocalCpsDecl          optLocalCpsDecl;
  private final IBlockCmd                 blockCmd;

  public FunDecl(final Token<?> fun,
      final Token<String> ident,
      final IConcSyn.IParamList paramList, final Token<?> returns,
      final IStoreDecl storeDecl, final IOptGlobImpList optGlobImpList,
      final IOptLocalCpsDecl optLocalCpsDecl, final IBlockCmd blockCmd) {
    this.fun = fun;
    this.ident = ident;
    this.paramList = paramList;
    this.returns = returns;
    this.storeDecl = storeDecl;
    this.optGlobImpList = optGlobImpList;
    this.optLocalCpsDecl = optLocalCpsDecl;
    this.blockCmd = blockCmd;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    final String newIndent = indent + " ";
    sb.append(indent);
    sb.append("fun");
    sb.append("\n");
    sb.append(indent);
    sb.append(this.ident.getAttribute());
    sb.append("\n");
    this.paramList.appendAsString(sb, newIndent);
    sb.append(indent);
    sb.append("returns");
    sb.append("\n");
    this.storeDecl.appendAsString(sb, newIndent);
    this.optGlobImpList.appendAsString(sb, newIndent);
    this.optLocalCpsDecl.appendAsString(sb, newIndent);
    this.blockCmd.appendAsString(sb, newIndent);
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.FunDecl toAbsSyn() {
    return new ch.fhnw.cpib.iml.parser.abssyntaxtree.FunDecl(this.ident,
        this.paramList.toAbsSyn(), this.storeDecl.toAbsSyn(),
        this.optGlobImpList.toAbsSyn(), this.optLocalCpsDecl.toAbsSyn(),
        this.blockCmd.toAbsSyn());
  }

}
