package cn.hhu.service;

import cn.hhu.Bean.Output;

import java.util.List;

/**
 * @author tlj
 */
public interface ShowOutputService {
    public Output getOutputByLongitudeAndLatitude(int timeInterval ,String positons, Double x , Double y);
    public int getMaxInterval(int id) ;
    public List<Output> getOutputsByTimeIntervalAndElement( int timeInterval , int element1 , int element2 );
    public Output getOutputByLongitudeAndLatitude(int timeInterval , Double latitudeL , Double longitudeL ,Double latitudeH, Double longitudeH,Double x , Double y);
    public List<Output> getOutputs(int id ,int timeInterval , Double latitudeL , Double longitudeL , Double latitudeH, Double longitudeH, Double startX , Double startY , Double endX , Double endY);
    public List<Output> getOutputs(int id ,int timeInterval , Double latitudeL , Double longitudeL , Double latitudeH, Double longitudeH, Double startX , Double startY , Double endX , Double endY ,int xSize,int ySize);
    public List<Output> getOutputs(int id ,int timeInterval ,String positions, Double startX , Double startY , Double endX , Double endY ,int xSize,int ySize);
}
