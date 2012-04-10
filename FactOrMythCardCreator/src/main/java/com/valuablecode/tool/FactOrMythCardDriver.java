package com.valuablecode.tool;

import static java.text.MessageFormat.format;

public class FactOrMythCardDriver {

    public static void main(String[] args) {
        FactOrMythConfiguration configuration = getConfiguration(args);

        FactOrMythCardProvider cardProvider = new FileBasedCardProvider(configuration);
        FactOrMythLayoutService layoutService = new PdfLayoutService(configuration);

        FactOrMythCardCreator cardCreator = new FactOrMythCardCreator(cardProvider, layoutService);

        cardCreator.createCards();

        showResult(configuration);
    }

    private static FactOrMythConfiguration getConfiguration(String[] args) {
        return new PropertyFileConfiguration(getConfigurationFileName(args));
    }

    private static String getConfigurationFileName(String[] args) {
        if (1 != args.length) {
            showUsage();
            System.exit(1);
        }

        return args[0];
    }

    private static void showUsage() {
        System.out.println("Error: please specify a single configuration file name");
    }

    private static void showResult(FactOrMythConfiguration configuration) {
        System.out.println(format("Created: {0}", configuration.getOutputFileName()));
    }

}
