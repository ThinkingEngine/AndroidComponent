package s.cala.androidcompent.network.serviceinstance;

import io.reactivex.Observable;
import okhttp3.Response;
import s.cala.androidcompent.network.RetrofitClient;

/**
 * package name:s.cala.androidcompent.network.serviceinstance
 * create:cala
 * date:2019/1/16
 * commits:服务实例测试
 */
public class TestServiceInstance {

    private TestServiceInstance testServiceInstance = null;

    private TestServiceInstance(){

    }

    public TestServiceInstance getInstance(){
        if(testServiceInstance == null){
            synchronized (TestServiceInstance.class){
                if(testServiceInstance == null){
                    testServiceInstance = new TestServiceInstance();
                }
            }
        }

        return testServiceInstance;
    }


    public Observable<Response> testAPI(){
        return RetrofitClient.getInstan().getTest().testAPI();
    }
}
