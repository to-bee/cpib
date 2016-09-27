package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.lib.enums.MechMode;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IIdent;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IParameter;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.StoreDecl;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

public interface IConcSyn {
  public void setProgram(IProgram program);

  public void printTree();

  interface IConcSynNode {
    public void appendAsString(StringBuilder sb, String indent);
  }

  interface IProgram extends IConcSynNode {
    public IAbsSyn.IProgram toAbsSyn();
  }

  interface IOptGlobalCpsDecl extends IConcSynNode {
    public IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl> toAbsSyn();
  }

  interface IDecl extends IConcSynNode {
    public IAbsSyn.IDecl toAbsSyn();
  }

  interface IStoreDecl extends IConcSynNode, IDecl {
    @Override
    public StoreDecl toAbsSyn();
  }

  interface IFunDecl extends IConcSynNode, IDecl {
    @Override
    public ch.fhnw.cpib.iml.parser.abssyntaxtree.FunDecl toAbsSyn();
  }

  interface IOptGlobImpList extends IConcSynNode {
    public IList<IGlobalImport> toAbsSyn();
  }

  interface IOptLocalCpsDecl extends IConcSynNode {
    public IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl> toAbsSyn();
  }

  interface IProcDecl extends IConcSynNode, IDecl {
    @Override
    public ch.fhnw.cpib.iml.parser.abssyntaxtree.ProcDecl toAbsSyn();
  }

  interface ICpsDecl extends IConcSynNode {
    public IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl> toAbsSyn();
  }

  interface IRepDecl extends IConcSynNode {
    public IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl> toAbsSyn();
  }

  interface IParamList extends IConcSynNode {
    public IList<IParameter> toAbsSyn();
  }

  interface IOptParam extends IConcSynNode {
    public IList<IParameter> toAbsSyn();
  }

  interface IRepParam extends IConcSynNode {
    public IList<IParameter> toAbsSyn();
  }

  interface IParam extends IConcSynNode {
    public IParameter toAbsSyn();
  }

  interface IOptFlowMode extends IConcSynNode {
    public Attribute<FlowMode> toAbsSyn();
  }

  interface IOptMechMode extends IConcSynNode {
    public Attribute<MechMode> toAbsSyn();
  }

  interface IGlobImpList extends IConcSynNode {
    public IList<IGlobalImport> toAbsSyn();
  }

  interface IRepGlobImp extends IConcSynNode {
    public IList<IGlobalImport> toAbsSyn();
  }

  interface IGlobImp extends IConcSynNode {
    public IAbsSyn.IGlobalImport toAbsSyn();
  }

  interface IOptChangeMode extends IConcSynNode {
    public Attribute<ChangeMode> toAbsSyn();
  }

  interface ICmd extends IConcSynNode {
    public IAbsSyn.ICmd toAbsSyn();
  }

  interface IOptGlobInitList extends IConcSynNode {
    public IList<IIdent> toAbsSyn();
  }

  interface IBlockCmd extends IConcSynNode {
    public IAbsSyn.ICmd toAbsSyn();
  }

  interface IRepCmd extends IConcSynNode {
    public IAbsSyn.IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd> toAbsSyn();
  }

  interface IGlobInitList extends IConcSynNode {
    public IAbsSyn.IList<IIdent> toAbsSyn();
  }

  interface IRepIdent extends IConcSynNode {
    public IAbsSyn.IList<IIdent> toAbsSyn();
  }

  interface IExpr extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn();
  }

  interface IRepBoolOprTerm1 extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn(ITerm1 term1);
  }

  interface ITerm1 extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn();
  }

  interface IOptRelOprTerm2 extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn(ITerm2 term2);
  }

  interface ITerm2 extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn();
  }

  interface IRepAddOprTerm3 extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr dyadicExpr);
  }

  interface ITerm3 extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn();
  }

  interface IRepMultOprFactor extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn(IFactor factor);
  }

  interface IFactor extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn();
  }

  interface IArrFactor extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn();
  }

  interface IOptFactorIdent extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn(Token<String> ident);
  }

  interface IOptArrFactor extends IConcSynNode {
    public IAbsSyn.IExpr toAbsSyn();
  }

  interface IExprList extends IConcSynNode {
    public IAbsSyn.IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr> toAbsSyn();
  }

  interface IOptExpr extends IConcSynNode {
    public IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr> toAbsSyn();
  }

  interface IRepExpr extends IConcSynNode {
    public IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr> toAbsSyn();
  }

  interface IMonadicOpr extends IConcSynNode {
    public Token<?> toAbsSyn();
  }
}
