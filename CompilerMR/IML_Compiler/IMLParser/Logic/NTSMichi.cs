using IML_Compiler.IMLParser.DataTypes.Concrete;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.Logic
{
    public partial class Parser
    {
        public IProgram programm()
        {
            Consume(Terminals.PROGRAM);
            var i = token;
            Consume(Terminals.IDENT);

            var ppl = programParameterList();

            var ogd = optionalGlobalDeclarations();

            Consume(Terminals.DO);

            var bc = blockCmd();

            Consume(Terminals.ENDPROGRAM);
            return new Program((Ident)i, ppl, ogd, bc);
        }

        public IBlockCmd blockCmd()
        {
            var c = cmd();

            var roc = repeatingOptionalCmds();

            return new BlockCmd(c, roc);
        }

        public ICmd cmd()
        {
            switch (terminal)
            {
                case Terminals.SKIP:
                    Consume(Terminals.SKIP);
                    return new CmdSkip();
                case Terminals.IF:
                    {
                        Consume(Terminals.IF);
                        var e = expression();
                        Consume(Terminals.THEN);
                        var bc1 = blockCmd();
                        var oe = optionalElse();
                        Consume(Terminals.ENDIF);
                        return new CmdIf(e, bc1, oe);
                    }
                case Terminals.WHILE:
                    {
                        Consume(Terminals.WHILE);
                        var e = expression();
                        Consume(Terminals.DO);
                        var bc = blockCmd();
                        Consume(Terminals.ENDWHILE);
                        return new CmdWhile(e, bc);
                    }
                case Terminals.CALL:
                    {
                        Consume(Terminals.CALL);
                        var i = token;
                        Consume(Terminals.IDENT);
                        var eL = expressionList();
                        var ogi = optionalGlobalInits();
                        return new CmdCall((Ident)i, eL, ogi);
                    }
                case Terminals.DEBUGIN:
                    {
                        Consume(Terminals.DEBUGIN);
                        var e = expression();
                        return new CmdDebugIn(e);
                    }
                case Terminals.DEBUGOUT:
                    {
                        Consume(Terminals.DEBUGOUT);
                        var e = expression();
                        return new CmdDebugOut(e);
                    }
                default:
                    {
                        var e1 = expression();
                        Consume(Terminals.BECOMES);
                        var e2 = expression();
                        return new CmdBecomes(e1, e2);
                    }
            }
        }

        public IOptionalElse optionalElse()
        {
            if (terminal == Terminals.ELSE)
            {
                Consume(Terminals.ELSE);
                var bc = blockCmd();
                return new OptionalElseCmd(bc);
            }
            return new OptionalElseEps();
        }

        public IOptionalGlobalInits optionalGlobalInits()
        {
            if (terminal != Terminals.INIT) return new OptionalGlobalInitsEps();

            Consume(Terminals.INIT);
            var i = idents();
            return new OptionalGlobalInitsInit(i);
        }

        public IIdents idents()
        {
            var i = token;
            Consume(Terminals.IDENT);
            var roi = repeatingOptionalIdents();
            return new Idents((Ident)i, roi);
        }

        public IRepeatingOptionalIdents repeatingOptionalIdents()
        {
            if (terminal != Terminals.COMMA) return new RepeatingOptionalIdentsEps();

            Consume(Terminals.COMMA);
            var i = token;
            Consume(Terminals.IDENT);
            var ii = repeatingOptionalIdents();

            return new RepeatingOptionalIdentsIdent((Ident)i, ii);
        }

        public IRepeatingOptionalCmds repeatingOptionalCmds()
        {
            if (terminal != Terminals.SEMICOLON) return new RepeatingOptionalCmdsEps();

            Consume(Terminals.SEMICOLON);
            var c = cmd();
            var roc = repeatingOptionalCmds();
            return new RepeatingOptionalCmdsCmd(c, roc);
        }

        public IDeclaration declaration()
        {
            if (terminal == Terminals.CHANGEMODE || terminal == Terminals.IDENT)
            {
                var sd = storageDeclaration();
                return new DeclarationStorage(sd);
            }
            else if (terminal == Terminals.FUN)
            {
                var fd = functionDeclaration();
                return new DeclarationFunction(fd);
            }
            else if (terminal == Terminals.PROC)
            {
                var pd = procedureDeclaration();
                return new DeclarationProcedure(pd);
            } else
            {
                throw new ParserException("declaration");
            }
        }

        public IStorageDeclaration storageDeclaration()
        {
            var oc = optionalCHANGEMODE();
            var ti = typedIdent();
            return new StorageDeclaration(oc, ti);
        }

        public IOptionalChangeMode optionalCHANGEMODE()
        {
            if (terminal == Terminals.CHANGEMODE)
            {
                var m = token;
                Consume(Terminals.CHANGEMODE);
                return new OptionalChangeModeMode((ChangeMode)m);
            }

            return new OptionalChangeModeEps();
        }

        public IOptionalFlowMode optionalFLOWMODE()
        {
            if (terminal == Terminals.FLOWMODE)
            {
                var m = token;
                Consume(Terminals.FLOWMODE);
                return new OptionalFlowModeMode((FlowMode)m);
            }

            return new OptionalFlowModeEps();
        }

        public IOptionalMechMode optionalMECHMODE()
        {
            if (terminal == Terminals.MECHMODE)
            {
                var m = token;
                Consume(Terminals.MECHMODE);
                return new OptionalMechModeMode((MechMode)m);
            }

            return new OptionalMechModeEps();
        }

        public ITypedIdent typedIdent()
        {
            var i = token;
            Consume(Terminals.IDENT);
            Consume(Terminals.COLON);
            //var td = typeDeclaration();
            var t = token;
            Consume(Terminals.TYPE);
            return new TypedIdent((Ident)i, (Type)t);
        }

        //public ITypeDeclaration typeDeclaration()
        //{
        //    if (terminal == Terminals.TYPE)
        //    {
        //        var t = token;
        //        Consume(Terminals.TYPE);
        //        return new TypeDeclarationType((IMLScanner.DataTypes.Type)t);
        //    } else if (terminal == Terminals.IDENT)
        //    {
        //        var i = token;
        //        Consume(Terminals.IDENT);
        //        return new TypeDeclarationIdent((Ident)i);
        //    } else
        //    {
        //        throw new ParserException("typeDeclaration");
        //    }
        //}

        public IFunctionDeclaration functionDeclaration()
        {
            Consume(Terminals.FUN);
            var i = token;
            Consume(Terminals.IDENT);
            var pl = parameterList();
            Consume(Terminals.RETURNS);
            var sd = storageDeclaration();
            var ogi = optionalGlobalImports();
            var olsd = optionalLocalStorageDeclarations();
            Consume(Terminals.DO);
            var bc = blockCmd();
            Consume(Terminals.ENDFUN);
            return new FunctionDeclaration((Ident)i, pl, sd, ogi, olsd, bc);
        }

        public IProcedureDeclaration procedureDeclaration()
        {
            Consume(Terminals.PROC);
            var i = token;
            Consume(Terminals.IDENT);
            var pl = parameterList();
            var ogi = optionalGlobalImports();
            var olsd = optionalLocalStorageDeclarations();
            Consume(Terminals.DO);
            var bc = blockCmd();
            Consume(Terminals.ENDPROC);
            return new ProcedureDeclaration((Ident)i, pl, ogi, olsd, bc);
        }

        public IOptionalGlobalImports optionalGlobalImports()
        {
            if (terminal == Terminals.GLOBAL)
            {
                Consume(Terminals.GLOBAL);
                var gi = globalImport();
                var rogi = repeatingOptionalGlobalImports();
                return new OptionalGlobalImportsImport(gi, rogi);
            }

            return new OptionalGlobalImportsEps();
        }

        public IGlobalImport globalImport()
        {
            var fm = optionalFLOWMODE();
            var cm = optionalCHANGEMODE();
            var i = token;
            Consume(Terminals.IDENT);
            return new GlobalImport(fm, cm, (Ident)i);
        }

        public IRepeatingOptionalGlobalImports repeatingOptionalGlobalImports()
        {
            if (terminal == Terminals.COMMA)
            {
                Consume(Terminals.COMMA);
                var gi = globalImport();
                var rogi = repeatingOptionalGlobalImports();
                return new RepeatingOptionalGlobalImportsImport(gi, rogi);
            }

            return new RepeatingOptionalGlobalImportsEps();
        }

        public IProgramParameterList programParameterList()
        {
            Consume(Terminals.LPAREN);
            var opp = optionalProgramParameters();
            Consume(Terminals.RPAREN);
            return new ProgramParameterList(opp);
        }

        public IOptionalProgramParameters optionalProgramParameters()
        {
            if (terminal == Terminals.FLOWMODE || terminal == Terminals.CHANGEMODE || terminal == Terminals.IDENT)
            {
                var fm = optionalFLOWMODE();
                var cm = optionalCHANGEMODE();
                var ti = typedIdent();
                var ropp = repeatingOptionalProgramParameters();

                return new OptionalProgramParametersParams(fm, cm, ti, ropp);
            }

            return new OptionalProgramParametersEps();
        }

        public IRepeatingOptionalProgramParameters repeatingOptionalProgramParameters()
        {
            if (terminal == Terminals.COMMA)
            {
                Consume(Terminals.COMMA);
                var fm = optionalFLOWMODE();
                var cm = optionalCHANGEMODE();
                var ti = typedIdent();
                var ropp = repeatingOptionalProgramParameters();
                return new RepeatingOptionalProgramParametersParams(fm, cm, ti, ropp);
            }

            return new RepeatingOptionalProgramParametersEps();
        }

        public IOptionalGlobalDeclarations optionalGlobalDeclarations()
        {
            if (terminal == Terminals.GLOBAL)
            {
                Consume(Terminals.GLOBAL);
                var d = declarations();
                return new OptionalGlobalDeclarationsDecl(d);
            }

            return new OptionalGlobalDeclarationsEps();
        }

        public IDeclarations declarations()
        {
            var d = declaration();
            var rod = repeatingOptionalDeclarations();
            return new Declarations(d, rod);
        }
    }
}
