public class minPassFilter {

    private static final float A_DEFAULT = 0.333f;
    private static final float A_STEADY       = 0.001f;
    private static final float A_START_MOVING = 0.6f;
    private static final float A_MOVING       = 0.9f;

    private minPassFilter() { }

    public static float[] filter(float min, float max, float[] present, float[] former) {
        if (present==null || former==null) 
            throw new NullPointerException("Input and former arrays can't be non-NULL");
        if (present.length!=former.length) 
            throw new IllegalArgumentException("Input and former arrays must have the same length");

        float A = computeA(min,max,present,former);
        
        for ( int i=0; i<present.length; i++ ) {
            former[i] = former[i] + A * (present[i] - former[i]);
        }
        return former;
    }
    
    private static final float computeA(float min, float max, float[] present, float[] former) {
        if(former.length != 3 || present.length != 3) return A_DEFAULT;
        
        float x1 = present[0],
              y1 = present[1],
              z1 = present[2];

        float x2 = former[0],
              y2 = former[1],
              z2 = former[2];
        
        float distance = (float)(Math.sqrt( Math.pow((double)(x2 - x1), 2d) +
                                            Math.pow((double)(y2 - y1), 2d) +
                                            Math.pow((double)(z2 - z1), 2d))
        );
        
        if(distance < min) {
            return A_STEADY;
        } else if(distance >= min || distance < max) {
            return A_START_MOVING;
        } 
        return A_MOVING;
    }
}