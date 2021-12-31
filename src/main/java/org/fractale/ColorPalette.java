package org.fractale;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.awt.Color;
/**
 * Interface fonctionnelle qui permet de lier le nombre d'iterations a une couleur
 *
 */
public interface ColorPalette extends BiFunction<Integer,Integer, Color> {

    final static ColorPalette SIMPLE_PALETTE =
            ((indice,max_iter) -> {
                return Color.getHSBColor(90 + 180 * ((float)indice/257),
                        (float)(indice)/257,
                        (float) (256 - indice)/257);
            });

    final static ColorPalette PALETTE_2 =
            ((indice,max_iter) -> {
                return Color.getHSBColor((float) 285 - 270 * ((float)indice/max_iter),
                        (float)(1d),
                        (float) 1d);
            });

    final static ColorPalette PALETTE_3 =
            ((indice,max_iter) -> {
                return Color.getHSBColor(150 + 30 * ((float)indice/257),
                        (float) (256 - indice)/257,
                        (float)(indice)/257);
            });
    final static ColorPalette PALETTE_4 =
            ((indice,max_iter) -> {
                final float colorHueValue = (float) indice * 12.0f / (float) max_iter;
                return Color.getHSBColor(colorHueValue, 0.9f, 0.5f);
            });
}
