package ch.fhnw.cpib.iml.parser.concsyntaxtree;


public class ConcSyn implements IConcSyn {
  private IProgram program;

  @Override
  public void setProgram(final IProgram program) {
    this.program = program;
  }

  public IProgram getProgram() {
    return this.program;
  }

  @Override
  public void printTree() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Start of ConcreteSyntax Tree\n");
    this.program.appendAsString(sb, "");
    System.out.print(sb.toString());
  }
}
