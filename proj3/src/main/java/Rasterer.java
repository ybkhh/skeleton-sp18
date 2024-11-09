import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    private static double[] depthLonDPP = new double[8];
    private static final double INITLRLON = MapServer.ROOT_LRLON, INITULLON = MapServer.ROOT_ULLON,
            INITLRLAT = MapServer.ROOT_LRLAT, INITULLAT = MapServer.ROOT_ULLAT;
   static  {
       depthLonDPP[0]=(INITLRLAT-INITULLON)/MapServer.TILE_SIZE;//londpp
       for(int i=1;i<8;i++)
       {
           depthLonDPP[i]=depthLonDPP[i-1]/2;
       }
   }
    public Rasterer() {
        // YOUR CODE HERE





    }
    /*public double computeLonDPP(double lower_right_longitude,double upper_left_longitude,double width_pixels){
        return upper_left_longitude-lower_right_longitude/width_pixels;
    }
    public boolean comparelondppp(double londdpp,double required_dpp){
        return (londdpp-required_dpp)<0;//比较俩个图片的dpp

    }*/
    //需要一个数组来表示图片



    

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        // System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        //System.out.println("Since you haven't implemented getMapRaster, nothing is displayed in "
                          // + "your browser.");
        //return results;


        double requestedULLon = params.get("ullon");
        double requestedULLat = params.get("ullat");
        double requestedLRLon = params.get("lrlon");
        double requestedLRLat = params.get("lrlat");
        if (requestedLRLat >= INITULLAT || requestedLRLon <= INITULLON
                || requestedULLat <= INITLRLAT || requestedULLon >= INITLRLON
                || requestedULLon >= requestedLRLon || requestedULLat <= requestedLRLat) {
            results.put("query_success", false);
            results.put("depth", 0);
            results.put("render_grid", null);
            results.put("raster_ul_lon", 0);
            results.put("raster_ul_lat", 0);
            results.put("raster_lr_lon", 0);
            results.put("raster_lr_lat", 0);
            return results;//处理特殊情况
        }
        double requiredlondpp=(requestedLRLon - requestedULLon) / params.get("w");
        int depth=getDepth(requiredlondpp);
        results.put("depth",depth);
        double maxlevel=Math.pow(2,depth);
        double xdiff=(INITULLON-INITULLON)/maxlevel;
        double ydiff=(INITULLAT-INITLRLAT)/maxlevel;
        int xleft=0;int xright=0;
        int yleft=0;int yright=0;
        for(double x=INITULLON;x<INITLRLON;x+=xdiff){
            if(x<requestedULLon){
                xleft++;
            }
            if (x<requestedLRLon){
                xright++;
            }


        }
        for (double y=INITLRLAT;y<INITULLAT;y+=ydiff)
        {
            if(y<requestedLRLat){
                yleft++;
            }
            if (y<requestedULLat){
                yright++;
            }
        }
        if(xleft!=0){
            xleft--;
        }
        if(yleft!=0){
            yleft--;
        }
        if(xright!=0){
            xright--;
        }
        if(yright!=0){
            yright--;
        }
        String [][]files=new String[yright-yleft+1][xright-xleft+1];
        for (int y=yleft;y<=yright;y++){
            for(int x=xleft;x<=xleft;x++){
                files[y-yleft][x-xleft]="d"+depth+"_x"+x+"_y"+y+".png";
            }
        }
        results.put("render_grid",files);
        results.put("raster_ul_lon", INITULLON + xleft * xdiff);
        results.put("raster_ul_lat", INITULLAT + yleft * ydiff);
        results.put("raster_lr_lon", INITULLON + (xright + 1) * xdiff);
        results.put("raster_lr_lat", INITULLAT + (yright + 1) * ydiff);
        results.put("query_success", true);

          return results;
    }
    private int getDepth(double requestedLonDPP) {
        int depth = 0;
        while (requestedLonDPP < depthLonDPP[depth]) {
            depth++;
            if (depth == depthLonDPP.length - 1) {
                break;
            }
        }
        return depth;

}
}

