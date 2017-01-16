using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLScanner.Logic
{
    /// <summary>
    /// Scans the text given and creates a tokenlist.
    /// </summary>
    public class Scanner
    {
        private TokenList list;
        //private long numAccu;
        //private long floatAccu;
        //private long floatScientific;
        private string numHolder;
        //private bool floatScientificMinus;
        private StringBuilder sb;

        /// <summary>
        /// Creates a tokenlist from a given input
        /// </summary>
        /// <param name="input">The code written in IML language</param>
        /// <returns>The tokenlist</returns>
        public ITokenList Scan(string input)
        {
            // Initial checks
            if (string.IsNullOrEmpty(input))
            {
                throw new ScannerException("Empyt input given");
            }

            //Init
            list = new TokenList();

            int state = 0;
            sb = new StringBuilder();
            //numAccu = 0;
            //floatScientific = 0;
            numHolder = "";
            //floatScientificMinus = false;
            input += " "; //important, otherwise the last word may not be scanned successfully


            //Final State Machine
            for (int i = 0; i < input.Length; i++)
            {
                char c = input[i];
                switch (state)
                {
                    case 0:
                        state = State0(c);
                        break;
                    case 1:
                        state = State1(c);
                        break;
                    case 2:
                        state = State2(c);
                        break;
                    case 3:
                        state = State3(c);
                        break;
                    case 4:
                        state = State4(c);
                        break;
                    case 5:
                        state = State5(c);
                        break;
                    case 6:
                        state = State6(c);
                        break;
                    case 7:
                        state = State7(c);
                        break;
                    default:
                        throw new ScannerException($"Illegal Scanner State: {state}");
                }

                if (state == 0) i -= 1;
            }

            list.Add(new Token(Terminals.SENTINEL));

            if (state != 4)
            {
                throw new ScannerException("Scanner didn't end as expected, the last character which should have been a whitespace wasn't");
            }

            return list;
        }

        private int State7(char c)
        {
            if (char.IsNumber(c))
            {
                //floatScientific = floatScientific * 10 + (c - 48);
                numHolder += c;
                return 7;
            }
            else if (c == '-')
            {
                if (numHolder.EndsWith("E"))
                {
                    //floatScientificMinus = true;
                    numHolder += '-';
                    return 7;
                } else
                {
                    throw new ScannerException($"'-' is not allowed here: {numHolder}-");
                }
            }
            else
            {
                //double d;
                //if (double.TryParse($"{numAccu}.{floatAccu}E{(floatScientificMinus ? "-" : "")}{floatScientific}", out d))
                //{
                //    if (d <= float.MaxValue)
                //    {
                //        list.Add(new Literal(LITERAL.FLOAT, (float)d));
                //        floatScientific = 0;
                //        floatScientificMinus = false;
                //        return 0;
                //    }
                //    throw new ScannerException($"Float literal too big. {numAccu}.{floatAccu}E{(floatScientificMinus ? "-" : "")}{floatScientific}");
                //}
                //throw new ScannerException("Couldn't parse float literal");
                try
                {
                    float f = float.Parse(numHolder);
                    list.Add(new Literal(TYPE.FLOAT, f));
                    numHolder = "";
                    return 0;
                }
                catch (Exception e)
                {
                    throw new ScannerException(numHolder + ": " + e.Message.Replace("Byte", "Float"));
                }
            }
        }

        private int State6(char c)
        {
            if (char.IsNumber(c))
            {
                //floatAccu = floatAccu * 10 + (c - 48);
                numHolder += c;
                return 6;
            }
            else if (c == 'E')
            {
                numHolder += c;
                return 7;
            }
            else
            {
                //double tmp = floatAccu;
                //while (tmp >= 1) tmp /= 10;
                //tmp += numAccu;
                //list.Add(new Literal(LITERAL.FLOAT, (float)tmp));

                try
                {
                    float f = float.Parse(numHolder);
                    list.Add(new Literal(TYPE.FLOAT, f));
                    numHolder = "";
                    return 0;
                }
                catch (Exception e)
                {
                    throw new ScannerException(numHolder + ": " + e.Message);
                }
            }
        }

        // Comment, skips all characters until a newline (\n) character is found
        private int State5(char c)
        {
            if (c == '\n') return 0;
            return 5;
        }

        // skip all whitespaces (space, tab, newline, etc)
        private int State4(char c)
        {
            if (char.IsWhiteSpace(c))
            {
                return 4;
            }
            return 0;
        }

        // finds symbol based terminals, such as &&, <, >=, ...
        // some of these contain 1 characters, some others more
        private int State3(char c)
        {
            // At least one character is already in sb

            if (char.IsWhiteSpace(c))
            { //whitespace is following, case cleared
                var t = CreateToken(sb.ToString(), true);
                list.Add(t);
                return 0;
            }
            else
            { // more than one character

                //filtering out comments:
                if (sb.ToString() == "/" && c == '/')
                {
                    return 5;
                }
                
                //get a list of possible terminals
                var keys = Dictionaries.dicTerm.Keys.Where(key => key.StartsWith(sb.ToString()));
                if (keys.Count() == 1)
                { //only one possible, still need to check if they match
                    if (keys.First() == sb.ToString())
                    { // if they match, add the terminal in sb to the list
                        // the new read character c, therefore is a new terminal or part of it
                        var t = CreateToken(sb.ToString());
                        list.Add(t);
                        return 0;
                    }
                    else
                    { //otherwise append and check further
                        sb.Append(c);
                        return 3;
                    }
                }
                else
                { //more than one possible endings for sb
                    if (Dictionaries.dicTerm.Keys.Count(key => key.StartsWith(sb.ToString() + c)) == 0)
                    { // if sb+c doesn't appear to be a terminal, add sb to the list
                        var t = CreateToken(sb.ToString());
                        list.Add(t);
                        return 0;
                    }
                    else
                    { //otherwise, when sb+c appears to be a (part of a) terminal, add c to sb and investigate further
                        sb.Append(c);
                        return 3;
                    }
                }
            }
        }

        // Adding identifiers (IDENT) to the list
        private int State2(char c)
        {
            if (char.IsWhiteSpace(c))
            {
                var t = CreateToken(sb.ToString());
                list.Add(t);
                return 0;
            }
            else
            {
                //allow special characters to be part of a IDENT, except those who are marking the start of a terminal
                if (!char.IsLetterOrDigit(c) && !(c == '\'' || c == '_'))
                {
                    var t = CreateToken(sb.ToString());
                    list.Add(t);
                    return 0;
                }

                sb.Append(c);
                return 2;
            }
        }

        // adding up the number
        private int State1(char c)
        {
            if (char.IsDigit(c))
            {
                //int digit = c - 48;
                //numAccu = numAccu * 10 + digit;
                //if (numAccu < 0)
                //{
                //    throw new ScannerException("Integer literal too large");
                //}
                numHolder += c;
                return 1;
            }
            else if (c == '\'') return 1;
            else if (c == '.')
            {
                numHolder += c;
                return 6;
            } else if (c == 'E')
            {
                numHolder += c;
                return 7;
            }
            else
            {
                try
                {
                    var i = Int64.Parse(numHolder);
                    if (i > Int32.MaxValue)
                    {
                        list.Add(new Literal(TYPE.INT64, i));
                    }
                    else
                    {
                        list.Add(new Literal(TYPE.INT32, (int) i));
                    }
                    return 0;
                }
                catch (Exception e)
                {
                    throw new ScannerException(numHolder + ": " + e.Message);
                }
                //list.Add(new Literal(LITERAL.INT32, (int)numAccu));
                //return 0;
            }
        }

        // read the character and determine which state should be responsible
        private int State0(char c)
        {
            //numAccu = 0;
            numHolder = "";
            sb.Clear();
            if (char.IsDigit(c))
            {
                //numAccu = c - 48; //ascii 48 is digit 0
                numHolder += c;
                return 1;
            } else if (char.IsLetter(c))
            {
                sb.Append(c);
                return 2;
            }
            else if (char.IsWhiteSpace(c))
            {
                return 4;
            }
            else
            {
                sb.Append(c);
                return 3;
            }
        }

        /// <summary>
        /// Creates a token according to the expressiong
        /// </summary>
        /// <param name="expr">The expression from which the token will be created</param>
        /// <param name="mustmatch">Defines if a terminal must exist for the expression. 
        /// If an expression doesn't match a terminal, the method will create a IDENT if set to false, otherwise an ScannerException is thrown</param>
        /// <returns>The created token</returns>
        private Token CreateToken(string expr, bool mustmatch = false)
        {
            try
            {
                // lookup the expression in the directory
                var term = Dictionaries.dicTerm[expr];

                // a terminal has been found, create the terminal object
                switch (term)
                {
                    case Terminals.RELOPR:
                        var rel = RELOPR.LE;
                        if (expr == "=") rel = RELOPR.EQ;
                        else if (expr == "/=") rel = RELOPR.NE;
                        else if (expr == "<") rel = RELOPR.LT;
                        else if (expr == ">") rel = RELOPR.GT;
                        else if (expr == ">=") rel = RELOPR.GE;
                        return new RelOpr(rel);

                    case Terminals.MULTOPR:
                        var mul = MULTOPR.TIMES;
                        if (expr == "divE") mul = MULTOPR.DIV_E;
                        if (expr == "modE") mul = MULTOPR.MOD_E;
                        return new MultOpr(mul);

                    case Terminals.ADDOPR:
                        var add = ADDOPR.MINUS;
                        if (expr == "+") add = ADDOPR.PLUS;
                        return new AddOpr(add);

                    case Terminals.BOOLOPR:
                        var bop = BOOLOPR.AND;
                        if (expr == "||") bop = BOOLOPR.OR;
                        else if (expr == "&?") bop = BOOLOPR.CAND;
                        else if (expr == "|?") bop = BOOLOPR.COR;
                        return new BoolOpr(bop);

                    case Terminals.TYPE:
                        var typ = TYPE.BOOL;
                        if (expr == "int32") typ = TYPE.INT32;
                        else if (expr == "int64") typ = TYPE.INT64;
                        else if (expr == "float") typ = TYPE.FLOAT;
                        return new DataTypes.Type(typ);

                    case Terminals.CHANGEMODE:
                        var mod = CHANGEMODE.CONST;
                        if (expr == "var") mod = CHANGEMODE.VAR;
                        return new ChangeMode(mod);

                    case Terminals.MECHMODE:
                        var mmod = MECHMODE.COPY;
                        if (expr == "ref") mmod = MECHMODE.REF;
                        return new MechMode(mmod);

                    case Terminals.FLOWMODE:
                        var fmod = FLOWMODE.IN;
                        if (expr == "out") fmod = FLOWMODE.OUT;
                        else if (expr == "inout") fmod = FLOWMODE.INOUT;
                        return new FlowMode(fmod);

                    case Terminals.LITERAL:
                        if (expr == "true" || expr == "false")
                            return new Literal(TYPE.BOOL, expr != "false");

                        if (expr == "POSITIVE_INFINITY")
                        {
                            return new Literal(TYPE.FLOAT, float.PositiveInfinity);
                        } else if (expr == "NEGATIVE_INFINITY")
                        {
                            return new Literal(TYPE.FLOAT, float.NegativeInfinity);
                        } else
                        {
                            return new Literal(TYPE.FLOAT, float.NaN);
                        }

                    default:
                        return new Token(term);
                }
            }
            catch (KeyNotFoundException)
            {
                //no terminal found for expr
                if (!mustmatch)
                {
                    return new Ident(expr);
                }
                else
                {
                    throw new ScannerException($"Expression {expr} is not recognized as a terminal");
                }
            }
        }
    }
}
