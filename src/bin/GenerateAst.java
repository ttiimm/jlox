import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

void main(String[] args) throws IOException {
    if (args.length != 1) {
        System.err.println("usage: generate_ast <output directory>");
        System.exit(64);
    }

    var outputDir = args[0];
    defineAst(outputDir, "Expr", Arrays.asList(
       "Binary   : Expr left, Token operator, Expr right",
       "Grouping : Expr expression",
       "Literal  : Object value",
       "Unary    : Token operator, Expr right"
    ));
}

private void defineAst(String outputDir, String baseName, List<String> types) throws IOException {
    var path = Paths.get(outputDir, STR."\{baseName}.java");
    try (var writer = new PrintWriter(path.toFile(), StandardCharsets.UTF_8)) {
        writer.println("package net.ttiimm.lox;");
        writer.println("");
        writer.println("import java.util.List;");
        writer.println("");
        writer.println(STR."abstract class \{baseName} {");

        for (var type : types) {
            var className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
        }

        writer.println("}");
        writer.println("");
    }

}

private void defineType(PrintWriter writer, String baseName, String className, String fieldList) {
    writer.println("");
    writer.println(STR."  static class \{className} extends \{baseName} {");
    // Fields
    var fields = fieldList.split(", ");
    for (var field : fields) {
        writer.println(STR."    final \{field};");
    }

    // Constructor
    writer.println("");
    writer.println(STR."    \{className} (\{fieldList}) {");

    // Field assignment
    for (var field : fields) {
        String name = field.split(" ")[1];
        writer.println(STR."      this.\{name} = \{name};");
    }
    // End constructor
    writer.println("    }");
    writer.println("  }");
    // End class
}