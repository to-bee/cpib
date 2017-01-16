using System;
using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{

    public interface INonTerminalSymbol
    {
        // nothing here, yet later we may need to add some functions here...
    }

    public interface IProgram : INonTerminalSymbol
    {
        AbsProgram ToAbsSyn();
    }

    public interface IBlockCmd : INonTerminalSymbol
    {
        IAbsList<IAbsCmd> ToAbySyn();
    }

    public interface ICmd : INonTerminalSymbol
    {
        IAbsCmd ToAbySyn();
    }
    public interface IOptionalElse : INonTerminalSymbol
    {
        IAbsList<IAbsCmd> ToAbySyn();
    }
    public interface IOptionalGlobalInits : INonTerminalSymbol
    {
        IAbsList<string> ToAbySyn();
    }

    public interface IIdents : INonTerminalSymbol
    {
        IAbsList<string> ToAbySyn();
    }
    public interface IRepeatingOptionalIdents : INonTerminalSymbol
    {
        IAbsList<string> ToAbySyn();
    }

    public interface IRepeatingOptionalCmds : INonTerminalSymbol
    {
        IAbsList<IAbsCmd> ToAbySyn();
    }
    public interface IDeclaration : INonTerminalSymbol
    {
        IAbsDeclaration ToAbySyn();
    }
    public interface IStorageDeclaration : INonTerminalSymbol
    {
        AbsStorageDeclaration ToAbySyn();
    }

    public interface IOptionalChangeMode : INonTerminalSymbol
    {
        CHANGEMODE ToAbySyn();
    }
    public interface IOptionalFlowMode : INonTerminalSymbol
    {
        FLOWMODE ToAbsSyn();
    }
    public interface IOptionalMechMode : INonTerminalSymbol
    {
        MECHMODE ToAbsSyn();
    }
    public interface ITypedIdent : INonTerminalSymbol
    {
        AbsTypedIdent ToAbySyn();
    }

    [Obsolete("Don't use anymore, IML was updated", true)]
    public interface ITypeDeclaration : INonTerminalSymbol
    {
        // was kommt zurück? wtf? warum IDENT --> maybe roman knows?
        //AbsTypeDeclaration ToAbsSyn();
    }

    public interface IFunctionDeclaration : INonTerminalSymbol
    {
        AbsFunctionDeclaration ToAbySyn();
    }
    public interface IProcedureDeclaration : INonTerminalSymbol
    {
        AbsProcedureDeclaration ToAbySyn();
    }
    public interface IOptionalGlobalImports : INonTerminalSymbol
    {
        IAbsList<AbsGlobalImport> ToAbySyn();
    }
    public interface IGlobalImport : INonTerminalSymbol
    {
        AbsGlobalImport ToAbySyn();
    }
    public interface IRepeatingOptionalGlobalImports : INonTerminalSymbol
    {
        IAbsList<AbsGlobalImport> ToAbySyn();
    }
    public interface IProgramParameterList : INonTerminalSymbol
    {
        IAbsList<AbsProgramParameter> ToAbySyn();
    }
    public interface IOptionalProgramParameters : INonTerminalSymbol
    {
        IAbsList<AbsProgramParameter> ToAbySyn();
    }
    public interface IRepeatingOptionalProgramParameters : INonTerminalSymbol
    {
        IAbsList<AbsProgramParameter> ToAbySyn();
    }
    public interface IOptionalGlobalDeclarations : INonTerminalSymbol
    {
        IAbsList<IAbsDeclaration> ToAbySyn();
    }
    public interface IDeclarations : INonTerminalSymbol
    {
        IAbsList<IAbsDeclaration> ToAbySyn();
    }
    public interface IRepeatingOptionalDeclarations : INonTerminalSymbol
    { 
        IAbsList<IAbsDeclaration> ToAbySyn();
    }
    public interface IOptionalLocalStorageDeclarations : INonTerminalSymbol
    {
        IAbsList<AbsStorageDeclaration> ToAbySyn();
    }
    public interface IRepeatingOptionalStorageDeclarations : INonTerminalSymbol
    {
        IAbsList<AbsStorageDeclaration> ToAbySyn();
    }
    public interface IParameterList : INonTerminalSymbol
    {
        IAbsList<AbsParameter> ToAbySyn();
    }
    public interface IOptionalParameters : INonTerminalSymbol
    {
        IAbsList<AbsParameter> ToAbySyn();
    }
    public interface IParameter : INonTerminalSymbol
    {
        AbsParameter ToAbySyn();
    }
    public interface IRepeatingOptionalParameters : INonTerminalSymbol
    {
        IAbsList<AbsParameter> ToAbySyn();
    }
    public interface IExpressionList : INonTerminalSymbol
    {
        IAbsList<IAbsExpression> ToAbySyn();
    }
    public interface IOptionalExpressions : INonTerminalSymbol
    {
        IAbsList<IAbsExpression> ToAbySyn();
    }
    public interface IExpression : INonTerminalSymbol
    {
        IAbsExpression ToAbySyn();
    }
    public interface IRepeatingOptionalExpressions : INonTerminalSymbol
    {
        IAbsList<IAbsExpression> ToAbySyn();
    }
    public interface IRepBoolOprTerm1 : INonTerminalSymbol
    {
        IAbsExpression ToAbsSyn(IAbsExpression term1);
    }
    public interface ITerm1 : INonTerminalSymbol
    {
        IAbsExpression ToAbySyn();
    }
    public interface IRepRelOprTerm2 : INonTerminalSymbol
    {
        IAbsExpression ToAbsSyn(IAbsExpression term2);
    }
    public interface ITerm2 : INonTerminalSymbol
    {
        IAbsExpression ToAbySyn();
    }
    public interface IRepAddOprTerm3 : INonTerminalSymbol
    {
        //IAbsExpression ToAbySyn(ITerm3 term3);
        IAbsExpression ToAbsSyn(IAbsExpression expr);
    }
    public interface ITerm3 : INonTerminalSymbol
    {
        IAbsExpression ToAbySyn();
    }
    public interface IRepMultOprFactor : INonTerminalSymbol
    {
        IAbsExpression ToAbsSyn(IAbsExpression factor);
    }
    public interface IFactor : INonTerminalSymbol
    {
        IAbsExpression ToAbySyn();
    }
    public interface IOptionalIdent : INonTerminalSymbol
    {
        IAbsExpression ToAbySyn(Ident ident);
    }
    public interface IMonadicOperator : INonTerminalSymbol
    {
        MonadicOperator ToAbySyn();
    }
    public interface ICasting : INonTerminalSymbol
    {
        TYPE ToAbySyn();
    }
}
