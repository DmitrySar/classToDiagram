class AnalizerSrc {

    private String[] textClasses;
    private String[] names;

    public AnalizerSrc(String[] textClasses, String[] names) {
        this.textClasses = textClasses;
        this.names = names;
    }

    public String[] getResult () {
        String[] filter = new String[textClasses.length + 1];
        int i = 0;
        for (String s: textClasses) {
            s = replaceReservedSymbols(s);
            s = replaceMethods(s);
            s = replaceDoubleEnter(s);
            filter[i++] = s;
        }
        filter[i] = "";
        filter[i] = getCoupling(names, filter);
        return filter;
    }

    public String getCoupling(String[] names, String[] textClassesFiltered) {
        String result = "";
        for (String s: names) {
            s = s.replaceAll("\\..*", "");
            for (String text: textClassesFiltered) {
                 if (text.contains(s) && !text.contains("class " + s) && !text.contains("interface " + s)) {
                    result += s + " <-- " + text.split("\n")[0].split("\\s")[1] + "\n";
                }
            }
        }
        return result;
    }

    private String replaceReservedSymbols(String text) {
        String s = text;
        s = s.replaceAll("public ", "+ ");
        s = s.replaceAll("private ", "- ");
        s = s.replaceAll("protected ", "# ");
        while(s.contains("import ")) {
            s = s.replaceAll("import .*\n", "");
        }        
        s = s.replaceAll(";", "");
        s = s.replaceAll("\\=.*", "");
        return s;
    }

    private String replaceDoubleEnter(String text) {
        String s = text;
        while (s.contains("\n\n")) {
            s = s.replaceAll("(\n{2})+", "\n");
        }
        return s;
    }

    private String replaceMethods(String text) {
        String[] lines = text.split("\n");
        String result = "";
        for (String s: lines) {
            if (s.matches("(\\s)*\\#.*") || s.matches("(\\s)*\\+.*") || s.matches("(\\s)*\\-.*") || s.contains("class ") || s.contains("interface ")) {
                s = s.replaceAll("\\) \\{", ")");
                result += s + "\n";
            }
        }
        result = String.format("%s\n%s\n", result, lines[lines.length - 1]);
        return result;
    }

}
