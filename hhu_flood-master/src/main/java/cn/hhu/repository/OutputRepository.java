package cn.hhu.repository;

import cn.hhu.Bean.Output;

import java.util.List;

/**
 * @author tlj
 */
public interface OutputRepository {


    public Output getOutputByIntervalAndElement(int id,int timeInterval, int element);

    public List<Output> getOutputByInterval();
    public int getMaxTimeInterval(int id);
    public List<Output> getOutputsByTimeIntervalAndElement(int id , int timeInterval , int element1 , int element2 );
    public List<Output> getOutputsByTimeIntervalAndElementArray(int id , int timeInterval , int[] elements );
    public List<Output> getOutputsByTimeIntervalAndElementArray(int id , int timeInterval , List<Integer> elements );
}
