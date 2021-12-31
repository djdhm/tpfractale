package org.fractale;

/**
 * enum ColorPaletteEnum
 * enumeration permettant de representer les palettes de couleur disponible
 *
 */
public enum ColorPaletteEnum {
    PALETTE_1(ColorPalette.SIMPLE_PALETTE), PALETTE_2(ColorPalette.PALETTE_2),PALETTE_3(ColorPalette.PALETTE_3), PALETTE_4(ColorPalette.PALETTE_4);

    public ColorPalette function;
    /**
     * Constructeur de l'enum.
     * @param function Fonction de coloriage de type ColorPalette
     */
    ColorPaletteEnum(ColorPalette function){
        this.function = function;
    }
}
