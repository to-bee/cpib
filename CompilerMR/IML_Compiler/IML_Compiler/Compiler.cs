
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLScanner.Logic;
using IML_Compiler.IMLParser;
using IML_Compiler.IMLParser.DataTypes;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;
using IML_Compiler.IMLParser.DataTypes.Concrete;
using IML_Compiler.IMLParser.Logic;
using Parser = IML_Compiler.IMLParser.Logic.Parser;


namespace IML_Compiler
{
    /// <summary>
    /// Woah, this thing became more messy than it should be...
    /// 
    /// (c) by Michael Roth & Elias Henz, CPIB, HS 2016
    /// </summary>
    class Compiler
    {
        static void Main(string[] args)
        {
            var tmp = Environment.GetEnvironmentVariable("Path")?.Split(';') ?? new string[] { };
            var javaexe = "";
            foreach (var p in tmp)
            {
                if (p.ToLower().Contains("java"))
                {
                    //javaexe = p.Substring(1, p.Length-2);
                    javaexe = p;
                    break;
                }
            }
            javaexe += "\\java.exe";

            var inParam = args.Length > 0 ? args[0] : @"IML_Programs\OverloadDemo.iml";
            var content = File.ReadAllText(inParam);
            var inFile = new FileInfo(inParam).Name;
            var progParams = "";
            if (args.Length > 1)
            {
                progParams = string.Join(" ", args.SubSequence(1));
            }

            var origWriter = Console.Out;
#if (ToFile)
            System.IO.File.WriteAllText("output.txt", string.Empty);
            var writer = new StreamWriter("output.txt", false, Encoding.UTF8);
            Console.SetOut(writer);
#endif

            Console.WriteLine("=============Start of Input=============");
            content.Replace("\r", "").Split('\n').ToList().ForEach(Console.WriteLine);
            Console.WriteLine("============= End of Input  =============");
            Console.WriteLine();
            Console.WriteLine();

            Parser parser = null;

            try
            {
                var startTime = DateTime.Now;

                // 1. Scanner
                Console.WriteLine("=============Starting Scanner=============");
                var scanner = new Scanner();
                var tokenList = scanner.Scan(content);
                Console.WriteLine(tokenList.ToString());
                Console.WriteLine("============= Scanner Ended  =============");
                var scannerEnded = DateTime.Now;
                Console.WriteLine("Scanner time needed: " + scannerEnded.Subtract(startTime).TotalSeconds + "s");
                Console.WriteLine();
                Console.WriteLine();


                // 2. Parser
                Console.WriteLine("=============Starting Parser=============");
                parser = new Parser(tokenList);
                IProgram parseTree = parser.Parse();
                Console.WriteLine(parseTree.ToStr());
                Console.WriteLine("============= Parser Ended  =============");
                var parserEnded = DateTime.Now;
                Console.WriteLine("Parser time needed: " + parserEnded.Subtract(scannerEnded).TotalSeconds + "s");
                Console.WriteLine($"Max depth of parse tree: {parseTree.Depth()}");
                Console.WriteLine();
                Console.WriteLine();


                // 3. Converter
                Console.WriteLine("=============Starting Converter=============");
                var absTree = parseTree.ToAbsSyn();
                Console.WriteLine(absTree.ToStr());
                Console.WriteLine("============= Converter Ended  =============");
                var converterEnded = DateTime.Now;
                Console.WriteLine("Converter time needed: " + converterEnded.Subtract(parserEnded).TotalSeconds + "s");
                Console.WriteLine($"Max depth of abstract tree: {absTree.Depth()}");
                Console.WriteLine();
                Console.WriteLine();


                // 4. Checker
                // the following 3 lines could possibly be safely removed
                // since there were some side-effects (yeah!) I will leave it here just to be safe
                Context init = GlobalContext.Context;
                init = GlobalContext.CurrentContext;
                init = null;

                Console.WriteLine("=============Starting Checker=============");
                absTree.Check();
                Console.WriteLine("============= Checker Ended  =============");
                var checkerEnded = DateTime.Now;
                Console.WriteLine("Checker time needed: " + checkerEnded.Subtract(converterEnded).TotalSeconds + "s");
                Console.WriteLine();
                Console.WriteLine();

                // Optional checker: Flow checker, Aliasing checker



                // 5. Code generator
                Console.WriteLine("=============Starting Code generator=============");
                absTree.Code(0);
                Console.WriteLine("Function addresses");
                foreach (var tupel in SymbolTable.RoutineSymbols)
                {
                    Console.WriteLine($"{tupel.Key}: {tupel.Value}");
                }
                Console.WriteLine();
                Console.WriteLine(CodeGenerationContext.Export());
                Console.WriteLine("============= Code generator Ended  =============");
                var codegenEnded = DateTime.Now;
                Console.WriteLine("Code generator time needed: " + codegenEnded.Subtract(checkerEnded).TotalSeconds +
                                  "s");
                Console.WriteLine();
                Console.WriteLine();


                Console.WriteLine("============= Compiler Ended  =============");
                Console.WriteLine($"Total time needed: {DateTime.Now.Subtract(startTime).TotalSeconds} s");

                var outFileName = absTree.Ident + ".cpid";
                System.IO.File.WriteAllText(outFileName, CodeGenerationContext.Export());

#if ToFile
                Console.SetOut(origWriter);
#endif

                //starting vm

                Console.WriteLine();
                Console.WriteLine();
                Console.WriteLine("============= Running Program  =============");
                var psi = new ProcessStartInfo()
                {
                    WorkingDirectory = Environment.CurrentDirectory + @"\..\..\..\..\IML_VirtualMachine\virtualmachineFS2015\bin",
                    Arguments = $"ch.fhnw.lederer.virtualmachineFS2015.RunVirtualMachine \"{Environment.CurrentDirectory + @"\" + outFileName}\" {progParams}",
                    FileName = javaexe,
                    UseShellExecute = false
                };
                var p = new Process();
                p.StartInfo = psi;
                Console.WriteLine($"Executing: {inFile} {progParams}");
                Console.WriteLine();
                p.Start();
                p.WaitForExit();

                Console.WriteLine();
            }
            catch (ScannerException se)
            {
                Console.WriteLine("Error while scanning: " + se.Message);
            }
            catch (ParserException pe)
            {
                Console.WriteLine($"Error while parsing: {pe.Message} at position {parser.Position}: {parser.token}");
            }
            catch (CheckerException ce)
            {
                Console.WriteLine("Error while checking: " + ce.Message);
            }

            Console.Out.Flush();
            //Console.ReadKey(true);
        }
    }
}
