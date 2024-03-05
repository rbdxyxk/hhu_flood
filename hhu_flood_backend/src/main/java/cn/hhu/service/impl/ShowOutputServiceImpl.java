package cn.hhu.service.impl;

import cn.hhu.Bean.Output;
import cn.hhu.repository.impl.OutputRepositoryImpl;
import cn.hhu.service.ShowOutputService;
import cn.hhu.utils.ElementCompute;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
/**
 * @author tlj
 */
public class ShowOutputServiceImpl implements ShowOutputService {
    private OutputRepositoryImpl ori = new OutputRepositoryImpl();
    @Override
    public int getMaxInterval(int id) {
        return ori.getMaxTimeInterval(id);

    }

    @Override
    public List<Output> getOutputsByTimeIntervalAndElement(int timeInterval, int element1, int element2) {
        return ori.getOutputsByTimeIntervalAndElement(0,timeInterval,element1,element2);
    }
    @Override
    public Output getOutputByLongitudeAndLatitude(int timeInterval , Double latitudeL , Double longitudeL , Double latitudeH, Double longitudeH, Double x , Double y){
        System.out.println(timeInterval+":"+latitudeL+":"+x+":"+y);
        Double longitudeGap = longitudeH - longitudeL;
        Double latitudeGap = latitudeH - latitudeL;
        x = x -longitudeL;
        y = y - latitudeL;
        Double elementXL =longitudeGap/40;
        Double elementYL = latitudeGap/40;
        int numX = (int) (x/elementXL);
        int numY = (int) (y/elementYL);
        int element = (numX+1)*(numY+1);
        System.out.println(numX+":"+numY+":"+element);
        Output output = ori.getOutputByIntervalAndElement(0, timeInterval, element);
        output.setTimeInterval(null);
        output.setID(null);
        return output;
    }

    @Override
    public Output getOutputByLongitudeAndLatitude(int timeInterval ,String positons, Double x , Double y){

        int[] elements=ElementCompute.getElements(timeInterval,positons,x,y,100,18);
        if(elements==null){
            return null;
        }
        int element = elements[1]*100+elements[0];
        Output output = ori.getOutputByIntervalAndElement(0, timeInterval, element);
        output.setTimeInterval(null);
        output.setID(null);
        
        return output;
    }
    @Override
    public List<Output> getOutputs(int id ,int timeInterval , Double latitudeL , Double longitudeL , Double latitudeH, Double longitudeH, Double startX , Double startY , Double endX , Double endY){
            System.out.println("start");
            System.out.println(timeInterval+":"+latitudeL+":"+longitudeL+":"+latitudeH+":"+longitudeH+":"+startX +":"+startY+":"+endX+":"+endY );
            int[] elementsStart = ElementCompute.getElements(timeInterval,latitudeL,longitudeL , latitudeH,  longitudeH, startX , startY );
            int[] elementsEnd = ElementCompute.getElements(timeInterval,latitudeL,longitudeL , latitudeH,  longitudeH, endX , endY );
            int num = (Math.abs(elementsStart[0]-elementsEnd[0])+1)*(Math.abs(elementsStart[1]-elementsEnd[1])+1);
            int[] elements = new int[num];
            int bigX,smallX,bigY,smallY;
            if(elementsStart[0]>elementsEnd[0]){
                bigX=elementsStart[0];
                smallX= elementsEnd[0];
            }else{
                smallX=elementsStart[0];
                bigX= elementsEnd[0];
            }
            if(elementsStart[1]>elementsEnd[1]){
                bigY=elementsStart[1];
                smallY= elementsEnd[1];
            }else{
                smallY=elementsStart[1];
                bigY= elementsEnd[1];
            }
            int k = 0 ;
            for (int i = smallX; i <= bigX; i++) {
                for (int j = smallY; j <= bigY ; j++) {
                    //System.out.println(i+":"+j);
                    elements[k++] = (i-1)*40+j;
                }
            }
            System.out.println(Arrays.toString(elements));
            return ori.getOutputsByTimeIntervalAndElementArray(id ,timeInterval ,elements);
    }
    @Override
    public List<Output> getOutputs(int id ,int timeInterval , Double latitudeL , Double longitudeL , Double latitudeH, Double longitudeH, Double startX , Double startY , Double endX , Double endY ,int xSize,int ySize){
        List<Integer> list = ElementCompute.computeElements(latitudeL, longitudeL, latitudeH, longitudeH, startX, startY, endX, endY, xSize, ySize);
        //Integer[] objects =  list.toArray(new Integer[list.size()]);
        return ori.getOutputsByTimeIntervalAndElementArray(id , timeInterval , list);
    }
    public List<Output> getOutputs(int id ,int timeInterval ,String positions, Double startX , Double startY , Double endX , Double endY ,int xSize,int ySize){
        List<Integer> list = ElementCompute.computeElements(positions, startX, startY, endX, endY, xSize, ySize);
        return ori.getOutputsByTimeIntervalAndElementArray(id , timeInterval , list);
    }
}
