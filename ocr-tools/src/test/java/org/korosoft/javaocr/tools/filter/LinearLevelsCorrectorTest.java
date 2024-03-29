package org.korosoft.javaocr.tools.filter;

import junit.framework.Assert;
import org.junit.Test;
import org.korosoft.javaocr.core.ImgUtil;
import org.korosoft.javaocr.core.MutableImage;

/**
 * Levels corrector test
 *
 * @author Dmitry Korotkov
 * @since 1.0
 */
public class LinearLevelsCorrectorTest {
    @Test
    public void testLevelsCorrector() throws Exception {
        final LinearLevelsCorrector levelsCorrector = new LinearLevelsCorrector();
        final MutableImage image = ImgUtil.readMutableImageFromSupportedStream(getClass().getResourceAsStream("/test-rotated-l.png"));
        levelsCorrector.doFilter(image);

        int p = image.firstPixel;
        boolean haveBlack = false;
        boolean haveWhite = false;
        for (int y = 0; y < image.height; y++) {
            for (int x = 0; x < image.width; x++) {
                if (image.pixels[p] == 0) {
                    haveBlack = true;
                } else if (image.pixels[p] == -1) {
                    haveWhite = true;
                }
                p++;
            }
            p += image.lineSpan;
        }
        Assert.assertTrue("Black pixels must present", haveBlack);
        Assert.assertTrue("White pixels must present", haveWhite);
    }
}
