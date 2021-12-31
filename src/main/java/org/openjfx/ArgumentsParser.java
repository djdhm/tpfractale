package org.openjfx;
import org.apache.commons.cli.*;
import org.fractale.ColorPaletteEnum;
import org.fractale.FractaleConfig;
import org.fractale.Polynomial;
import org.view.PaletteChoice;

import java.util.Locale;

class ArgumentsParser {
    FractaleConfig config;
    /**
     * Constructeur de Argument Parser .
     *
     * @param config la configuration de Fractale
     */
    public ArgumentsParser(FractaleConfig config){
        this.config=config;
    }
    public static final String[] SETS = {"julia","mandelbort"};
    public static void printHelp() {
        System.out.println("Usage:");
        System.out.println("java Fractal -s Mandelbrot --minX <start_x> --maxX <end_x> --minY <start_y> --maxY <end_y> --max_iter <iterations>");
        System.out.println("java Fractal -s Julia --minX <start_x> --maxX <end_x> --minY <start_y> --maxY <end_y> --max_iter <iterations> --cX <constant_re> --cy <constant_im> ");
        System.exit(-1); // exit value -1 unsuccessful completion
    }
    /**
     * parse function.
     * Prends les arguments passés a la ligne de commande comme parametre
     * et les sauvegarder dans la configuration de fractale
     *
     * @param args  command line argumetns
     *
     */
    public void parse(String[] args){
        Options options = new Options();

        Option setType = new Option("s", "set", true, "Type de l'ensemble  : Julia/Mandelbrot.\nValeur par défaut : Julia");
        setType.setRequired(false);
        options.addOption(setType);


        Option maxIterOption = new Option("i","max_iter",true, "Nombre maximal d'iteration pour determiner la convergence \n Valeur par défaut :500 ");
        maxIterOption.setRequired(false);
        options.addOption(maxIterOption);

        Option cXOption = new Option("cx","cx",true, "Valeur reel de la constante \n Valeur par défaut : 0");
        cXOption.setRequired(false);
        options.addOption(cXOption);

        Option cYOption = new Option("cy","cy",true, "Valeur imaginaire de la constante \n Valeur par défaut : -0.75 ");
        cYOption.setRequired(false);
        options.addOption(cYOption);

        Option minXOption = new Option("x","minX",true, "X min \n Valeur par défaut : -1 ");
        minXOption.setRequired(false);
        options.addOption(minXOption);

        Option minYOption = new Option("y","minY",true, "Y min \n Valeur par défaut :-1 ");
        minYOption.setRequired(false);
        options.addOption(minYOption);

        Option maxXOption = new Option("X","maxX",true, " X max \n Valeur par défaut :1 ");
        maxXOption.setRequired(false);
        options.addOption(maxXOption);

        Option maxYOption = new Option("Y","maxY",true, "Y max \n Valeur par défaut :1 ");
        maxYOption.setRequired(false);
        options.addOption(maxYOption);

        Option widthOption = new Option("w","width",true, " Largeur de l'image \n Valeur par défaut : 1000 ");
        widthOption.setRequired(false);
        options.addOption(widthOption);

        Option heightOption = new Option("h","height",true, "Hauteur de l'image \n Valeur par défaut :1000 ");
        heightOption.setRequired(false);
        options.addOption(heightOption);

        Option filenameOption = new Option("o","output",true, "Fichier de sortie \n Valeur par défaut :fractale ");
        filenameOption.setRequired(false);
        options.addOption(filenameOption);

        Option polynomeOption = new Option("p","polynome",true,"Polynome utilisé pour le calcul \n Valeur par défaut : 1X^2");
        polynomeOption.setRequired(false);
        options.addOption(polynomeOption);

        Option threadsOption = new Option("t", "thread", true, "Nombre de Thread de calcul (GUI seulement)\n Valeur par défaut : 1");
        threadsOption.setRequired(false);
        options.addOption(threadsOption);

        Option help = new Option("m", "manual", false, "Afficher le manuel d'utilisation ");
        help.setRequired(false);
        options.addOption(help);

        Option graphic = new Option("g", "graphic", false, "Lancer en mode graphique \n Valeur par défaut : false ");
        graphic.setRequired(false);
        options.addOption(graphic);

        Option paletteOption = new Option("z", "palette", true, "Choisir la palette de couleur (0-3)\n Valeur par défaut : 2 ");
        graphic.setRequired(false);
        options.addOption(paletteOption);

        CommandLineParser parser = new BasicParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;
        try
        {
            cmd = parser.parse(options, args);
        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
            formatter.printHelp("arguments :", options);
            System.exit(1);
        }
        if(cmd.hasOption("m")){
            printHelp();
        }

        String setValue = cmd.getOptionValue("set","julia").toLowerCase(Locale.ROOT);
        String threadsValue = cmd.getOptionValue("thread","1");
        String xMinValue = cmd.getOptionValue("xmin","-1");
        String xMaxValue = cmd.getOptionValue("xmax","1");
        String yMinValue = cmd.getOptionValue("ymin","-1");
        String yMaxValue = cmd.getOptionValue("ymax","1");
        String widthValue = cmd.getOptionValue("width","640");
        String heightValue = cmd.getOptionValue("height","480");
        String maxIterationValue = cmd.getOptionValue("max_iter","400");
        String constantReValue = cmd.getOptionValue("cx","0");
        String constantImValue = cmd.getOptionValue("cy","-0.75");
        String polynomeExp = cmd.getOptionValue("polynome","1X^2");
        String graphicMode = cmd.getOptionValue("graphic","true");
        String paletteValue = cmd.getOptionValue("palette","1");
        if ( ! setValue.equals("julia") && ! setValue.equals("mandelbrot") )
        {
            System.out.println("-s/--set doit etre   Julia ou Mandelbrot");
            System.exit(1);
        }
        config.setChoice = setValue;

        config.nbThreads = Integer.parseInt(threadsValue);
        try
        {
            config.minX = Double.parseDouble(xMinValue);
            config.maxX = Double.parseDouble(xMaxValue);
            config.minY = Double.parseDouble(yMinValue);
            config.maxY = Double.parseDouble(yMaxValue);
        }
        catch (NumberFormatException e)
        {
            System.out.println(e.getMessage());
            System.out.println("-x,y- doivent etre des nombres valides.");
            System.exit(1);
        }
        try
        {
            config.width = Integer.parseInt(widthValue);
            config.height = Integer.parseInt(heightValue);
            if(config.height<2|| config.width <2) {
                System.out.println("--width,height doit etre > 2");
                System.exit(1);
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println(e.getMessage());
            System.out.println("--width,height doivent etre des nombres entiers .");
            System.exit(1);
        }

        try {
            config.maxIter = Integer.parseInt(maxIterationValue);
            if(config.maxIter<50) {
                System.out.println("--max_iter doit etre > 50");
            }
        }catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("--max_iter  doit etre un nombre entier .");
            System.exit(1);
        }
        try {
            config.cx = Double.parseDouble(constantReValue);
            config.cy = Double.parseDouble(constantImValue);

        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            System.out.println("--cx,cy  doivent etre des doubles valides.");
            System.exit(1);
        }

        try {
            Polynomial p=new Polynomial(polynomeExp);
            config.polynome=polynomeExp;

        }catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println(" expression de polynome malformée ");
            System.exit(1);
        }

        try{
            int choice = Integer.parseInt(paletteValue);
            config.paletteChoice = ColorPaletteEnum.values()[choice];

        }catch (Exception e){
            System.out.println(e);
            System.out.println("Erreur de choix de palette de couleur ");
            System.exit(1);
        }

        config.gui = cmd.hasOption("graphic");
        config.filename = cmd.getOptionValue("output","fractale");
    }
}
